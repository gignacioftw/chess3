package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {
	
	enum Player { white, black }
	public static ReturnPlay p;
	public static int i = 0;
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */

	//the board is already created
	//just moves piece
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		//make = new ArrayList<ReturnPiece>();
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		//Chess.p.message = null;
		
		ReturnPlay.Message m = Board.move(p.piecesOnBoard, move);

		if (m == null){
			return p;
		}
		switch (m) {
			case ILLEGAL_MOVE:
				p.message = ReturnPlay.Message.ILLEGAL_MOVE;
				break;
			case RESIGN_BLACK_WINS:
				p.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
				break;
			case RESIGN_WHITE_WINS:
				p.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
				break;
			case CHECK:
				p.message = ReturnPlay.Message.CHECK;
				break;
			case CHECKMATE_BLACK_WINS:
				p.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
				break;
			case CHECKMATE_WHITE_WINS:
				p.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
				break;
			case DRAW:
				p.message = ReturnPlay.Message.DRAW;
				break;
			case STALEMATE:
				p.message = ReturnPlay.Message.STALEMATE;
				break;
			default:
				p.message = null;
				break;
		}

		// if (Board.move(p.piecesOnBoard, move) == ReturnPlay.Message.ILLEGAL_MOVE){
		// 	p.message = ReturnPlay.Message.ILLEGAL_MOVE;
		// }
		// else {
		// 	p.message = null;
		// }
		
		return p;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	//makes the return play in the chess field
	//and fills the board
	public static void start() {
		/* FILL IN THIS METHOD */
		//makePlay();
		Chess.p = new ReturnPlay();
		p.piecesOnBoard = Board.createBoard();
		
	}
}

class Board {

	public static int turn = 0;

	//this method returns a "board" aka the arraylist of return pieces
	//calls all the addPiece methods
	public static ArrayList<ReturnPiece> createBoard(){
		ArrayList<ReturnPiece> p = new ArrayList<>();
		addPawn(p);
		addQueen(p);
		addRook(p);
		addKnight(p);
		addBishop(p);
		addKing(p);
		
		return p;
	}

	public static String[] parseMove(String move){
		String[] moveList = move.split(" ");

		return moveList;
	}

