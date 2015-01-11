package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import java.util.Date;
import java.util.List;

@Entity
public class Picture {

	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;
	    private String name;
	    private String file;
	    private String about;
	    private Date created;
	    
	    public Picture(){
	    	
	    }
	    
		public Picture(String name, String file, String about, Date created) {
			super();
			this.name = name;
			this.file = file;
			this.about = about;
			this.created = created;
		}
		@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		@JoinTable(name = "grouptopicture", joinColumns = {
		        @JoinColumn(name = "pictureId", referencedColumnName = "id")}, inverseJoinColumns = {
		        @JoinColumn(name = "groupId", referencedColumnName = "id")})	
	    private List<PictureGroup> picturegroupList;
		
		 @XmlTransient
		    public List<PictureGroup> getPicturegroupList() {
		        return picturegroupList;
		    }

		    public void setPicturegroupList(List<PictureGroup> picturegroupList) {
		        this.picturegroupList = picturegroupList;
		    }
		
	    
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public String getAbout() {
			return about;
		}

		public void setAbout(String about) {
			this.about = about;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
}
