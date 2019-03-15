package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService {
	@EJB
	ICommandeDao coDao;

	@Override
	public Commande ajouterCommandeService(Commande co) {
		
		return coDao.ajouterCommandeDao(co);
	}

	@Override
	public Commande consulterCommandeParIDService(Commande co) {
		
		return coDao.consulterCommandeParIDDao(co);
	}

}
