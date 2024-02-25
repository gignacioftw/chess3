package chess;

import java.util.ArrayList;

public class King extends Piece{
    public boolean canMove(String initial, String destination, PieceType type){
        char fileI = initial.charAt(0);
        int rankI = initial.charAt(1) - '0';
        char fileD = destination.charAt(0);
        int rankD = destination.charAt(1) - '0';
        
        if(fileD - fileI == 1 || rankD - rankI == 1 || fileD - fileI == -1 || rankD - rankI == -1){
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
