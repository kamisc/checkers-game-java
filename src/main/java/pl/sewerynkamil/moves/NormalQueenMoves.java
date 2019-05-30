package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class NormalQueenMoves {

    private Board board;

    private Set<PositionsPieces> possibleQueenMoves = new HashSet<>();
    private Set<PositionsPieces> possibleQueenKicks = new HashSet<>();

    public NormalQueenMoves(Board board) {
        this.board = board;
    }

    public void normalQueenMoveCalculator(PositionsPieces position){

        for(int i = 1; i < 8; i ++){
            PositionsPieces upLeft = new PositionsPieces(position.getCol() - i, position.getRow() - i);

            if(queenNormalMove(upLeft)){
                possibleQueenMoves.add(upLeft);
            } else {
                if(board.getBoard().containsValue(upLeft)){
                    possibleQueenKicks.add(upLeft);
                } else {
                    break;
                }
            }
        }

        for(int i = 1; i < 8; i ++){
            PositionsPieces downLeft = new PositionsPieces(position.getCol() - i, position.getRow() + i);

            if(queenNormalMove(downLeft)){
                possibleQueenMoves.add(downLeft);
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i ++){
            PositionsPieces upRight = new PositionsPieces(position.getCol() + i, position.getRow() - i);

            if(queenNormalMove(upRight)){
                possibleQueenMoves.add(upRight);
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i ++){
            PositionsPieces downRight = new PositionsPieces(position.getCol() + i, position.getRow() + i);

            if(queenNormalMove(downRight)){
                possibleQueenMoves.add(downRight);
            } else {
                break;
            }
        }
    }

    private boolean queenNormalMove(PositionsPieces position){
        if (position.isValidPosition()) {
            if(board.isFieldNull(position)){
                return true;
            }
        }
        return false;
    }

    public Set<PositionsPieces> getPossibleQueenMoves() {
        return possibleQueenMoves;
    }

    public Set<PositionsPieces> getPossibleQueenKicks() {
        return possibleQueenKicks;
    }
}
