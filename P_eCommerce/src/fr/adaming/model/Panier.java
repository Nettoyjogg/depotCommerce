package fr.adaming.model;

import java.util.List;

public class Panier {

	//transformation de l'association UML
	List<LigneCommande> listeLigneCommande;

	public Panier() {
		super();
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	@Override
	public String toString() {
		return "Panier [listeLigneCommande=" + listeLigneCommande + "]";
	}
	
}
