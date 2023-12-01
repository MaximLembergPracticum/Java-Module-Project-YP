package storage;

import java.util.ArrayList;
import java.util.List;

import model.Item;

public class Repository {

    public List<Item> itemList = new ArrayList<>();

    public void add(Item item) {
        double itemPrice = item.getPrice();
        double round = Math.pow(10, 2);
        item.setPrice(Math.ceil(itemPrice * round) / round);
        itemList.add(item);
        System.out.println("Товар - " + item.getName() + " и стоимостью: " + item.getPrice()
                + " рублей" + " добавлен в счет.");
    }

    public void getAll() {
        System.out.println("Итог:");
        for (Item item : itemList) {
            System.out.println("Добавленный товар: " + item.getName() + "и стоимостью: " + item.getPrice()
                    + " рублей.");
        }
    }

    public int getSize() {
        return itemList.size();
    }

    public double getSum() {
        double sum = 0.0;
        double round = Math.pow(10, 2);
        for (Item item : itemList) {
            sum += item.getPrice();
        }
        return (Math.ceil(sum * round) / round);
    }
}
