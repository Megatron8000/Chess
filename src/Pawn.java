class Pawn extends Piece{
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    boolean canMove(Board board, Spot start, Spot end) {
        if (sameColor(start.getPiece(), end.getPiece())) return false;

        int dir = white ? -1 : 1;     // допустим, белые идут вверх (y уменьшается)
        int startRank = white ? 6 : 1; // начальный ряд пешек

        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();

        // ход вперёд на 1
        if (dx == 0 && dy == dir && end.getPiece() == null) {
            return true;
        }

        // ход вперёд на 2 с начальной позиции
        if (dx == 0 && dy == 2 * dir && start.getY() == startRank) {
            Spot mid = board.getBox(start.getX(), start.getY() + dir);
            if (mid.getPiece() == null && end.getPiece() == null) return true;
        }

        // взятие по диагонали
        if (Math.abs(dx) == 1 && dy == dir && end.getPiece() != null &&
                end.getPiece().white != this.white) {
            return true;
        }

        return false;
    }
}
