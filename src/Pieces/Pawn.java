package Pieces;

//Tower
public class Pawn extends Piece{
    public Pawn(int startX, int startY, boolean playerOne){
        super(startX, startY, playerOne);
    }

    public void move(int newX, int newY) throws IllegalArgumentException{
        int[] pos = getPos();
        if(isPlayerOne()){
            if(pos[0] == newX && pos[1]+1 == newY){
                changePos(newX, newY);
            }
        }else{
            if(pos[0] == newX && pos[1]-1 == newY){
                changePos(newX, newY);
            }
        }
    }
}
