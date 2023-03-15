package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static GameState inputGameState() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return GameState.from(scanner.nextLine());
    }

}
