package fr.adaming.model;

public class LigneCommande {
//déclaration des attributs
	private int quantite;
	private int prix;
	//transformation de l'association UML en java
	
	//déclaration des constructeurs
	public LigneCommande() {
		super();
	}
	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}
	
	//déclaration des getters et setters
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	//to string
	@Override
	public String toString() {
		return "LigneCommande [quantite=" + quantite + ", prix=" + prix + "]";
	}
	

	
	
	
	
}
