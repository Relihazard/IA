/**
 *
 */

package iia.jeux.alg;

import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class Minimax implements AlgoJeu {

    /**
     * La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 4;


    // -------------------------------------------
    // Attributs
    // -------------------------------------------

    /**
     * La profondeur de recherche utilisée pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

    /**
     * L'heuristique utilisée par l'algorithme
     */
    private Heuristique h;

    /**
     * Le joueur Min
     * (l'adversaire)
     */
    private Joueur joueurMin;

    /**
     * Le joueur Max
     * (celui dont l'algorithme de recherche adopte le point de vue)
     */
    private Joueur joueurMax;

    /**
     * Le nombre de noeuds développé par l'algorithme
     * (intéressant pour se faire une idée du nombre de noeuds développés)
     */
    private int nbnoeuds;

    /**
     * Le nombre de feuilles évaluées par l'algorithme
     */
    private int nbfeuilles;


    // -------------------------------------------
    // Constructeurs
    // -------------------------------------------
    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h, joueurMax, joueurMin, PROFMAXDEFAUT);
    }

    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }

    // -------------------------------------------
    // Méthodes de l'interface AlgoJeu
    // -------------------------------------------
    //faire une copie du plateau et jouer dessus
    public CoupJeu meilleurCoup(PlateauJeu p) {
        ArrayList<CoupJeu> coupsPossibles = p.coupsPossibles(joueurMax);
        CoupJeu meilleurCoup = coupsPossibles.get(0);
        int max = minMax(p, 0);
        for (int i = 0; i < coupsPossibles.size(); i++) {
            int newVal = minMax(p, 0);
            if (newVal > max) {
                meilleurCoup = coupsPossibles.get(i);
                max = newVal;
            }
        }
        return meilleurCoup;
    }

    // -------------------------------------------
    // Méthodes publiques
    // -------------------------------------------
    public String toString() {
        return "MiniMax(ProfMax=" + profMax + ")";
    }


    // -------------------------------------------
    // Méthodes internes
    // -------------------------------------------
    private int maxMin(PlateauJeu p, int profondeur) {
        profondeur++;
        if (p.coupsPossibles(joueurMax).size() == 0) {
            return Integer.MIN_VALUE;
        } else if (profondeur > profMax) {
            return h.eval(p, joueurMax);
        } else {
            PlateauJeu pCopy = p.copy();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < pCopy.coupsPossibles(joueurMax).size(); i++) {
                pCopy.joue(joueurMax, pCopy.coupsPossibles(joueurMax).get(i));
                max = Math.max(minMax(pCopy, profondeur), max);
            }
            return max;
        }
    }

    private int minMax(PlateauJeu p, int profondeur) {
        profondeur++;
        if (p.coupsPossibles(joueurMin).size() == 0) {
            return Integer.MAX_VALUE;
        } else if (profondeur > profMax) {
            return h.eval(p, joueurMin);
        } else {
            PlateauJeu pCopy = p.copy();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < pCopy.coupsPossibles(joueurMin).size(); i++) {
                pCopy.joue(joueurMin, pCopy.coupsPossibles(joueurMin).get(i));
                min = Math.min(maxMin(pCopy, profondeur), min);
            }
            return min;
        }
    }
}
