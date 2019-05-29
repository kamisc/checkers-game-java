package pl.sewerynkamil.pieces;

public class Piece {

    private Color pieceColor;
    private Type pieceType;

    public Piece(Color pieceColor, Type pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public enum Color{
        BLACK, WHITE;

        public boolean isWhite(){
            return this == WHITE;
        }

        public boolean isBlack(){
            return this == BLACK;
        }
    }

    public enum Type{
        NORMAL, QUEEN;

        public boolean isNormal(){
            return this == NORMAL;
        }

        public boolean isQueen(){
            return this == QUEEN;
        }
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceColor=" + pieceColor +
                ", pieceType=" + pieceType +
                '}';
    }
}
