package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MovesCalculator {

    private Board board;

    private Set<PositionsPieces> allPossibleBlackKicks = new HashSet<>();
    private Set<PositionsPieces> allPossibleWhiteKicks = new HashSet<>();

    private Set<PositionsPieces> allPossibleBlackMoves = new HashSet<>();
    private Set<PositionsPieces> allPossibleWhiteMoves = new HashSet<>();

    private Set<PositionsPieces> blackPiecesToKick = new HashSet<>();
    private Set<PositionsPieces> whitePiecesToKick = new HashSet<>();

    private Set<PositionsPieces> allPossibleBlackKickMoves = new HashSet<>();
    private Set<PositionsPieces> allPossibleWhiteKickMoves = new HashSet<>();

    public MovesCalculator(Board board){
        this.board = board;
    }

    public boolean isValidPosition(PositionsPieces position){
        return position.getCol() >= 0 && position.getCol() <= 7 && position.getRow() >= 0 && position.getRow() <= 7;
    }

    // Calculate all possible black moves
    public Set<PositionsPieces> calculateAllPossibleBlackMoves() {
        for(PositionsPieces position : board.getBlackPieces().getBlackPiecesMap().keySet()){
            if(isValidPosition(new PositionsPieces(position.getCol() - 1, position.getRow() + 1))){
                allPossibleBlackMoves.add(new PositionsPieces(position.getCol() - 1, position.getRow() + 1));
            }

            if(isValidPosition(new PositionsPieces(position.getCol() + 1, position.getRow() + 1))){
                allPossibleBlackMoves.add(new PositionsPieces(position.getCol() + 1, position.getRow() + 1));
            }
        }
        allPossibleBlackMoves.removeAll(board.getBlackPieces().getBlackPiecesMap().keySet());
        allPossibleBlackMoves.removeAll(board.getWhitePieces().getWhitePiecesMap().keySet());

        return allPossibleBlackMoves;
    }

    // Calculate all possible white moves
    public Set<PositionsPieces> calculateAllPossibleWhiteMoves() {
        for(PositionsPieces position : board.getWhitePieces().getWhitePiecesMap().keySet()){
            if(isValidPosition(new PositionsPieces(position.getCol() - 1, position.getRow() - 1))){
                allPossibleWhiteMoves.add(new PositionsPieces(position.getCol() - 1, position.getRow() - 1));
            }

            if(isValidPosition(new PositionsPieces(position.getCol() + 1, position.getRow() - 1))){
                allPossibleWhiteMoves.add(new PositionsPieces(position.getCol() + 1, position.getRow() - 1));
            }
        }
        allPossibleWhiteMoves.removeAll(board.getWhitePieces().getWhitePiecesMap().keySet());
        allPossibleWhiteMoves.removeAll(board.getBlackPieces().getBlackPiecesMap().keySet());

        return allPossibleWhiteMoves;
    }

    // Calculate all possible black kick moves (position where black pieces can go around)
    public Set<PositionsPieces> calculateAllPossibleBlackKicks() {

        for (Map.Entry<PositionsPieces, Piece> blackPiece : board.getBlackPieces().getBlackPiecesMap().entrySet()) {
            if(isValidPosition(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() + 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() + 1));
            }

            if(isValidPosition(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() + 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() + 1));
            }

            if(isValidPosition(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() - 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() - 1));
            }

            if(isValidPosition(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() - 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() - 1));
            }
        }
        allPossibleBlackKicks.removeAll(board.getBlackPieces().getBlackPiecesMap().keySet());

        return allPossibleBlackKicks;
    }

    // Calculate all possible white kick moves (position where white pieces can go around)
    public Set<PositionsPieces> calculateAllPossibleWhiteKicks() {

        for (Map.Entry<PositionsPieces, Piece> whitePiece : board.getWhitePieces().getWhitePiecesMap().entrySet()) {
            if(isValidPosition(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1));
            }

            if(isValidPosition(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1));
            }

            if(isValidPosition(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1));
            }

            if(isValidPosition(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1));
            }
        }
        allPossibleWhiteKicks.removeAll(board.getWhitePieces().getWhitePiecesMap().keySet());

        return allPossibleWhiteKicks;
    }

    public Set<PositionsPieces> calculateAllPossibleBlackKickMoves(){
        for(PositionsPieces actualPosition : board.getBlackPieces().getBlackPiecesMap().keySet()){
            if (isValidPosition(new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() + 2))) {
                allPossibleBlackKickMoves.add(new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() + 2));
            }

            if (isValidPosition(new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() + 2))) {
                allPossibleBlackKickMoves.add(new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() + 2));
            }

            if (isValidPosition(new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() - 2))) {
                allPossibleBlackKickMoves.add(new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() - 2));
            }

            if (isValidPosition(new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() - 2))) {
                allPossibleBlackKickMoves.add(new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() - 2));
            }
        }
        return allPossibleBlackKickMoves;
    }

    // Calculate white pieces to kicked
    public Set<PositionsPieces> setWhitePieceToKick(){
        calculateAllPossibleBlackKicks();
        Set<PositionsPieces> piecesToKick = board.getWhitePieces().getWhitePiecesMap().keySet().stream()
                .filter(allPossibleBlackKicks::contains)
                .collect(Collectors.toSet());
        return piecesToKick;
        // whitePiecesToKick.addAll(piecesToKick);
    }

    public Set<PositionsPieces> setBlackPiecesWhichCanKick(){
        calculateAllPossibleWhiteKicks();
        Set<PositionsPieces> piecesWhichKick = board.getBlackPieces().getBlackPiecesMap().keySet().stream()
                .filter(allPossibleWhiteKicks::contains)
                .collect(Collectors.toSet());
        // board.getPickedPiece().addAll(piecesWhichKick);
        return piecesWhichKick;
    }

}
