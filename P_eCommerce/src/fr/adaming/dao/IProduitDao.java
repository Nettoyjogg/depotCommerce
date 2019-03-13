package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public List<Produit> afficherProduitDao();
	
	public Produit ajouterProduitDao(Produit p);
	
	public int modifierProduitDao(Produit p);
	
	public int supprimerProduitDao(Produit p);
	
	public Produit consulterProduitDao(Produit p);

	public List<Produit> consulterProduitSelectionnes(Produit p);
	
	public Produit chercherProduitParMotCle();
	
	public Produit ajouterUnProduitQuantitePanier(Produit p);
	
	public int supprimerProduitPanier(Produit p);

}
