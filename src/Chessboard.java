import Pieces.*;

public class Chessboard {
    Piece[][] board = new Piece[8][8];  //x and y

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
                board[currentX][currentY] = null;
                board[newX][newY] = currentPiece;
            } else {
                throw new IllegalArgumentException("Cant kill own Pawn");
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
}
