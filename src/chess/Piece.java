package chess;

import java.util.ArrayList;

public abstract class Piece extends ReturnPiece{

    Piece(){}

    int fileI;
    int rankI;
    int fileD;
    int rankD;

        // if (rankI > 8 || rankI < 1 || rankD > 8 || rankD < 1){
        //     return false;
        // }
    

    Piece(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }

    protected abstract boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece);
        

        // return true;
    

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
