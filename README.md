# Test
Бала использованна h2 in memory database и flyway для миграции дб. 

public class Source {
    private String digits;

    public Source(String digits) {
        this.digits = digits;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }
}

public class Destination {
    private String firstDigit;
    private String secondDigit;
    private String thirdDigit;

    public String getFirstDigit() {
        return firstDigit;
    }

    public void setFirstDigit(String firstDigit) {
        this.firstDigit = firstDigit;
    }

    public String getSecondDigit() {
        return secondDigit;
    }

    public void setSecondDigit(String secondDigit) {
        this.secondDigit = secondDigit;
    }

    public String getThirdDigit() {
        return thirdDigit;
    }

    public void setThirdDigit(String thirdDigit) {
        this.thirdDigit = thirdDigit;
    }
}

public class DigitMapper extends PropertyMap<Source, Destination> {
    @Override
    protected void configure() {
        using((MappingContext<Source, Destination> context) -> {
            String[] digits = context.getSource().getDigits().split(",");
            return new String[]{digits[0].trim(), digits[1].trim(), digits[2].trim()};
        }).map(source, destination -> {
            destination.setFirstDigit(source[0]);
            destination.setSecondDigit(source[1]);
            destination.setThirdDigit(source[2]);
        });
    }
}
