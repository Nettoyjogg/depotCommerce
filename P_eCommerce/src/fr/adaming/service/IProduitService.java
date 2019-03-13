package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	public List<Produit> afficherProduitDao(Administrateur admin);

	public Produit ajouterProduitDao(Produit p,Administrateur admin);

	public int modifierProduitDao(Produit p,Administrateur admin);

	public int supprimerProduitDao(Produit p,Administrateur admin);

	public Produit consulterProduitDao(Produit p,Administrateur admin);
}
