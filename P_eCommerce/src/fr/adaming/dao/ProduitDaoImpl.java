package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {
	// LIER AUX BONNES REGLES DE CONFIG
	@PersistenceContext(unitName = "pu_commerce")

	private EntityManager em;

	@Override
	public List<Produit> afficherProduitDao() {
		// Requete JPQL
		String req = "SELECT p FROM Produit as p";

		// Répérer un objet query
		Query query = em.createQuery(req);

		List<Produit> ListeProduit= query.getResultList();
		
			for(Produit p:ListeProduit){

			p.setImg("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));

			}
		
		return ListeProduit;
	}

	@Override
	public Produit ajouterProduitDao(Produit p) {
		em.persist(p);
		return p;
	}

	@Override
	public int modifierProduitDao(Produit p) {
		// requete JPQL
		String req = "UPDATE Produit as p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.selectionne=:pSelectionne, p.photo=:pPhoto WHERE p.idProduit=:pId";
		// récupérer un objet query
		Query query = em.createQuery(req);

		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pSelectionne", p.isSelectionne());
		query.setParameter("pPhoto", p.getPhoto());
		query.setParameter("pId", p.getIdProduit());
		int verif = query.executeUpdate();
		return verif;
	}

	@Override
	public int supprimerProduitDao(Produit p) {
		// requete JPQL
		String req = "DELETE FROM Produit as p WHERE p.idProduit=:pId";
		// récupérer un objet query
		Query query = em.createQuery(req);

		query.setParameter("pId", p.getIdProduit());
		int verif = query.executeUpdate();
		return verif;
	}

	@Override
	public Produit consulterProduitDao(Produit p) {
		return em.find(Produit.class, p.getIdProduit());
	}

	@Override
	public List<Produit> consulterProduitSelectionnes(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit chercherProduitParMotCle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit ajouterUnProduitQuantitePanier(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int supprimerProduitPanier(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}



}
