package model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@SuppressWarnings("serial")
@Entity
public class Category implements java.io.Serializable {
	
	@Id
	@NotEmpty
	@Length(min=3)
	String name;
	@NotEmpty
	@Length(min=5)
	String description;
	
	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Category(){
		//JPA required}
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


}
