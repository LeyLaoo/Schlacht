package Pieces;

/**
 * An exception to be thrown when a move isn't allowed
 */
public class IllegalMoveException extends Exception{
    public IllegalMoveException(){
        super();
    }
    public IllegalMoveException(String message){
        super(message);
    }
}
