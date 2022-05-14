package Pieces;


//Tower
public class Pawn extends Piece {
    public Pawn(int startX, int startY, boolean playerOne) {
        super(startX, startY, playerOne);
    }

    public void kill(int newX, int newY) throws IllegalArgumentException {
        int[] pos = getPos();
        if (isPlayerOne()) {
            if (pos[0] - 1 == newX && (pos[1] + 1 == newY || pos[1] - 1 == newY)) {
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        } else {
            if (pos[0] + 1 == newX && (pos[1] + 1 == newY || pos[1] - 1 == newY)) {
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        }
    }

    public void move(int newX, int newY, Piece[][] board) throws IllegalArgumentException {
        int[] pos = getPos();
        if (isPlayerOne()) {
            if (pos[1] - 1 == newY && pos[0] == newX) {
                changePos(newX, newY);
            } else if (pos[1] == 6 && pos[1] - 2 == newY && pos[0] == newX) {
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        } else {
            if (pos[1] + 1 == newY && pos[0] == newX) {
                changePos(newX, newY);
            } else if (pos[1] == 1 && pos[1] + 2 == newY && pos[0] == newX) {
                changePos(newX, newY);
            } else {
                throw new IllegalArgumentException("Illegal move");
            }
        }
    }

    @Override
    public String toString() {
        return "Pawn" + (isPlayerOne() ? 1 : 2);
    }
}
