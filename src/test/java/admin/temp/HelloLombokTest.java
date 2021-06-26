package admin.temp;

import org.junit.Assert;
import org.junit.Test;

public class HelloLombokTest {

    @Test
    public void 롬복_기능_테스트() {
        HelloLombok dd = new HelloLombok();
        Assert.assertEquals(1,dd.getAmount());
    }
}