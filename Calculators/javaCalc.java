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
            List<Object> parsedInput = parseInput(input);

            double output = calculate(parsedInput);
            System.out.printf("%s = %f\n", input, output);
        }
        scan.close();
    }

    public static double calculate(List<Object> parsedInput) {
        if (parsedInput.size() == 1 && parsedInput.get(0) instanceof Double) {
            return (Double) parsedInput.get(0);
        } // Base case: calculated

        // Recursive case: find the first operation to perform
        int operationIndex = findNextOperation(parsedInput);

        // Perform the operation
        performOperation(parsedInput, operationIndex);

        return calculate(parsedInput); // Recursion to do the next operation
    }

    public static int getOperatorMultiplier(String operator) {
        switch (operator) {
            case "*":
                return 4;
            case "/":
                return 3; 
            case "+":
                return 2;
            case "-":
                return 1;
            default:
                return 0; // Non-operator case
        }
    }
    
    public static int findNextOperation(List<Object> list) {
        // Weight each symbol by its (priorityConstant * listSize) + (listSize - i)
        // A better symbol will always win, and a further left will always win between two of the same
        int bestIndex = -1;
        int highestStrength = 0;
        int listSize = list.size();
    
        for (int i = 0; i < listSize; i++) {
            if (list.get(i) instanceof String) {
                String operator = (String) list.get(i);
                int priorityConstant = getOperatorMultiplier(operator);
                int strength = (priorityConstant * listSize) + (listSize - i);
    
                if (strength > highestStrength) {
                    highestStrength = strength;
                    bestIndex = i;
                }
            }
        }
    
        return bestIndex;
    } 

    public static void performOperation(List<Object> list, int operationIndex) {
        double leftOperand = (double) list.get(operationIndex - 1);
        double rightOperand = (double) list.get(operationIndex + 1);
        String operator = (String) list.get(operationIndex);
    
        double result = 0.0;
        switch (operator) {
            case "+":
                result = leftOperand + rightOperand;
                break;
            case "-":
                result = leftOperand - rightOperand;
                break;
            case "*":
                result = leftOperand * rightOperand;
                break;
            case "/":
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = leftOperand / rightOperand;
                break;
        }
    
        // Replace the operation and its operands with the result
        list.set(operationIndex - 1, result);
        list.remove(operationIndex); // Remove operator
        list.remove(operationIndex); // Remove right operand
    }
    

    public static void printWelcome(){
        System.out.println("================================================================\nWelcome to the Java Calcuator\n================================================================");
        System.out.println("Currently supports addition (+), subtraction(-), multiplication(*), and division(/). Parenthesis not yet supported, only order of operations");
        System.out.println("Enter a statement, or type 'exit' to leave calcuator\n");
    }

    public static List<Object> parseInput(String input) {
        // Remove all spaces from the input string
        input = input.replaceAll("\\s+", "");
    
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