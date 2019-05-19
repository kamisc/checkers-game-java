package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    // Calculate all possible black kick moves (position where black pieces can go around)
    public Set<PositionsPieces> calculateAllPossibleBlackKicks() {
        for (Map.Entry<PositionsPieces, Piece> blackPiece : board.getBlackPieces().getBlackPiecesMap().entrySet()) {
            if(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() + 1).isValidPosition()
                    && board.getWhitePieces().isFieldNotNull(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() + 1))){

                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() + 1));
            }

            if(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() + 1).isValidPosition()
                    && board.getWhitePieces().isFieldNotNull(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() + 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() + 1));
            }

            if(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() - 1).isValidPosition()
                    && board.getWhitePieces().isFieldNotNull(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() - 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() - 1, blackPiece.getKey().getRow() - 1));
            }

            if(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() - 1).isValidPosition()
                    && board.getWhitePieces().isFieldNotNull(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() - 1))){
                allPossibleBlackKicks.add(new PositionsPieces(blackPiece.getKey().getCol() + 1, blackPiece.getKey().getRow() - 1));
            }
        }
        allPossibleBlackKicks.removeAll(board.getBlackPieces().getBlackPiecesMap().keySet());

        return allPossibleBlackKicks;
    }



    // Calculate all possible white kick moves (position where white pieces can go around)
    public Set<PositionsPieces> calculateAllPossibleWhiteKicks() {

        for (Map.Entry<PositionsPieces, Piece> whitePiece : board.getWhitePieces().getWhitePiecesMap().entrySet()) {
            if(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1))){

                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1));
            }

            if(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1));
            }

            if(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1));
            }

            if(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1))){
                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1));
            }
        }
        allPossibleWhiteKicks.removeAll(board.getWhitePieces().getWhitePiecesMap().keySet());

        return allPossibleWhiteKicks;
    }

    /*// Calculate white pieces to kicked
    public Set<PositionsPieces> setWhitePieceToKick(){
        calculateAllPossibleBlackKicks();
        Set<PositionsPieces> piecesToKick = board.getWhitePieces().getWhitePiecesMap().keySet().stream()
                .filter(allPossibleBlackKicks::contains)
                .collect(Collectors.toSet());
        return piecesToKick;
        // whitePiecesToKick.addAll(piecesToKick);
    }

    // Calculate black pieces to kicked
    public Set<PositionsPieces> setBlackPieceToKick(){
        calculateAllPossibleWhiteKicks();
        Set<PositionsPieces> piecesToKick = board.getBlackPieces().getBlackPiecesMap().keySet().stream()
                .filter(allPossibleWhiteKicks::contains)
                .collect(Collectors.toSet());
        return piecesToKick;
    }

    // Calculate black pieces which kick
    public Set<PositionsPieces> setBlackPiecesWhichCanKick(){
        calculateAllPossibleWhiteKicks();
        Set<PositionsPieces> piecesWhichKick = board.getBlackPieces().getBlackPiecesMap().keySet().stream()
                .filter(allPossibleWhiteKicks::contains)
                .collect(Collectors.toSet());
        // board.getPickedPiece().addAll(piecesWhichKick);
        return piecesWhichKick;
    }

    // Calculate white pieces which kick
    public Set<PositionsPieces> setWhitePiecesWhichCanKick(){
        calculateAllPossibleBlackKicks();
        Set<PositionsPieces> piecesWhichKick = board.getWhitePieces().getWhitePiecesMap().keySet().stream()
                .filter(allPossibleBlackKicks::contains)
                .collect(Collectors.toSet());
        // board.getPickedPiece().addAll(piecesWhichKick);
        return piecesWhichKick;
    }

    public void kickPossibility(Set<PositionsPieces> kickerPositions, Set<PositionsPieces> kickedPositions){
        for(PositionsPieces kicker : kickerPositions){
            for(PositionsPieces kicked : kickedPositions){
                int positionCol = kicker.getCol() - kicked.getCol();
                int positionRow = kicker.getRow() - kicked.getRow();
                if(positionCol == 1 && positionRow == -1){

                }
            }
        }
    }

    public int kickDirection(PositionsPieces kickerPosition, PositionsPieces kickedPosition){
        int positionCol = kickerPosition.getCol() - kickedPosition.getCol();
        int positionRow = kickerPosition.getRow() - kickedPosition.getRow();

        if(positionCol == 1 && positionRow == - 1){
            return 1;
        } else if(positionCol == - 1 && positionRow == - 1){
            return 2;
        } else if(positionCol == 1 && positionRow == 1){
            return 3;
        } else if(positionCol == - 1 && positionRow == 1){
            return 4;
        } else {
            return 0;
        }
    }*/

}
