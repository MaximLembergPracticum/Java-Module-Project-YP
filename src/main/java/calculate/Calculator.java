package calculate;

public class Calculator {

    public void sumPerPerson(double sum, int person) {
        double result = sum / person;
        double round = Math.pow(10, 2);
        result = (Math.ceil(result * round) / round);
        System.out.print("Сумма к оплате для каждого человека: " + result);
        System.out.println(result % 1 == 0 ? " рубля." : " рубль.");
    }
}
