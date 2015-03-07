package com.mcd.wallet.objects;


public class SubCategory implements java.io.Serializable{
	
	private Integer id;
	private String name;
	private Category pelicula;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getPelicula() {
		return pelicula;
	}
	public void setPelicula(Category pelicula) {
		this.pelicula = pelicula;
	}
	
	
}
