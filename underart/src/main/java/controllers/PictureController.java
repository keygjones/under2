/**
 * Copyright (C) 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package controllers;

import models.Article;
import models.ArticleDto;
import models.Picture;
import models.PictureGroup;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.params.PathParam;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import static org.imgscalr.Scalr.*;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.ArticleDao;
import dao.PictureDao;
import etc.LoggedInUser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

@Singleton
public class PictureController {

	@Inject
	PictureDao pictureDao;


	@FilterWith(SecureFilter.class)
	public Result pictureNew() {
		return Results.html();
	}
	
	public Result pictureEdit(@PathParam("id") Long id) {
        Picture picture = null;
        if (id != null) {
            picture = pictureDao.getPicture(id);
        }
        return Results.html()
        		.render("picture", picture)
        		.render("groups",pictureDao.getAllGroups())
        		.render("selected",picture.getPicturegroupList());
    }
	
	
	public Result groups() {
		List<PictureGroup> allGroups = pictureDao.getAllGroups();
        return Results.html().render("allGroups", allGroups);
    }
	
	public Result showAll() {
		List<Picture> allPictures = pictureDao.getAllPictures();
        Map<String, Object> map = Maps.newHashMap();
        map.put("allPictures", allPictures);
        return Results.html().template("views/galleries/groupView.ftl.html").render("allPictures", allPictures);
    }
	
	public Result pictureGroup(@PathParam("id") Long id) {
		List<Picture> allPictures = pictureDao.getAllPicturesInGroup(id);
        return Results.html().template("views/galleries/groupView.ftl.html").render("allPictures", allPictures);
    }
	
	public Result deletePicture(@PathParam("id") Long id) {
		pictureDao.deletePicture(id);
        return Results.redirect("/picture/all");
    }
	
	
	public Result editSubmit(@LoggedInUser String username,Context context) {
		
		List <String> groups =context.getParameterValues("group");
		String pictureId = context.getParameter("pictureId");
		String name = context.getParameter("name");
		String about = context.getParameter("about"); 
		Long pId = Long.parseLong(pictureId,10);
		pictureDao.editPicture(username, groups,pId,name,about);
		
		return Results.redirect("/picture/all");
    }

	@FilterWith(SecureFilter.class)
	public Result uploadFinish(@LoggedInUser String username,Context context) throws Exception {
		String fileLocation ="";
		// Make sure the context really is a multipart context...
		if (context.isMultipart()) {
			Picture pic = new Picture();
			// This is the iterator we can use to iterate over the
			// contents of the request.
			FileItemIterator fileItemIterator = context.getFileItemIterator();

			while (fileItemIterator.hasNext()) {

				FileItemStream item = fileItemIterator.next();

				String name = item.getFieldName();
				InputStream stream = item.openStream();

				String contentType = item.getContentType();

				if (item.isFormField()) {
					String value = Streams.asString(stream);
					switch (name) {
					case "name":
						pic.setName(value);
						break;
					case "about":
						pic.setAbout(value);
						break;
					}

				} else {
					OutputStream outputStream = null;

					try {
						fileLocation = "public/pictures/"+item.getName();
						outputStream = new FileOutputStream(new File(
								fileLocation));

						int read = 0;
						byte[] bytes = new byte[1024];

						while ((read = stream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
						pic.setFile(item.getName());
						

					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (stream != null) {
							try {
								stream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (outputStream != null) {
							try {
								// outputStream.flush();
								outputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
					}
				}
			}
			pictureDao.postArticlePicture(username, pic);
		}

		reziseImage(fileLocation);
		// We always return ok. You don't want to do that in production ;)
		return Results.redirect("/picture/all");

	}

	private void reziseImage(String fileLocation) {
		BufferedImage img = null;
		String extension = FilenameUtils.getExtension(fileLocation);
		String fileName = FilenameUtils.getName(fileLocation);
		File file = new File(fileLocation);
		try {
		    img = ImageIO.read(file);
		    BufferedImage webPic = resize(img, 1024);
		    BufferedImage thumbnail =
		    		  resize(img, Method.SPEED, Mode.FIT_TO_WIDTH,
		    		               150, 100, OP_ANTIALIAS);
		    
		    ImageIO.write(webPic, extension,new File("public/pictures/"+fileName));
		    ImageIO.write(thumbnail, extension,new File("public/pictures/thumbs/"+fileName));
		} catch (IOException e) {
		}
		
	}
}
