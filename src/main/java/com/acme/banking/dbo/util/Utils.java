package com.acme.banking.dbo.util;

public class Utils {
    public static void require(boolean precondition) {
        if (!precondition) {
            throw new IllegalArgumentException();
        }
    }

    public static void require(boolean precondition, String message) {
        if (!precondition) {
            throw new IllegalArgumentException(message);
        }
    }
}
