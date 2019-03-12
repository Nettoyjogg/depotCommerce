package fr.adaming.service;

import javax.ejb.EJB;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;

public class AdministrateurServiceImpl implements IAdministrateurService {
	// Transformation de l'associaton UML en JAVA
	@EJB
	private IAdministrateurDao adminDao;

	@Override
	public Administrateur estExistant(Administrateur admin) {

		return adminDao.estExistant(admin);

	}

}
