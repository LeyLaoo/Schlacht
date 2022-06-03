package Pieces;

/**
 * A piece that can't be moved by either player and just blocks a field forever
 */
public class Closed extends Piece{

    public Closed(int startX, int startY){
        super(startX, startY, true);
    }

    @Override
    public String toString() {
        //noinspection SpellCheckingInspection
        return "XXXXXXXX";
    }

    @Override
    public void move(int newX, int newY, Piece[][] board) throws IllegalMoveException {
        throw new IllegalMoveException();
    }
}
