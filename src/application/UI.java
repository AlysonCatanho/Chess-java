package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	//codigos das cores o console
	
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	//Scanner instanciado no programa principal e recebe ele aqui como argumento
	//para fazer a leitura 
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			//a letra que representa a coluna vem primeiro (b7, d5, a8)
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	// logica para aparecer na tela o tabuleiro
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			// mostrar na tela os numeros das linhas (de cima pra baixo 8, 7, 6 etc)
			System.out.print((8 - i) + " ");
			// pieces.length mesmo ja que a matriz é quadrada
			for (int j = 0; j < pieces.length; j++) {
				// agora imprimir a peça
				printPiece(pieces[i][j]);
			}
			// para pular uma linha
			System.out.println();
		}
		// linha com as letras de cada coluna
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece) {
		//antes apenas imprimia a peça, agora imprime com cor 
		if (piece == null) {
            System.out.print("-");
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
            	//usar amarelo ja que o console tem fundo preto 
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
		
		/*
		// se não tiver peça aparece um - e se tiver peça mostra a peça
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		// um espaço em branco para separar cada posição entre as peças
		System.out.print(" ");*/
	}
}
