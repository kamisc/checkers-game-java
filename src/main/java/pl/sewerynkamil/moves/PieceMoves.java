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

    public PieceMoves(Board board, Controller controller){
        this.board = board;
        this.controller = controller;
    }

    /*public Set<PositionsPieces> moveBlackAfterKick(PositionsPieces position){
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
    }*/

    public void clear(){
        possibleBlackPieceMoves.clear();
        possibleWhitePieceMoves.clear();

        possibleBlackPieceMovesAfterKick.clear();
        possibleWhitePieceMovesAfterKick.clear();

        allPossibleBlack.clear();
        allPossibleWhite.clear();
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

}