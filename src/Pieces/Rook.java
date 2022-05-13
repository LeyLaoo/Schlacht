package Pieces;

//Turm
public class Rook extends Piece{
    public Rook(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY) throws IllegalArgumentException{
        int[] pos = getPos();
        if(pos[0] == newX || pos[1] == newY){
            changePos(newX, newY);
        }
    }

    @Override
    public String toString() {
        return "Rook" + (isPlayerOne() ? 1 : 2);
    }
}
