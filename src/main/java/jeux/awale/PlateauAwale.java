package jeux.awale;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlateauAwale implements PlateauJeu {

    /* Pour coder un nouveau jeu... il faut au minimum coder
     * - Une classe PlateauAwale pour représenter l'état du "plateau"
     *  de ce jeu.
     *  Cette classe doit fournir le code des méthodes de l'interface PlateauJeu
     *  qui permettent de caractériser les règles du jeu
     *  Une classe CoupAwale qui
     */

    private static final int TAILLE = 12:

    private static Joueur joueurBlanc;
    private static Joueur joueurNoir;

    private int[] plateau;

    public PlateauAwale() {
        this.plateau = new int[TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            this.plateau[i] = 4;
        }
    }

    public PlateauAwale(int[] depuis) {
        System.arraycopy(depuis, 0, plateau, 0, depuis.length);
    }

    static void setJoueurs(Joueur jb, Joueur jn) {
        joueurBlanc = jb;
        joueurNoir = jn;
    }

    public boolean isJoueurBlanc(Joueur j) {
        return joueurBlanc.equals(j);
    }

    public boolean isJoueurNoir(Joueur j) {
        return joueurNoir.equals(j);
    }

    public ArrayList<CoupJeu> coupsPossibles(Joueur j) {
        ArrayList<CoupJeu> lesCoupsPossibles = new ArrayList<CoupJeu>();
        if (j.equals(joueurBlanc)) {
            for (int i = 0; i < TAILLE/2; i++) {
                if (plateau[i] != 0)
                    lesCoupsPossibles.add(new CoupAwale(i));
            }
        }
        else {
            for (int i = 6; i < TAILLE; i++) {
                if (plateau[i] != 0)
                    lesCoupsPossibles.add(new CoupAwale(i));
            }
        }
        return lesCoupsPossibles;
    }

    public void joue(Joueur j, CoupJeu c) {
        CoupAwale ca = (CoupAwale)c;
        int cell = ca.getCell();
        if (j.equals(joueurBlanc)) {
            for (int i = plateau[cell]; i <= plateau[cell]; i--) {
                plateau[cell] += 1;
            }
            plateau[cell] = 0;
        } else {
            for (int i = plateau[cell]; i <= plateau[cell]; i--) {
                plateau[i] += 1;
            }
            plateau[cell] = 0;
        }
    }

    public boolean finDePartie() {
        return (nbGrainesBlancs() >= 25 || nbGrainesNoirs() >= 25);
    }

    public PlateauJeu copy() {
        return new PlateauAwale(this.plateau);
    }

    public boolean coupValide(Joueur j, CoupJeu c) {
        throw new UnsupportedOperationException("Il vous faut coder cette méthode");
    }

    @Override
    public String toString() {

    }

    private int nbGrainesBlancs() {
        int compteur = 0;
        for (int i = 0; i < TAILLE/2; i++) {
            compteur += plateau[i];
        }
        return compteur;
    }

    private int nbGrainesNoirs() {
        int compteur = 0;
        for (int i = 6; i < TAILLE; i++) {
            compteur += plateau[i];
        }
        return compteur;
    }
}
