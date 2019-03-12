package fr.adaming.model;

import java.util.Arrays;

public class Categorie {
//déclaration des attributs
	private int idCategorie;
	private String nomCategorie;
	private byte[] photo;
	private String description;
	//transformation de l'association UML en JAVA
	
	//déclaration des 3 constructeurs
	public Categorie() {
		super();
	}
	public Categorie(String nomCategorie, byte[] photo, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}
	public Categorie(int idCategorie, String nomCategorie, byte[] photo, String description) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}
	
	//Déclaration des getters et setters
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//to string
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]";
	}
	
	
	
	
}
