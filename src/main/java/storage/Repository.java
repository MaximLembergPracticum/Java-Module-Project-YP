package storage;

import java.util.ArrayList;
import java.util.List;

import format.Formatter;
import model.Item;

public class Repository {

    public List<Item> itemList = new ArrayList<>();
    Formatter formatter = new Formatter();

    public void add(Item item) {
        double itemPrice = round(item.getPrice(), 2);
        item.setPrice(itemPrice);
        itemList.add(item);
        System.out.println("Товар - " + item.getName() + " и стоимостью: " + itemPrice + " " +
                formatter.formatRubles(item.getPrice()) + " добавлен в счет.");
    }

    public void getAll() {
        System.out.println("Итог:");
        for (Item item : itemList) {
            System.out.println("Добавленный товар: " + item.getName() + " и стоимостью: "
                    + item.getPrice() + " " +
                    formatter.formatRubles(item.getPrice()) + " добавлен в счет.");
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

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
