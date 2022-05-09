package admin.netty.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestData {

    private int intValue;
    private String stringValue;

    @Override
    public String toString() {
        return "intValue " + intValue + "  " + "stringValue " + stringValue;
    }
}
