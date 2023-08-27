package admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Bindings {

    private int cnt;
    private List<XTestFanOutQueue> xTestFanOutQueues;

    @Builder
    public Bindings(int cnt, List<XTestFanOutQueue> xTestFanOutQueues) {
        this.cnt = cnt;
        this.xTestFanOutQueues = xTestFanOutQueues;
    }

    public static Bindings toRes(List<XTestFanOutQueue> contents) {
        return Bindings.builder()
                       .cnt(contents.size())
                       .xTestFanOutQueues(contents)
                       .build();
    }
}
