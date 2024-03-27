package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		//não faz sentido criar um tabuleiro de 0 linhas ou 0 colunas
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	//uma vez criado o tabuleiro, não faz sentido permitir mudar a quantidade de linhas e colunas
	//por isso vou tirar o setRows e setColumns

	public int getColumns() {
		return columns;
	}
	
	//retorna a peça dada uma linha e uma coluna
	public Piece piece (int row, int column) {
		//se a posição não existir lançar uma exception com a msg 
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	//sobrecarga do metodo recebendo uma position
	public Piece piece(Position position) {
		//mesmo esquema dali de cima 
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//atribuindo uma peça em uma posição do tabuleiro instanciado no construtor
	public void placePiece(Piece piece, Position position) {
		//testar se há uma peça nesta posição, se houver uma peça na posição não posso colocar outra peça
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//mais facil de testar pela linha e coluna 
	private boolean positionExists(int row, int column) {
		//condição para a posição existir 
		return row >=0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		//reaproveitando o metodo de cima 
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		//antes de testar se tem uma peça, ele ja testa se a posição existe
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		//se a peça for diferente de null é pq há uma peça naquele lugar e não um espaço vazio
		return piece(position) != null; 
	}
}
