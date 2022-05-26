package com.icebear.stream.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class Util {
    // Help encrypt the user password before save to the database
    public static String encryptPassword(String userId, String password) throws IOException {
//      // generate a code according to userId and password
        return DigestUtils.md5Hex(userId
                + DigestUtils.md5Hex(password)).toLowerCase();
    }
}

