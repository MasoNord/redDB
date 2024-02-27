package org.masonord;

import org.masonord.exception.InternalServerError;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Protocol {
    // this prevents the class from instantiation
    public Protocol() {}

    public static final byte DOLLAR = '$';
    public static final byte PLUS = '+';
    public static final byte MINUS = '-';
    public static final byte COLON = ':';
    public static final byte ASTERISK = '*';
    public static final byte GREATE_THEN = '>';
    public static final byte HASH = '#';
    public static final byte TILDE = '~';
    public static final byte UNDERSCORE = '_';


    public static byte[] toBytes(int number) {
        return toBytes(String.valueOf(number));
    }

    public static byte[] toBytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

}
