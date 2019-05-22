package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.game.Controller;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class PieceMoves {

    private Controller controller;
    private Board board;

    private Set<PositionsPieces> possibleBlackPieceMoves = new HashSet<>();
    private Set<PositionsPieces> possibleWhitePieceMoves = new HashSet<>();

    private Set<PositionsPieces> possibleBlackPieceMovesAfterKick = new HashSet<>();
    private Set<PositionsPieces> possibleWhitePieceMovesAfterKick = new HashSet<>();

    private Set<PositionsPieces> allPossibleBlack = new HashSet<>();

    public PieceMoves(Board board, Controller controller){
        this.board = board;
        this.controller = controller;
    }

    public Set<PositionsPieces> moveBlack(PositionsPieces actualPosition) {
        possibleBlackPieceMoves.clear();
        move(actualPosition, possibleBlackPieceMoves, true);
        return possibleBlackPieceMoves;
    }

    public Set<PositionsPieces> moveWhite(PositionsPieces actualPosition){
        possibleWhitePieceMoves.clear();
        move(actualPosition, possibleWhitePieceMoves, false);
        return possibleWhitePieceMoves;
    }

    public Set<PositionsPieces> allPossibleMovingPieces(){
        for(PositionsPieces positionsPieces : board.getBlackPieces().getBlackPiecesMap().keySet()){
            move(positionsPieces, allPossibleBlack, true);
        }
        return allPossibleBlack;
    }

    private void move(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, boolean up) {
        int direction = up ? 1 : -1;

        PositionsPieces left = new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PositionsPieces right = new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        // Move forward-left
        if (left.isValidPosition() && controller.isFieldNull(left)) {
            positionsPieces.add(left);
        }
        // Move forward-right
        if (right.isValidPosition() && controller.isFieldNull(right)) {
            positionsPieces.add(right);
        }
    }

    public Set<PositionsPieces> moveBlackAfterKick(PositionsPieces position){
        possibleBlackPieceMovesAfterKick.clear();

        if(!board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() - 1))
                && board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() - 1))
                && new PositionsPieces(position.getCol() - 1, position.getRow() - 1).isValidPosition()
                && new PositionsPieces(position.getCol() - 2, position.getRow() - 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() - 2, position.getRow() - 2))){

            possibleBlackPieceMovesAfterKick.add(new PositionsPieces(position.getCol() - 2, position.getRow() - 2));
        }

        if(!board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() - 1))
                && board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() - 1))
                && new PositionsPieces(position.getCol() + 1, position.getRow() - 1).isValidPosition()
                && new PositionsPieces(position.getCol() + 2, position.getRow() - 2).isValidPosition()){

            possibleBlackPieceMovesAfterKick.add(new PositionsPieces(position.getCol() + 2, position.getRow() - 2));
        }

        if(!board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() + 1))
                && board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() - 1, position.getRow() + 1))
                && new PositionsPieces(position.getCol() - 1, position.getRow() + 1).isValidPosition()
                && new PositionsPieces(position.getCol() - 2, position.getRow() + 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() - 2, position.getRow() + 2))){

            possibleBlackPieceMovesAfterKick.add(new PositionsPieces(position.getCol() - 2, position.getRow() + 2));
        }

        if(!board.getBlackPieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() + 1))
                && board.getWhitePieces().isFieldNotNull(new PositionsPieces(position.getCol() + 1, position.getRow() + 1))
                && new PositionsPieces(position.getCol() + 1, position.getRow() + 1).isValidPosition()
                && new PositionsPieces(position.getCol() + 2, position.getRow() + 2).isValidPosition()
                && controller.isFieldNull(new PositionsPieces(position.getCol() + 2, position.getRow() + 2))){

            possibleBlackPieceMovesAfterKick.add(new PositionsPieces(position.getCol() + 2, position.getRow() + 2));
        }

        return possibleBlackPieceMovesAfterKick;
    }

    public Set<PositionsPieces> moveWhiteAfterKick(PositionsPieces position){
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
                && new PositionsPieces(position.getCol() + 2, position.getRow() - 2).isValidPosition()){

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
    }

    // King moves - later

    public Set<PositionsPieces> getPossibleBlackPieceMoves() {
        return possibleBlackPieceMoves;
    }

    public Set<PositionsPieces> getPossibleWhitePieceMoves() {
        return possibleWhitePieceMoves;
    }

    public Set<PositionsPieces> getPossibleBlackPieceMovesAfterKick() {
        return possibleBlackPieceMovesAfterKick;
    }

    public Set<PositionsPieces> getPossibleWhitePieceMovesAfterKick() {
        return possibleWhitePieceMovesAfterKick;
    }

    public Set<PositionsPieces> getAllPossibleBlack() {
        return allPossibleBlack;
    }
}

