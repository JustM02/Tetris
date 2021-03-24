package game;

import java.util.Random;

import gui.TetrisGUI;

public class Piece {

	private String name;
	private final Board board;
	private final Random rand;
	private int x1, x2, x3, x4;
	private int y1, y2, y3, y4;
	public Tile tile;
	private int rotation;

	/**
	 * Initializes new random tetrimino All pieces have a name, and 8 coordinates
	 * associated with 4 different tiles on a 2D array of tiles Also has a tile type
	 * associated with it to change board Has rotation between 0-3 to determine how
	 * piece is organized on board currently
	 * 
	 * @param rand
	 * @param board
	 */
	public Piece(Random rand, Board board) {
		this.rand = rand;
		rotation = 0;
		int nextPiece = rand.nextInt(7);
		switch (nextPiece) {
		case 0:
			name = "I";
			tile = Tile.LIGHTBLUE;
			break;
		case 1:
			name = "J";
			tile = Tile.BLUE;
			break;
		case 2:
			name = "T";
			tile = Tile.PURPLE;
			break;
		case 3:
			name = "L";
			tile = Tile.ORANGE;
			break;
		// S piece is default case
		default:
		case 4:
			name = "S";
			tile = Tile.GREEN;
			break;
		case 5:
			name = "Z";
			tile = Tile.RED;
			break;
		case 6:
			name = "O";
			tile = Tile.YELLOW;
			break;
		}
		this.board = board;
		switch (name) {
		case "I":
			x1 = 1;
			x2 = 1;
			x3 = 1;
			x4 = 1;
			y1 = 3;
			y2 = 4;
			y3 = 5;
			y4 = 6;
			break;
		case "J":
			x1 = 0;
			x2 = 1;
			x3 = 1;
			x4 = 1;
			y1 = 3;
			y2 = 3;
			y3 = 4;
			y4 = 5;
			break;
		case "T":
			x1 = 0;
			x2 = 1;
			x3 = 1;
			x4 = 1;
			y1 = 4;
			y2 = 3;
			y3 = 4;
			y4 = 5;
			break;
		case "L":
			x1 = 0;
			x2 = 1;
			x3 = 1;
			x4 = 1;
			y1 = 5;
			y2 = 3;
			y3 = 4;
			y4 = 5;
			break;
		default:
		case "S":
			x1 = 0;
			x2 = 0;
			x3 = 1;
			x4 = 1;
			y1 = 4;
			y2 = 5;
			y3 = 3;
			y4 = 4;
			break;
		case "Z":
			x1 = 0;
			x2 = 0;
			x3 = 1;
			x4 = 1;
			y1 = 3;
			y2 = 4;
			y3 = 4;
			y4 = 5;
			break;
		case "O":
			x1 = 0;
			x2 = 0;
			x3 = 1;
			x4 = 1;
			y1 = 4;
			y2 = 5;
			y3 = 4;
			y4 = 5;
			break;
		}
	}
	
	public Piece(Piece piece, Random rand, Board board) {
		this.board = board;
		this.name = piece.name;
		this.rotation = 0;
		this.tile = piece.tile;
		this.rand = rand;
	
		switch (name) {
		case "I":
			this.x1 = 1;
			this.x2 = 1;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 3;
			this.y2 = 4;
			this.y3 = 5;
			this.y4 = 6;
			break;
		case "J":
			this.x1 = 0;
			this.x2 = 1;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 3;
			this.y2 = 3;
			this.y3 = 4;
			this.y4 = 5;
			break;
		case "T":
			this.x1 = 0;
			this.x2 = 1;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 4;
			this.y2 = 3;
			this.y3 = 4;
			this.y4 = 5;
			break;
		case "L":
			this.x1 = 0;
			this.x2 = 1;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 5;
			this.y2 = 3;
			this.y3 = 4;
			this.y4 = 5;
			break;
		default:
		case "S":
			this.x1 = 0;
			this.x2 = 0;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 4;
			this.y2 = 5;
			this.y3 = 3;
			this.y4 = 4;
			break;
		case "Z":
			this.x1 = 0;
			this.x2 = 0;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 3;
			this.y2 = 4;
			this.y3 = 4;
			this.y4 = 5;
			break;
		case "O":
			this.x1 = 0;
			this.x2 = 0;
			this.x3 = 1;
			this.x4 = 1;
			this.y1 = 4;
			this.y2 = 5;
			this.y3 = 4;
			this.y4 = 5;
			break;
		}
	}

