package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlTransient;

import java.util.Date;
import java.util.List;

@Entity
public class PictureGroup {

	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;
	    private String name;
	    private String description;
	    
	    public PictureGroup(){
	    	
	    }
	    
		public PictureGroup(String name, String description) {
			super();
			this.name = name;
			this.description = description;
		}

			
		@ManyToMany(mappedBy = "picturegroupList",fetch=FetchType.EAGER)
		private List<Picture> pictureList;
		
		@XmlTransient
	    public List<Picture> getPictureList() {
	        return pictureList;
	    }

	    public void setPictureList(List<Picture> pictureList) {
	        this.pictureList = pictureList;
	    }
	    
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
}
