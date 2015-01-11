/**
 * Copyright (C) 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Article;
import models.ArticleDto;
import models.ArticlesDto;
import models.PictureGroup;
import models.Picture;
import models.User;
import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class PictureDao {

	@Inject
	Provider<EntityManager> entitiyManagerProvider;

	@Transactional
	public boolean postArticlePicture(String username, Picture pic) {

		EntityManager entityManager = entitiyManagerProvider.get();

		Query query = entityManager
				.createQuery("SELECT x FROM User x WHERE username = :usernameParam");
		User user = (User) query.setParameter("usernameParam", username)
				.getSingleResult();

		if (user == null) {
			return false;
		}

		entityManager.persist(pic);

		return true;

	}

	@UnitOfWork
	public List<Picture> getAllPictures() {
		EntityManager entityManager = entitiyManagerProvider.get();
		Query q = entityManager
				.createQuery("SELECT x FROM Picture x ORDER BY x.created DESC");
		List<Picture> pictures = (List<Picture>) q.getResultList();
		return pictures;
	}
	
	
	@UnitOfWork
	public Picture getPicture(Long id) {
		EntityManager entityManager = entitiyManagerProvider.get();
		return getPicture(id, entityManager);

	}

	private Picture getPicture(Long id, EntityManager entityManager) {
		Query q = entityManager
				.createQuery("SELECT x FROM Picture x WHERE x.id = :idParam");
		Picture picture = (Picture) q.setParameter("idParam", id)
				.getSingleResult();
		return picture;
	}
	
	@UnitOfWork
	public List<PictureGroup> getAllGroups() {
		EntityManager entityManager = entitiyManagerProvider.get();
		TypedQuery<PictureGroup> query= entityManager.createQuery("SELECT x FROM PictureGroup x", PictureGroup.class);
	    List<PictureGroup> groups = query.getResultList();        
		return groups;
	}
	
	@UnitOfWork
	public PictureGroup getGroup(Long id) {
		EntityManager entityManager = entitiyManagerProvider.get();
		return getGroup(id, entityManager);

	}

	private PictureGroup getGroup(Long id, EntityManager entityManager) {
		Query q = entityManager
				.createQuery("SELECT x FROM PictureGroup x WHERE x.id = :idParam");
		PictureGroup group = (PictureGroup) q.setParameter("idParam", id)
				.getSingleResult();
		return group;
	}

	@Transactional
	public void editPicture(String username, List<String> groupIds, Long pId, String name, String about) {
		EntityManager entityManager = entitiyManagerProvider.get();
		
		Picture picture = getPicture(pId,entityManager);
		picture.setName(name);
		picture.setAbout(about);
		picture.getPicturegroupList().clear();
		for(String id: groupIds){
			PictureGroup group = getGroup(Long.parseLong(id,10),entityManager);
			picture.getPicturegroupList().add(group);
		}
		
		entityManager.persist(picture);
	}

	@Transactional
	public List<Picture> getAllPicturesInGroup(Long groupId) {
		EntityManager entityManager = entitiyManagerProvider.get();
		PictureGroup group = getGroup(groupId,entityManager);
		List<Picture> pics = group.getPictureList();
		return pics;
	}

	@Transactional
	public void deletePicture(Long id) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Picture picture = getPicture(id,entityManager);
		entityManager.remove(picture);
	}

	

}
