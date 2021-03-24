package gui;

import java.util.Random;

import game.Board;
import game.Piece;
import game.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TetrisGUI extends Application {
	public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 800, EDGE_OFFSET = 10, UNIT_SIZE = 40, GRID_WIDTH = 10,
			GRID_HEIGHT = 22, TIME = 300;
	private BorderPane mainPane;
	private Board board;
	private Piece piece;
	private Timeline animation;
	private GraphicsContext gc, holdGC;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Initialize board
		board = new Board(this);
		Random rand = new Random();
		piece = new Piece(rand, board);
		piece.setTiles();

		// Initialize main visual component
		mainPane = new BorderPane();
		mainPane.setPrefHeight(SCREEN_HEIGHT);
		mainPane.setPrefWidth(SCREEN_WIDTH);
		mainPane.setPadding(new Insets(EDGE_OFFSET, EDGE_OFFSET, EDGE_OFFSET, EDGE_OFFSET));

		// Initialize canvas
		Canvas canvas = new Canvas();
		canvas.setWidth(GRID_WIDTH * UNIT_SIZE);
		canvas.setHeight(GRID_HEIGHT * UNIT_SIZE);
		gc = canvas.getGraphicsContext2D();
		initBoard(gc);
		draw(gc);

		animation = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
			if (!piece.updateAllTilePos()) {
				if (board.checkForRowClear()) {
					board.clearEmptyRows();
				}
				piece = new Piece(rand, board);
				piece.setTiles();
			}
			draw(gc);
		}));
		
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		mainPane.setCenter(canvas);
		
		VBox rightPane = new VBox();
		rightPane.setPadding(new Insets(0, 75, 0, 0));
		rightPane.setSpacing(75);
		HBox scoreBox = new HBox();
		Label scoreLabel = new Label("Score: ");
				
		TextField scoreDisplay = new TextField();
		scoreDisplay.setPrefHeight(25);
		scoreDisplay.setPrefWidth(50);
		scoreDisplay.setEditable(false);
		
		Canvas heldPiece = new Canvas();
		holdGC = heldPiece.getGraphicsContext2D();
		heldPiece.setWidth(UNIT_SIZE * 3);
		heldPiece.setHeight(UNIT_SIZE * 3);
		
		Label holdLabel = new Label("Held Piece");
		
		VBox holdPane = new VBox();
		holdPane.getChildren().addAll(holdLabel, heldPiece);
		
		scoreBox.getChildren().addAll(scoreLabel, scoreDisplay);
		rightPane.getChildren().addAll(scoreBox, holdPane);
		
		mainPane.setRight(rightPane);
						
		drawHeldPiece();

		// Initialize scene
		Scene scene = new Scene(mainPane);

		// Initialize keyboard functionality
		mainPane.setOnKeyPressed(e -> {
			KeyCode key = e.getCode();
			switch (key) {
			case DOWN:
				animation.setRate(8);
				break;
			case LEFT:
				if (piece.shiftLeft()) {
					draw(gc);
				}
				break;
			case SPACE:
				piece.sendPieceDown();
				if (board.checkForRowClear()) {
					board.clearEmptyRows();
				}
				piece = new Piece(rand, board);
				piece.setTiles();

				draw(gc);
				break;
			case RIGHT:
				if (piece.shiftRight()) {
					draw(gc);
				}
				break;
			case E:
				piece.rotateRight();
				draw(gc);
				break;
			case Q:
				piece.rotateLeft();
				draw(gc);
				break;
			case R:
				//Store piece
				Piece newPiece;
				if(board.getHeldPiece() == null) {
					newPiece = new Piece(rand, board);
				} else {
					newPiece = new Piece(board.getHeldPiece(), rand, board);
				}
				board.storePiece(piece);
				piece = newPiece;
				piece.setTiles();
				
				
				draw(gc);
				drawHeldPiece();
				break;
			default:
			}
		});
		mainPane.setOnKeyReleased(e -> {
			KeyCode key = e.getCode();
			switch(key) {
			case DOWN:
				animation.setRate(1);
				break;
			default:
			}
		});

		mainPane.requestFocus();
		
		// Add scene to stage and display
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initBoard(GraphicsContext gc) {
		Canvas canvas = gc.getCanvas();

		// Draw gridlines
		gc.setStroke(Color.BLACK);
		gc.strokeLine(0, 0, canvas.getWidth(), 0);
		gc.strokeLine(0, 0, 0, canvas.getHeight());
		gc.strokeLine(0, canvas.getHeight(), canvas.getWidth(), canvas.getHeight());
		gc.strokeLine(canvas.getWidth(), 0, canvas.getWidth(), canvas.getHeight());

		for (int row = 0; row < canvas.getHeight(); row += UNIT_SIZE) {
			gc.strokeLine(0, row, canvas.getWidth(), row);
		}
		for (int col = 0; col < canvas.getWidth(); col += UNIT_SIZE) {
			gc.strokeLine(col, 0, col, canvas.getHeight());
		}
	}

	private void draw(GraphicsContext gc) {
		Canvas canvas = gc.getCanvas();

		for (int row = 0; row < GRID_HEIGHT; row++) {
			for (int col = 0; col < GRID_WIDTH; col++) {
				Tile tile = board.getTile(row, col);
				gc.setFill(tile.getColor());
				gc.fillRect(UNIT_SIZE * col, UNIT_SIZE * row, (UNIT_SIZE * col) + UNIT_SIZE,
						(UNIT_SIZE * row) + UNIT_SIZE);
			}
		}

		gc.setStroke(Color.BLACK);

		for (int row = 0; row < canvas.getHeight(); row += UNIT_SIZE) {
			gc.strokeLine(0, row, canvas.getWidth(), row);
		}
		for (int col = 0; col < canvas.getWidth(); col += UNIT_SIZE) {
			gc.strokeLine(col, 0, col, canvas.getHeight());
		}
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, UNIT_SIZE * GRID_WIDTH, UNIT_SIZE * 2);
	}
	
	private void drawHeldPiece() {
		Canvas holdCanvas = holdGC.getCanvas();
		
		holdGC.setFill(Color.LIGHTGRAY);
		holdGC.setStroke(Color.BLACK);
		holdGC.fillRect(0, 0, holdCanvas.getWidth(), holdCanvas.getHeight());
		if(board.getHeldPiece() == null) {
			for (int row = 0; row < holdCanvas.getHeight(); row += UNIT_SIZE) {
				holdGC.strokeLine(0, row, holdCanvas.getWidth(), row);
			}
			for (int col = 0; col < holdCanvas.getWidth(); col += UNIT_SIZE) {
				holdGC.strokeLine(col, 0, col, holdCanvas.getHeight());
			}
			return;
		}
		holdGC.setFill(board.getHeldPiece().tile.getColor());
		
		switch(board.getHeldPiece().tile) {
		case LIGHTBLUE:
			holdGC.fillRect(0, UNIT_SIZE, holdCanvas.getWidth(), UNIT_SIZE);
			break;
		case BLUE:
			holdGC.fillRect(0, 0, UNIT_SIZE, UNIT_SIZE);
			holdGC.fillRect(0, UNIT_SIZE, holdCanvas.getWidth(), UNIT_SIZE);
			break;
		case YELLOW:
			holdGC.fillRect(0, 0, UNIT_SIZE * 2, UNIT_SIZE * 2);
			break;
		case PURPLE:
			holdGC.fillRect(UNIT_SIZE, 0, UNIT_SIZE, UNIT_SIZE);
			holdGC.fillRect(0, UNIT_SIZE, holdCanvas.getWidth(), UNIT_SIZE);
			break;
		case GREEN:
			holdGC.fillRect(UNIT_SIZE, 0, holdCanvas.getWidth(), UNIT_SIZE);
			holdGC.fillRect(0, UNIT_SIZE, UNIT_SIZE * 2, UNIT_SIZE);
			break;
		case RED:
			holdGC.fillRect(0, 0, UNIT_SIZE * 2, UNIT_SIZE);
			holdGC.fillRect(UNIT_SIZE, UNIT_SIZE, holdCanvas.getWidth(), UNIT_SIZE);
			break;
		case ORANGE:
			holdGC.fillRect(UNIT_SIZE  * 2, 0, holdCanvas.getWidth(), UNIT_SIZE);
			holdGC.fillRect(0, UNIT_SIZE, holdCanvas.getWidth(), UNIT_SIZE);
			break;
		default:
			System.out.println("Error");
		}
		for (int row = 0; row < holdCanvas.getHeight(); row += UNIT_SIZE) {
			holdGC.strokeLine(0, row, holdCanvas.getWidth(), row);
		}
		for (int col = 0; col < holdCanvas.getWidth(); col += UNIT_SIZE) {
			holdGC.strokeLine(col, 0, col, holdCanvas.getHeight());
		}
	}
			
	public void gameOver() {
		animation.stop();		
		//canvas = new Canvas();
		//mainPane.setCenter(canvas);
		
	}
}
