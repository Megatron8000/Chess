import java.util.Scanner;

public class ChessGame {
    private Board board;
    private boolean whiteToMove;

    public ChessGame() {
        board = new Board();
        whiteToMove = true;
    }

    public boolean makeMove(int fromX, int fromY, int toX, int toY) {
        Spot start = board.getBox(fromX, fromY);
        Spot end = board.getBox(toX, toY);
        Piece p = start.getPiece();

        if (p == null || p.isWhite() != whiteToMove) return false;
        if (!p.canMove(board, start, end)) return false;
        if (!isMoveSafeForKing(start, end)) return false;

        movePiece(board, start, end);
        whiteToMove = !whiteToMove;

        boolean enemyColor = whiteToMove;
        if (isCheck(board, enemyColor)) {
            System.out.println("Шах " + (enemyColor ? "белому" : "чёрному") + " королю!");
            if (isCheckmate(board, enemyColor)) {
                System.out.println("МАТ " + (enemyColor ? "белым" : "чёрным") + "!");
                return true;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int y = 7; y >= 0; y--) {
            System.out.print((y + 1) + " ");
            for (int x = 0; x < 8; x++) {
                Piece p = board.getBox(x, y).getPiece();
                if (p == null) System.out.print(". ");
                else System.out.print(p.isWhite() ? Character.toUpperCase(getPieceChar(p)) :
                        Character.toLowerCase(getPieceChar(p)));
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
        System.out.println("Ход " + (whiteToMove ? "белых" : "чёрных"));
    }

    private char getPieceChar(Piece p) {
        if (p instanceof Pawn) return 'p';
        if (p instanceof Rook) return 'r';
        if (p instanceof Knight) return 'n';
        if (p instanceof Bishop) return 'b';
        if (p instanceof Queen) return 'q';
        if (p instanceof King) return 'k';
        return '?';
    }

    private boolean isMoveSafeForKing(Spot start, Spot end) {
        Board sim = copyBoard(board);
        Spot sFrom = sim.getBox(start.getX(), start.getY());
        Spot sTo = sim.getBox(end.getX(), end.getY());
        movePiece(sim, sFrom, sTo);
        return !isCheck(sim, whiteToMove);
    }

    private void movePiece(Board b, Spot start, Spot end) {
        end.setPiece(start.getPiece());
        start.setPiece(null);
    }

    private Board copyBoard(Board original) {
        Board b = new Board();
        for (int x = 0; x < 8; x++) for (int y = 0; y < 8; y++) {
            b.getBox(x, y).setPiece(original.getBox(x, y).getPiece());
        }
        return b;
    }

    private boolean isCheck(Board b, boolean whiteKing) {
        Spot king = b.findKing(whiteKing);
        if (king == null) return false;
        for (int x = 0; x < 8; x++) for (int y = 0; y < 8; y++) {
            Spot from = b.getBox(x, y);
            Piece p = from.getPiece();
            if (p == null || p.isWhite() == whiteKing) continue;
            if (p.canMove(b, from, king)) return true;
        }
        return false;
    }

    private boolean isCheckmate(Board b, boolean whiteToMove) {
        if (!isCheck(b, whiteToMove)) return false;
        for (int x1 = 0; x1 < 8; x1++) for (int y1 = 0; y1 < 8; y1++) {
            Spot from = b.getBox(x1, y1);
            Piece p = from.getPiece();
            if (p == null || p.isWhite() != whiteToMove) continue;
            for (int x2 = 0; x2 < 8; x2++) for (int y2 = 0; y2 < 8; y2++) {
                Spot to = b.getBox(x2, y2);
                if (!p.canMove(b, from, to)) continue;
                Board sim = copyBoard(b);
                movePiece(sim, sim.getBox(x1, y1), sim.getBox(x2, y2));
                if (!isCheck(sim, whiteToMove)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.printBoard();

        // Пример ходов: e2-e4 (4 1 4 3)
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Ход (fromX fromY toX toY): ");
            int fx = sc.nextInt(), fy = sc.nextInt(), tx = sc.nextInt(), ty = sc.nextInt();
            if (game.makeMove(fx, fy, tx, ty)) {
                game.printBoard();
            } else {
                System.out.println("Недопустимый ход!");
            }
        }
    }
}