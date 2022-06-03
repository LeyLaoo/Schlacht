import Pieces.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Chessboard {
    Piece[][] board = new Piece[8][8];  //x and y

    /**
     * Creates a chessboard with the standard settings
     * @author Uhlig Bastian
     */
    Chessboard() {
        board[0][0] = new Rook(0, 0, false);
        board[1][0] = new Bishop(1, 0, false);
        board[2][0] = new Knight(2, 0, false);
        board[3][0] = new Queen(3, 0, false);
        board[4][0] = new King(4, 0, false);
        board[5][0] = new Knight(5, 0, false);
        board[6][0] = new Bishop(6, 0, false);
        board[7][0] = new Rook(7, 0, false);
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(i, 1, false);
        }

        board[0][7] = new Rook(0, 7, true);
        board[1][7] = new Bishop(1, 7, true);
        board[2][7] = new Knight(2, 7, true);
        board[3][7] = new Queen(3, 7, true);
        board[4][7] = new King(4, 7, true);
        board[5][7] = new Knight(5, 7, true);
        board[6][7] = new Bishop(6, 7, true);
        board[7][7] = new Rook(7, 7, true);
        for (int i = 0; i < 8; i++) {
            board[i][6] = new Pawn(i, 6, true);
        }
    }

    /**
     * Moves a chesspiece to a new location if possible
     * @param currentX  the current X Position of the piece you want to move
     * @param currentY  the current Y Position of the piece you want to move
     * @param newX      the X position of where you want to move your piece
     * @param newY      the Y position of where you want to move your piece
     * @throws IllegalArgumentException throws this exception if the move is not a valid move
     * @throws IllegalStateException    throws this exception if the move is blocked my another pawn
     * @author Uhlig Bastian
     */
    public void move(int currentX, int currentY, int newX, int newY) throws IllegalArgumentException, IllegalStateException {
        Piece currentPiece = board[currentX][currentY];
        if (currentPiece == null) {
            return;
        }
        if (board[newX][newY] == null) {
            currentPiece.move(newX, newY, board);
            board[currentX][currentY] = null;
            board[newX][newY] = currentPiece;

        } else {
            if (currentPiece.isPlayerOne() != board[newX][newY].isPlayerOne()) {
                if (currentPiece instanceof Pawn) {
                    ((Pawn) currentPiece).kill(newX, newY);
                } else {
                    currentPiece.move(newX, newY, board);
                }
                if(board[newX][newY] instanceof King){
                    //Checkmate TODO
                }
                board[currentX][currentY] = null;
                board[newX][newY] = currentPiece;
            } else {
                throw new IllegalStateException("Cant kill own Pawn");
            }
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                out += String.format("%8s \t", board[j][i] == null ? "--------" : board[j][i].toString());
            }
            out += "\n";
        }
        return out;
    }

    /**
     * Creates the Chessboard
     * @param stage
     */

    public void start(Stage stage) {
        stage.setTitle("Schachbrett");
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);


        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 600, 600); // w, h
        stage.setScene(scene);
        gridPane.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), Insets.EMPTY)));

        gridPane.setHgap(0);
        gridPane.setVgap(   0);
        gridPane.setPadding(new Insets(10)); // top, right, bottom, left

        Button[][] button = new Button[8][8];

        for(int i = 0; i < button.length; i++){
            for(int j = 0; j < button.length; j++){
                button[i][j] = new Button();
                button[i][j].setPrefSize(75,75);
                gridPane.add(button[i][j], i, j);

                if((i + j) % 2 == 1){
                    button[i][j].setStyle("-fx-background-color: BLACK");
                } else{
                    button[i][j].setStyle("-fx-background-color: WHITE");
                }
            }
        }


        //GridPane.setValignment(button1, VPos.CENTER);
        //GridPane.setHalignment(button4, HPos.CENTER);

        gridPane.setGridLinesVisible(true); // uncomment the line to see the grid
        stage.show();
    }
}
