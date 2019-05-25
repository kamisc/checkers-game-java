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
    private Set<PositionsPieces> allPossibleWhite = new HashSet<>();

    private Set<PositionsPieces> allPossibleWhiteQueen = new HashSet<>();

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

    public void allPossibleBlackMoves(){
        allPossibleBlack.clear();
        for (PositionsPieces position : board.getBlackPieces().getBlackPiecesMap().keySet()){
            moveBlack(position);
            for(PositionsPieces positions : possibleBlackPieceMoves){
                if(positions != null){
                    allPossibleBlack.add(position);
                }
            }
        }
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
    }

    public void clear(){
        possibleBlackPieceMoves.clear();
        possibleWhitePieceMoves.clear();

        possibleBlackPieceMovesAfterKick.clear();
        possibleWhitePieceMovesAfterKick.clear();

        allPossibleBlack.clear();
        allPossibleWhite.clear();
    }

    public void queenMoves(PositionsPieces actualPosition){

        for(int i = 0; i < 8; i++){
            PositionsPieces upLeft = new PositionsPieces(actualPosition.getCol() - i, actualPosition.getRow() - i);
            PositionsPieces upRight = new PositionsPieces(actualPosition.getCol() + i, actualPosition.getRow() - i);
            PositionsPieces downLeft = new PositionsPieces(actualPosition.getCol() + i, actualPosition.getRow() + i);
            PositionsPieces downRight = new PositionsPieces(actualPosition.getCol() - i, actualPosition.getRow() + i);

            if (upLeft.isValidPosition() && controller.isFieldNull(upLeft)) {
                allPossibleWhiteQueen.add(upLeft);
            }

            if (upRight.isValidPosition() && controller.isFieldNull(upRight)) {
                allPossibleWhiteQueen.add(upRight);
            }

            if (downLeft.isValidPosition() && controller.isFieldNull(downLeft)) {
                allPossibleWhiteQueen.add(downLeft);
            }

            if (downRight.isValidPosition() && controller.isFieldNull(downRight)) {
                allPossibleWhiteQueen.add(downRight);
            }
        }
    }

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

    public Set<PositionsPieces> getAllPossibleWhiteQueen() {
        return allPossibleWhiteQueen;
    }
}

// Przerobić gettery - zmienna prywatna - wielokrotnie obliczanie pozycji - przy każdym wywołaniu w MouseControll
