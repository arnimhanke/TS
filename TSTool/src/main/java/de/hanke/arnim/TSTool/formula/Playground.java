package de.hanke.arnim.TSTool.formula;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

import java.math.BigDecimal;

public class Playground {

    public static void main(String[] args) {

//        Expression eh = new Expression("5^2 * 7^3 * 11^1 * 67^1 * 49201^1");
//        Expression ew = new Expression("71^1 * 218549^1 * 6195547^1");
//        String h = mXparser.numberToAsciiString( eh.calculate() );
//        String w = mXparser.numberToAsciiString( ew.calculate() );
//        mXparser.consolePrintln(h + " " + w);

        Argument x = new Argument("aaa_aaa = 1");
        Argument y = new Argument("b = 2");
        Argument z = new Argument("c", 3);

        Expression e = new Expression("aaa_aaa * b + c", x, y, z);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            e.setArgumentValue("aaa_aaa", i);
            e.removeArguments("aaa_aaa");
            e.setArgumentValue("b", i + 1);
            e.setArgumentValue("c", i + 2);
            double calculate = e.calculate();
            System.out.println(calculate);
        }

//        for (int i = 0; i < 86400 * 365; i++) {
//            BigDecimal add = BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(i + 1)).add(BigDecimal.valueOf(i + 2));
//
//        }

        System.out.println("Dauer : " + (System.currentTimeMillis() - start));


    }
}
