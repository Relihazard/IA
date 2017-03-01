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

    private static final int TAILLE = 12;

    private static Joueur joueurBlanc;
    private static Joueur joueurNoir;

    private static int nbGrainesBlancs;
    private static int nbGrainesNoirs;

    private int[] plateau;

    public PlateauAwale() {
        this.plateau = new int[TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            this.plateau[i] = 4;
        }
    }

    public PlateauAwale(int[] depuis) {
        this.plateau = new int[TAILLE];
        for (int i = 0; i < TAILLE; i++)
            this.plateau[i] = depuis[i];
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
        int firstCell = ca.getCell();
        int cell;
        int x = plateau[firstCell];
        if (j.equals(joueurBlanc)) {
            for (cell = ca.getCell(); x > 0; cell++) {
                if (cell == 12) {
                    cell = 0;
                }
                if (cell == firstCell) {
                    cell++;
                }
                plateau[cell]++;
                x--;
            }
            plateau[firstCell] = 0;
            if (cell > 5) {
                for (cell = cell; cell > 5 && cell < 12; cell--) {
                    if (plateau[cell] == 2 || plateau[cell] == 3) {
                        nbGrainesBlancs += plateau[cell];
                        plateau[cell] = 0;
                    }
                }
            }
        } else {
            for (cell = ca.getCell(); x > 0; cell++) {
                if (cell == 12) {
                    cell = 0;
                }
                if (cell == firstCell && firstCell != 11) {
                    cell++;
                }
                plateau[cell]++;
                x--;
            }
            plateau[firstCell] = 0;
            if (cell > 0) {
                for (cell = cell; cell > 0 && cell < 12; cell--) {
                    if (plateau[cell] == 2 || plateau[cell] == 3) {
                        nbGrainesNoirs += plateau[cell];
                        plateau[cell] = 0;
                    }
                }
            }
        }
    }

    public boolean finDePartie() {
        return (nbGrainesBlancs >= 25 || nbGrainesNoirs >= 25);
    }

    public PlateauJeu copy() {
        return new PlateauAwale(this.plateau);
    }

    public boolean coupValide(Joueur j, CoupJeu c) {
        CoupAwale ca = (CoupAwale)c;
        if (j.equals(joueurBlanc)) {
            int noir = 0;
            for (int i = 5; i < TAILLE; i++) {
                noir += plateau[i];
            }
            if (noir == 0 && plateau[ca.getCell() - 1] >= ca.getCell() - 1 - 5) {
                return true;
            }
        } else {
            int blanc = 0;
            for (int i = 0; i < TAILLE/2; i++) {
                blanc += plateau[i];
            }
            if (blanc == 0 && plateau[ca.getCell() - 1] >= ca.getCell() - 1 - 5) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String restr = "";
        for (int i = 0; i < TAILLE; i++) {
            restr += Integer.toString(plateau[i]);
        }
        restr = restr.substring(0, 5) + "\n" + restr.substring(7, 12);
        return restr;
    }

    public int nbGrainesBlancs() {
        return nbGrainesBlancs;
    }

    public int nbGrainesNoires() {
        return nbGrainesNoirs;
    }
}
