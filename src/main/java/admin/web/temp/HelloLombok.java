package admin.web.temp;

import lombok.Getter;

@Getter
public class HelloLombok {
    private String name;
    private long amount;

    public HelloLombok() {
        setValue(name, amount);
    }

    private void setValue(String name, long amount) {
        this.name = "1";
        this.amount = 1;
    }
}
