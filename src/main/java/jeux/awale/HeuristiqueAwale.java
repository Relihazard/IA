package jeux.awale;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class HeuristiqueAwale {

    public static Heuristique hblanc = new Heuristique(){

        public int eval(PlateauJeu p, Joueur j) {
            return ((PlateauAwale)p).nbGrainesBlancs() - ((PlateauAwale)p).nbGrainesNoires();
        }
    };

    public static  Heuristique hnoir = new Heuristique() {

        public int eval(PlateauJeu p, Joueur j) {
            return ((PlateauAwale) p).nbGrainesNoires() - ((PlateauAwale) p).nbGrainesBlancs();
        }
    };
}
