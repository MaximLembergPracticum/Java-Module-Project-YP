package calculate;

import format.Formatter;

public class Calculator {

    public void sumPerPerson(double sum, int person) {
        Formatter formatter = new Formatter();
        double result = sum / person;
        result = round(result, 2);
        System.out.print("Сумма к оплате для каждого человека: " + result + " ");
        System.out.println(formatter.formatRubles(result));
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
