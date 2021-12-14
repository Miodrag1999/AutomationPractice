package Helpers;

import java.util.UUID;

public class RandomEmail {
    public static String generateRandomEmail(){
        return UUID.randomUUID().toString().substring(0,9) + "@mail.com";
    }
}
