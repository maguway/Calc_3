package com;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {

    // проверяем на правильность ввода строки, если некорректно, генерируем исключение и выход
    public static boolean validator(String expr) {
        // вернет true, если арабские фицры
        boolean arab = false, valid = false;

        // проверка ввода, чтобы a и b одновременно были либо римские, либо арабские
        // причем должны быть от 1 до 10. Все через RegExp
        Pattern p1 = Pattern.compile("^\\s*([1-9]|10)\\s*[*/+-]\\s*([1-9]|10)\\s*$");
        Matcher m1 = p1.matcher(expr);
        Pattern p2 = Pattern.compile("^\\s*[ivx]{1,4}\\s*[*/+-]\\s*[ivx]{1,4}\\s*$");
        Matcher m2 = p2.matcher(expr);

        if (m1.find()) {
            arab = true;
            valid = true;
        } else if (m2.find()) {
            valid = true;
        }

        // Генерируем исключение в случае некорректного ввода
        try {
            if (!valid) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Исключение. Не соответствует требованиям ввода");
            System.exit(2);
        }
        return arab;
    }

    private static int transform_roman_numeral(String roman_numeral) {
        Map<Character, Integer> roman_char_dict = new HashMap<Character, Integer>();
        roman_char_dict.put('i', 1);
        roman_char_dict.put('v', 5);
        roman_char_dict.put('x', 10);
        int res = 0;

        int prev = 0;
        for (int i = roman_numeral.length() - 1; i >= 0; i--) {
            int temp = roman_char_dict.get(roman_numeral.charAt(i));
            if (temp < prev)
                res -= temp;
            else
                res += temp;
            prev = temp;
        }

        return res;
    }

    public static Integer[] stroka(String expr, boolean arabian) {

        String numbers[] = expr.split("\\s*[*/+-]\\s*");

        Integer oper[] = new Integer[3];

        if (!arabian) {
            // Если римские, то запускаем процедуру перевода из римских в арабские
            oper[0] = transform_roman_numeral(numbers[0]);
            oper[1] = transform_roman_numeral(numbers[1]);
        } else {
            // Если арабские, то просто заполняем, конвертируя в Int
            oper[0] = Integer.parseInt(numbers[0]);
            oper[1] = Integer.parseInt(numbers[1]);
        }

        // Выделяем и определяем операнд, учитывая, что строку на валидность уже проверили, т.е. только один операнд
        numbers = expr.split("\\s*(\\d{1,2}+|[ivx]+)\\s*");
        String tmp = numbers[1];

        switch (numbers[1]) {
            case ("*"):
                oper[2] = 1;
                break;
            case ("/"):
                oper[2] = 2;
                break;
            case ("+"):
                oper[2] = 3;
                break;
            case ("-"):
                oper[2] = 4;
                break;
        }

        return oper;
    }

    public static Double count(Integer[] oper) {
        double a = oper[0], b = oper[1], res = 0;

        switch (oper[2]) {
            case (1):
                res = a * b;
                break;
            case (2):
                res = a / b;
                break;
            case (3):
                res = a + b;
                break;
            case (4):
                res = a - b;
                break;
        }
        return res;
    }


    public static String toRoman (int number) {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        int l =  map.floorKey(number);

        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}
