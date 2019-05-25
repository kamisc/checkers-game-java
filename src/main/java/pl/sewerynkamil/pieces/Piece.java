package pl.sewerynkamil.pieces;

public class Piece {

    public enum Color{
        BLACK, WHITE, KING_BLACK, KING_WHITE;

        public boolean isWhite(){
            return this == WHITE;
        }

        public boolean isBlack(){
            return this == BLACK;
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
