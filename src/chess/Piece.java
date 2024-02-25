package chess;

import java.util.ArrayList;

public abstract class Piece extends ReturnPiece{

    protected boolean canMove(String p, String m, PieceType type, boolean desthaspiece){
        final char fileP = p.charAt(0);
        final int rankP = p.charAt(1);
        final char fileM = m.charAt(0);
        final int rankM = m.charAt(1);
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

    protected void getPosition(){
        // return
    }

    //this is just here idk how im gonna do taking it maybe shoudnt even be here we can think about it
    protected void take(){

    }
}
