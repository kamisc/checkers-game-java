package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author Kamil Seweryn
 */

public class KickScanner {

    private Board board;

    private Set<PositionsPieces> allPossibleKicks = new HashSet<>();
    private Set<PositionsPieces> allPiecesWhichKick = new HashSet<>();

    public KickScanner(Board board) {
        this.board = board;
    }

    public void calculateAllPossibleWhiteKicks() {
        allPossibleKicks.clear();
        allPiecesWhichKick.clear();

        for (Map.Entry<PositionsPieces, Piece> whitePiece : board.getBoard().entrySet()) {
            if(whitePiece.getValue().getPieceColor().isWhite() && whitePiece.getValue().getPieceType().isNormal()){
                int col = whitePiece.getKey().getCol();
                int row = whitePiece.getKey().getRow();

                calculateAllPossibleKicks(whitePiece.getKey(), col, row + 1, row + 2, row - 1, row - 2);
            }
        }
    }

    public void calculateAllPossibleBlackKicks() {
        allPossibleKicks.clear();
        allPiecesWhichKick.clear();

        for (Map.Entry<PositionsPieces, Piece> blackPiece : board.getBoard().entrySet()) {
            if(blackPiece.getValue().getPieceColor().isBlack() && blackPiece.getValue().getPieceType().isNormal()){
                PositionsPieces key = blackPiece.getKey();
                int col = key.getCol();
                int row = key.getRow();

                calculateAllPossibleKicks(key, col, row + 1, row + 2, row - 1, row - 2);
            }
        }
    }

    private void calculateAllPossibleKicks(PositionsPieces key, int col, int nextRow, int secondNextRow, int backRow, int secondBackRow) {
        calculatePossibleKicks(key, nextRow, secondNextRow, col - 1, col - 2, col + 1, col + 2);
        calculatePossibleKicks(key, backRow, secondBackRow, col - 1, col - 2, col + 1, col + 2);
    }

    private void calculatePossibleKicks(PositionsPieces key, int nextRow, int secondNextRow, int leftCol, int secondLeftCol, int rightCol, int secondRightCol) {
        calculatePossibleKick(key, nextRow, secondNextRow, leftCol, secondLeftCol);
        calculatePossibleKick(key, nextRow, secondNextRow, rightCol, secondRightCol);
    }

    private void calculatePossibleKick(PositionsPieces key, int nextRow, int secondNextRow, int rightCol, int secondRightCol) {
        if (new PositionsPieces(rightCol, nextRow).isValidPosition()
                && !board.isFieldNull(new PositionsPieces(rightCol, nextRow))
                && board.isFieldNull(new PositionsPieces(secondRightCol, secondNextRow))
                && new PositionsPieces(secondRightCol, secondNextRow).isValidPosition()
                && board.getPiece(new PositionsPieces(rightCol, nextRow)).getPieceColor() != board.getPiece(key).getPieceColor()) {

            allPossibleKicks.add(new PositionsPieces(rightCol, nextRow));
            allPiecesWhichKick.add(key);
        }
    }

    public void clear() {
        allPossibleKicks.clear();
        allPiecesWhichKick.clear();
    }

    public Set<PositionsPieces> getAllPossibleKicks() {
        return allPossibleKicks;
    }

    public Set<PositionsPieces> getAllPiecesWhichKick() {
        return allPiecesWhichKick;
    }
}