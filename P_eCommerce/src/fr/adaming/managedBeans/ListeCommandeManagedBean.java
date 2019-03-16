package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	}

	// Getters and Setters

	public HttpSession getMaSession() {
		return maSession;
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

	// Méthodes :

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
		try {
			listeLigneCommande.addAll((List<LigneCommande>) maSession.getAttribute("listeLigneCommandeSession"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (test >= 0) {

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
			this.listeLigneCommande.add(ligneCommande);
			maSession.setAttribute("listeLigneCommandeSession", listeLigneCommande);
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

	public String lierCommandeLigneCommandeMB() {
		int c = 0;
		this.listeLigneCommande = (List<LigneCommande>) maSession.getAttribute("listeLigneCommandeSession");
		try {
			c = listeLigneCommande.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (c > 0) {

			commande.setDateCommande(date);
			Commande com = coService.ajouterCommandeService(commande);
			com = coService.consulterCommandeParIDService(com);
			for (int i = 0; i < listeLigneCommande.size(); i++) {
				lcService.LierLigneCommandeCommandeService(listeLigneCommande.get(i), com);
			}
			return "accueilproduit";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de produit choisis"));
			return "testcommande";
		}
	}

	public String lierClientCommandeMB() {
		cService.ajouterClientService(client, adresse);
		this.listeLigneCommande = (List<LigneCommande>) maSession.getAttribute("listeLigneCommandeSession");
		commande.setIdCommande(listeLigneCommande.get(0).getCommande().getIdCommande());
		coService.consulterCommandeParIDService(commande);
		// Rajouter un if(isExistClientSession....) pour choisir entre remplir
		// le formulaire client ou connecter un client.
		int verif = coService.ajouterClientCommandeService(commande, client);
		if (verif != 0) {
			return "accueilproduit";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de client choisi"));
			return "testclient";
		}
	}
}
