package game;

import gui.TetrisGUI;

public class Board {

	private Tile[][] board;
	protected TetrisGUI gui;
	protected int score;
	protected Piece heldPiece;

	public Board(TetrisGUI gui) {
		board = new Tile[22][10];
		score = 0;
		initEmptyBoard();
		this.gui = gui;
		heldPiece = null;
	}

	private void initEmptyBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = Tile.EMPTY;
			}
		}
	}
	
	public Piece getHeldPiece() {
		return heldPiece;
	}

	public Tile getTile(int x, int y) {
		return board[x][y];
	}

	public void setTile(int x, int y, Tile tile) {
		board[x][y] = tile;
	}

	public Tile[][] getBoard() {
		return board;
	}

	public void setRow(int row) {
		Tile[] testRow = new Tile[10];
		for (int i = 0; i < testRow.length; i++) {
			testRow[i] = Tile.BLUE;
		}
		board[row] = testRow;
	}

	public boolean checkForRowClear() {
		boolean rowCleared = false;
		for (int row = 0; row < board.length; row++) {
			if (isRowFull(row)) {
				board[row] = genEmptyRow();
				rowCleared = true;
			}
		}
		return rowCleared;
	}

	public void clearEmptyRows() {
		Board newBoard = new Board(gui);
		newBoard.initEmptyBoard();

		for (int row = board.length - 1, newRow = board.length - 1; row >= 0; row--) {
			if (!isRowEmpty(row)) {
				newBoard.getBoard()[newRow--] = board[row];
			}
		}
		board = newBoard.getBoard();
	}
	
	public void storePiece(Piece piece) {
		heldPiece = piece;
		piece.setCurrPieceEmpty();
	}

	private Tile[] genEmptyRow() {
		Tile[] row = new Tile[10];
		for (int i = 0; i < 10; i++) {
			row[i] = Tile.EMPTY;
		}
		return row;
	}

	private boolean isRowFull(int row) {
		for (int col = 0; col < board[row].length; col++) {
			if (board[row][col] == Tile.EMPTY) {
				return false;
			}
		}
		return true;
	}

	private boolean isRowEmpty(int row) {
		for (int col = 0; col < board[row].length; col++) {
			if (board[row][col] != Tile.EMPTY) {
				return false;
			}
		}
		return true;
	}

	public String genBoardStr() {
		String boardStr = "";
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				boardStr += board[row][col].getName() + " ";
			}
			boardStr += "\n";
		}
		return boardStr;
	}
}
