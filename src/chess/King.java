package chess;

import java.util.ArrayList;

public class King extends Piece{

    private boolean moved = false;
    King(){}
    

    King(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }
    
    public boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';
        
        if(!moved && fileD - fileI <= 2 && rankD - rankI <= 2 && fileD - fileI >= -2 && rankD - rankI >= -2){
            moved = true;
            return true;
        }
        else{
            return false;
        }            
    }

    public boolean getMoved(){
        return this.moved;
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
