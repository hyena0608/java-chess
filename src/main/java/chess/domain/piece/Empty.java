package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public class Empty extends Piece {
    private Empty(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Empty create() {
        return new Empty(PieceType.EMPTY, Color.EMPTY);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        throw new UnsupportedOperationException("비어있는 위치입니다.");
    }
}