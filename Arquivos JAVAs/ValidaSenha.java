package com.mycompany.comandavirtual;

public class ValidaSenha {

    private static boolean contain8Char(String pass) {
        if (pass.length() < 8 || pass.length() > 20) {
            return false;
        } else {
            return true;
        }

    }

    private static boolean containStr(String pass) {

        try {
            double d = Double.parseDouble(pass);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    private static boolean containNum(String pass) {
        if (pass.matches("[A-Z a-z Çç]{" + pass.length() + "}") == false) {
            return true;
        }
        return false;
    }

    public static boolean isPASS(String pass) {

        if (pass == null) {
            return false;
        } else if (!containNum(pass) || !containStr(pass) || !contain8Char(pass)) {
            return false;
        } else {
            return true;
        }

    }

}
