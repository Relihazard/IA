package jeux.awale;

import iia.jeux.modele.CoupJeu;

public class CoupAwale implements CoupJeu {

	private int cell;
	
	public CoupAwale(int c) {
		cell = c;
	}

	public int getCell() { return cell; }

	@Override
	public String toString() { return "("+cell+")"; }
}
