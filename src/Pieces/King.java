package Pieces;

//König
public class King extends Piece{
    King(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY) throws IllegalArgumentException{
        int[] pos = getPos();
        if(pos[0] - newX >= 1 && pos[0] - newX <= -1 && pos[1] - newY >= 1 && pos[1] - newY <= -1){
            changePos(newX, newY);
        }
    }
}