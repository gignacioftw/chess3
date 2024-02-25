package chess;

import java.util.ArrayList;

public abstract class Piece extends ReturnPiece{

    protected boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';

        if (rankI > 8 || rankI < 1 || rankD > 8 || rankD < 1){
            return false;
        }

        return false;
    }

    //moves the piece
    protected void move(String p, String m, ArrayList<ReturnPiece> l){
        for(ReturnPiece piece : l){
            String s = piece.toString();
            String[] sl = s.split(":");
            int x = m.charAt(0);
            x -= 97;
            if(sl[0].equalsIgnoreCase(p)){
                piece.pieceRank = m.charAt(1) - '0';
                piece.pieceFile = PieceFile.values()[x];
                break;
            }
        }
    }

    //this is just here idk how im gonna do taking it maybe shoudnt even be here we can think about it
    protected void take(){

    }
}
