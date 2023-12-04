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
        int preLastDigit = price % 100 / 10;
        if (preLastDigit == 1) {
            return "рублей.";
        }
        return switch (price % 10) {
            case 1 -> "рубль.";
            case 2, 3, 4 -> "рубля.";
            default -> "рублей.";
        };
    }

}

