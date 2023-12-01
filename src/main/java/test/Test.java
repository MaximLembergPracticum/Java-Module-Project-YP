package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import calculate.Calculator;
import model.Item;
import storage.Repository;
import validate.Validator;

public class Test {

    private static List<String> itemName = new ArrayList<>();
    private static final int itemCount = 5;
    private static final int min = 2;
    private static final int max = 10;

    public void init(int iteration) {
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
        while (iteration > 0) {
            Repository testRepository = new Repository();
            Calculator testCalculator = new Calculator();
            String input;
            int person;
            Scanner scanner = new Scanner(String.valueOf(rnd(min, max)));
            System.out.println("\uD83C\uDF7D На скольких человек необходимо разделить счёт? \uD83E\uDDEE");
            System.out.print("Введите количество человек: ");
            while (!validator.check(input = scanner.next(), true, false)) {
                System.out.println("Некорректный ввод.");
                System.out.print("Введите количество человек: ");
            }
            person = Integer.parseInt(input);
            System.out.println(person);
            System.out.print("Ваш счет будет разделен на - " + person);
            System.out.println(person < 5 ? " человека." : " человек.");
            while (true) {
                Item item = new Item();
                System.out.println("Введите наименование и стоимость товара:");
                System.out.print("Введите наименование: ");
                item.setName(itemName.get(rnd(0, itemName.size() - 1)));
                System.out.println(item.getName());
                System.out.print("Введите стоимость: ");
                while (!validator.check(input = String.valueOf(rnd(10, 1000)), false, true)) {
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
            iteration--;
            System.out.println("Тестов осталось: " + iteration);
            System.out.println();
        }
    }


    private int rnd(int min, int max) {
        Random random = new Random();
        int result = random.nextInt(max) + min;
        while (result > max) {
            result = random.nextInt(max) + min;
        }
        return result;
    }
}
