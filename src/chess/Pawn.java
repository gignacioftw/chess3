package chess;

import java.util.ArrayList;

public class Pawn extends Piece{
    public boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';
        
        switch(type){
            case WP:
                if (rankI == 2 && rankD - rankI > 0 && rankD - rankI <= 2 && fileD == fileI){
                    return true;
                }
                else if (rankI != 2 && rankD - rankI == 1 && fileD == fileI && !desthaspiece){
                    return true;
                }
                else if (desthaspiece && ((fileD - fileI == 1) || (fileD - fileI == -1)) && rankD - rankI == 1){
                    return true;
                }
                else {
                    return false;
                }
            case BP:
                if(rankI == 7 && rankD - rankI < 0 && rankD - rankI >= -2 && fileD == fileI){
                    return true;
                }
                else if(rankI != 7 && rankD - rankI == -1 && fileD == fileI && !desthaspiece){
                    return true;
                }
                else if (desthaspiece && ((fileD - fileI == 1) || (fileD - fileI == -1)) && rankD - rankI == -1){
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
