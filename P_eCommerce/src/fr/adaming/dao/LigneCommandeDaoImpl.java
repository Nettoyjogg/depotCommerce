package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {
	
	@PersistenceContext(unitName = "pu_commerce")
	private EntityManager em;

	@Override
	public LigneCommande AjouterLigneCommandeDao(Commande co) {
		
		String req="UPDATE LigneCommande  as lc SET lc.commande=:pCommande ";
		
		return null;
	}

	@Override
	public LigneCommande AjouterLigneCommandeDao(Produit p) {
	
		return null;
	}

}
