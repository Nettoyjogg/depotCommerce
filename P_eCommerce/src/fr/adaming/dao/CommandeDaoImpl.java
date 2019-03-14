package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {
	@PersistenceContext(unitName = "pu_commerce")

	private EntityManager em;

	@Override
	public Commande ajouterCommandeDao(Commande co) {
		em.persist(co);
		return co;
	}

	@Override
	public Commande consulterCommandeParIDDao(Commande co) {
		return em.find(Commande.class, co.getIdCommande());
	}

}
