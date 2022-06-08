import Pieces.IllegalMoveException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    int firstX = -1, firstY = -1;
    boolean playerOneTurn = true;

    public static void main(String[] args) {
        Chessboard board = new Chessboard();
        System.out.println(board);
        try {
            board.move(1, 1, 1, 3);    //Pawn 2 move 2
            System.out.println(board);
            board.move(3, 1, 3, 2);    //Pawn 2 move 1
            System.out.println(board);
            board.move(7, 1, 7, 3);
            System.out.println(board);
            board.move(6, 6, 6, 4);
            System.out.println(board);
            board.move(7, 3, 6, 4);
            System.out.println(board);
            board.move(2, 0, 1, 2);
            System.out.println(board);
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        Chessboard newboard = new Chessboard(3);
      /*  System.out.println(newboard);
        try {
            for (int i = 0; i < 8; i++) {
                newboard.move(i, 1, i, 2);
                System.out.println(newboard);
                newboard.move(i, 6, i, 5);
                System.out.println(newboard);
            }
        } catch (IllegalMoveException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(newboard);*/
        launch(args);
        System.out.println("UwU");
    }


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
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(10)); // top, right, bottom, left

        Button[][] button = new Button[8][8];

        Chessboard board = new Chessboard();

        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button.length; j++) {
                button[i][j] = new Button();
                button[i][j].setPrefSize(75, 75);
                gridPane.add(button[i][j], i, j);

                if ((i + j) % 2 == 1) {
                    button[i][j].setStyle("-fx-background-color: DARKGREY");
                } else {
                    button[i][j].setStyle("-fx-background-color: LIGHTGREY");
                }
                int finalJ = j;
                int finalI = i;
                button[i][j].setOnAction(actionEvent -> movePawn(finalI, finalJ, board, button));
            }
        }

        updateButtons(button, board);


        //GridPane.setValignment(button1, VPos.CENTER);
        //GridPane.setHalignment(button4, HPos.CENTER);

        gridPane.setGridLinesVisible(true); // uncomment the line to see the grid
        stage.show();
    }

    private Button[][] updateButtons(Button[][] button, Chessboard board) {
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                String path = ".\\Chesspieces\\";
                try {
                    if (board.isPlayerOne(i, j)) {
                        path += "White\\White" + board.getType(i, j) + ".png";
                    } else {
                        path += "Black\\Black" + board.getType(i, j) + ".png";
                    }
                    Image image = new Image(path);
                    ImageView view = new ImageView(image);
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    button[i][j].setGraphic(view);
                } catch (IllegalStateException e) {
                    button[i][j].setGraphic(null);
                }
                if(firstX == i && firstY == j){
                    button[i][j].setEffect(new Glow(1));
                }else{
                    button[i][j].setEffect(null);
                }
            }
        }
        return button;
    }

    private void movePawn(int x, int y, Chessboard board, Button[][] buttons) {
        if (firstX == -1) {
            try {
                if (board.isPlayerOne(x, y) != playerOneTurn) {
                    firstX = -1;
                    firstY = -1;
                    return;
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                return;
            }
            firstX = x;
            firstY = y;
        } else {
            try {
                System.out.println(firstX + " " + firstY + " , " + x + " " + y);
                board.move(firstX, firstY, x, y);
                firstX = -1;
                firstY = -1;
                playerOneTurn = !playerOneTurn;
            } catch (IllegalMoveException | IllegalArgumentException e ) {
                firstX = -1;
                firstY = -1;
                System.out.println(e.getMessage());
            }
        }
        updateButtons(buttons, board);
    }
}