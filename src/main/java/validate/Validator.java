package validate;


public class Validator {

    public boolean check(String input, boolean personFlag, boolean itemFlag) {
        boolean isCorrectInput = false;
        boolean isInteger = isInteger(input);
        boolean isDouble = isDouble(input);
        if (isInteger && personFlag) {
            if (Integer.parseInt(input) == 1) {
                System.out.println("Количество человек, введённых пользователем, " +
                        "равно 1. В этом случае нет смысла ничего считать и делить.");
            } else if (Integer.parseInt(input) < 1 || Integer.parseInt(input) == 0) {
                System.out.println("Количество человек меньше 1. " +
                        "Это некорректное значение для подсчёта.");
            } else {
                isCorrectInput = true;
            }
        }
        if (itemFlag && (isDouble || isInteger)) {
            if (Double.parseDouble(input) < 0) {
                System.out.println("Стоимость товара не может быть отрицательной.");
            } else {
                isCorrectInput = true;
            }
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
