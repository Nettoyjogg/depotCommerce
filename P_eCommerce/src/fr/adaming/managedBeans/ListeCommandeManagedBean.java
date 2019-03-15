package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class ListeCommandeManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ILigneCommandeService lcService;
	@EJB
	private ICommandeService coService;
	@EJB
	private IProduitService pService;

	// Attribut
	private LigneCommande ligneCommande;
	private HttpSession maSession;
	private List<LigneCommande> listeLigneCommande;
	private Commande commande;
	private Produit produit;
	private Administrateur admin;

	// Constructeur vide
	public ListeCommandeManagedBean() {
		this.ligneCommande = new LigneCommande();
		this.commande = new Commande();
		this.produit = new Produit();
		this.admin = new Administrateur();
	}

	// Getters and Setters

	public HttpSession getMaSession() {
		return maSession;
	}

	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	// Méthodes :

	public String ajouterLigneCommandeMB() {
		int test = 0;
		// Commande com = coService.ajouterCommandeService(commande);
		// Ne pas utiliser, la commande doit avoir le meme id, ajouter apres
		// com = coService.consulterCommandeParIDService(com); idem
		// int verif = lcService.LierLigneCommandeCommandeService(lcAjout, com);

		// Ici, On check si on a assez de produit en stock
		try {
			produit = pService.consulterProduitService(produit);
			test = produit.getQuantite() - ligneCommande.getQuantite();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ce produit n'est pas en stock ou n'existe pas"));
			return "testajoutlc";
		}

		if (test > 0) {

			// On ajoute a lc en mettant le prix
			ligneCommande.setPrix(produit.getPrix() * ligneCommande.getQuantite());
			lcService.AjouterLigneCommandeService(ligneCommande);
			lcService.LierLigneCommandeProduitService(ligneCommande, produit);
			// Ici on, modifie la quantité du produit dans la BD après la
			// commande (en vrai il faudra le faire à la confirmation de la
			// commande)
			produit.setQuantite(produit.getQuantite() - ligneCommande.getQuantite());
			admin.setIdAdmin(1);// C'est moche, mais c'est pour le test, un
								// genre d'admin "intégré", sans mail...
								// pour
								// faire fonctionner ces méthods
			int verif = pService.modifierProduitService(produit, admin);

			if (verif != 0) {
				return "accueilproduit";
			} else {
				// Ajouter un message d'erreur
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout a échoué"));
				return "testajoutlc";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas assez de produit en stock"));
			return "testajoutlc";
		}
	}
}
