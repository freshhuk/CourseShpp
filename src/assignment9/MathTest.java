package assignment9;

public class MathTest {


    public static void main(String[] args) {
        MathLogic.main(new String[]{"-(-(5+5))"});
        MathLogic.main(new String[]{"1+(2+3*(4+5-sin(45*cos(a))))/7", "a=30"});
        MathLogic.main(new String[]{"-a-b","a =-5","b = 1 "});
        MathLogic.main(new String[]{"2^2^3"});
        MathLogic.main(new String[]{"-14--2"});
        MathLogic.main(new String[]{"-14-2*5/2"});
        MathLogic.main(new String[]{"20/10-a", "a = -5"});
        MathLogic.main(new String[]{"(6+4)/10"});
        MathLogic.main(new String[]{"5+5.2"});
        MathLogic.main(new String[]{"b+a*2","a=-5","b =1  "});
        MathLogic.main(new String[]{"sin(abc)", "abc=30"});
        MathLogic.main(new String[]{"cos(60)"});
        MathLogic.main(new String[]{"tan(45)"});
        MathLogic.main(new String[]{"atan(45)"});
        MathLogic.main(new String[]{"log10(100)"});
        MathLogic.main(new String[]{"log2(8)"});


    }
}
