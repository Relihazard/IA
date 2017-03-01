package jeux.dominos;
import iia.jeux.modele.CoupJeu;

public class CoupDominos implements CoupJeu {

	/****** Attributs *******/ 

	private int ligne;

	private int colonne;


	/****** Clonstructeur *******/ 

	CoupDominos(int l, int c) {
		ligne = l;
		colonne = c;
	}

	/****** Accesseurs *******/ 

	int getLigne() {
		return ligne;
	}

	int getColonne() {
		return colonne;
	}

	/****** Accesseurs *******/ 

	public String toString() {
		return "("+ligne+","+colonne+")";
	}
}

