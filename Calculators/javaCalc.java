import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class javaCalc {

    public static void main(String[] args){
        printWelcome();
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        while(running){
            System.out.print(":  ");
            String input = scan.nextLine();
            if(input.equals("exit")) break;
            // Parse into numbers and symbols
            List<Object> parsedInput = parseInput(input);
            for (Object str : parsedInput) System.out.println(str);
            double output = calculate(parsedInput);
            System.out.printf("%s = %f", input, output);
        }
        scan.close();
    }

    public static double calculate(List<Object> parsedInput) {
        if (parsedInput.size() == 1 && parsedInput.get(0) instanceof Double) {
            return (Double) parsedInput.get(0);
        } // Base case: calculated

        // Recursive case: find the first operation to perform
        int operationIndex = findNextOperation(parsedInput);

        // Perform the operation and modify the list
        List<Object> modifiedList = performOperation(parsedInput, operationIndex);

        return calculate(modifiedList); // Recursion to do the next operation
    }

    private static int findNextOperation(List<Object> list) {
        // Find the index of the operation to perform, respecting operator precedence
        // This method should return the index of the first '*' or '/' found, 
        // or the first '+' or '-' if no '*' or '/' is present.
        return 0;
    }

    private static List<Object> performOperation(List<Object> list, int operationIndex) {
        // Perform the operation at operationIndex, and return a new list with the operation result
        // replacing the operation and its operands.
        return null;
    }

    public static void printWelcome(){
        System.out.println("================================================================\nWelcome to the Java Calcuator\n================================================================");
        System.out.println("Currently supports addition (+), subtraction(-), multiplication(*), and division(/). Parenthesis not yet supported, only order of operations");
        System.out.println("Enter a statement, or type 'exit' to leave calcuator\n");
    }

    public static List<Object> parseInput(String input) {
        String[] parts = input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        List<Object> resultList = new ArrayList<>();
        for (String part : parts) {
            if (part.matches("-?\\d+(\\.\\d+)?")) { // Check if it's a number
                resultList.add(Double.parseDouble(part));
            } else {
                resultList.add(part);
            }
        }
        return resultList;
    }
}