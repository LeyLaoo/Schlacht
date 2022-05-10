package Pieces;

//Pferd
public class Knight extends Piece{
    Knight(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY) throws IllegalArgumentException{
        int[] pos = getPos();
        if((pos[0] + 2 == newX || pos[0] -2 == newX) && (pos[1] +1 == newY || pos[1] - 1 == newY)){
            changePos(newX, newY);
        }
    }
}
