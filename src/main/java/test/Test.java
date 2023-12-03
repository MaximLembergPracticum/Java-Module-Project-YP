package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import calculate.Calculator;
import format.Formatter;
import model.Item;
import storage.Repository;
import validate.Validator;

public class Test {

    private static final List<String> itemName = new ArrayList<>();
    private static final int itemCount = 5;
    private static final int minPerson = 2;
    private static final double minRubles = 50.0;
    private static final double maxRubles = 1000.0;

    public void init() {
        System.out.println(testCheckPerson());
        System.out.println(testCheckPersonFormat());
        System.out.println(testCheckPrice());
        System.out.println(testCheckRublesFormat());
        mainTest(10);
        //manualFormatRublesTest();
    }

    public void mainTest(int testCount) {
        itemName.add("Пицца");
        itemName.add("Гренки");
        itemName.add("Стейк");
        itemName.add("Арахис");
        itemName.add("Салат \"Оливье\"");
        itemName.add("Пиво");
        itemName.add("Кола");
        itemName.add("Грибой суп");
        itemName.add("Сырная тарелка");
        Validator validator = new Validator();
        while (testCount != 0) {
            Repository testRepository = new Repository();
            Calculator testCalculator = new Calculator();
            Formatter testFormatter = new Formatter();
            String input;
            int person;
            int maxPerson = 100;
            Scanner scanner = new Scanner(String.valueOf(rndInt(minPerson, maxPerson)));
            System.out.println("\uD83C\uDF7D На скольких человек необходимо разделить счёт? \uD83E\uDDEE");
            System.out.print("Введите количество человек: ");
            while (!validator.checkPerson(input = scanner.next())) {
                System.out.println("Некорректный ввод.");
                System.out.print("Введите количество человек: ");
            }
            person = Integer.parseInt(input);
            System.out.println(person);
            System.out.print("Ваш счет будет разделен на - " + person + " ");
            System.out.println(testFormatter.formatPerson(person));
            while (true) {
                Item item = new Item();
                System.out.println("Введите наименование и стоимость товара:");
                System.out.print("Введите наименование: ");
                item.setName(itemName.get(rndInt(0, itemName.size() - 1)));
                System.out.println(item.getName());
                System.out.print("Введите стоимость: ");
                while (!validator.checkItem(input = String.valueOf(rndDouble(minRubles, maxRubles)))) {
                    System.out.println("Некорректный ввод.");
                    System.out.print("Введите стоимость: ");
                }
                item.setPrice(Double.parseDouble(input));
                System.out.println(item.getPrice());
                testRepository.add(item);
                System.out.println("Хотитет добавить еще один товар или введите \"Завершить\"?");
                if (testRepository.getSize() >= itemCount) {
                    input = "Завершить";
                } else {
                    input = "Хочу добавить товар еще.";
                }
                System.out.println(input);
                System.out.println();
                if (input.trim().equalsIgnoreCase("Завершить")) break;
            }
            System.out.println();
            testRepository.getAll();
            testCalculator.sumPerPerson(testRepository.getSum(), person);
            System.out.println();
            testCount--;
        }
    }

