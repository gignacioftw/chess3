package chess;

import java.util.ArrayList;

public class Pawn extends Piece{
    public boolean canMove(String p, String m, PieceType type){
        char fileP = p.charAt(0);
        int rankP = p.charAt(1) - '0';
        char fileM = m.charAt(0);
        int rankM = m.charAt(1) - '0';
        
        switch(type){
            case WP:
                if(rankP == 2 && rankM - rankP > 0 && rankM - rankP <= 2 && fileM == fileP){
                    return true;
                }
                else if(rankP != 2 && rankM - rankP == 1 && fileM == fileP){
                    return true;
                }
                else{
                    return false;
                }
            case BP:
                if(rankP == 7 && rankM - rankP < 0 && rankM - rankP >= -2 && fileM == fileP){
                    return true;
                }
                else if(rankP != 7 && rankM - rankP == -1 && fileM == fileP){
                    return true;
                }
                else{
                    return false;
                }
            default:
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
