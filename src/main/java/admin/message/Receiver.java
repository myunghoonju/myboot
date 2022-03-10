package admin.message;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Receiver {

    private AtomicInteger cnt = new AtomicInteger();

    public void receiveM(String message) {
        log.info("Received:: {}", message);
        cnt.incrementAndGet();
    }

    public int getCnt() {
        return cnt.get();
    }

}