	/**
	 * 
	 * @return name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * 
	 * @return rotation
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * Set tiles on board for 4 coordinates piece is located in to change same
	 * coordinates on board to same tile type as current piece Called after any
	 * method that changes piece location
	 */
	public void setTiles() {
		board.setTile(x1, y1, tile);
		board.setTile(x2, y2, tile);
		board.setTile(x3, y3, tile);
		board.setTile(x4, y4, tile);
	}

	/**
	 * If false, call setStopped to set current piece to equivalent tile type on
	 * board but piece's stopped state is set to true
	 * 
	 * @return false if piece can't be moved downwards and true otherwise
	 */
	public boolean updateAllTilePos() {
		if (!canShiftPiece()) {
			setStopped();
			return false;
		}
		setCurrPieceEmpty();
		x1++;
		x2++;
		x3++;
		x4++;
		setTiles();
		return true;
	}

	/**
	 * If piece can be shifted left, y coordinates of all tiles are shifted left one
	 * and board is updated
	 * 
	 * @return false if piece can't be shifted left and true otherwise
	 */
	public boolean shiftLeft() {
		if (y1 - 1 < 0 || y2 - 1 < 0 || y3 - 1 < 0 || y4 - 1 < 0) {
			return false;
		}
		// x1 and y1
		Tile tile1 = board.getTile(x1, y1 - 1);
		// x2 and y2
		Tile tile2 = board.getTile(x2, y2 - 1);
		// x3 and y3
		Tile tile3 = board.getTile(x3, y3 - 1);
		// x4 and y4
		Tile tile4 = board.getTile(x4, y4 - 1);
		if (!tile1.getStopped() && !tile2.getStopped() && !tile3.getStopped() && !tile4.getStopped()) {
			setCurrPieceEmpty();
			y1--;
			y2--;
			y3--;
			y4--;
			setTiles();
			return true;
		}
		return false;

	}

	/**
	 * If piece can be shifted right, y coordinates of all tiles are shifted right
	 * one and board is updated
	 * 
	 * @return false if piece can't be shifted right and true otherwise
	 */
	public boolean shiftRight() {
		int screenEdge = TetrisGUI.GRID_WIDTH;
		if (y1 + 1 == screenEdge || y2 + 1 == screenEdge || y3 + 1 == screenEdge || y4 + 1 == screenEdge) {
			return false;
		}
		// x1 and y1
		Tile tile1 = board.getTile(x1, y1 + 1);
		// x2 and y2
		Tile tile2 = board.getTile(x2, y2 + 1);
		// x3 and y3
		Tile tile3 = board.getTile(x3, y3 + 1);
		// x4 and y4
		Tile tile4 = board.getTile(x4, y4 + 1);
		if (!tile1.getStopped() && !tile2.getStopped() && !tile3.getStopped() && !tile4.getStopped()) {
			setCurrPieceEmpty();
			y1++;
			y2++;
			y3++;
			y4++;
			setTiles();
			return true;
		}
		return false;

	}

	/*
	 * 
	 */
	protected void setCurrPieceEmpty() {
		board.setTile(x1, y1, Tile.EMPTY);
		board.setTile(x2, y2, Tile.EMPTY);
		board.setTile(x3, y3, Tile.EMPTY);
		board.setTile(x4, y4, Tile.EMPTY);
	}

	public void setStopped() {
		switch (tile) {
		case LIGHTBLUE:
			tile = Tile.S_LIGHTBLUE;
			break;
		case BLUE:
			tile = Tile.S_BLUE;
			break;
		case PURPLE:
			tile = Tile.S_PURPLE;
			break;
		case ORANGE:
			tile = Tile.S_ORANGE;
			break;
		default:
		case GREEN:
			tile = Tile.S_GREEN;
			break;
		case RED:
			tile = Tile.S_RED;
			break;
		case YELLOW:
			tile = Tile.S_YELLOW;
			break;
		}
		if(x1 <= 1 || x2 <= 1 || x3 <= 1 || x4 <= 1) {
			board.gui.gameOver();
			return;
		}
		setTiles();
	}

	public void rotateRight() {
		if (rotation == 3) {
			rotation = 0;
		} else {
			rotation++;
		}
		setCurrPieceEmpty();
		if(!checkRotationValid(true)) {
			if(rotation == 0) {
				rotation = 3;
			} else {
				rotation--;
			}
			setTiles();
			System.out.println("invalid");
			return;
		}
		
		
		setTiles();
	}
	
