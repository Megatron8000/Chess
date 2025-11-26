//абстрактный класс для всех фигур

abstract class Piece {

    boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    abstract boolean canMove(Board board, Spot start, Spot end);

    boolean sameColor(Piece a, Piece b) {
        return a != null && b != null && a.white == b.white;
    }
}
