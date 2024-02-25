package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

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
		Board.move(p.piecesOnBoard, move);
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
	public static void move(ArrayList<ReturnPiece> p, String move){
		String[] list = parseMove(move);
		String firstSquare = list[0];
		String secondSquare = list[1];
		PieceType type = null;
		
		if(canMovePiece(move, p)){
			for(ReturnPiece piece : p){
				String s = piece.toString();
				String[] sl = s.split(":");
				if(sl[0].equalsIgnoreCase(list[0])){
					type = piece.pieceType;
				}
			}
			new Pawn().move(firstSquare, secondSquare, p);
		}
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

		
		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(list[0])){
				type = piece.pieceType;
			}
		}

		switch(type){
			case WP, BP:
				return new Pawn().canMove(firstSquare, secondSquare, type);
			case WR, BR:
				return new Rook().canMove(firstSquare, secondSquare, type);
			case WB, BB:
				return new Bishop().canMove(firstSquare, secondSquare, type);
			case WN, BN:
				return new Knight().canMove(firstSquare, secondSquare, type);
			case WQ, BQ:
				return new Queen().canMove(firstSquare, secondSquare, type);
			case WK, BK:
				return new King().canMove(firstSquare, secondSquare, type);
			default:
				return false;
		}
	}
}

