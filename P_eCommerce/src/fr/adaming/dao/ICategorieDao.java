package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public interface ICategorieDao {
	
	public List<Categorie> afficherCategorie(Administrateur admin);
	
	public Categorie ajouterCategorieDao(Categorie ca);
			
	public int modifierCategorieDao(Categorie ca);
	
	public int supprimerCategorieDao(Categorie ca);
	
	public Categorie consulterCategorieDao(Categorie ca);

}
