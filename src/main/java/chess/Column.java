package chess;

import java.util.Arrays;

public enum Column {
    COLUMN_8("8", 0),
    COLUMN_7("7", 1),
    COLUMN_6("6", 2),
    COLUMN_5("5", 3),
    COLUMN_4("4", 4),
    COLUMN_3("3", 5),
    COLUMN_2("2", 6),
    COLUMN_1("1", 7),
    ;

    private final String type;
    private final int index;

    Column(final String type, final int index) {
        this.type = type;
        this.index = index;
    }

    public static int findIndex(final String inputType) {
        return Arrays.stream(Column.values())
                .filter(columnType -> columnType.type.equals(inputType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 세로 입력값 입니다."))
                .index;
    }
}