package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class NormalKick {

    private Board board;

    private Set<PositionsPieces> possibleKickMoves = new HashSet<>();

    public NormalKick(Board board) {
        this.board = board;
    }

    public void kickMovesCalculator(PositionsPieces position){
        possibleKickMoves.clear();

        if(kickMove(position, 1, 1)){
            possibleKickMoves.add(new PositionsPieces(position.getCol() + 2, position.getRow() + 2));
        }

        if(kickMove(position, - 1, - 1)){
            possibleKickMoves.add(new PositionsPieces(position.getCol() - 2, position.getRow() - 2));
        }

        if(kickMove(position, 1, - 1)){
            possibleKickMoves.add(new PositionsPieces(position.getCol() + 2, position.getRow() - 2));
        }

        if(kickMove(position, - 1, 1)){
            possibleKickMoves.add(new PositionsPieces(position.getCol() - 2, position.getRow() + 2));
        }
    }

    private boolean kickMove(PositionsPieces actualPosition, int col, int row){

        if(new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row).isValidPosition() &&
                !board.isFieldNull(new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row))) {

            if (new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)).isValidPosition()
                    && board.isFieldNull(new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)))) { // tu szukaj błędu

                if(board.getPiece(actualPosition).getPieceColor() != board.getPiece(
                        new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row)).getPieceColor()){

                    return true;
                }
            }
        }
        return false;
    }

    public Set<PositionsPieces> getPossibleKickMoves() {
        return possibleKickMoves;
    }
}
