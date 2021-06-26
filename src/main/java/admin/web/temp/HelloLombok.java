package admin.web.temp;

import lombok.Getter;

@Getter
public class HelloLombok {
    private String name;
    private long amount;

    public HelloLombok() {
        setValue();
    }

    private void setValue() {
        this.name = "1";
        this.amount = 1;
    }
}
