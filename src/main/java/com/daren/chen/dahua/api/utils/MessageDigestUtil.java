package com.daren.chen.dahua.api.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 15:12
 */
public class MessageDigestUtil {

    /**
     *
     * @param str
     * @return
     */
    public static String base64AndMD5(String str) {
        if (str == null) {
            throw new IllegalArgumentException("inStr can not be null");
        } else {
            return base64AndMD5(toBytes(str)).trim();
        }
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static String base64AndMD5(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes can not be null");
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(bytes);
                Base64 base64 = new Base64();
                byte[] enbytes = base64.encode(md.digest());
                return new String(enbytes);
            } catch (NoSuchAlgorithmException var4) {
                throw new IllegalArgumentException("unknown algorithm MD5");
            }
        }
    }

    public static String utf8ToIso88591(String str) {
        if (str == null) {
            return str;
        } else {
            return new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }
    }

    public static String iso88591ToUtf8(String str) {
        if (str == null) {
            return str;
        } else {
            return new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
    }

    private static byte[] toBytes(String str) {
        if (str == null) {
            return null;
        } else {
            return str.getBytes(StandardCharsets.UTF_8);
        }
    }
}
