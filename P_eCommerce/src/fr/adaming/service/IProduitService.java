package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	public List<Produit> afficherProduitService(Administrateur admin);

	public Produit ajouterProduitService(Produit p,Administrateur admin);

	public int modifierProduitService(Produit p,Administrateur admin);

	public int supprimerProduitService(Produit p,Administrateur admin);

	public Produit consulterProduitService(Produit p,Administrateur admin);
}
