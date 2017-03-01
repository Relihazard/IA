package iia.jeux.modele;

import iia.jeux.modele.joueur.Joueur;

import java.util.ArrayList;

public interface PlateauJeu {

    /** renvoie la liste des coups possibles
     *
     * @param j	le joueur dont c'est au tour de jouer
     * @return liste des coups possibles pour le joueur j
     */
    ArrayList<CoupJeu> coupsPossibles(Joueur j);

    /** joue un coup sur le plateau
     *
     * @param j	Le joueur qui joue
     * @param c Le coup joué par le joueur
     */
    void joue(Joueur j, CoupJeu c);

    /** indique si la partie est terminée
     * 
     * @return vrai si la partie est terminée
     */
    boolean finDePartie();

    //   public abstract CoupJeu parseCoup(String s);
    /** duplique le plateau courant
     * 
     */
    PlateauJeu copy();

    /** indique si un coup est possible pour un joueur sur le plateau courant
     *
     * @param j	Le joueur qui pourrait jouer ce coup
     * @param c Le coup envisagé par le joueur
     */
    boolean coupValide(Joueur j, CoupJeu c);
}
