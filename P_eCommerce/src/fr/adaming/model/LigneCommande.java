package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignecommandes")
public class LigneCommande {
//d�claration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lc")
	private int idLigneCommande;
	@Column(name = "quantite_lc")
	private int quantite;
	@Column(name = "prix_lc")
	private int prix;
	//transformation de l'association UML en java
	@ManyToOne
	@JoinColumn(name="produit_lc",referencedColumnName="id_p")
	private Produit produit;
	@ManyToOne
	@JoinColumn(name="commande_lc",referencedColumnName="id_co")
	private Commande commande;
	//d�claration des constructeurs
	public LigneCommande() {
		super();
	}
	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}
	
	public LigneCommande(int idLigneCommande, int quantite, int prix) {
		super();
		this.idLigneCommande = idLigneCommande;
		this.quantite = quantite;
		this.prix = prix;
	}
	//d�claration des getters et setters
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getIdLigneCommande() {
		return idLigneCommande;
	}
	public void setIdLigneCommande(int idLigneCommande) {
		this.idLigneCommande = idLigneCommande;
	}
	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	@Override
	public String toString() {
		return "LigneCommande [idLigneCommande=" + idLigneCommande + ", quantite=" + quantite + ", prix=" + prix + "]";
	}

	
	
	

	
	
	
	
}
