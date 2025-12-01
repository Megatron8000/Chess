class King extends Piece {

    public King(boolean white) {
        super(white);
    }

    @Override
    boolean canMove(Board board, Spot start, Spot end) {
        if (sameColor(start.getPiece(), end.getPiece())) return false;

        int dx = Math.abs(end.getX() - start.getX());
        int dy = Math.abs(end.getY() - start.getY());

        // максимум 1 по любой оси
        return dx <= 1 && dy <= 1 && (dx + dy > 0);
    }
}