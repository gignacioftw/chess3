package chess;

import java.util.ArrayList;

public class Knight extends Piece{
    public boolean canMove(String p, String m, PieceType type){
        char fileP = p.charAt(0);
        int rankP = p.charAt(1) - '0';
        char fileM = m.charAt(0);
        int rankM = m.charAt(1) - '0';
        
        if(rankM - rankP == 2 && fileM - fileP == 1 || rankM - rankP == 2 && fileM - fileP == -1 || rankM - rankP == -2 && fileM - fileP == 1 
            || rankM - rankP == -2 && fileM - fileP == -1|| rankM - rankP == 1 && fileM - fileP == 2 || rankM - rankP == 1 && fileM - fileP == -2 
                || rankM - rankP == -1 && fileM - fileP == 2 || rankM - rankP == -1 && fileM - fileP == -2){
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
