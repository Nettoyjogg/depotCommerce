package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {
	
	// Transfo UML Java
	@EJB
	ICategorieDao caDao;

	@Override
	public List<Categorie> afficherCategorieService(Administrateur admin) {
		// Il faudra redéfinir pour les admins
		return caDao.afficherCategorieDao();
	}

	@Override
	public Categorie ajouterCategorieService(Categorie ca, Administrateur admin) {
		
		if(admin.getIdAdmin()!=0){
		return caDao.ajouterCategorieDao(ca);
		}
		return null;
	}

	@Override
	public int modifierCategorieService(Categorie ca, Administrateur admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprimerCategorieService(Categorie ca, Administrateur admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie consulterCategorieParIDService(Categorie ca, Administrateur admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
