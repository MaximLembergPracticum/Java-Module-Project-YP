package format;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Formatter {

    public String formatPerson(int person) {
        if (person > 1 && person <= 4) {
            return "человека.";
        }
        if ((person > 5 && person <= 20) || person == 1) {
            return "человек.";
        }
        int lastDigit = person % 10;
        if ((lastDigit >= 2 && lastDigit <= 4)) {
            return "человека.";
        } else {
            return "человек.";
        }
    }

    public String formatRubles(double value) {
        BigDecimal result = new BigDecimal(value);
        result = result.setScale(0, RoundingMode.DOWN);
        int price = result.intValue();
        if (price == 1) {
            return "рубль.";
        }
        if (price > 1 && price <= 4) {
            return "рубля.";
        }
        if ((price >= 5 && price <= 9) || price == 0) {
            return "рублей.";
        }
        if (price >= 11 && price <= 19) {
            return "рублей.";
        }
        double lastDigit = price % 10;
        if ((lastDigit >= 2 && lastDigit <= 4)) {
            return "рубля.";
        } else {
            return "рублей.";
        }
    }

}
