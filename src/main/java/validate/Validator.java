package validate;


public class Validator {


    public boolean checkPerson(String input) {
        boolean isCorrectInput = false;
        boolean isInteger = isInteger(input);
        if (isInteger) {
            if (Integer.parseInt(input) == 1) {
                System.out.println("Количество человек, введённых пользователем, " +
                        "равно 1. В этом случае нет смысла ничего считать и делить.");
            } else if (Integer.parseInt(input) < 1 || Integer.parseInt(input) == 0) {
                System.out.println("Количество человек меньше 1. " +
                        "Это некорректное значение для подсчёта.");
            } else {
                isCorrectInput = true;
            }
        } else {
            System.out.println("Некорректный ввод,введите целое число.");
        }
        return isCorrectInput;
    }

    public boolean checkItem(String input) {
        boolean isCorrectInput = false;
        boolean isInteger = isInteger(input);
        boolean isDouble = isDouble(input);
        if (isDouble || isInteger) {
            if (Double.parseDouble(input) < 0) {
                System.out.println("Стоимость товара не может быть отрицательной.");
            } else if (Double.parseDouble(input) == 0) {
                System.out.println("Подарок от шефа \uD83C\uDF81.");
                isCorrectInput = true;
            } else {
                isCorrectInput = true;
            }
        } else {
            System.out.println("Некорректный ввод,введите число.");
        }
        return isCorrectInput;
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
