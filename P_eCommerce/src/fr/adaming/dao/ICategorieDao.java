package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieDao {
	
	public List<Categorie> afficherCategorieDao();
	
	public Categorie ajouterCategorieDao(Categorie ca);
			
	public int modifierCategorieDao(Categorie ca);
	
	public int supprimerCategorieDao(Categorie ca);
	
	public Categorie consulterCategorieParIDDao(Categorie ca);

}
