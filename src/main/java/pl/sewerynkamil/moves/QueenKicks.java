package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class QueenKicks {

    private Board board;

    private Set<PositionsPieces> possibleKickMoves = new HashSet<>();
    private Set<PositionsPieces> possibleKicks = new HashSet<>();

    public QueenKicks(Board board) {
        this.board = board;
    }

    public void calculateAllPossibleQueenKicks(PositionsPieces position){
        possibleKickMoves.clear();
        possibleKicks.clear();

        for(int i = 1; i < 8; i++){

            PositionsPieces upLeft = new PositionsPieces(position.getCol() - i, position.getRow() - i);

            /*if(calculatePossibleKick(position, upLeft, - 1, - 1)){
                possibleKickMoves.add(new PositionsPieces(upLeft.getCol() - 1, upLeft.getRow() - 1));
                break;
            } else {
                break;
            }*/

            if(upLeft.isValidPosition() && !board.isFieldNull(upLeft)){
                if(board.getPiece(position).getPieceColor() != board.getPiece(upLeft).getPieceColor()){

                    if(board.isFieldNull(new PositionsPieces(upLeft.getCol() - 1, upLeft.getRow() - 1))){
                        possibleKicks.add(upLeft);
                        possibleKickMoves.add(new PositionsPieces(upLeft.getCol() - 1, upLeft.getRow() - 1));
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i++){

            PositionsPieces downLeft = new PositionsPieces(position.getCol() - i, position.getRow() + i);

            /*if(calculatePossibleKick(position, downLeft, - 1, + 1)){
                possibleKickMoves.add(new PositionsPieces(downLeft.getCol() - 1, downLeft.getRow() + 1));
                break;
            } else {
                break;
            }*/

            if(downLeft.isValidPosition() && !board.isFieldNull(downLeft)){
                if(board.getPiece(position).getPieceColor() != board.getPiece(downLeft).getPieceColor()){

                    if(board.isFieldNull(new PositionsPieces(downLeft.getCol() - 1, downLeft.getRow() + 1))){
                        possibleKicks.add(downLeft);
                        possibleKickMoves.add(new PositionsPieces(downLeft.getCol() - 1, downLeft.getRow() + 1));
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i++){

            PositionsPieces upRight = new PositionsPieces(position.getCol() + i, position.getRow() - i);

            /*if(calculatePossibleKick(position, upRight, + 1, - 1)){
                possibleKickMoves.add(new PositionsPieces(upRight.getCol() + 1, upRight.getRow() - 1));
                break;
            } else {
                break;
            }*/

            if(upRight.isValidPosition() && !board.isFieldNull(upRight)){
                if(board.getPiece(position).getPieceColor() != board.getPiece(upRight).getPieceColor()){

                    if(board.isFieldNull(new PositionsPieces(upRight.getCol() + 1, upRight.getRow() - 1))){
                        possibleKicks.add(upRight);
                        possibleKickMoves.add(new PositionsPieces(upRight.getCol() + 1, upRight.getRow() - 1));
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i++){

            PositionsPieces downRight = new PositionsPieces(position.getCol() + i, position.getRow() + i);

            /*if(calculatePossibleKick(position, downRight, + 1, + 1)){
                possibleKickMoves.add(new PositionsPieces(downRight.getCol() + 1, downRight.getRow() + 1));
                break;
            } else {
                break;
            }*/

            if(downRight.isValidPosition() && !board.isFieldNull(downRight)){
                if(board.getPiece(position).getPieceColor() != board.getPiece(downRight).getPieceColor()){

                    if(board.isFieldNull(new PositionsPieces(downRight.getCol() + 1, downRight.getRow() + 1))){
                        possibleKicks.add(downRight);
                        possibleKickMoves.add(new PositionsPieces(downRight.getCol() + 1, downRight.getRow() + 1));
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private boolean calculatePossibleKick(PositionsPieces actualPosition, PositionsPieces checkPosition, int col, int row) {
        if(checkPosition.isValidPosition() && !board.isFieldNull(checkPosition)) {
            if (board.getPiece(actualPosition).getPieceColor() != board.getPiece(checkPosition).getPieceColor()) {
                if (board.isFieldNull(new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row))) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<PositionsPieces> getPossibleKickMoves() {
        return possibleKickMoves;
    }

    public Set<PositionsPieces> getPossibleKicks() {
        return possibleKicks;
    }

    public void clear(){
        possibleKickMoves.clear();
        possibleKicks.clear();
    }
}
