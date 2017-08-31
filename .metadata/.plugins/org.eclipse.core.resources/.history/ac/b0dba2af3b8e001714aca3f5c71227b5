package edu.hibernate.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="car")
public class Car extends Vehicle{

	@Column(name="type")
	private String type;

	public Car(){
		super();
	}
	
	public Car(Long id, String owner, String type) {
		super(id, owner);
		this.type = type;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [type=" + type + "]";
	}
	
	
}
