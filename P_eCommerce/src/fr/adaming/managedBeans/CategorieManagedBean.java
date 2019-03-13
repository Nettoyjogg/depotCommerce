package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Administrateur;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "caMB")
@RequestScoped
public class CategorieManagedBean {
	@EJB
	private ICategorieService caService;

	// Attribut
	private Administrateur administrateur;
	private Categorie categorie;
	private HttpSession maSession;
	private boolean indice;
	private UploadedFile image;

	// Constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
		// Récupérer le administrateur de la session
		this.indice = false;
	}

	@PostConstruct // Cette annotation sert à dire que la méthode doit être
					// exécutée après l'instanciation de l'objet.
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.administrateur = (Administrateur) maSession.getAttribute("adminSession");
	}

	// Getters and Setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	// Méthodes métiers
	public String ajouterCategorieMB() {
		if(this.image!=null){
			this.categorie.setPhoto(this.image.getContents());
		}
		Categorie caAjout = caService.ajouterCategorieService(categorie, administrateur);
		if (caAjout.getIdCategorie() != 0) {
			// Récuperer la liste
			List<Categorie> liste = caService.afficherCategorieService(administrateur);

			// Mettre à jour la liste dans la sessin
			maSession.setAttribute("categorieSession", liste);
			return "accueil";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout a échoué"));
			return "ajoutcategorie";
		}

	}

	public String modifierCategorieMB() {
		int verif = caService.modifierCategorieService(categorie, administrateur);
		if (verif != 0) {
			// Récuperer la liste
			List<Categorie> liste = caService.afficherCategorieService(administrateur);

			// Mettre à jour la liste dans la sessin
			maSession.setAttribute("categorieSession", liste);
			return "accueil";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modif a échoué"));
			return "modifiercategorie";
		}

	}

	public String supprimerCategorieMB() {
		int verif = caService.supprimerCategorieService(categorie, administrateur);
		if (verif != 0) {
			// Récuperer la liste
			List<Categorie> liste = caService.afficherCategorieService(administrateur);

			// Mettre à jour la liste dans la sessin
			maSession.setAttribute("categorieSession", liste);
			return "accueil";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression a échoué"));
			return "supprimercategorie";
		}

	}

	public void rechercherCategorieParIdMB() {
		this.categorie = caService.consulterCategorieParIDService(categorie, administrateur);
		if (categorie == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie est null"));
			indice = false;
		} else {
			indice = true;
		}
	}
	
	public void modifierCategorieAutoMB() {
		this.categorie=caService.consulterCategorieParIDService(categorie, administrateur);
	}

}
