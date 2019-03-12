package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public class CategorieDaoImpl implements ICategorieDao {
	@PersistenceContext(unitName = "pu_commerce")

	private EntityManager em;

	@Override
	public List<Categorie> afficherCategorie(Administrateur admin) {
		// Requete
		String req = "SELECT ca FROM Categorie as ca WHERE ca.Administrateur.idAdmin=:pId";

		// Répérer un objet query
		Query query = em.createQuery(req);

		// Passage des params
		query.setParameter("pId", admin.getIdAdmin());
		return query.getResultList();
	}

	@Override
	public Categorie ajouterCategorieDao(Categorie ca) {
		em.persist(ca);
		return ca;		
	}

	@Override
	public int modifierCategorieDao(Categorie ca) {
		Query req = em.createQuery(
				"UPDATE Categorie as ca SET ca.nomCategorie=:pNom, ca.photo=:pPhoto, ca.description=:pDescription WHERE ca.idCategorie=:pId");
		req.setParameter("pNom", ca.getNomCategorie());
		req.setParameter("pPhoto", ca.getPhoto());
		req.setParameter("pDescription", ca.getDescription());
		req.setParameter("pId", ca.getIdCategorie());
		int verif = req.executeUpdate();
		return verif;		
	}

	@Override
	public int supprimerCategorieDao(Categorie ca) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie consulterCategorieDao(Categorie ca) {
		// TODO Auto-generated method stub
		return null;
	}

}
