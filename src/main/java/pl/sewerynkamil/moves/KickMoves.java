package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;

public class KickMoves {

    private Board board;

    public KickMoves(Board board) {
        this.board = board;
    }

    /*public Set<PositionsPieces> moveWhiteAfterKick(PositionsPieces position){
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
