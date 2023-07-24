package study.section4.item19;

import java.io.Serial;
import java.io.Serializable;

public class OverrideRisk {

    public static void main(String[] args) {
        Sub a = new Sub("test");
        a.overrideMe();

    }
}
