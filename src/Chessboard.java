import Pieces.*;

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

    Chessboard(int numOfBlockedFields){
        this();
        while(numOfBlockedFields > 0){
            int randomX = (int) (Math.random()* 8);
            int randomY = (int) (Math.random() * (5 - 2 + 1) + 2);
            if(board[randomX][randomY] == null){
                board[randomX][randomY] = new Closed(randomX, randomY);
                numOfBlockedFields--;
            }
        }
    }

    /**
     * Moves a chesspiece to a new location if possible
     * @param currentX  the current X Position of the piece you want to move
     * @param currentY  the current Y Position of the piece you want to move
     * @param newX      the X position of where you want to move your piece
     * @param newY      the Y position of where you want to move your piece
     * @throws IllegalMoveException throws this exception if the move is not a valid move
     * @throws IllegalStateException    throws this exception if the move is blocked my another pawn
     * @author Uhlig Bastian
     */
    public void move(int currentX, int currentY, int newX, int newY) throws IllegalMoveException, IllegalStateException {
        Piece currentPiece = board[currentX][currentY];
        if(currentX == newX && currentY == newY) throw new IllegalStateException("not a move");
        if (currentPiece == null) return;
        if ( board[newX][newY] instanceof Closed || currentPiece instanceof Closed) throw new IllegalStateException("Closed Place");
        if (board[newX][newY] == null) {
            currentPiece.move(newX, newY, board);
            board[currentX][currentY] = null;
            board[newX][newY] = currentPiece;

        } else {
            if (currentPiece.isPlayerOne() != board[newX][newY].isPlayerOne()) {
                if (currentPiece instanceof Pawn) ((Pawn) currentPiece).kill(newX, newY);
                else currentPiece.move(newX, newY, board);
                if(board[newX][newY] instanceof King){
                    //TODO Checkmate
                }
                board[currentX][currentY] = null;
                board[newX][newY] = currentPiece;
            } else throw new IllegalMoveException("Change");
        }
        if(board[newX][newY] instanceof Pawn && ((newY == 7 && !board[newX][newY].isPlayerOne())||(newY == 0 && board[newX][newY].isPlayerOne()))){
            board[newX][newY] = new Queen(newX, newY, board[newX][newY].isPlayerOne());
        }
    }

    public boolean isPlayerOne(int x, int y){
        if(board[x][y] == null) throw new IllegalStateException("not a player");
        return board[x][y].isPlayerOne();
    }

    public String getType(int x, int y){
        Piece piece = board[x][y];
        if(piece instanceof Pawn){
            return "Pawn";
        } else if (piece instanceof King) {
            return "King";
        } else if (piece instanceof Bishop) {
            return "Bishop";
        } else if (piece instanceof Knight) {
            return "Knight";
        } else if (piece instanceof Rook){
            return "Rook";
        } else if (piece instanceof Queen) {
            return "Queen";
        }else if(piece instanceof Closed){
            return "Closed";
        }else {
            return "";
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