	/*public static String isolateStrings(String s, ){
		s.split(":");
	}*/
	//this should call both canMovePiece and canMoveBoard
	//like a if(canMovePiece) then if(canMoveBoard), then the piece moves.
	public static ReturnPlay.Message move(ArrayList<ReturnPiece> p, String move){
		String[] list = parseMove(move);
		String firstSquare = list[0];
		String secondSquare = null;
		if (list.length >= 2){
			secondSquare = list[1];
		}
		String specifier = null;
		String draw = null;
		if (list.length == 3){
			if (list[2].length() == 1){
				specifier = list[2];
			}
			else {
				draw = list[2];
			}
		}
		if (firstSquare.equalsIgnoreCase("resign") && turn % 2 == 1){
			return Message.RESIGN_WHITE_WINS; //ADD WHITE BLACK ONCE ADD TURNS
		}
		if (firstSquare.equalsIgnoreCase("resign") && turn % 2 == 0){
			return Message.RESIGN_BLACK_WINS; //ADD WHITE BLACK ONCE ADD TURNS
		}

		ReturnPiece initialPiece = null;
		ReturnPiece takenpiece = null;
		
		if(canMovePiece(move, p)){
			for(ReturnPiece piece : p){
				String s = piece.toString();
				String[] sl = s.split(":");
				if(sl[0].equalsIgnoreCase(firstSquare)){
					initialPiece = piece;
					break;
				}
			}

			for (ReturnPiece piece : p){
				String s = piece.toString();
				String[] sl = s.split(":");
				if (sl[0].equalsIgnoreCase(secondSquare) && !sl[1].equalsIgnoreCase(firstSquare)){
					takenpiece = piece;
					break;
				}
			}


			switch (initialPiece.pieceType) {
				case WP:
					if (Character.getNumericValue(secondSquare.charAt(1)) == 8){
						
						p.remove(initialPiece);
							if (specifier.equalsIgnoreCase("N")){
								Knight n = new Knight(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WN);
								p.add(n);
							}
							else if (specifier.equalsIgnoreCase("R")){
								Rook r = new Rook(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WR);
								p.add(r);
							}
							else if (specifier.equalsIgnoreCase("B")){
								Bishop b = new Bishop(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WB);
								p.add(b);
							}
							else if (specifier.equalsIgnoreCase("Q")){
								Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WQ);
								p.add(q);
							}
							else {
								Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WQ);
								p.add(q);
							}
						
						
					}
					else {
						new Pawn().move(firstSquare, secondSquare, p);
					}
					break;
				case BP:
					if (Character.getNumericValue(secondSquare.charAt(1)) == 1){
							
						p.remove(initialPiece);

						if (specifier.equalsIgnoreCase("N")){
							Knight n = new Knight(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BN);
							p.add(n);
						}
						else if (specifier.equalsIgnoreCase("R")){
							Rook r = new Rook(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BR);
							p.add(r);
						}
						else if (specifier.equalsIgnoreCase("B")){
							Bishop b = new Bishop(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BB);
							p.add(b);
						}
						else if (specifier.equalsIgnoreCase("Q")){
							Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BQ);
							p.add(q);
						}
						else {
							Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BQ);
							p.add(q);
						}
					}
					else {
						new Pawn().move(firstSquare, secondSquare, p);
					}
					break;
				case WR, BR:
					new Rook().move(firstSquare, secondSquare, p);
					break;
				case WN, BN:
					new Knight().move(firstSquare, secondSquare, p);
					break;
				case WB, BB:
					new Bishop().move(firstSquare, secondSquare, p);
					break;
				case WK: // make sure that rook is unmoved also
					if (!hasTile("f1", p) && !hasTile("g1", p) && secondSquare.equalsIgnoreCase("g1")){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("h1", "f1", p);
					}
					else if (!hasTile("d1", p) && !hasTile("c1", p) && !hasTile("b1", p) && secondSquare.equalsIgnoreCase("c1")){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("a1", "d1", p);
					}
					else {
						new King().move(firstSquare, secondSquare, p);
					}

					break;
				case BK:
					if (!hasTile("f8", p) && !hasTile("g8", p) && secondSquare.equalsIgnoreCase("g8")){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("h8", "f8", p);
					}
					else if (!hasTile("d8", p) && !hasTile("c8", p) && !hasTile("b8", p) && secondSquare.equalsIgnoreCase("c8")){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("a8", "d8", p);
					}
					else {
						new King().move(firstSquare, secondSquare, p);
					}

					break;
				case WQ, BQ:
					new Queen().move(firstSquare, secondSquare, p);
				default:
					break;
			}
			// if (!traversable){
			// 	return ReturnPlay.Message.ILLEGAL_MOVE;
			// }
			if (takenpiece != null){
				p.remove(takenpiece);
			}

			
			if (draw != null){
				if (draw.equalsIgnoreCase("draw?")){
					return ReturnPlay.Message.DRAW;
				}

			}
			turn++;
			return null;
		}
		else {
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}
	}

	public static ReturnPiece getPiece(String tile, ArrayList<ReturnPiece> p){
		ReturnPiece z = null;
		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(tile)){
				z = piece;
			}
		}
		return z;
	}

	public static boolean hasTile(String tile, ArrayList<ReturnPiece> p){

		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(tile)){
				return true;
			}
		}
		return false;
	}
	
	//i intend this method to detect if there is something in its path
	//lowkey need to figure out how to do this
	//maybe a getPath method in the diff pieces to calcualte the places in between the piece and the desired spot
	//ill get back to this
	public static boolean canMoveBoard(String move){

		return false;
	}

	/*public static void clearBoard(){

	}*/

	public static void addPawn(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 2; j++){
				Pawn pawn = new Pawn();
				pawn.pieceFile = PieceFile.values()[i];
				if(j == 0){
					pawn.pieceRank = 2;
					pawn.pieceType = PieceType.values()[j];
				}
				else{
					pawn.pieceRank = 7;
					pawn.pieceType = PieceType.values()[6];
				}
				p.add(pawn);
			}
		}
	}

	public static void addQueen(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Queen q = new Queen();
			q.pieceFile = PieceFile.values()[3];
			if(i == 0){
				q.pieceRank = 1;
				q.pieceType = PieceType.values()[4];
			}
			else{
				q.pieceRank = 8;
				q.pieceType = PieceType.values()[11];
			}
			p.add(q);
		}
	}
	
	public static void addRook(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Rook r = new Rook();
			Rook r2 = new Rook();
			r.pieceFile = PieceFile.values()[0];
			r2.pieceFile = PieceFile.values()[7];
			if(i == 0){
				r.pieceRank = 1;
				r.pieceType = PieceType.values()[1];
				r2.pieceRank = 1;
				r2.pieceType = PieceType.values()[1];
			}
			else{
				r.pieceRank = 8;
				r.pieceType = PieceType.values()[7];
				r2.pieceRank = 8;
				r2.pieceType = PieceType.values()[7];
			}
			p.add(r);
			p.add(r2);
		}
	}

	public static void addBishop(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Bishop  b= new Bishop();
			Bishop b2 = new Bishop();
			b.pieceFile = PieceFile.values()[2];
			b2.pieceFile = PieceFile.values()[5];
			if(i == 0){
				b.pieceRank = 1;
				b.pieceType = PieceType.values()[3];
				b2.pieceRank = 1;
				b2.pieceType = PieceType.values()[3];
			}
			else{
				b.pieceRank = 8;
				b.pieceType = PieceType.values()[9];
				b2.pieceRank = 8;
				b2.pieceType = PieceType.values()[9];
			}
			p.add(b);
			p.add(b2);
		}
	}

	public static void addKing(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			King k = new King();
			k.pieceFile = PieceFile.values()[4];
			if(i == 0){
				k.pieceRank = 1;
				k.pieceType = PieceType.values()[5];
			}
			else{
				k.pieceRank = 8;
				k.pieceType = PieceType.values()[10];
			}
			p.add(k);
		}
	}

	public static void addKnight(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Knight k = new Knight();
			Knight k2 = new Knight();
			k.pieceFile = PieceFile.values()[1];
			k2.pieceFile = PieceFile.values()[6];
			if(i == 0){
				k.pieceRank = 1;
				k.pieceType = PieceType.values()[2];
				k2.pieceRank = 1;
				k2.pieceType = PieceType.values()[2];
			}
			else{
				k.pieceRank = 8;
				k.pieceType = PieceType.values()[8];
				k2.pieceRank = 8;
				k2.pieceType = PieceType.values()[8];
			}
			p.add(k);
			p.add(k2);
		}
	}

	//for every type of piece it calls the canMove for it 
	public static boolean canMovePiece(String move, ArrayList<ReturnPiece> p){
		String[] list = parseMove(move);
		String firstSquare = list[0];
		String secondSquare = list[1];
		PieceType type = null;
		PieceType type2 = null;

		
		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(firstSquare)){
				type = piece.pieceType;
				break;
			}
		}

		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(secondSquare)){
				type2 = piece.pieceType;
				break;
			}
		}

		boolean desthaspiece = false;
		if (type2 != null){
			desthaspiece = true;
			if (type.name().charAt(0) == type2.name().charAt(0)){
				return false;
			}
		}

		if (type == null){
			return false;
		}

		if (type.name().charAt(0) == 'B' && turn % 2 == 0){
			return false;
		}
		if (type.name().charAt(0) == 'W' && turn % 2 == 1){
			return false;
		}


		switch(type){
			case WP, BP:
				return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece);
			case WR, BR:
				boolean rtraversable = true;

				String rdiag = firstSquare;
				char ra = rdiag.charAt(0);
				char rb = rdiag.charAt(1);
				String rdiag2 = secondSquare;
				char ra2 = rdiag2.charAt(0);
				char rb2 = rdiag2.charAt(1);
				if (ra2 == ra && rb2 - rb > 0){
					rb++;
				}
				else if (ra2 == ra && rb2 - rb < 0){
					rb--;
				}
				else if (ra2 > ra && rb2 == rb){
					ra++;
				}
				else if (ra2 < ra && rb2 == rb){
					ra--;
				}
				else {
					return new Rook().canMove(firstSquare, secondSquare, type, desthaspiece);
				}
				
				rdiag = "" + ra + rb;
				while(rtraversable && !rdiag.equalsIgnoreCase(secondSquare)){
					ReturnPiece z = getPiece(rdiag, p);
					if (z == null){
						rtraversable = true;
						if (ra2 == ra && rb2 - rb > 0){
							rb++;
						}
						else if (ra2 == ra && rb2 - rb < 0){
							rb--;
						}
						else if (ra2 > ra && rb2 == rb){
							ra++;
						}
						else if (ra2 < ra && rb2 == rb){
							ra--;
						}
						rdiag = "" + ra + rb;
					}else {
						rtraversable = false;
					}
				}
					
				if (rtraversable){
					return new Rook().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}

			case WB, BB:
				boolean btraversable = true;

				String bdiag = firstSquare;
				char ba = bdiag.charAt(0);
				char bb = bdiag.charAt(1);
				String bdiag2 = secondSquare;
				char ba2 = bdiag2.charAt(0);
				char bb2 = bdiag2.charAt(1);
				if (ba2 - ba > 0 && bb2 - bb > 0){
					ba++;
					bb++;
				}
				else if (ba2 - ba > 0 && bb2 - bb < 0){
					ba++;
					bb--;
				}
				else if (ba2 - ba < 0 && bb2 - bb > 0){
					ba--;
					bb++;
				}
				else {
					ba--;
					bb--;
				}
				
				bdiag = "" + ba + bb;
				while(btraversable && !bdiag.equalsIgnoreCase(secondSquare)){
					ReturnPiece z = getPiece(bdiag, p);
					if (z == null){
						btraversable = true;
						if (ba2 - ba > 0 && bb2 - bb > 0){
							ba++;
							bb++;
						}
						else if (ba2 - ba > 0 && bb2 - bb < 0){
							ba++;
							bb--;
						}
						else if (ba2 - ba < 0 && bb2 - bb > 0){
							ba--;
							bb++;
						}
						else {
							ba--;
							bb--;
						}
						bdiag = "" + ba + bb;
					}else {
						btraversable = false;
					}
				}
					
				if (btraversable){
					return new Bishop().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}
	

			case WN, BN:
				return new Knight().canMove(firstSquare, secondSquare, type, desthaspiece);
			case WQ, BQ:
				boolean qtraversable = true;

				String qdiag = firstSquare;
				char qa = qdiag.charAt(0);
				char qb = qdiag.charAt(1);
				String qdiag2 = secondSquare;
				char qa2 = qdiag2.charAt(0);
				char qb2 = qdiag2.charAt(1);
				if (qa2 - qa > 0 && qb2 - qb > 0){
					qa++;
					qb++;
				}
				else if (qa2 - qa > 0 && qb2 - qb < 0){
					qa++;
					qb--;
				}
				else if (qa2 - qa < 0 && qb2 - qb > 0){
					qa--;
					qb++;
				}
				else if (qa2 - qa < 0 && qb2 - qb < 0){
					qa--;
					qb--;
				}
				else if (qa2 == qa && qb2 - qb > 0){
					qb++;
				}
				else if (qa2 == qa && qb2 - qb < 0){
					qb--;
				}
				else if (qa2 > qa && qb2 == qb){
					qa++;
				}
				else if (qa2 < qa && qb2 == qb){
					qa--;
				}
				else {
					return new Queen().canMove(firstSquare, secondSquare, type, desthaspiece);
				}
				
				qdiag = "" + qa + qb;
				while(qtraversable && !qdiag.equalsIgnoreCase(secondSquare)){
					ReturnPiece z = getPiece(qdiag, p);
					if (z == null){
						btraversable = true;
						if (qa2 - qa > 0 && qb2 - qb > 0){
							qa++;
							qb++;
						}
						else if (qa2 - qa > 0 && qb2 - qb < 0){
							qa++;
							qb--;
						}
						else if (qa2 - qa < 0 && qb2 - qb > 0){
							qa--;
							qb++;
						}
						else if (qa2 - qa < 0 && qb2 - qb < 0){
							qa--;
							qb--;
						}
						else if (qa2 == qa && qb2 - qb > 0){
							qb++;
						}
						else if (qa2 == qa && qb2 - qb < 0){
							qb--;
						}
						else if (qa2 > qa && qb2 == qb){
							qa++;
						}
						else if (qa2 < qa && qb2 == qb){
							qa--;
						}
						qdiag = "" + qa + qb;
					}else {
						qtraversable = false;
					}
				}
					
				if (qtraversable){
					return new Queen().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}
			case WK, BK:
				return new King().canMove(firstSquare, secondSquare, type, desthaspiece);
			default:
				return false;
		}
	}
}

