package chess.view;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.point.Point;
import chess.dto.GameDto;

import java.util.List;
import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.print(
                "> 체스 게임을 시작합니다." + System.lineSeparator()
                        + "> 게임 시작 : start" + System.lineSeparator()
                        + "> 게임 종료 : end" + System.lineSeparator()
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator()
                        + "> 게임 상태 보기 : status" + System.lineSeparator()
                        + "> 게임 리스트 불러오기 : load_list" + System.lineSeparator()
        );
    }

    public static void printChessBoard(final Map<Position, Piece> board) {
        final String chessBoard = BoardViewForm.createChessBoard(board);
        System.out.println(chessBoard + System.lineSeparator());
    }

    public static void printChessStatus(final Point blackPoint, final Point whitePoint, final Color winner) {
        System.out.println(System.lineSeparator()
                + "검정색 플레이어 점수 : " + blackPoint + System.lineSeparator()
                + "흰색 플레이어 점수 : " + whitePoint + System.lineSeparator()
                + "체스 결과 : " + winner.getPlayer()
        );
    }

    public static void printFindAllGames(final List<GameDto> games) {
        final StringBuilder gamesBuilder = new StringBuilder();
        final String form = "%10s%15s%15s";
        gamesBuilder.append(System.lineSeparator())
                .append(String.format(form, "게임 번호", "게임 종료 여부", "마지막 턴 소유자"))
                .append(System.lineSeparator());

        games.forEach(game -> gamesBuilder.append(String.format(form, game.getGameId(), game.isEnd(), game.getLastPlayer().getPlayer()))
                .append(System.lineSeparator()));

        System.out.println(gamesBuilder);
    }
}
