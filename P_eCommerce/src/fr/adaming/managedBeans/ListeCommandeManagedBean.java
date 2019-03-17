package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
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
	@EJB
	private IClientService cService;

	// Attribut
	private LigneCommande ligneCommande;
	private HttpSession maSession;
	private List<LigneCommande> listeLigneCommande;
	private Commande commande;
	private Produit produit;
	private Administrateur admin;
	private Date date;
	private Client client;
	private Adresse adresse;
	private Panier panier;

	// Constructeur vide
	public ListeCommandeManagedBean() {
		this.ligneCommande = new LigneCommande();
		this.commande = new Commande();
		this.produit = new Produit();
		this.admin = new Administrateur();
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeLigneCommande = new ArrayList<LigneCommande>();
		this.date = new Date();
		this.client = new Client();
		this.adresse = new Adresse();
		this.panier = new Panier();
	}

	// Getters and Setters

	public HttpSession getMaSession() {
		return maSession;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@PostConstruct // Cette annotation sert � dire que la m�thode doit �tre
	// ex�cut�e apr�s l'instanciation de l'objet.
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// M�thodes :

	public String ajouterLigneCommandeMB() {
		int test = 0;
		try {
			produit = pService.consulterProduitService(produit);
			test = produit.getQuantite() - ligneCommande.getQuantite();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ce produit n'est pas en stock ou n'existe pas"));
			return "testajoutlc";
		}
		if (maSession.getAttribute("panierSession") != null) {
			this.panier = (Panier) maSession.getAttribute("panierSession");
			this.panier.getListeLigneCommande();
		} else {
			this.panier = new Panier();
			List<LigneCommande> listtest = new ArrayList<LigneCommande>();
			this.panier.setListeLigneCommande(listtest);
		}
		if (test >= 0) {
			// On ajoute a lc en mettant le prix
			ligneCommande.setPrix(produit.getPrix() * ligneCommande.getQuantite());
			lcService.AjouterLigneCommandeService(ligneCommande);
			lcService.LierLigneCommandeProduitService(ligneCommande, produit);

			// Ici on, modifie la quantit� du produit dans la BD apr�s la
			// commande (en vrai il faudra le faire � la confirmation de la
			// commande)
			produit.setQuantite(produit.getQuantite() - ligneCommande.getQuantite());
			admin.setIdAdmin(1);// C'est moche, mais c'est pour le test, un
								// genre d'admin "int�gr�", sans mail...
								// pour
								// faire fonctionner ces m�thodes
			int verif = pService.modifierProduitService(produit, admin);
			this.listeLigneCommande.add(ligneCommande);
			panier.getListeLigneCommande().addAll(listeLigneCommande);
			maSession.setAttribute("panierSession", panier);
			if (verif != 0) {
				return "accueilproduit";
			} else {
				// Ajouter un message d'erreur
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout a �chou�"));
				return "testajoutlc";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas assez de produit en stock"));
			return "testajoutlc";
		}
	}

	public String lierCommandeLigneCommandeMB() {
		int c = 0;
		this.panier = (Panier) maSession.getAttribute("panierSession");

		try {
			c = panier.getListeLigneCommande().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (c > 0) {

			commande.setDateCommande(date);
			Commande com = coService.ajouterCommandeService(commande);
			com = coService.consulterCommandeParIDService(com);
			for (int i = 0; i < panier.getListeLigneCommande().size(); i++) {
				lcService.LierLigneCommandeCommandeService(panier.getListeLigneCommande().get(i), com);
			}
			return "accueilproduit";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de produit choisis"));
			return "testcommande";
		}
	}

	public String lierClientCommandeMB() {

		int verif;
		this.panier = (Panier) maSession.getAttribute("panierSession");
		commande.setIdCommande(panier.getListeLigneCommande().get(0).getCommande().getIdCommande());
		coService.consulterCommandeParIDService(commande);
		if ((Client) maSession.getAttribute("clientSession") != null) {
			this.client = (Client) maSession.getAttribute("clientSession");
			verif = coService.ajouterClientCommandeService(commande, client);
		} else {
			cService.ajouterClientService(client, adresse);
			verif = coService.ajouterClientCommandeService(commande, client);
		}

		if (verif != 0) {
			return "accueilproduit";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de client choisi"));
			return "testclient";
		}
	}

}
