package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public  ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	//o programa principal irá enxergar apenas ChessPiece e não Piece do pacote boardgame
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		//percorrer as peças do board e para cada peça fazer um downcast para ChessPiece
		for (int i=0; i< board.getRows(); i++) {
			
			for (int j=0;j < board.getColumns();j++) {
				  mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//primeiro converter as posições para posição de matriz (letra, numero) para (numero, numero)
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		//validar se na posição havia uma peça
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//retirar a peça que está na posição de origem
		Piece p = board.removePiece(source);
		//remover uma possivel peça da posição de destino
		Piece capturedPiece = board.removePiece(target);
		//agora colocar a peça de origem na posição de destino
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		//o proprio thereIsAPiece pode dar uma exception de BoardException
		//e a minha validação lança uma ChessException mas eu posso considerar que uma
		//exception de xadrez tambem é uma exception de tabuleiro 
		//então posso atualizar ChessException de RuntimeException para BoardException
		//e ai quando eu pegar uma ChessException tb pego uma BoardException
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		//colocando uma peça ja instanciando uma chessposition com column e row 
		//convertendo para a posição de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	//responsavel por iniciar a partida de xadrez, colocando as peças no tabuleiro
	// agora ao passa uma peça ja consigo passar com a posição de xadrez 
	// por exemplo b6 e e8 inves de (2, 1) e (0, 4) 
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
		/*board.placePiece(new Rook(board, Color.WHITE), new Position (2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position (0, 4));*/
	}
}
