package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {
	@PersistenceContext(unitName = "pu_commerce")

	private EntityManager em;

	@Override
	public List<Categorie> afficherCategorieDao() {
		// Requete
		String req = "SELECT ca FROM Categorie as ca";

		// R�p�rer un objet query
		Query query = em.createQuery(req);

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
		Query req = em.createQuery("DELETE FROM Categorie as ca WHERE ca.idCategorie=:pId");
		req.setParameter("pId", ca.getIdCategorie());
		int verif = req.executeUpdate();
		return verif;
	}

	@Override
	public Categorie consulterCategorieParIDDao(Categorie ca) {
		return em.find(Categorie.class, ca.getIdCategorie());
	}

}