	public Piece getRotationRight(Piece piece) {
		Piece temp = new Piece(rand, board);
		temp.name = name;
		temp.tile = tile;
		temp.rotation = rotation;
		temp.x1 = x1;
		temp.x2 = x2;
		temp.x3 = x3;
		temp.x4 = x4;
		temp.y1 = y1;
		temp.y2 = y2;
		temp.y3 = y3;
		temp.y4 = y4;
		
		switch (name) {
		case "I":
			switch (temp.rotation) {
			case 0:
				temp.x1++;
				temp.x3--;
				temp.x4 -= 2;
				temp.y1--;
				temp.y3++;
				temp.y4 += 2;
				break;
			case 1:
				temp.x1--;
				temp.x3++;
				temp.x4 += 2;
				temp.y1 += 2;
				temp.y2++;
				temp.y4--;
				break;
			case 2:
				temp.x1 += 2;
				temp.x2++;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2--;
				temp.y4++;
				break;
			case 3:
				temp.x1 -= 2;
				temp.x2--;
				temp.x4++;
				temp.y1++;
				temp.y3--;
				temp.y4 -= 2;
				break;
			}
			break;
		case "J":
			switch (rotation) {
			case 0:
				///////
				//JEE//
				//JJJ//
				//EEE//
				///////
				temp.x1 -= 2;
				temp.x2--;
				temp.x4++;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				///////
				//EJJ//
				//EJE//
				//EJE//
				///////
				temp.x2--;
				temp.x4++;
				temp.y1 += 2;
				temp.y2++;
				temp.y4--;
				break;
			case 2:
				///////
				//EEE//
				//JJJ//
				//EEJ//
				///////
				temp.x1 += 2;
				temp.x2++;
				temp.x4--;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				///////
				//EJE//
				//EJE//
				//JJE//
				///////
				temp.x2++;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2--;
				temp.y4++;
				break;
			}
			break;
		case "T":
			switch (rotation) {
			case 0:
				///////
				//ETE//
				//TTT//
				//EEE//
				///////
				temp.x1--;
				temp.x2--;
				temp.x4++;
				temp.y1++;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				///////
				//ETE//
				//ETT//
				//ETE//
				///////
				temp.x1++;
				temp.x2--;
				temp.x4++;
				temp.y1++;
				temp.y2++;
				temp.y4--;
				break;
			case 2:
				///////
				//EEE//
				//TTT//
				//ETE//
				///////
				temp.x1++;
				temp.x2++;
				temp.x4--;
				temp.y1--;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				///////
				//ETE//
				//TTE//
				//ETE//
				///////
				temp.x1--;
				temp.x2++;
				temp.x4--;
				temp.y1--;
				temp.y2--;
				temp.y4++;
				break;
			}
			break;
		case "L":
			switch (rotation) {
			case 0:
				///////
				//EEL//
				//LLL//
				//EEE//
				///////
				temp.x2--;
				temp.x4++;
				temp.y1 += 2;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				///////
				//ELE//
				//ELE//
				//ELL//
				///////
				temp.x1 += 2;
				temp.x2--;
				temp.x4++;
				temp.y2++;
				temp.y4--;
				break;
			case 2:
				///////
				//EEE//
				//LLL//
				//LEE//
				///////
				temp.x2++;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				///////
				//LLE//
				//ELE//
				//ELE//
				///////
				temp.x1 -= 2;
				temp.x2++;
				temp.x4--;
				temp.y2--;
				temp.y4++;
				break;
			}
			break;
		case "S":
			switch (rotation) {
			case 0:
				///////
				//ESS//
				//SSE//
				//EEE//
				///////
				temp.x1--;
				temp.x3--;
				temp.y1++;
				temp.y2 += 2;
				temp.y3--;
				break;
			case 1:
				///////
				//ESE//
				//ESS//
				//EES//
				///////
				temp.x1++;
				temp.x2 += 2;
				temp.x3--;
				temp.y1++;
				temp.y3++;
				break;
			case 2:
				///////
				//EEE//
				//ESS//
				//SSE//
				///////
				temp.x1++;
				temp.x3++;
				temp.y1--;
				temp.y2 -= 2;
				temp.y3++;
				break;
			case 3:
				///////
				//SEE//
				//SSE//
				//ESE//
				///////
				temp.x1--;
				temp.x2 -= 2;
				temp.x3++;
				temp.y1--;
				temp.y3--;
				break;
			}
			break;
		case "Z":
			switch (rotation) {
			case 0:
				///////
				//ZZE//
				//EZZ//
				//EEE//
				///////
				temp.x1 -= 2;
				temp.x2--;
				temp.x4++;
				temp.y2++;
				temp.y4++;
				break;
			case 1:
				///////
				//EEZ//
				//EZZ//
				//EZE//
				///////
				temp.x2++;
				temp.x4++;
				temp.y1 += 2;
				temp.y2++;
				temp.y4--;
				break;
			case 2:
				///////
				//EEE//
				//ZZE//
				//EZZ//
				///////
				temp.x1 += 2;
				temp.x2++;
				temp.x4--;
				temp.y2--;
				temp.y4--;
				break;
			case 3:
				///////
				//EZE//
				//ZZE//
				//ZEE//
				///////
				temp.x2--;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2--;
				temp.y4++;
				break;
			}
			break;
		}
		return temp;
	}

