package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	//position inicia com null
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//apagar set para não permitir que o tabuleiro seja alterado
	//protected para não deixar o tabuleiro acessivel pela camada de xadrez
	protected Board getBoard() {
		return board;
	}
	
}
