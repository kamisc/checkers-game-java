package pl.sewerynkamil.pieces;

public class Piece {

    public enum Color{
        BLACK, WHITE, QUEEN_BLACK, QUEEN_WHITE;

        public boolean isWhite(){
            return this == WHITE;
        }

        public boolean isBlack(){
            return this == BLACK;
        }

        public boolean isQueenBlack(){
            return this == QUEEN_BLACK;
        }

        public boolean isQueenWhite(){
            return this == QUEEN_WHITE;
        }
    }

    private Color pieceColor;

    public Piece(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceColor=" + pieceColor +
                '}';
    }
}
