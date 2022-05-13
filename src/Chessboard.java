import Pieces.*;

public class Chessboard {
    Piece[][] board = new Piece[8][8];  //x and y

    Chessboard() {
        board[0][0] = new Rook(0, 0, false);
        board[0][1] = new Bishop(0, 1, false);
        board[0][2] = new Knight(0, 2, false);
        board[0][3] = new Queen(0, 3, false);
        board[0][4] = new King(0, 4, false);
        board[0][5] = new Knight(0, 5, false);
        board[0][6] = new Bishop(0, 6, false);
        board[0][7] = new Rook(0, 7, false);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1, i, false);
        }

        board[7][0] = new Rook(0, 0, true);
        board[7][1] = new Bishop(0, 1, true);
        board[7][2] = new Knight(0, 2, true);
        board[7][3] = new Queen(0, 3, true);
        board[7][4] = new King(0, 4, true);
        board[7][5] = new Knight(0, 5, true);
        board[7][6] = new Bishop(0, 6, true);
        board[7][7] = new Rook(0, 7, true);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(1, i, true);
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    out += "\t";
                } else {
                    out += board[i][j].toString() + "\t";
                }
            }
            out += "\n";
        }
        return out;
    }
}