    private String testCheckPerson() {
        String testName = "Тест валидности ввода человек";
        System.out.println("**** " + testName + " ****\n");
        int pass = 0;
        Validator validator = new Validator();
        String[] inputs = new String[]{"OoOoO", "-1", "0", "1", "2", "100", "100.5"};
        Boolean[] correctInput = new Boolean[]{false, false, false, false, true, true, false};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Tecт: " + (i + 1));
            System.out.print("Ответ валидатора: ");
            boolean checkOutput = validator.checkPerson(inputs[i]);
            boolean passTest = checkOutput == correctInput[i];
            System.out.println("Введено человек: " + inputs[i] +
                    "\nТеста валидатора: " + checkOutput +
                    "\nТест пройден: " + passTest);
            System.out.println();
            if (passTest) {
                pass++;
            }
        }
        return pass == inputs.length ? testName + " пройден \uD83D\uDC4D\n" :
                testName + " не пройден \uD83D\uDC4E\n";
    }

    private String testCheckPersonFormat() {
        String testName = "Тест склонения по множеству человек";
        System.out.println("**** " + testName + " ****\n");
        int pass = 0;
        Formatter formatter = new Formatter();
        int[] inputs = new int[]{1, 2, 3, 4, 5, 10, 21, 22, 25, 15, 19};
        String[] checkOutputs = new String[]{"человек.", "человека.", "человек.", "человеков.",
                "человек.", "человек.", "человек.", "человека.", "человек.", "человек.",
                "челововеков."};
        Boolean[] correctInput = new Boolean[]{true, true, false, false, true, true, true,
                true, true, true, false};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Tecт: " + (i + 1));
            System.out.print("Ответ форматера: ");
            String output = formatter.formatPerson(inputs[i]);
            System.out.println(output);
            boolean passTest = output.trim().equalsIgnoreCase(checkOutputs[i]);
            passTest = passTest == correctInput[i];
            System.out.println("Введено человек: " + inputs[i] +
                    "\nСравниваем с: " + checkOutputs[i] +
                    "\nОжидаем: " + correctInput[i] +
                    "\nТест пройден: " + passTest);
            System.out.println();
            if (passTest) {
                pass++;
            }
        }
        return pass == inputs.length ? testName + " пройден \uD83D\uDC4D\n" :
                testName + " не пройден \uD83D\uDC4E\n";
    }

    private String testCheckPrice() {
        String testName = "Тест валидности ввода цены товара";
        System.out.println("**** " + testName + " ****\n");
        int pass = 0;
        Validator validator = new Validator();
        String[] inputs = new String[]{"OoOoO", "-1", "0", "1", "2", "100", "100.5"};
        Boolean[] correctInput = new Boolean[]{false, false, true, true, true, true, true};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Tecт: " + (i + 1));
            System.out.print("Ответ валидатора: ");
            boolean checkOutput = validator.checkItem(inputs[i]);
            boolean passTest = checkOutput == correctInput[i];
            System.out.println("Введена стоимость: " + inputs[i] +
                    "\nТеста валидатора: " + checkOutput +
                    "\nТест пройден: " + passTest);
            System.out.println();
            if (passTest) {
                pass++;
            }
        }
        return pass == inputs.length ? testName + " пройден \uD83D\uDC4D\n" :
                testName + " не пройден \uD83D\uDC4E\n";
    }

    private String testCheckRublesFormat() {
        String testName = "Тест склонения по множеству рубля";
        System.out.println("**** " + testName + " ****\n");
        int pass = 0;
        Formatter formatter = new Formatter();
        double[] inputs = new double[]{1, 2.5, 3.2, 4.7, 5.2, 10, 21.2, 22.6, 25.2, 150.2, 1900};
        String[] checkOutputs = new String[]{"рубль.", "рубля.", "рубля.", "рублей.",
                "рублей.", "рублей.", "рублей.", "рублей.", "рублей.", "рублей.",
                "рублей."};
        Boolean[] correctInput = new Boolean[]{true, true, true, false, true, true, true,
                false, true, true, true};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("Tecт: " + (i + 1));
            System.out.print("Ответ форматера: ");
            String output = formatter.formatRubles(inputs[i]);
            System.out.println(output);
            boolean passTest = output.trim().equalsIgnoreCase(checkOutputs[i]);
            passTest = passTest == correctInput[i];
            System.out.println("Введено рублей: " + inputs[i] +
                    "\nСравниваем с: " + checkOutputs[i] +
                    "\nОжидаем: " + correctInput[i] +
                    "\nТест пройден: " + passTest);
            System.out.println();
            if (passTest) {
                pass++;
            }
        }
        return pass == inputs.length ? testName + " пройден \uD83D\uDC4D\n" :
                testName + " не пройден \uD83D\uDC4E\n";
    }

    private double rndDouble(double min, double max) {
        return min + new Random().nextDouble() * (max - min);
    }

    private int rndInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public void manualFormatRublesTest() {
        while (true) {
            System.out.print("Строго double(rub) иначе того...: ");
            Formatter testFormatter = new Formatter();
            Scanner scanner = new Scanner(System.in);
            Double input = scanner.nextDouble();
            System.out.println(testFormatter.formatRubles(input));
        }
    }
}
