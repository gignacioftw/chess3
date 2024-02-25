package chess;

import java.util.ArrayList;

public class Queen extends Piece{
    public boolean canMove(String p, String m, PieceType type){
        char fileP = p.charAt(0);
        int rankP = p.charAt(1) - '0';
        char fileM = m.charAt(0);
        int rankM = m.charAt(1) - '0';
        
        
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
