package com.gmail.dev.le.elin.SeafoodStore.security;

public class Sha256RefreshEncoder {

    public String encode(String refreshTokenRaw) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(refreshTokenRaw.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean matches(String refreshTokenRaw, String encodedRefreshToken) {
        return encode(refreshTokenRaw).equals(encodedRefreshToken);
    }
}
