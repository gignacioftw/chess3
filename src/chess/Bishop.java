package chess;

import java.util.ArrayList;
import java.math.*;

public class Bishop extends Piece{

    Bishop(){}
    

    Bishop(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }

    public boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';

        // if(rankD - rankI >= 1 && fileD - fileI >= 1 || rankD - rankI <= -1 && fileD - fileI <= -1
        //     || rankD - rankI >= 1 && fileD - fileI <= 1 || rankD - rankI <= -1 && fileD - fileI >= 1){                    
        //             return true;
        // }
        if( Math.abs(fileI-fileD) == Math.abs(rankI-rankD) ){
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
