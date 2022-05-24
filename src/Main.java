public class Main {
    public static void main(String[] args) {
        Chessboard board = new Chessboard();
        System.out.println(board);
        board.move(1,1,1,3);    //Pawn 2 move 2
        System.out.println(board);
        board.move(3,1,3,2);    //Pawn 2 move 1
        System.out.println(board);
        board.move(7,1,7,3);
        System.out.println(board);
        board.move(6,6,6,4);
        System.out.println(board);
        board.move(7,3,6,4);
        System.out.println(board);

        Chessboard newboard = new Chessboard(3);
        System.out.println(newboard);
        for(int i = 0; i < 8; i++){
            newboard.move(i, 1, i ,2);
            System.out.println(newboard);
            newboard.move(i, 6, i ,5);
            System.out.println(newboard);
        }
        System.out.println(newboard);
    }
}