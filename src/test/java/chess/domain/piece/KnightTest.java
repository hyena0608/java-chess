package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("knight이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("knightMovableSuccessTestDummy")
    void knightMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece knight = new Knight(Color.WHITE);
        // when
        List<Position> result = knight.findPositions(source, target);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> knightMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        new Position(1, 1),
                        new Position(3, 2),
                        List.of(
                                new Position(3, 2)
                        )
                ),
                Arguments.arguments(
                        new Position(4, 4),
                        new Position(0, 4),
                        Collections.emptyList()
                )
        );
    }
}