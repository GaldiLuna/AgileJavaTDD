package util;

import java.util.Random;

public class PasswordGenerator {
    private String password;
    private static final int PASSWORD_LENGTH = 8;
    private Random random = new Random();
    static final char LOW_END_PASSWORD_CHAR = 48;
    static final char HIGH_END_PASSWORD_CHAR = 122;

    void setRandom(Random random) {
        this.random = random;
    }

    public String generatePassword() {
        StringBuffer buffer = new StringBuffer(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++)
            buffer.append(getRandomChar());
        return buffer.toString();
    }

    private char getRandomChar() {
        final char max = HIGH_END_PASSWORD_CHAR - LOW_END_PASSWORD_CHAR;
        return (char)(random.nextInt(max) + LOW_END_PASSWORD_CHAR);
    }

    public String getPassword() {
        return password;
    }
}
