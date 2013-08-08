package com.veggie411.veggie411;

public class Ingredient {
	String name;
	String id;
	String description;
	
	public Ingredient(String name) {
		this.name = name;
	}
	
	public Ingredient(String name, String id) {
		this(name);
		this.id = id;
	}
	
	public Ingredient(String name, String id, String description) {
		this(name, id);
		this.description = description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
}
