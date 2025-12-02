abstract class Piece {
    protected boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() { return white; }

    abstract boolean canMove(Board board, Spot start, Spot end);

    protected boolean sameColor(Piece a, Piece b) {
        return a != null && b != null && a.white == b.white;
    }
}