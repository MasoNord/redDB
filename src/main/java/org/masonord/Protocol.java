package org.masonord;

import java.nio.charset.StandardCharsets;

public class Protocol {
    // this prevents the class from instantiation
    public Protocol() {}

    public static final byte DOLLAR = '$';
    public static final byte PLUS = '+';
    public static final byte MINUS = '-';
    public static final byte COLON = ':';
    public static final byte ASTERISK = '*';


    public static byte[] toBytes(int number) {
        return toBytes(String.valueOf(number));
    }

    public static byte[] toBytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }
}
