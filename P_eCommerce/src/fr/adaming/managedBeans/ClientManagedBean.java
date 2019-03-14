package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "cMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;

	// Transformation de l'association UML en Java
	@EJB
	private IClientService cService;
	@EJB
	private ICategorieService caService;
	@EJB
	private IProduitService pService;

	// D�claration des attributs
	private Client client;

	
	//d�claration du constructeur
	public ClientManagedBean() {
		super();
		this.client = new Client();
	}

//getter et setter
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	//m�thodes et m�tiers
	public String seConnecter() {
		//sed�connecter d'une session ant�rieure aucasou
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		// chercher le administrateur par son mail et mdp
		Client cOut = cService.estExistantService(client);

		if (cOut != null) {
			// R�cuprer les diff�rentes liste sur la session de ce
			// administrateur
			//------------------------------------------------Il va falloir r�d�finir les m�thodes sans les admins
//			List<Categorie> liste = caService.afficherCategorieService(adminOut);
//			List<Produit> listep = pService.afficherProduitService(adminOut);

			// Mettre la liste dans la session
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieSession", liste);
//
//			// Mettre la liste dans la session
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitSession", listep);

			// Mettre le administrateur dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession", cOut);
			return "accueilclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le mot de passe ou mail est mauvais"));
			return "accueilclient";
		}
	}

	public String seDeconnecter() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "accueilclient";
		
	}

	
}
