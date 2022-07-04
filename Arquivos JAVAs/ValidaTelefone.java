package com.mycompany.comandavirtual;

public class ValidaTelefone {

    private static boolean lenPhone(String phone) {
        //verifica se tem a qtd de caracteres correta (10 fixo / 11 cel)
        if (!(phone.length() >= 10 && phone.length() <= 11)) {
            return false;
        }

        //Se tiver 11 caracteres (numero de celular) , olha se começa com 9 o numero
        if (phone.length() == 11 && Integer.parseInt(phone.substring(2, 3)) != 9) {
            return false;
        }

        return true;
    }

    private static boolean veriDDD(String phone) {
        Integer[] DDD = {
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            21, 22, 24, 27, 28, 31, 32, 33, 34,
            35, 37, 38, 41, 42, 43, 44, 45, 46,
            47, 48, 49, 51, 53, 54, 55, 61, 62,
            64, 63, 65, 66, 67, 68, 69, 71, 73,
            74, 75, 77, 79, 81, 82, 83, 84, 85,
            86, 87, 88, 89, 91, 92, 93, 94, 95,
            96, 97, 98, 99};

        if (java.util.Arrays.asList(DDD).indexOf(Integer.parseInt(phone.substring(0, 2))) == -1) {
            return false;
        }
        return true;
    }

    private static boolean veriRepeat(String phone) {
        //verifica se o numero foi digitado com todos os dígitos iguais
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(phone.charAt(0) + "{" + phone.length() + "}");
        java.util.regex.Matcher m = p.matcher(phone);

        if (m.find()) {
            return false;
        }

        return true;
    }

    private static boolean veriPrefix(String phone) {
        //Se o número tiver dez char é um fixo e por isso o número logo após o DDD deve ser necessariamente -> 2, 3, 4, 5, 7 

        Integer[] padroesPrefixo = {2, 3, 4, 5, 7};

        if (phone.length() == 10 && java.util.Arrays.asList(padroesPrefixo).indexOf(Integer.parseInt(phone.substring(2, 3))) == -1) {
            return false;
        }

        return true;

    }

    public static boolean isTelefone(String phone) {

        phone = phone.replaceAll("\\D", "");

        if (!lenPhone(phone) || !veriDDD(phone) || !veriRepeat(phone) || !veriPrefix(phone)) {
            return false;
        } else {
            return true;
        }
    }
}
