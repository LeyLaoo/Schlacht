public class Main {
    public static void main(String[] args) {
        Chessboard board = new Chessboard();
        System.out.println(board);
        board.move(1,1,3,1);
        board.move(1,3,2,3);
        board.move(2,3,4,3);
        System.out.println(board);
        board.move(7,2, 5, 3);
        System.out.println(board);

    }
}