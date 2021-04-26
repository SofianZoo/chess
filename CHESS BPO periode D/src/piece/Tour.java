package piece;

import échiquier.Case;
import échiquier.Coord;

public class Tour extends Piece{

    public Tour(CouleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (!(getLigne() == c.getLigne() && getColonne() !=c.getColonne() || getColonne() == c.getColonne() && getLigne() != c.getLigne()))
            return false;

        if (getLigne() != c.getLigne() && getColonne() == c.getColonne()){
            if(getLigne() < c.getLigne()) {
                for (int cmp = c.getLigne() - 1; cmp > getLigne(); cmp--) {
                    if (echiquier[cmp][getColonne()].estOccupé()) {
                        System.out.println("piece sur le passage de haut en bas");
                        return false;
                    }
                }
            }

            else if(getLigne() > c.getLigne()) {
                for (int cmp = c.getLigne() + 1; cmp < getLigne(); cmp++) {
                    if (echiquier[cmp][getColonne()].estOccupé()) {
                        System.out.println("piece sur le passage de bas en haut");
                        return false;
                    }
                }
            }
        }

        if (getLigne() == c.getLigne() && getColonne() != c.getColonne()){
            if(getColonne() < c.getColonne()) {
                for (int cmp = c.getColonne() - 1; cmp > getColonne(); cmp--) {
                    if (echiquier[getLigne()][cmp].estOccupé()) {
                        System.out.println("piece sur le passage de gauche vers droite");
                        return false;
                    }
                }
            }

            if(getColonne() > c.getColonne()) {
                for (int cmp = c.getColonne() + 1; cmp < getColonne(); cmp++) {
                    if (echiquier[getLigne()][cmp].estOccupé()) {
                        System.out.println("piece sur le passage de droite vers gauche");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
