package Test;

import java.util.Random;

import org.junit.Test;

import game.Board;
import game.Piece;
import game.Tile;
import gui.TetrisGUI;

public class Tests {
	
	private static final TetrisGUI gui = new TetrisGUI();

	@Test
	public void testRowClear() {
		Board board = new Board(gui);
		board.setRow(19);
		board.setRow(18);
		board.setTile(17, 0, Tile.BLUE);
		board.setTile(20, 0, Tile.BLUE);
		System.out.println(board.genBoardStr());
		board.checkForRowClear();
		System.out.println(board.genBoardStr());
		board.clearEmptyRows();
		System.out.println(board.genBoardStr());
	}
	@Test
	public void testIRotateRight() {
		Board board = new Board(gui);
		Random rand = new Random(9L);
		Piece piece = new Piece(rand, board);
		piece.setTiles();
		System.out.println(board.genBoardStr());
		piece.updateAllTilePos();
		piece.updateAllTilePos();
		piece.updateAllTilePos();
		System.out.println(board.genBoardStr());
		piece.rotateRight();
		System.out.println(board.genBoardStr());
		piece.rotateRight();
		System.out.println(board.genBoardStr());
		piece.rotateRight();
		System.out.println(board.genBoardStr());
		piece.rotateRight();
		System.out.println(board.genBoardStr());
	}
	@Test
	public void testJRotateRight() {
		Board board = new Board(gui);
		Random rand = new Random(8L);
		Piece piece = new Piece(rand, board);
		piece.setTiles();
		System.out.println(board.genBoardStr());
		piece.updateAllTilePos();
		piece.updateAllTilePos();
		piece.updateAllTilePos();
		System.out.println(board.genBoardStr());
		piece.rotateRight();
		
	}
	
	@Test
	public void setPieceDown() {
		Board board = new Board(gui);
		Random rand = new Random(8L);
		Piece piece = new Piece(rand, board);
		piece.setTiles();
		piece.sendPieceDown();
		Piece piece2 = new Piece(rand, board);
		piece2.setTiles();
		System.out.println(board.genBoardStr());
		piece2.sendPieceDown();
		System.out.println(board.genBoardStr());
	}
}
