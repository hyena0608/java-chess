package chess.domain;

import chess.cache.BoardCache;
import chess.cache.PieceCache;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int PAWN_SINGLE_MOVE = 1;
    private static final int PAWN_DOUBLE_MOVE = 2;

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final Map<Position, Piece> board = new HashMap<>();
        board.putAll(BoardCache.create());
        board.putAll(PieceCache.create());
        return new Board(board);
    }

    public void move(final Position source, final Position target, final Color currentPlayer) {
        validateRange(source, target);
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateInvalidColor(currentPlayer, sourcePiece, targetPiece);
        validateInvalidPosition(source, target, sourcePiece);
        validateBlockedPosition(source, target, sourcePiece);
        validateIfPawnUnmovable(source, target, sourcePiece);

        board.put(target, sourcePiece);
        board.put(source, Empty.create());
    }

    private void validateInvalidPosition(final Position source, final Position target, final Piece sourcePiece) {
        final List<Position> positions = sourcePiece.findPositions(source, target);

        if (!positions.contains(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
    }

    private void validateBlockedPosition(final Position source, final Position target, final Piece sourcePiece) {
        final List<Position> positions = sourcePiece.findPositions(source, target);

        final boolean isCrossed = positions.subList(0, positions.indexOf(target))
                .stream()
                .anyMatch(this::isCrossed);

        if (isCrossed) {
            throw new IllegalArgumentException("이동 위치가 다른 기물에 의해 막혀 있습니다.");
        }
    }

    private boolean isCrossed(final Position position) {
        return board.getOrDefault(position, Empty.create()).isNotSamePieceType(PieceType.EMPTY);
    }

    private void validateInvalidColor(final Color currentPlayer, final Piece sourcePiece, final Piece targetPiece) {
        if (sourcePiece.isNotSameColor(currentPlayer)) {
            throw new IllegalArgumentException("자신의 기물을 선택해야 합니다.");
        }

        if (targetPiece.isSameColor(currentPlayer)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
    }

    private void validateIfPawnUnmovable(final Position source, final Position target, final Piece sourcePiece) {
        if (sourcePiece.isSamePieceType(PieceType.PAWN) && isPawnUnmovable(source, target)) {
            throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isPawnUnmovable(final Position source, final Position target) {
        return !(isFrontMove(source, target, PAWN_SINGLE_MOVE)
                || isFrontMove(source, target, PAWN_DOUBLE_MOVE)
                || isDiagonalMove(source, target));
    }

    private boolean isFrontMove(final Position source, final Position target, final int moveCount) {
        return source.getRow() == target.getRow()
                && Math.abs(source.getColumn() - target.getColumn()) == moveCount
                && board.get(target).isSamePieceType(PieceType.EMPTY);
    }

    private boolean isDiagonalMove(final Position source, final Position target) {
        return Math.abs(source.getRow() - target.getRow()) == 1
                && Math.abs(source.getColumn() - target.getColumn()) == 1
                && board.get(target).isNotSamePieceType(PieceType.EMPTY);
    }

    private void validateRange(final Position source, final Position target) {
        final int maxRange = (int) Math.sqrt(board.size());
        if (isRangeOver(source, maxRange) || isRangeOver(target, maxRange)) {
            throw new IllegalArgumentException("입력 값이 보드의 범위를 초과하였습니다.");
        }
    }

    private boolean isRangeOver(final Position position, final int maxRange) {
        return 0 > position.getColumn() || position.getColumn() > maxRange
                || 0 > position.getRow() || position.getRow() > maxRange;
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
