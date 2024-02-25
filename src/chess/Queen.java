package chess;

import java.util.ArrayList;

public class Queen extends Piece{

    Queen(){}
    

    Queen(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }

    public boolean canMove(String initial, String destination, PieceType type){
        char fileI = initial.charAt(0);
        int rankI = initial.charAt(1) - '0';
        char fileD = destination.charAt(0);
        int rankD = destination.charAt(1) - '0';
        
        
        return true;
    }
    
    public void move(String p, String m, ArrayList<ReturnPiece> l){
    
    }

    public boolean canTake(){
        return false;
    }

    public void take(){

    }
}
