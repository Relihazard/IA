package iia.jeux.alg;

import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class NegaMax implements AlgoJeu {
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

    public NegaMax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h, joueurMax, joueurMin, PROFMAXDEFAUT);
    }

    public NegaMax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }

    public CoupJeu meilleurCoup(PlateauJeu p) {
        ArrayList<CoupJeu> coupsPossibles = p.coupsPossibles(joueurMax);
        CoupJeu meilleurCoup = coupsPossibles.get(0);
        int max = negaMax(p, 0);
        for (int i = 0; i < coupsPossibles.size(); i++) {
            int newVal = negaMax(p, 0);
            if (newVal > max) {
                meilleurCoup = coupsPossibles.get(i);
                max = newVal;
            }
        }
        return meilleurCoup;
    }

    public String toString() {
        return "MegaMax(ProfMax=" + profMax + ")";
    }

    private int negaMax(PlateauJeu p, int profondeur) {
        profondeur++;
        if (p.coupsPossibles(joueurMax).size() == 0)
            return Integer.MIN_VALUE;
        else if (profondeur > profMax)
            return h.eval(p, joueurMax);
        else {
            PlateauJeu pCopy = p.copy();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < pCopy.coupsPossibles(joueurMax).size(); i++) {
                pCopy.joue(joueurMax, p.coupsPossibles(joueurMax).get(i));
                max = Math.max(max, -negaMax(pCopy, profondeur));
            }
            return max;
        }
    }
}
