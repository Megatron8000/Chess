//абстрактный класс для всех фигур

abstract class Piece {
    boolean white;
    public Piece(boolean white) { this.white = white; }
    abstract boolean canMove(Board board, Spot start, Spot end);
}
