package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="administrateurs")
public class Administrateur implements Serializable{

	// d�claration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_admin")
	private int idAdmin;
	@Column(name = "nom_admin")
	private String nomAdmin;
	@Column(name = "prenom_admin")
	private String prenomAdmin;
	@Column(name = "mail_admin")
	private String mailAdmin;
	@Column(name = "mdp_admin")
	private String mdp;
	
	// transformation de l'association UML en JAVA
	@ManyToMany
	@JoinTable(name="assoc_produit_admin", joinColumns=@JoinColumn(name="id_admin"),inverseJoinColumns=@JoinColumn(name="id_p"))
	private List<Produit> listeProduit;
	@ManyToMany
	@JoinTable(name="assoc_categorie_admin", joinColumns=@JoinColumn(name="id_admin"),inverseJoinColumns=@JoinColumn(name="id_ca"))
	private List<Categorie> listeCategorie;
	//d�claration des constructeurs
	public Administrateur() {
		super();
	}
	public Administrateur(String nomAdmin, String prenomAdmin, String mailAdmin, String mdp) {
		super();
		this.nomAdmin = nomAdmin;
		this.prenomAdmin = prenomAdmin;
		this.mailAdmin = mailAdmin;
		this.mdp = mdp;
	}
	public Administrateur(int idAdmin, String nomAdmin, String prenomAdmin, String mailAdmin, String mdp) {
		super();
		this.idAdmin = idAdmin;
		this.nomAdmin = nomAdmin;
		this.prenomAdmin = prenomAdmin;
		this.mailAdmin = mailAdmin;
		this.mdp = mdp;
	}
	
	//d�claration des getters et setter
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getNomAdmin() {
		return nomAdmin;
	}
	public void setNomAdmin(String nomAdmin) {
		this.nomAdmin = nomAdmin;
	}
	public String getPrenomAdmin() {
		return prenomAdmin;
	}
	public void setPrenomAdmin(String prenomAdmin) {
		this.prenomAdmin = prenomAdmin;
	}
	public String getMailAdmin() {
		return mailAdmin;
	}
	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	public List<Produit> getListeProduit() {
		return listeProduit;
	}
	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}
	
	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}
	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}
	@Override
	public String toString() {
		return "Administrateur [idAdmin=" + idAdmin + ", nomAdmin=" + nomAdmin + ", prenomAdmin=" + prenomAdmin
				+ ", mailAdmin=" + mailAdmin + ", mdp=" + mdp + "]";
	}
	
	
	
}