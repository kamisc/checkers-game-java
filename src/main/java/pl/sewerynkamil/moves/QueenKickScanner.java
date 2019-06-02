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
            if(whitePiece.getValue().getPieceColor().isWhite() && whitePiece.getValue().getPieceType().isQueen()){

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

            if(upLeft.isValidPosition()
                    && !board.isFieldNull(upLeft)) {
                if(new PositionsPieces(upLeft.getCol() + i, upLeft.getRow() + i).isValidPosition()
                        && board.isFieldNull(new PositionsPieces(upLeft.getCol() + i, upLeft.getRow() + i))) {
                    if(board.getPiece(position).getPieceColor() != board.getPiece(upLeft).getPieceColor()) {
                        allPossibleQueenKicks.add(upLeft);
                        allQueenPiecesWhichKick.add(position);
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i++) {
            PositionsPieces downLeft = new PositionsPieces(position.getCol() - i, position.getRow() + i);

            if(downLeft.isValidPosition()
                    && !board.isFieldNull(downLeft)) {
                if(new PositionsPieces(downLeft.getCol() + i, downLeft.getRow() + i).isValidPosition()
                        && board.isFieldNull(new PositionsPieces(downLeft.getCol() + i, downLeft.getRow() + i))) {
                    if(board.getPiece(position).getPieceColor() != board.getPiece(downLeft).getPieceColor()) {
                        allPossibleQueenKicks.add(downLeft);
                        allQueenPiecesWhichKick.add(position);
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i++) {
            PositionsPieces upRight = new PositionsPieces(position.getCol() + i, position.getRow() - i);

            if(upRight.isValidPosition()
                    && !board.isFieldNull(upRight)) {
                if(new PositionsPieces(upRight.getCol() + i, upRight.getRow() + i).isValidPosition()
                        && board.isFieldNull(new PositionsPieces(upRight.getCol() + i, upRight.getRow() + i))) {
                    if(board.getPiece(position).getPieceColor() != board.getPiece(upRight).getPieceColor()) {
                        allPossibleQueenKicks.add(upRight);
                        allQueenPiecesWhichKick.add(position);
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i++) {
            PositionsPieces downRight = new PositionsPieces(position.getCol() + i, position.getRow() + i);

            if(downRight.isValidPosition()
                    && !board.isFieldNull(downRight)) {
                if(new PositionsPieces(downRight.getCol() + i, downRight.getRow() + i).isValidPosition()
                        && board.isFieldNull(new PositionsPieces(downRight.getCol() + i, downRight.getRow() + i))) {
                    if(board.getPiece(position).getPieceColor() != board.getPiece(downRight).getPieceColor()) {
                        allPossibleQueenKicks.add(downRight);
                        allQueenPiecesWhichKick.add(position);
                    }
                } else {
                    break;
                }
            }
        }
    }

    private boolean calculatePossibleKick(PositionsPieces actualPosition, PositionsPieces checkPosition, int col, int row) {
        if(checkPosition.isValidPosition()
                && !board.isFieldNull(checkPosition)
                && new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row).isValidPosition()
                && board.isFieldNull(new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row))) {
            if(board.getPiece(actualPosition).getPieceColor() != board.getPiece(checkPosition).getPieceColor()) {
                return true;
            }
        }
        return false;
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
