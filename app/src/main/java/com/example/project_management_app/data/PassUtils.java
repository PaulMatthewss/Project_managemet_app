package com.example.project_management_app.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassUtils {

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean checkPassword(String enteredPassword, String storedPasswordHash) {
        String enteredPasswordHash = hashPassword(enteredPassword);
        return enteredPasswordHash.equals(storedPasswordHash);
    }
}
