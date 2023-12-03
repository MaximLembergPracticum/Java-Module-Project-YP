import java.util.Scanner;

import calculate.Calculator;
import format.Formatter;
import model.Item;
import storage.Repository;
import test.Test;
import validate.Validator;

public class Main {
    static Repository repository = new Repository();
    static Calculator calculator = new Calculator();
    static Formatter formatter = new Formatter();


    public static void main(String[] args) {
        Test test = new Test();
        test.init();
        Validator validator = new Validator();
        String input;
        int person;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\uD83C\uDF7D На скольких человек необходимо разделить счёт? \uD83E\uDDEE");
        System.out.print("Введите количество человек: ");
        while (!validator.checkPerson(input = scanner.next())) {
            System.out.println("Некорректный ввод.");
            System.out.print("Введите количество человек: ");
        }
        person = Integer.parseInt(input);
        System.out.print("Ваш счет будет разделен на - " + person + " ");
        System.out.println(formatter.formatPerson(person));
        while (true) {
            Item item = new Item();
            System.out.println("Введите наименование и стоимость товара");
            System.out.print("Введите наименование: ");
            item.setName(scanner.next());
            System.out.print("Введите стоимость: ");
            while (!validator.checkItem(input = scanner.next())) {
                System.out.println("Некорректный ввод.");
                System.out.print("Введите стоимость: ");
            }
            item.setPrice(Double.parseDouble(input));
            repository.add(item);
            System.out.println("Хотитет добавить еще один товар или введите \"Завершить\"?");
            if (scanner.next().trim().equalsIgnoreCase("Завершить")) break;
        }
        System.out.println();
        repository.getAll();
        calculator.sumPerPerson(repository.getSum(), person);
    }

}