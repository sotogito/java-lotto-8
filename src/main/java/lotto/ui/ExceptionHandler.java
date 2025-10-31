package lotto.ui;

import lotto.common.exception.RereadRequestException;

public class ExceptionHandler {
    private final static String EXCEPTION_FORMAT = "\n[ERROR] %s\n";

    public static void read(RereadRequestException e) {
        System.out.printf(EXCEPTION_FORMAT, e.getMessage());
    }

}