	public Piece getRotationLeft(Piece piece) {
		Piece temp = new Piece(rand, board);
		temp.rotation = rotation;
		temp.x1 = x1;
		temp.x2 = x2;
		temp.x3 = x3;
		temp.x4 = x4;
		temp.y1 = y1;
		temp.y2 = y2;
		temp.y3 = y3;
		temp.y4 = y4;
		
		switch (name) {
		case "I":
			switch (temp.rotation) {
			case 0:
				temp.x1++;
				temp.x3--;
				temp.x4 -= 2;
				temp.y1 -= 2;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				temp.x1 -= 2;
				temp.x2--;
				temp.x4++;
				temp.y1 += 2;
				temp.y2++;
				temp.y4--;
				break;
			case 2:
				temp.x1 += 2;
				temp.x2++;
				temp.x4--;
				temp.y1--;
				temp.y3++;
				temp.y4 += 2;
				break;
			case 3:
				temp.x1--;
				temp.x3++;
				temp.x4 += 2;
				temp.y1++;
				temp.y3--;
				temp.y4 -= 2;
				break;
			}
			break;
		case "J":
			switch (rotation) {
			case 0:
				temp.x2++;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				temp.x1 -= 2;
				temp.x2--;
				temp.x4++;
				temp.y2--;
				temp.y4++;
				break;
			case 2:
				temp.x2--;
				temp.x4++;
				temp.y1 += 2;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				temp.x1 += 2;
				temp.x2++;
				temp.x4--;
				temp.y2++;
				temp.y4--;
				break;
			}
			break;
		case "T":
			switch (rotation) {
			case 0:
				///////
				//ETE//
				//TTT//
				//EEE//
				///////
				temp.x1--;
				temp.x2++;
				temp.x4--;
				temp.y1--;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				///////
				// ETE//
				// ETT//
				// ETE//
				///////
				temp.x1--;
				temp.x2--;
				temp.x4++;
				temp.y1++;
				temp.y2--;
				temp.y4++;
				break;
			case 2:
				///////
				// EEE//
				// TTT//
				// ETE//
				///////
				temp.x1++;
				temp.x2--;
				temp.x4++;
				temp.y1++;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				///////
				// ETE//
				// TTE//
				// ETE//
				///////
				temp.x1++;
				temp.x2++;
				temp.x4--;
				temp.y1--;
				temp.y2++;
				temp.y4--;
				break;
			}
			break;
		case "L":
			switch (rotation) {
			case 0:
				///////
				// EEL//
				// LLL//
				// EEE//
				///////
				temp.x1 -= 2;
				temp.x2++;
				temp.x4--;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				///////
				// ELE//
				// ELE//
				// ELL//
				///////
				temp.x2--;
				temp.x4++;
				temp.y1 += 2;
				temp.y2--;
				temp.y4++;
				break;
			case 2:
				///////
				// EEE//
				// LLL//
				// LEE//
				///////
				temp.x1 += 2;
				temp.x2--;
				temp.x4++;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				///////
				// LLE//
				// ELE//
				// ELE//
				///////
				temp.x2++;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2++;
				temp.y4--;
				break;
			}
			break;
		case "S":
			switch (rotation) {
			case 0:
				///////
				// ESS//
				// SSE//
				// EEE//
				///////
				temp.x1--;
				temp.x2 -= 2;
				temp.x3++;
				temp.y1--;
				temp.y3--;
				break;
			case 1:
				///////
				// ESE//
				// ESS//
				// EES//
				///////
				temp.x1--;
				temp.x3--;
				temp.y1++;
				temp.y2 += 2;
				temp.y3--;
				break;
			case 2:
				///////
				// EEE//
				// ESS//
				// SSE//
				///////
				temp.x1++;
				temp.x2 += 2;
				temp.x3--;
				temp.y1++;
				temp.y3++;
				break;
			case 3:
				///////
				// SEE//
				// SSE//
				// ESE//
				///////
				temp.x1++;
				temp.x3++;
				temp.y1--;
				temp.y2 -= 2;
				temp.y3++;
				break;
			}
			break;
		case "Z":
			switch (rotation) {
			case 0:
				///////
				// ZZE//
				// EZZ//
				// EEE//
				///////
				temp.x2--;
				temp.x4--;
				temp.y1 -= 2;
				temp.y2--;
				temp.y4++;
				break;
			case 1:
				///////
				// EEZ//
				// EZZ//
				// EZE//
				///////
				temp.x1 -= 2;
				temp.x2--;
				temp.x4++;
				temp.y2++;
				temp.y4++;
				break;
			case 2:
				///////
				// EEE//
				// ZZE//
				// EZZ//
				///////
				temp.x2++;
				temp.x4++;
				temp.y1 += 2;
				temp.y2++;
				temp.y4--;
				break;
			case 3:
				///////
				// EZE//
				// ZZE//
				// ZEE//
				///////
				temp.x1 += 2;
				temp.x2++;
				temp.x4--;
				temp.y2--;
				temp.y4--;
				break;
			}
			break;
		}

		return temp;
	}
	

