package com;

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

            System.out.println("Output: \n" + Calculate.count(Calculate.stroka(strIn, numeral)));
        }
    }
}
