package échiquier;

import appli.Coord;
import piece.Piece;
import piece.Roi;
import piece.Tour;
import piece.couleurPiece;

import java.util.ArrayList;

public class Plateau {
    private final Case[][] echiquier;
    private final int HAUTEUR = 8, LONGUEUR = 8;
    private final ArrayList<Piece> listePieces;

    public Plateau(){
        echiquier = new Case[LONGUEUR][HAUTEUR];
        for(int x = 0; x < this.LONGUEUR; x++){
            for(int y = 0; y <this.HAUTEUR; y++){
                echiquier[x][y] = new Case();
            }
        }
        listePieces=new ArrayList<>();
    }

    public void déplacer(String coup){
        if (!coupValableSurPlateau(coup)) {
            System.out.println("pine ta grand mere");
        }
        char x = coup.charAt(0), x2 = coup.charAt(2);            /*b7b8*/
        int y = Integer.parseInt(String.valueOf(coup.charAt(1))),y2 = Integer.parseInt(String.valueOf(coup.charAt(3)));
        Coord coordIni,coordFin;

        coordIni = getCoord(x, y);
        coordFin = getCoord(x2, y2);


        if (echiquier[coordIni.getX()][coordIni.getY()].getPieceActuelle().peutJouer(coordFin)){
            System.out.println("YOUPI");
            echiquier[coordIni.getX()][coordIni.getY()].getPieceActuelle().changeCoord(coordFin);
            echiquier[(coordFin.getX())][coordFin.getY()].rajouterPiece(echiquier[coordIni.getX()][coordIni.getY()].getPieceActuelle());
            echiquier[coordIni.getX()][coordIni.getY()].retirerPiece();
            System.out.println("Normalement y'a un changement ici");
        }
        else System.out.println("nike ta soeur tu peux pas faire ");
    }

    private boolean coupValableSurPlateau(String coup){
        int intoInt = Integer.parseInt(String.valueOf(coup.charAt(1)));
        int intoInt2 = Integer.parseInt(String.valueOf(coup.charAt(3)));

        if (coup.length()!=4)
            return false;
        if(coup.charAt(0)<'a'||coup.charAt(2)<'a' || coup.charAt(0)>'h'|| coup.charAt(2)>'h')
            return false;

        if(intoInt <1 || intoInt >7 || intoInt2<1 || intoInt2>7)
            return false;

        return true;
    }

    private Coord getCoord(char x2, int y2) {
        Coord coordIni;
        switch(x2){
            case 'a' : {
                coordIni = new Coord(8 - y2, 0);
                break;
            }
            case 'b' : {
                coordIni = new Coord(8 - y2, 1);
                break;
            }
            case 'c' : {
                coordIni = new Coord(8 - y2, 2);
                break;
            }
            case 'd' : {
                coordIni = new Coord(8 - y2, 3);
                break;
            }
            case 'e' : {
                coordIni = new Coord(8 - y2, 4);
                break;
            }
            case 'f' : {
                coordIni = new Coord(8 - y2, 5);
                break;
            }
            case 'g' : {
                coordIni = new Coord(8 - y2, 6);
                break;
            }
            case 'h' : {
                coordIni = new Coord(8 - y2, 7);
                break;
            }
            default : coordIni = new Coord(0, 0);// TODO: DINGUERIE A CHANGER
        };
        return coordIni;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     a     b     c     d     e     f     g     h \n");
        for (int cmpHauteur = 0,cmp = 8; cmpHauteur < HAUTEUR; cmpHauteur++,cmp--) {
            sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
            sb.append(cmp).append(" | ");
            for (int cmpLongueur = 0; cmpLongueur < LONGUEUR; cmpLongueur++) {
                sb.append(" ").append(echiquier[cmpHauteur][cmpLongueur]).append("  | ");
            }
            sb.append(cmp).append("\n");
        }
        sb.append("    ---   ---   ---   ---   ---   ---   ---   ---\n");
        sb.append("     a     b     c     d     e     f     g     h \n");
        return sb.toString();
    }

    public void initialiserEchiquier(){
        Tour t1= new Tour(couleurPiece.NOIR ,0,0);
        Tour t2 = new Tour(couleurPiece.BLANC, 7,0);
        Roi r1 = new Roi(couleurPiece.BLANC,7,4);
        Roi r2 = new Roi(couleurPiece.NOIR,0,4);
        listePieces.add(t1);
        listePieces.add(t2);
        listePieces.add(r1);
        listePieces.add(r2);

        for(Piece p : listePieces)
            echiquier[p.getPosX()][p.getPosY()].rajouterPiece(p);
    }
}
