package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.game.Controller;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;
import pl.sewerynkamil.pieces.WhitePieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KickScanner {

    // Zdefiniować jako metoda, która zwraca

    private Board board;
    private Controller controller;
    private WhitePieces whitePieces;

    private Set<PositionsPieces> allPossibleBlackKicks = new HashSet<>();
    private Set<PositionsPieces> allPossibleWhiteKicks = new HashSet<>();

    private Set<PositionsPieces> allPossibleBlackMovesAfterKick = new HashSet<>();
    private Set<PositionsPieces> allPossibleWhiteMovesAfterKick = new HashSet<>();

    private Set<PositionsPieces> allBlackPiecesWhichKick = new HashSet<>();
    private Set<PositionsPieces> allWhitePiecesWhichKick = new HashSet<>();

    public KickScanner(Board board, Controller controller) {
        this.board = board;
        this.controller = controller;
    }

    // Calculate all possible black kick moves (position where black pieces have white piece around)
    public void calculateAllPossibleBlackKicks() {
        allPossibleBlackKicks.clear();
        allPossibleBlackMovesAfterKick.clear();
        allBlackPiecesWhichKick.clear();

        for (Map.Entry<PositionsPieces, Piece> blackPiece : board.getBlackPieces().getBlackPiecesMap().entrySet()) {
            PositionsPieces key = blackPiece.getKey();
            int col = key.getCol();
            int row = key.getRow();

            calculateAllPossibleKicks(key, col, board.getWhitePieces(), row + 1, row + 2, row - 1, row - 2);
        }
        allPossibleBlackKicks.removeAll(board.getBlackPieces().getBlackPiecesMap().keySet());
        allPossibleBlackMovesAfterKick.removeAll(board.getBlackPieces().getBlackPiecesMap().keySet());
    }

    private void calculateAllPossibleKicks(PositionsPieces key, int col, WhitePieces oppositePieces, int nextRow, int secondNextRow, int backRow, int secondBackRow) {
        calculatePossibleKicks(key, oppositePieces, nextRow, secondNextRow, col - 1, col - 2, col + 1, col + 2);
        calculatePossibleKicks(key, oppositePieces, backRow, secondBackRow, col - 1, col - 2, col + 1, col + 2);
    }

    private void calculatePossibleKicks(PositionsPieces key, WhitePieces oppositePieces, int nextRow, int secondNextRow, int leftCol, int secondLeftCol, int rightCol, int secondRightCol) {
        calculatePossibleKick(key, oppositePieces, nextRow, secondNextRow, leftCol, secondLeftCol);
        calculatePossibleKick(key, oppositePieces, nextRow, secondNextRow, rightCol, secondRightCol);
    }

    private void calculatePossibleKick(PositionsPieces key, WhitePieces oppositePieces, int nextRow, int secondNextRow, int rightCol, int secondRightCol) {
        if (new PositionsPieces(rightCol, nextRow).isValidPosition()
                && oppositePieces.isFieldNotNull(new PositionsPieces(rightCol, nextRow))
                && controller.isFieldNull(new PositionsPieces(secondRightCol, secondNextRow))
                && new PositionsPieces(secondRightCol, secondNextRow).isValidPosition()) {

            allPossibleBlackKicks.add(new PositionsPieces(rightCol, nextRow));
            allPossibleBlackMovesAfterKick.add(new PositionsPieces(secondRightCol, secondNextRow));
            allBlackPiecesWhichKick.add(key);
        }
    }

    // Calculate all possible white kick moves (position where white pieces have black piece around)
    public void calculateAllPossibleWhiteKicks() {
        allPossibleWhiteKicks.clear();
        allPossibleWhiteMovesAfterKick.clear();
        allWhitePiecesWhichKick.clear();

        for (Map.Entry<PositionsPieces, Piece> whitePiece : board.getWhitePieces().getWhitePiecesMap().entrySet()) {
            if (new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1))
                    && controller.isFieldNull(new PositionsPieces(whitePiece.getKey().getCol() - 2, whitePiece.getKey().getRow() - 2))
                    && new PositionsPieces(whitePiece.getKey().getCol() - 2, whitePiece.getKey().getRow() - 2).isValidPosition()) {

                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() - 1));
                allPossibleWhiteMovesAfterKick.add(new PositionsPieces(whitePiece.getKey().getCol() - 2, whitePiece.getKey().getRow() - 2));
                allWhitePiecesWhichKick.add(whitePiece.getKey());

            }

            if (new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1))
                    && controller.isFieldNull(new PositionsPieces(whitePiece.getKey().getCol() + 2, whitePiece.getKey().getRow() - 2))
                    && new PositionsPieces(whitePiece.getKey().getCol() + 2, whitePiece.getKey().getRow() - 2).isValidPosition()) {

                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() - 1));
                allPossibleWhiteMovesAfterKick.add(new PositionsPieces(whitePiece.getKey().getCol() + 2, whitePiece.getKey().getRow() - 2));
                allWhitePiecesWhichKick.add(whitePiece.getKey());

            }

            if (new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1))
                    && controller.isFieldNull(new PositionsPieces(whitePiece.getKey().getCol() - 2, whitePiece.getKey().getRow() + 2))
                    && new PositionsPieces(whitePiece.getKey().getCol() - 2, whitePiece.getKey().getRow() + 2).isValidPosition()) {

                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() - 1, whitePiece.getKey().getRow() + 1));
                allPossibleWhiteMovesAfterKick.add(new PositionsPieces(whitePiece.getKey().getCol() - 2, whitePiece.getKey().getRow() + 2));
                allWhitePiecesWhichKick.add(whitePiece.getKey());

            }

            if (new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1).isValidPosition()
                    && board.getBlackPieces().isFieldNotNull(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1))
                    && controller.isFieldNull(new PositionsPieces(whitePiece.getKey().getCol() + 2, whitePiece.getKey().getRow() + 2))
                    && new PositionsPieces(whitePiece.getKey().getCol() + 2, whitePiece.getKey().getRow() + 2).isValidPosition()) {

                allPossibleWhiteKicks.add(new PositionsPieces(whitePiece.getKey().getCol() + 1, whitePiece.getKey().getRow() + 1));
                allPossibleWhiteMovesAfterKick.add(new PositionsPieces(whitePiece.getKey().getCol() + 2, whitePiece.getKey().getRow() + 2));
                allWhitePiecesWhichKick.add(whitePiece.getKey());

            }
        }
        allPossibleWhiteKicks.removeAll(board.getWhitePieces().getWhitePiecesMap().keySet());
        allPossibleWhiteMovesAfterKick.removeAll(board.getWhitePieces().getWhitePiecesMap().keySet());
    }

    public void clear(){
        allPossibleBlackKicks.clear();
        allPossibleWhiteKicks.clear();
        allBlackPiecesWhichKick.clear();
        allWhitePiecesWhichKick.clear();
        allPossibleBlackMovesAfterKick.clear();
        allPossibleWhiteMovesAfterKick.clear();
    }

    public Set<PositionsPieces> getAllPossibleBlackKicks() {
        return allPossibleBlackKicks;
    }

    public Set<PositionsPieces> getAllPossibleWhiteKicks() {
        return allPossibleWhiteKicks;
    }

    public Set<PositionsPieces> getAllPossibleBlackMovesAfterKick() {
        return allPossibleBlackMovesAfterKick;
    }

    public Set<PositionsPieces> getAllPossibleWhiteMovesAfterKick() {
        return allPossibleWhiteMovesAfterKick;
    }

    public Set<PositionsPieces> getAllBlackPiecesWhichKick() {
        return allBlackPiecesWhichKick;
    }

    public Set<PositionsPieces> getAllWhitePiecesWhichKick() {
        return allWhitePiecesWhichKick;
    }
}

