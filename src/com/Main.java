package com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            // для определения цифр: римские или арабские
            boolean numeral;

            Scanner inp = new Scanner(System.in);
            System.out.println("Input");
            String strIn = inp.nextLine();

            // переводим все в нижний регистр (актуально для римских) и отсекаем пробелы по краям
            strIn = strIn.toLowerCase().trim();

            // проверяем корректность ввода и узнаем римские/арабские
            numeral = Calculate.validator(strIn);

            // Получаем результат математических вычислений
            double templateDouble = Calculate.count(Calculate.stroka(strIn, numeral));

            // Округляем результат, для выведения дробной части
            double newDouble = new BigDecimal(templateDouble).setScale(2, RoundingMode.UP).doubleValue();

            // Получаем целое для дальнейших преобразований в римский вид и для целочисленного представления, если нужно
            Integer newInt = (int) Math.round(newDouble);

            // вывод результата на экран в зависимости от римского или арабского ввода
            if (!numeral) {
                System.out.println(Calculate.toRoman(newInt));
            } else {
                if (newDouble % 1 != 0) {   // определяем, есть ли дробная часть, если да, то печатаем ее
                    System.out.println("Output: \n" + newDouble);
                } else  // печатаем без дробной части для красоты
                    System.out.println("Output: \n" + newInt);
            }
        }
    }
}