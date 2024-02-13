import unittest
import PythonCalc

class TestStringSeparation(unittest.TestCase):
    def test_string_separation(self):
        test_cases = [
            ("9-19*3", [9.0, '-', 19.0, '*', 3.0]),
            ("12+4/6", [12.0, '+', 4.0, '/', 6.0]),
            ("5*7-8/2", [5.0, '*', 7.0, '-', 8.0, '/', 2]),
            ("10-2+3*4", [10.0, '-', 2.0, '+', 3.0, '*', 4.0])
        ]

        for input_string, expected_output in test_cases:
            with self.subTest(input_string=input_string):
                self.assertEqual(PythonCalc.parseInput(input_string), expected_output)

class TestFindNextOperation(unittest.TestCase):
    def test_find_next_operation(self):
        test_cases = [
            (['3', '+', '2', '*', '4'], 3),
            (['5', '-', '3', '+', '7'], 3),
            (['8', '/', '4', '/', '2'], 1),
            (['6', '*', '2', '-', '3'], 1) 
        ]

        for input_list, expected_index in test_cases:
            with self.subTest(input_list=input_list):
                self.assertEqual(PythonCalc.findNextOperation(input_list), expected_index)

class TestPerformCalculation(unittest.TestCase):
    def test_perform_calculation(self):
        test_cases = [
            (['3.0', '+', '2.0'], 1, [5.0]),   # Testing addition
            (['5.0', '-', '3.0'], 1, [2.0]),   # Testing subtraction
            (['6.0', '*', '2.0'], 1, [12.0]),  # Testing multiplication
            (['8.0', '/', '4.0'], 1, [2.0]),   # Testing division
            (['9.0', '/', '0.0'], 1, ZeroDivisionError)  # Testing division by zero
        ]

        for parsed, operationIndex, expected in test_cases:
            with self.subTest(parsed=parsed, operationIndex=operationIndex):
                if expected is ZeroDivisionError:
                    with self.assertRaises(ZeroDivisionError):
                        PythonCalc.performCalculation(parsed, operationIndex)
                else:
                    PythonCalc.performCalculation(parsed, operationIndex)
                    self.assertEqual(parsed, expected)

class TestCalculate(unittest.TestCase):
    def test_calculate(self):
        test_cases = [
            (['3', '+', '2'], 5.0),        # Basic addition
            (['5', '-', '3'], 2.0),        # Basic subtraction
            (['6', '*', '2'], 12.0),       # Basic multiplication
            (['8', '/', '4'], 2.0),        # Basic division
            (['3', '+', '2', '*', '4'], 11.0),  # Mixed operations with precedence
            (['3', '*', '2', '-', '4', '/', '2'], 4.0),  # Complex mixed operations
            (['9', '/', '0'], ZeroDivisionError)  # Division by zero
        ]

        for parsed_input, expected in test_cases:
            with self.subTest(parsed_input=parsed_input):
                if expected is ZeroDivisionError:
                    with self.assertRaises(ZeroDivisionError):
                        PythonCalc.calculate(parsed_input)
                else:
                    result = PythonCalc.calculate(parsed_input)
                    self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
