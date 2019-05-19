package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

public class Controller {

    private Board board;

    public Controller(Board board) {
        this.board = board;
    }

    // You can pick black piece if on the checkPosition is any black piece
    public boolean checkCanSelectBlackPiece(PositionsPieces checkPosition){
        return board.getBlackPieces().getBlackPiecesMap().containsKey(checkPosition);
    }

    // You can pick white piece if on the checkPosition is any white piece
    public boolean checkCanSelectWhitePiece(PositionsPieces checkPosition){
        return board.getWhitePieces().getWhitePiecesMap().containsKey(checkPosition);
    }

    // Return true if there is no piece in the newPosition
    public boolean isFieldNull(PositionsPieces newPosition){
        return !board.getBlackPieces().getBlackPiecesMap().containsKey(newPosition) &&
                !board.getWhitePieces().getWhitePiecesMap().containsKey(newPosition);
    }

 /*   public boolean check(){

    }*/
}

