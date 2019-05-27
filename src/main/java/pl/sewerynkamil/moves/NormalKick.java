package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
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
        kickMove(position, possibleKickMoves, 1, 1);
        kickMove(position, possibleKickMoves, - 1, - 1);
        kickMove(position, possibleKickMoves, - 1, 1);
        kickMove(position, possibleKickMoves, 1, - 1);
    }

    private void kickMove(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, int col, int row){
        Piece piece = board.getPiece(actualPosition);

        if(!board.isFieldNull(new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row))
                && new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + col).isValidPosition()) {
            if (new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (col * 2)).isValidPosition()
                    && board.isFieldNull(new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (col * 2)))) {
                if(piece.getPieceColor() != board.getPiece(
                        new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row)).getPieceColor()){

                    positionsPieces.add(new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (col * 2)));
                }
            }
        }
    }

    public Set<PositionsPieces> getPossibleKickMoves() {
        return possibleKickMoves;
    }

    /*   public Set<PositionsPieces> moveWhiteAfterKick(PositionsPieces position){
        possibleWhitePieceMovesAfterKick.clear();

        if(!board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() - 1))
                && board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() - 1))
                && new PositionsPieces(position.getCol() - 1, position.getRow() - 1).isValidPosition()
                && new PositionsPieces(position.getCol() - 2, position.getRow() - 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() - 2, position.getRow() - 2))){

            possibleWhitePieceMovesAfterKick.add(new PositionsPieces(position.getCol() - 2, position.getRow() - 2));
        }

        if(!board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() - 1))
                && board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() - 1))
                && new PositionsPieces(position.getCol() + 1, position.getRow() - 1).isValidPosition()
                && new PositionsPieces(position.getCol() + 2, position.getRow() - 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() + 2, position.getRow() - 2))){

            possibleWhitePieceMovesAfterKick.add(new PositionsPieces(position.getCol() + 2, position.getRow() - 2));
        }

        if(!board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() + 1))
                && board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() + 1))
                && new PositionsPieces(position.getCol() - 1, position.getRow() + 1).isValidPosition()
                && new PositionsPieces(position.getCol() - 2, position.getRow() + 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() - 2, position.getRow() + 2))){

            possibleWhitePieceMovesAfterKick.add(new PositionsPieces(position.getCol() - 2, position.getRow() + 2));
        }

        if(!board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() + 1))
                && board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() + 1))
                && new PositionsPieces(position.getCol() + 1, position.getRow() + 1).isValidPosition()
                && new PositionsPieces(position.getCol() + 2, position.getRow() + 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() + 2, position.getRow() + 2))){

            possibleWhitePieceMovesAfterKick.add(new PositionsPieces(position.getCol() + 2, position.getRow() + 2));
        }

        return possibleWhitePieceMovesAfterKick;
    }*/
}