	public void rotateLeft() {
		if (rotation == 0) {
			rotation = 3;
		} else {
			rotation--;
		}
		setCurrPieceEmpty();
		if(!checkRotationValid(false)) {
			if(rotation == 3) {
				rotation = 0;
			} else {
				rotation++;
			}
			setTiles();
			return;
		}
		setTiles();
	}

	public boolean checkRotationValid(boolean right) {
		Piece temp;
		if(right) {
			temp = getRotationRight(this);
		} else {
			temp = getRotationLeft(this);
		}
		if(temp.y1 < 0 || temp.y2 < 0 || temp.y3 < 0 || temp.y4 < 0) {
			return false;
		}
		if(temp.y1 >= 10 || temp.y2 >= 10 || temp.y3 >= 10 || temp.y4 >= 10) {
			return false;
		}
		Tile tile1 = board.getTile(temp.x1, temp.y1);
		Tile tile2 = board.getTile(temp.x2, temp.y2);
		Tile tile3 = board.getTile(temp.x3, temp.y3);
		Tile tile4 = board.getTile(temp.x4, temp.y4);
		
		if(tile1 != Tile.EMPTY || tile2 != Tile.EMPTY || tile3 != Tile.EMPTY || tile4 != Tile.EMPTY) {
			return false;
		}
		x1 = temp.x1;
		x2 = temp.x2;
		x3 = temp.x3;
		x4 = temp.x4;
		y1 = temp.y1;
		y2 = temp.y2;
		y3 = temp.y3;
		y4 = temp.y4;
		
		return true;
	}
	
	public void sendPieceDown() {
		// Horribly broken help//
		int x1Bottom = 21 - x1, x2Bottom = 21 - x2, x3Bottom = 21 - x3, x4Bottom = 21 - x4;
		for (int i1 = x1; i1 < 22; i1++) {
			if (board.getTile(i1, y1).getStopped()) {
				x1Bottom = i1 - 1 - x1;
				break;
			}
		}
		for (int i2 = x2; i2 < 22; i2++) {
			if (board.getTile(i2, y2).getStopped()) {
				x2Bottom = i2 - 1 - x2;
				break;
			}
		}
		for (int i3 = x3; i3 < 22; i3++) {
			if (board.getTile(i3, y3).getStopped()) {
				x3Bottom = i3 - 1 - x3;
				break;
			}
		}
		for (int i4 = x4; i4 < 22; i4++) {
			if (board.getTile(i4, y4).getStopped()) {
				x4Bottom = i4 - 1 - x4;
				break;
			}
		}
		int z1 = Math.min(x1Bottom, x2Bottom);
		int z2 = Math.min(z1, x3Bottom);
		int highestPoint = Math.min(z2, x4Bottom);

		setCurrPieceEmpty();
		x1 += highestPoint;
		x2 += highestPoint;
		x3 += highestPoint;
		x4 += highestPoint;
		setTiles();
		setStopped();
	}

	private boolean canShiftPiece() {
		// Check for bottom of board
		if (x1 + 1 == 22 || x2 + 1 == 22 || x3 + 1 == 22 || x4 + 1 == 22) {
			return false;
		}
		// x1 and y1
		Tile tile1 = board.getTile(x1 + 1, y1);
		// x2 and y2
		Tile tile2 = board.getTile(x2 + 1, y2);
		// x3 and y3
		Tile tile3 = board.getTile(x3 + 1, y3);
		// x4 and y4
		Tile tile4 = board.getTile(x4 + 1, y4);
		return (!tile1.getStopped() && !tile2.getStopped() && !tile3.getStopped() && !tile4.getStopped());
	}
}
