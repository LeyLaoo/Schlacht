import Pieces.IllegalMoveException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    int firstX = -1, firstY = -1;
    boolean playerOneTurn = true;

    //    Javafx library has to be added first
//
//    VM options for the run configuration
//    --module-path "[Path of the lib directory of javafx]" --add-modules javafx.controls,javafx.fxml
    public static void main(String[] args) {
        launch(args);
        System.out.println("Game closed");
    }

    public void start(Stage stage) {
        stage.setTitle("Chessboard");
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setResizable(false);
//        stage.initStyle(StageStyle.UNDECORATED);


        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 600, 600); // w, h
        stage.setScene(scene);
        gridPane.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), Insets.EMPTY)));

        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(10)); // top, right, bottom, left

        Button[][] button = new Button[8][8];

        Chessboard board = new Chessboard(5);

        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button.length; j++) {
                button[i][j] = new Button();
                button[i][j].setPrefSize(75, 75);
                gridPane.add(button[i][j], i, j);

                if ((i + j) % 2 == 1) {
                    button[i][j].setStyle("-fx-background-color: DARKGREY");
                    if (board.getType(i, j).equals("Closed")) {
                        button[i][j].setStyle("-fx-background-color: #e06969");
                    }
                } else {
                    button[i][j].setStyle("-fx-background-color: LIGHTGREY");
                    if (board.getType(i, j).equals("Closed")) {
                        button[i][j].setStyle("-fx-background-color: #ef8383");
                    }
                }
                int finalJ = j;
                int finalI = i;
                button[i][j].setOnAction(actionEvent -> {
                    try {
                        movePawn(finalI, finalJ, board, button);
                    } catch (InterruptedException e) {
                        if (e.getMessage().equals("Checkmate")) {
                            checkmate(stage);
                        }
                    }
                });
            }
        }

        updateButtons(button, board);

        gridPane.setGridLinesVisible(true); // uncomment the line to see the grid
        stage.show();
    }

    private void updateButtons(Button[][] button, Chessboard board) {
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                String path = ".\\Chesspieces\\";
                try {
                    if (board.getType(i, j).equals("Closed")) throw new IllegalStateException("Closed");
                    if (board.isPlayerOne(i, j)) path += "White\\White" + board.getType(i, j) + ".png";
                    else path += "Black\\Black" + board.getType(i, j) + ".png";
                    Image image = new Image(path);
                    ImageView view = new ImageView(image);
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    button[i][j].setGraphic(view);
                } catch (IllegalStateException e) {
                    button[i][j].setGraphic(null);
                }
                if (firstX == i && firstY == j) {
                    button[i][j].setEffect(new Glow(1));
                } else {
                    button[i][j].setEffect(null);
                }
            }
        }
    }

    private void movePawn(int x, int y, Chessboard board, Button[][] buttons) throws InterruptedException {
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
                board.move(firstX, firstY, x, y);
                System.out.println(firstX + " " + firstY + ", moving to " + x + " " + y);
                firstX = -1;
                firstY = -1;
                playerOneTurn = !playerOneTurn;
            } catch (IllegalMoveException | IllegalStateException | IllegalArgumentException e) {
                if (e.getMessage().equals("Change")) {
                    firstX = x;
                    firstY = y;
                    updateButtons(buttons, board);
                    return;
                }
                firstX = -1;
                firstY = -1;
                System.out.println(e.getMessage());
            }
        }
        updateButtons(buttons, board);
    }

    public static void checkmate(Stage stage) {
        TextArea checkmate = new TextArea("checkmate");
        Scene scene = new Scene(checkmate, 600, 600);
        stage.setScene(scene);
    }
}