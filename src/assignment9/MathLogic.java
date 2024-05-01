package assignment9;
import java.math.*;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathLogic {

    public static void main(String[] args){
        if(args[0] == null){
            System.out.println("Error args is null");
        }
        else{
            //Get the first element of the array which will be the equation
            String expression = args[0];
            //Parsing our equation
            HashMap<String, Double> variables = parsing(args);
            try {
                //We carry out the place to solve the equation
                double result = calculate(expression, variables);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }



    /**
     * A method that calculates our entire equation based
     * on the formula and parsed values
     * @param expression - Equation unchanged
     * @param variables Parsed numbers
     * @return Calculation result
     */
    public static double calculate(String expression, HashMap<String, Double> variables) {
        // Add spaces before and after the minus to correctly define the expression

        expression = expression.replaceAll("(?<=[-+*/^()])|(?=[-+*/^()])", " $0 ");
        System.out.println(expression);

        // Check for a unary minus at the beginning of an expression
        expression = expression.replaceFirst("^\\s*-", " -1 * ");
        System.out.println(expression);


        //Divide everything into tokens after adding spaces
        String[] tokens = expression.split("\\s+");
        System.out.println(expression);

        //Create a stack for our numbers and operators
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        boolean unaryMinusLast = true;

        for (String token : tokens) {
            if (token.trim().isEmpty()) {
                continue;
            }

            char firstChar = token.charAt(0);

            if (Character.isDigit(firstChar) || (firstChar == '-' && unaryMinusLast)) {
                values.push(Double.parseDouble(token));
                unaryMinusLast = false; // Resetting the unary minus
            } else if (Character.isLetter(firstChar)) {
                if (variables.containsKey(token)) {
                    values.push(variables.get(token));
                }
                //If there is some unnecessary symbol, throw an error about it
                else {
                    throw new IllegalArgumentException("Undefined variable: " + token);
                }
            }
            //Order of parentheses for calculations, support for parentheses in an equation
            else if (firstChar == '(') {
                operators.push('(');
            } else if (firstChar == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperation(operators.pop(), values);
                }
                if (!operators.isEmpty()) {
                    operators.pop(); // Remove '('
                }
            } else {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(firstChar)) {
                    applyOperation(operators.pop(), values);
                }
                operators.push(firstChar);
                unaryMinusLast = firstChar == '-'; // The next minus will be unary if the current minus
            }
        }
        //Until our operators run out, we count the action between them
        while (!operators.isEmpty()) {
            applyOperation(operators.pop(), values);
        }

        return values.pop();
    }

    /**
     * The method parses our entire equation
     * Replaces alphabetic variables with numbers
     * @param line - Accepting incoming parameters
     * @return Returns the entire value after calculations
     */
    private static HashMap<String, Double> parsing(String[] line){
        HashMap<String, Double> variables = new HashMap<>();
        for (int i = 1; i < line.length; i++) {
            String[] parts = line[i].replace(" ", "").split("=");
            if (parts.length == 2) {
                String variable = parts[0];
                double value = Double.parseDouble(parts[1]);
                variables.put(variable, value);
            } else {
                System.out.println("Invalid variable format: " + line[i]);
                return null;
            }
        }
        return variables;
    }
    /**
     * A method that performs a mathematical operation between two numbers
     * @param operator - We accept the sign of the operation that we must do
     * @param values - The meaning with which we perform a mathematical operation
     */
    private static void applyOperation(char operator, Stack<Double> values) {
        //Throw an error if the stack is empty
        if (values.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        double b = values.pop();
        double a = values.pop();
        switch (operator) {
            case '^':
                values.push(Math.pow(a, b));
                break;
            case '*':
                values.push(a * b);
                break;
            case '/':
                //You can't divide by zero
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                values.push(a / b);
                break;
            case '+':
                values.push(a + b);
                break;
            case '-':
                values.push(a - b);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    /**
     * A method that determines the sequence of actions performed on operators
     * @param operator - We accept a symbol that is a mathematical operator
     * @return We return a number that is the order in execution
     */
    private static int precedence(char operator) {
        return switch (operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }
    private static String unarniuMinus(String line){
        String result ="";
        for (int i = 0; i < line.length(); i++){

        }
        return result;
    }
}
