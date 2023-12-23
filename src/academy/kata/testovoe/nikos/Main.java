package academy.kata.testovoe.nikos;

	import java.util.InputMismatchException;
	import java.util.Scanner;
	public class Main {
	    static char sign;
	    public static void main(String[] args) throws Exception {
	        System.out.print("Введите выражение: ");
	        Scanner sc = new Scanner(System.in);
	        String example = sc.nextLine().toUpperCase();
	        System.out.println(calc(example));
	    }
	    
	    public static String calc(String input) throws Exception {
	        String example = input.replaceAll(" ", "");
	        char[] emampleChar = new char[example.length()];
	        for (int i = 0; i < example.length(); i++) {
	            emampleChar[i] = example.charAt(i);
	            if (emampleChar[i] == '+') {
	                sign = '+';
	            }
	            if (emampleChar[i] == '-') {
	                sign = '-';
	            }
	            if (emampleChar[i] == '*') {
	                sign = '*';
	            }
	            if (emampleChar[i] == '/') {
	                sign = '/';
	            }
	        }
	        String exampleString = String.copyValueOf(emampleChar);
	        String[] digits = exampleString.split("[+-/*]");
	        if (digits.length == 1) {
	            throw new Exception("Cтрока не является математической операцией");
	        }
	        if (digits.length != 2) {
	            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
	        }

	        String firtDigit = digits[0];
	        String secondDigit = digits[1].trim();
	        int num1 = ParseToArab(firtDigit);
	        int num2 = ParseToArab(secondDigit);

	        if (IsNumeric(firtDigit) && IsNumeric(secondDigit)) {
	            num1 = Integer.parseInt(firtDigit);
	            num2 = Integer.parseInt(secondDigit);
	            if (num1 <= 10 && num2 <= 10) {
	                return String.valueOf(calculate(num1, num2, sign));
	            } else {
	                throw new Exception("Неверный формат выражения. Принимаются числа от 1 до 10.");
	            }
	        }
	        if (!(IsNumeric(firtDigit)) && !(IsNumeric(secondDigit))) {
	            int result = calculate(num1, num2, sign);
	            String resultParsedtoRoman = ParseResult(result);
	            if (!(resultParsedtoRoman.isEmpty())) {
	                return resultParsedtoRoman;
	            } else {
	                throw new Exception("Неверный формат выражения");
	            }
	        } else {
	            throw new Exception("Используются одновременно разные системы счисления");
	        }


	    }

	    static int ParseToArab(String roman) {
	        try {
	            switch (roman) {
	                case "I":
	                    return 1;
	                case "II":
	                    return 2;
	                case "III":
	                    return 3;
	                case "IV":
	                    return 4;
	                case "V":
	                    return 5;
	                case "VI":
	                    return 6;
	                case "VII":
	                    return 7;
	                case "VIII":
	                    return 8;
	                case "IX":
	                    return 9;
	                case "X":
	                    return 10;
	            }
	        } catch (Exception e) {
	            return 0;
	        }
	        return 0;
	    }

	    static boolean IsNumeric(String string) {
	        try {
	            Integer.parseInt(string);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

	    static int calculate(int num1, int num2, char sign) throws Exception {
	        int result = 0;
	        switch (sign) {
	            case '+':
	                result = num1 + num2;
	                break;
	            case '-':
	                result = num1 - num2;
	                break;
	            case '*':
	                result = num1 * num2;
	                break;
	            case '/':
	                try {
	                    result = num1 / num2;
	                } catch (ArithmeticException | InputMismatchException e) {
	                    throw new Exception("Неверный формат выражения");
	                }

	        }
	        return result;

	    }

	    static String ParseResult(int result) throws Exception {
	        try {
	            String[] romanDigits = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
	                    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
	                    "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
	                    "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
	                    "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
	                    "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
	                    "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
	            return romanDigits[result];
	        } catch (Exception exception) {
	            throw new Exception("В римской системе нет отрицательных чисел");
	        }
	    }
	}


