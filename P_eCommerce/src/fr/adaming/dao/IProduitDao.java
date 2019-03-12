package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Produit;


public interface IProduitDao {
	
	public List<Produit> afficherProduit(Administrateur admin);
	
	public Produit ajouterProduitDao(Produit p, Administrateur admin);
	
	public Produit modifierProduitDao(Produit p, Administrateur admin);
	
	public int supprimerProduitDao(Produit p, Administrateur admin);
	
	public Produit consulterProduitDao(Produit p, Administrateur admin);
}
