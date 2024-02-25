# chess2
 - changed ReturnPlay playmethod check if Board.move is true or false, if false, change ReturnPlay's message
 - Chess.play and Board.move changed to now return a ReturnPlay.Message like ILLEGALMOVE, CHECKMATE, or null if can move the piece