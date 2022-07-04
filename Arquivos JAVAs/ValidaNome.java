package com.mycompany.comandavirtual;

public class ValidaNome {

    public static boolean containChar(String name) {
        if (name.matches("[A-Z a-z Çç]{" + name.length() + "}") == true) {
            return true;
        }
        return false;
    }

}
