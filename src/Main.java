import Pieces.IllegalMoveException;
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

public class Main {
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
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        Chessboard newboard = new Chessboard(3);
        System.out.println(newboard);
        try {
            for (int i = 0; i < 8; i++) {
                newboard.move(i, 1, i, 2);
                System.out.println(newboard);
                newboard.move(i, 6, i, 5);
                System.out.println(newboard);
            }
        }catch(IllegalMoveException | IllegalStateException e){
            System.out.println(e.getMessage());
        }
        System.out.println(newboard);
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

        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button.length; j++) {
                button[i][j] = new Button();
                button[i][j].setPrefSize(75, 75);
                gridPane.add(button[i][j], i, j);

                if ((i + j) % 2 == 1) {
                    button[i][j].setStyle("-fx-background-color: BLACK");
                } else {
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