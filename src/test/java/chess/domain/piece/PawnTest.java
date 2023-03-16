package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @DisplayName("Pawn이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("PawnMovableSuccessTestDummy")
    void PawnMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece pawn = new Pawn(Color.WHITE);
        // when
        List<Position> result = pawn.findPositions(source, target);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> PawnMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        new Position(1, 6),
                        new Position(1, 5),
                        List.of(
                                new Position(0, 5),
                                new Position(1, 5),
                                new Position(2, 5),
                                new Position(1, 4)
                        )
                )
        );
    }
}