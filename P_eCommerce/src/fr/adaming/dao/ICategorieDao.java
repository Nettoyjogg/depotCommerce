package fr.adaming.dao;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public interface ICategorieDao {
	
	public Categorie ajouterCategorieDao(Categorie ca, Administrateur admin);
			
	public Categorie modifierCategorieDao(Categorie ca, Administrateur admin);
	
	public int supprimerCategorieDao(Categorie ca, Administrateur admin);
	
	public Categorie consulterCategorieDao(Categorie ca, Administrateur admin);

}
