package Pieces;

//Turm
public class Rook extends Piece{
    public Rook(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY, Piece[][] board) throws IllegalArgumentException, IllegalStateException {
        int[] pos = getPos();
        int xMoveDir = (pos[0] - newX) < 0 ? 1 : -1;
        int yMoveDir = (pos[1] - newY) < 0 ? 1 : -1;
        if (pos[0] == newX) {
            Piece.checkInLine(pos[0], pos[1], newY, xMoveDir, board);
            changePos(newX, newY);
        } else if( pos[1] == newY){
            Piece.checkInLine(pos[0], pos[1], newX, yMoveDir, board);
            changePos(newX, newY);
        }
    }

    @Override
    public String toString() {
        return "Rook" + (isPlayerOne() ? 1 : 2);
    }
}
