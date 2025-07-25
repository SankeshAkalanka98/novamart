package model;

public class Util {
    
    public static String generateCode() {
        return String.format("%6d", (int) (Math.random() * 1000000));
    }
    
    public static boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }
    
    public static boolean isPasswordValid(String password) {
        return password.matches("^(?=.[A-Za-z])(?=.\\d)[A-Za-z\\d]{8,}$");
    }
    
    public static boolean isCodeValid(String code) {
        return code.matches("^\\d{4,5}$");
    }
    
    public static boolean isInteger(String value) {
        return value.matches("^\\d+$");
    }
    
    public static boolean isDouble(String text) {
        return text.matches("^\\d+(\\.\\d{2})?$");
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
    }
}
