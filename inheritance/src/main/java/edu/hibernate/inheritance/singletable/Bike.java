package edu.hibernate.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="bike")
public class Bike extends Vehicle{

	@Column(name="brake")
	private String brake;
	
	public Bike(){
		super();
	}

	public Bike(Long id, String owner, String brake) {
		super(id, owner);
		this.brake = brake;
	}

	public String getBrake() {
		return brake;
	}

	public void setBrake(String brake) {
		this.brake = brake;
	}

	@Override
	public String toString() {
		return "Bike [brake=" + brake + "]";
	}
	
	
}
