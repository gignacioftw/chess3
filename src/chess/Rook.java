package chess;

import java.util.ArrayList;

public class Rook extends Piece{
    
    Rook(){}
    

    Rook(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }

    public boolean canMove(String initial, String destination, PieceType type){
        char fileI = initial.charAt(0);
        int rankI = initial.charAt(1) - '0';
        char fileD = destination.charAt(0);
        int rankD = destination.charAt(1) - '0';
        
        if(fileI == fileD || rankI == rankD){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void move(String p, String m, ArrayList<ReturnPiece> l){
        super.move(p, m, l);
    }

    public boolean canTake(){
        return false;
    }

    public void take(){

    }
}
