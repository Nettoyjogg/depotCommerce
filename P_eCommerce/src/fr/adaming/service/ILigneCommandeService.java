package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Local
public interface ILigneCommandeService {

	public LigneCommande AjouterLigneCommandeService(LigneCommande lc);
	// Red�finition pour client connect�
	public LigneCommande AjouterLigneCommandeService(LigneCommande lc, Client c);

	public int LierLigneCommandeCommandeService(LigneCommande lc, Commande co);
	
	public int LierLigneCommandeProduitService(LigneCommande lc, Produit p);
	
	public int supprimerLigneCommandeService(LigneCommande lc);
}
