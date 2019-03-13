package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMB")
@RequestScoped
public class ProduitManagedBean {

	@EJB
	private IProduitService pService;
	@EJB
	private ICategorieService caService;

	// Attribut
	private Administrateur administrateur;
	private Produit produit;
	private HttpSession maSession;
	private boolean indice;
	private Categorie categorie;
	private UploadedFile image;
	private List<Produit> listeProduit;

	// déclaration du constructeur vide
	public ProduitManagedBean() {
		super();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.indice = false;
		this.listeProduit = new ArrayList<Produit>();
	}

	@PostConstruct // Cette annotation sert à dire que la méthode doit être
	// exécutée après l'instanciation de l'objet.
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.administrateur = (Administrateur) maSession.getAttribute("adminSession");
	}

	// getter et setter

	public Produit getProduit() {
		return produit;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	// les méthodes métiers du Managed Bean
	public String ajouterProduitMB() {
		if (this.image != null) {
			this.produit.setPhoto(this.image.getContents());
		}
		Produit pAjout = pService.ajouterProduitService(produit, categorie, administrateur);
		if (pAjout.getIdProduit() != 0) {
			// Récuperer la liste des produits
			List<Produit> listep = pService.afficherProduitService(administrateur);

			// Mettre à jour la liste dans la sessin
			maSession.setAttribute("produitSession", listep);
			return "accueilproduit";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'Ajout du produit"));
			return "ajoutproduit";
		}

	}

	public String modifierProduitMB() {
		categorie = caService.consulterCategorieParIDService(categorie);
		this.produit.setCategorie(categorie);
		int verif = pService.modifierProduitService(produit, administrateur);
		if (verif != 0) {
			// Récuperer la liste
			List<Produit> listep = pService.afficherProduitService(administrateur);

			// Mettre à jour la liste dans la sessin
			maSession.setAttribute("produitSession", listep);
			return "accueilproduit";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modif a échoué"));
			return "modifierproduit";
		}

	}

	public void modifierProduitAutoMB() {
		categorie = caService.consulterCategorieParIDService(categorie);
		this.produit.setCategorie(categorie);
		this.produit = pService.consulterProduitService(produit, administrateur);
	}

	public String supprimerProduitMB() {
		int verif = pService.supprimerProduitService(produit, administrateur);
		if (verif != 0) {
			// Récuperer la liste
			List<Produit> listep = pService.afficherProduitService(administrateur);

			// Mettre à jour la liste dans la sessin
			maSession.setAttribute("produitSession", listep);
			return "accueilproduit";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression du produit à échouer"));
			return "supprimerproduit";
		}

	}

	public void rechercherProduitParIdMB() {
		this.produit = pService.consulterProduitService(produit, administrateur);
		if (produit == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'existe pas"));
			indice = false;
		} else {
			indice = true;
		}
	}

	public void rechercherProduitCategorieMB() {
		try {
			categorie = caService.consulterCategorieParIDService(categorie);
			this.produit.setCategorie(categorie);
			this.listeProduit.addAll(pService.consulterProduitCategorieService(this.produit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (listeProduit.isEmpty() == true) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de produits"));
			indice = false;
		} else {
			indice = true;
		}

	}

}
