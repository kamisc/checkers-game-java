package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueenKickScanner {

    private Board board;

    private Set<PositionsPieces> allPossibleQueenKicks = new HashSet<>();
    private Set<PositionsPieces> allQueenPiecesWhichKick = new HashSet<>();

    public QueenKickScanner(Board board) {
        this.board = board;
    }

    public void calculateAllPossibleWhiteQueenKicks() {
        allPossibleQueenKicks.clear();
        allQueenPiecesWhichKick.clear();

        for (Map.Entry<PositionsPieces, Piece> whitePiece : board.getBoard().entrySet()) {
            if(whitePiece.getValue().getPieceColor().isWhite() && whitePiece.getValue().getPieceType().isQueen()) {

                calculateAllPossibleQueenKicks(whitePiece.getKey());
            }
        }
    }

    public void calculateAllPossibleBlackQueenKicks() {
        allPossibleQueenKicks.clear();
        allQueenPiecesWhichKick.clear();

        for (Map.Entry<PositionsPieces, Piece> blackPiece : board.getBoard().entrySet()) {
            if(blackPiece.getValue().getPieceColor().isBlack() && blackPiece.getValue().getPieceType().isQueen()) {

                calculateAllPossibleQueenKicks(blackPiece.getKey());
            }
        }
    }

    private void calculateAllPossibleQueenKicks(PositionsPieces position) {
        for(int i = 1; i < 8; i++) {
            PositionsPieces upLeft = new PositionsPieces(position.getCol() - i, position.getRow() - i);

            if(calculatePossibleKick(position, upLeft, - 1, - 1)) {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            PositionsPieces downLeft = new PositionsPieces(position.getCol() - i, position.getRow() + i);

            if(calculatePossibleKick(position, downLeft, - 1, + 1)) {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            PositionsPieces upRight = new PositionsPieces(position.getCol() + i, position.getRow() - i);

            if(calculatePossibleKick(position, upRight, + 1, - 1)) {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            PositionsPieces downRight = new PositionsPieces(position.getCol() + i, position.getRow() + i);

            if(calculatePossibleKick(position, downRight, + 1, + 1)) {
                break;
            }
        }
    }

    private boolean calculatePossibleKick(PositionsPieces actualPosition, PositionsPieces checkPosition, int col, int row) {
        if(!checkPosition.isValidPosition()) {
            return true;
        }

        if(!board.isFieldNull(checkPosition)) {
            if(board.getPiece(actualPosition).getPieceColor() != board.getPiece(checkPosition).getPieceColor()
                    && board.isFieldNull(new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row))
                    && new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row).isValidPosition()) {
                allPossibleQueenKicks.add(checkPosition);
                allQueenPiecesWhichKick.add(actualPosition);
            }
            return true;
        } else {
            return false;
        }
    }

    public Set<PositionsPieces> getAllPossibleQueenKicks() {
        return allPossibleQueenKicks;
    }

    public Set<PositionsPieces> getAllQueenPiecesWhichKick() {
        return allQueenPiecesWhichKick;
    }

    public void clear() {
        allPossibleQueenKicks.clear();
        allQueenPiecesWhichKick.clear();
    }
}
