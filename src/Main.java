public class Main {
    public static void main(String[] args) {
        Chessboard board = new Chessboard();
        System.out.println(board);
        board.move(1,1,1,3);    //Pawn 2 move 2
        board.move(3,1,3,2);    //Pawn 2 move 1
        board.move(6,7,4,5);
        board.move(1,2,3,2);
        board.move(7,7,7,5);
        System.out.println(board);

    }
}