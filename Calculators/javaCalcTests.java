import java.util.List;
import java.util.Arrays;


public class javaCalcTests {
    public static void main(String[] args) {
        inputParseTest();
        javaCalc.parseInput("");
    }

    public static void inputParseTest(){
        List<Object> parsedAddition = javaCalc.parseInput("2+30+200+4012+42003");
        List<Object> parsedSubtraction = javaCalc.parseInput("2-30-200-4012-42003");
        List<Object> parsedMultiplication = javaCalc.parseInput("2*30*200*4012*42003");
        List<Object> parsedDivision = javaCalc.parseInput("2/30/200/4012/42003");
        List<Object> parsedAll = javaCalc.parseInput("2+30-200*4012/42003");
        if (!parsedAddition.equals(Arrays.asList(new Object[]{2.0,"+",30.0,"+",200.0,"+",4012.0,"+",42003.0}))) System.out.println("Addition Parse Fail");
        if (!parsedSubtraction.equals(Arrays.asList(new Object[]{2.0,"-", 30.0,"-",200.0,"-",4012.0,"-",42003.0}))) System.out.println("Subtraction Parse Fail");
        if (!parsedMultiplication.equals(Arrays.asList(new Object[]{2.0,"*",30.0,"*",200.0,"*",4012.0,"*",42003.0}))) System.out.println("Multiplication Parse Fail");
        if (!parsedDivision.equals(Arrays.asList(new Object[]{2.0,"/",30.0,"/",200.0,"/",4012.0,"/",42003.0}))) System.out.println("Division Parse Fail");
        if (!parsedAll.equals(Arrays.asList(new Object[]{2.0,"+",30.0,"-",200.0,"*",4012.0,"/",42003.0}))) System.out.println("Combined Parse Fail"); 
    }
}
