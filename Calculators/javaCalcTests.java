import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class javaCalcTests {
    public static void main(String[] args) {
        inputParseTest();
        findNextOperationTest();
        performOperationTest();
        calculationTest();
    }

    public static void inputParseTest(){
        System.out.println("Executing input parsing test");
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

    public static void findNextOperationTest() {
        System.out.println("Executing find next operation test");
        List<Object> additionList = Arrays.asList(2.0, "+", 30.0, "+", 200.0);
        List<Object> subtractionList = Arrays.asList(2.0, "-", 30.0, "-", 200.0);
        List<Object> multiplicationList = Arrays.asList(2.0, "*", 30.0, "*", 200.0);
        List<Object> divisionList = Arrays.asList(2.0, "/", 30.0, "/", 200.0);
        List<Object> mixedList = Arrays.asList(2.0, "+", 30.0, "-", 200.0, "*", 4012.0, "/", 42003.0);

        if (javaCalc.findNextOperation(additionList) != 1) System.out.println("Priority addition Test Fail");
        if (javaCalc.findNextOperation(subtractionList) != 1) System.out.println("Priority subtraction Test Fail");
        if (javaCalc.findNextOperation(multiplicationList) != 1) System.out.println("Priority multiplication Test Fail");
        if (javaCalc.findNextOperation(divisionList) != 1) System.out.println("Priority division Test Fail");
        if (javaCalc.findNextOperation(mixedList) != 5) System.out.println("Mixed Operation Test Fail");
    }

    public static void performOperationTest() {
        System.out.println("Executing perform operation tests");
        List<Object> additionList = new ArrayList<>(Arrays.asList(2.0, "+", 3.0));
        List<Object> subtractionList = new ArrayList<>(Arrays.asList(5.0, "-", 3.0));
        List<Object> multiplicationList = new ArrayList<>(Arrays.asList(4.0, "*", 3.0));
        List<Object> divisionList = new ArrayList<>(Arrays.asList(12.0, "/", 3.0));
        List<Object> divisionByZeroList = new ArrayList<>(Arrays.asList(12.0, "/", 0.0));

        javaCalc.performOperation(divisionList, 1);
        javaCalc.performOperation(additionList, 1);
        javaCalc.performOperation(subtractionList, 1);
        javaCalc.performOperation(multiplicationList, 1);

        if (!additionList.equals(Arrays.asList(5.0))) System.out.println("Addition Test Fail");
        if (!subtractionList.equals(Arrays.asList(2.0))) System.out.println("Subtraction Test Fail");
        if (!multiplicationList.equals(Arrays.asList(12.0))) System.out.println("Multiplication Test Fail");
        if (!divisionList.equals(Arrays.asList(4.0))) System.out.println("Division Test Fail");
        try {
            javaCalc.performOperation(divisionByZeroList, 1);
            System.out.println("Division by Zero Test Fail"); // Should have thrown an exception
        } catch (ArithmeticException e) {
        // Expected exception, test passes
        }  
    }

    public static void calculationTest() {
        if (javaCalc.calculate(javaCalc.parseInput("2 + 3")) != 5.0) System.out.println("Simple Addition Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("5 - 3")) != 2.0) System.out.println("Simple Subtraction Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("4 * 3")) != 12.0) System.out.println("Simple Multiplication Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("12 / 3")) != 4.0) System.out.println("Simple Division Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("2 + 3 * 4")) != 14.0) System.out.println("Mixed Operation with Precedence Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("20 - 3 * 2 + 5")) != 9.0) System.out.println("Mixed Operation with Subtraction Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("2 + 3 + 4")) != 9.0) System.out.println("Multiple Operations of Same Type Test Fail");
        if (javaCalc.calculate(javaCalc.parseInput("2 + 3 * 4 - 8 / 2")) != 10.0) System.out.println("Complex Expression Test Fail");
    }
    

    
}
