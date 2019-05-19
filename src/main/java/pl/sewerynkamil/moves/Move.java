package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

public class Move {

    private PositionsPieces actualPosition;
    private PositionsPieces newPosition;
    private Board board;

    public enum MoveTypes{
        NORMAL, KICK, WRONG
    }

    private MoveTypes moveType;

   /* public Move(PositionsPieces actualPosition, PositionsPieces newPosition){
        this.actualPosition = actualPosition;
        this.newPosition = newPosition;

        board.getPiece(actualPosition);
        checkType();
    }

    private void checkType(){
        if(board.getPiece(actualPosition).getPieceColor().isBlack() && (newPosition.getRow() - actualPosition.getRow()) > 0){
            moveType = MoveTypes.WRONG;
        } else if (!board.getPiece(actualPosition).getPieceColor().isBlack() && (newPosition.getRow() - actualPosition.getRow() < 0)){
            moveType = MoveTypes.WRONG;
        } else if(Math.abs(actualPosition.getRow() - newPosition.getRow()) == 2
                && Math.abs(actualPosition.getCol() - newPosition.getCol()) == 2){
            moveType = MoveTypes.KICK;
        } else if(Math.abs(actualPosition.getRow() - newPosition.getRow()) == 1
                && Math.abs(actualPosition.getCol() - newPosition.getCol()) == 1){
            moveType = MoveTypes.NORMAL;
        } else {
            moveType = MoveTypes.WRONG;
        }
    }*/

}
