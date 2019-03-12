package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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

	// Constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
		// R�cup�rer le administrateur de la session
		this.indice = false;
	}

	@PostConstruct // Cette annotation sert � dire que la m�thode doit �tre
					// ex�cut�e apr�s l'instanciation de l'objet.
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

	// M�thodes m�tiers
	public String ajouterCategorieMB() {
		Categorie caAjout = caService.ajouterCategorieService(categorie, administrateur);
		if (caAjout.getIdCategorie() != 0) {
			// R�cuperer la liste
			List<Categorie> liste = caService.afficherCategorieService(administrateur);

			// Mettre � jour la liste dans la sessin
			maSession.setAttribute("categorieSession", liste);
			return "accueil";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout a �chou�"));
			return "ajoutcategorie";
		}

	}

	public String modifierCategorieMB() {
		int verif = caService.modifierCategorieService(categorie, administrateur);
		if (verif != 0) {
			// R�cuperer la liste
			List<Categorie> liste = caService.afficherCategorieService(administrateur);

			// Mettre � jour la liste dans la sessin
			maSession.setAttribute("categorieSession", liste);
			return "accueil";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modif a �chou�"));
			return "modifiercategorie";
		}

	}

	public String supprEtudiant() {
		int verif = caService.supprimerCategorieService(categorie, administrateur);
		if (verif != 0) {
			// R�cuperer la liste
			List<Categorie> liste = caService.afficherCategorieService(administrateur);

			// Mettre � jour la liste dans la sessin
			maSession.setAttribute("categorieSession", liste);
			return "accueil";
		} else {
			// Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression a �chou�"));
			return "supprimercategorie";
		}

	}

	public void searchEtudiant() {
		this.categorie = caService.consulterCategorieParIDService(categorie, administrateur);
		if (categorie == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La cat�gorie est null"));
			indice = false;
		} else {
			indice = true;
		}
	}

}
