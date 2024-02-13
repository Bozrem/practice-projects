import re
# Take string input like the Java one
# Parse it with Regex
# Evaluate with recursion like Java one

def welcome():
    print("================================================================\nWelcome to the Python Calcuator\n================================================================")
    print("Currently supports addition (+), subtraction(-), multiplication(*), and division(/). Parenthesis not yet supported, only order of operations")
    print("Enter 'exit' to quit")

def parseInput(input:str):
    # Remove spaces
    input = re.sub(r'\s', "", input)
    # Adjust regex to include floating-point numbers
    split = re.findall(r'\d+\.\d+|\d+|\D', input)

    # Convert numeric strings to floats
    for i, item in enumerate(split):
        if item.replace('.', '', 1).isdigit():
            split[i] = float(item)

    return split

def getOperatorMultiplier(operator):
    match operator:
        case "*":
            return 4
        case "/":
            return 3
        case "+":
            return 2
        case "-":
            return 1
    return 0
        
def findNextOperation(parsed):
    bestIndex = -1
    highestStrength = 0
    listSize = len(parsed)

    for i, item in enumerate(parsed):
        if isinstance(item, str):
            operator = item
            priorityConstant = getOperatorMultiplier(operator)
            strength = (priorityConstant * listSize) + (listSize - i)

            if strength > highestStrength:
                highestStrength = strength
                bestIndex = i

    return bestIndex

def performCalculation(parsed:list, operationIndex):
    leftOperand = float(parsed[operationIndex - 1])
    rightOperand = float(parsed[operationIndex + 1])
    operator = parsed[operationIndex]

    result = 0.0
    if operator == "+":
        result = leftOperand + rightOperand
    elif operator == "-":
        result = leftOperand - rightOperand
    elif operator == "*":
        result = leftOperand * rightOperand
    elif operator == "/":
        if rightOperand == 0:
            raise ZeroDivisionError("Division by zero")
        result = leftOperand / rightOperand
    del parsed[operationIndex + 1]
    parsed[operationIndex] = result
    del parsed[operationIndex - 1]

# Recursive calculating
def calculate(parsedInput: list):
    if len(parsedInput) == 1: return parsedInput[0]

    performCalculation(parsedInput, findNextOperation(parsedInput))
    return calculate(parsedInput)
    

if __name__ == "__main__":
    parseInput("9-193+13929*3")
    welcome()
    running = True
    while running:
        input_str = input(":  ")
        if input_str == "exit": break
        parsedInput = parseInput(input_str)
        result = calculate(parsedInput)
        print(f"{input_str} = {result}")
