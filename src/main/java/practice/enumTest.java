package practice;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum enumTest {
    LEVEL_ONE(1000, 0.01),
    LEVEL_TWO(2000, 0.02),
    LEVEL_THREE(3000,0.032),
    LEVEL_FOUR(4000,0.045),
    LEVEL_FIVE(5000,0.056),
    LEVEL_SIX(6000,0.071);

    private final int maxSal;
    private final double ratio;

    enumTest(int maxSal, double ratio) {
        this.maxSal = maxSal;
        this.ratio = ratio;
    }

    //get sal
    public static Set<enumTest> getSal() {
        return EnumSet.allOf(enumTest.class)
                .stream()
                    .collect(
                            Collectors
                                    .collectingAndThen(
                                            Collectors.toSet(),
                                            Collections :: unmodifiableSet));
    }
    // get ratio


    public static void main(String[] args) {
        System.out.println(getSal());
    }
}
