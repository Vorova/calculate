package KataAcademy;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(calc("7 / 2"));
    }

    public static String calc(String input) throws Exception{
        // ������ � ����������� � ���������
        String[] arrNumber = input.split(" ");

        // �������� �� ����� ������� � ����������� � ���������
        if(arrNumber.length != 3){
            try{
                throw new Exception();
            } catch (Exception e){
                System.out.println("�� ������ ������ �������");
            }
        }

        // �������� �� �����������
        int operand1, operand2;
        String operator = arrNumber[1];
        boolean isRoman = false;
        if(isNumeric(arrNumber[0]) && isNumeric(arrNumber[2])){
            operand1 = Integer.parseInt(arrNumber[0]);
            operand2 = Integer.parseInt(arrNumber[2]);
        } else {
            // �������������� ������� ���� � ��������
            operand1 = romanToArabic(arrNumber[0]);
            operand2 = romanToArabic(arrNumber[2]);
            // �������� �� ������������ ����
            if (operand1 == -1 || operand2 == -1){
                try {
                    throw new Exception();
                } catch (Exception e){
                    System.out.println("�� ������ ������ ���������!");
                    throw new Exception();
                }
            }
            isRoman = true;
        }

        // �������� �� ������� ��������� � �������� ������������ ���������
        if(operand1 < 0 || operand1 > 10 || operand2 < 0 || operand2 > 10){
            try {
                throw new Exception();
            } catch (NumberFormatException e) {
                System.out.println("�� ������ ������ ���������!");
                throw new Exception();
            }
        }

        // �������� �� ������������ ���������
        int result;
        switch (operator){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("������ �� ���������� ��������. ��������� ���������: '+,-,*,/,:'");
                    throw new Exception();
                }
        }

        // �������� �� ������� ����� � ��������
        String output;
        if(isRoman){
            // �������� �� �������� ���������� ( > 1)
            if (result < 1){
                try {
                    throw new Exception();
                } catch (Exception e){
                    System.out.println("������� ����� �� ����� ���� ������ �������!");
                    throw new Exception();
                }
            }
            // �������������� �������� ���� � �������
            output = arabicToRoman(result);
        } else {
            output = Integer.toString(result);
        }
        return output;
    }

    static String arabicToRoman(int number){
        if ((number <= 0) || (number > 10)) {
            System.out.println("�� ������ ������ ���������!");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    static int romanToArabic(String roman){
        String[] arr = {"I", "II", "III", "IV", "V", "VI", "VII", "VII", "IX", "X"};
        for(int x = 0; x < arr.length; x++){
            if (roman.equals(arr[x])){
                return x + 1;
            }
        }
        return -1;
    }

    static boolean isNumeric(String string) {
        if(string == null || string.equals("")) return false;
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}