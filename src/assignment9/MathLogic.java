package assignment9;
import java.util.*;
import java.util.function.Function;

public class MathLogic {
    private static final Map<String, Function<Double, Double>> mathFunctions = new HashMap<>();
    private static final Map<String, Integer> precedence = new HashMap<>();

    static {
        // Initialize math functions
        mathFunctions.put("sin", x -> Math.sin(Math.toRadians(x)));
        mathFunctions.put("cos", x -> Math.cos(Math.toRadians(x)));
        mathFunctions.put("tan", x -> Math.tan(Math.toRadians(x)));
        mathFunctions.put("atan", Math::atan);
        mathFunctions.put("log10", Math::log10);
        mathFunctions.put("log2", x -> Math.log(x) / Math.log(2));
        mathFunctions.put("sqrt", Math::sqrt);

        // Set operator priorities
        precedence.put("(", 0);
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("^", 3);
        precedence.put("~", 4); // Unary minus
    }

    public static void main(String[] args) {

        if (args == null || args.length < 1) {
            System.out.println("Error with args");
        }
        else {
            //Get the first element of the array which will be the equation
            String expression = args[0];
            //Parsing our equation
            HashMap<String, Double> variables = parsing(args);
            try {
                double result = calculate(expression, variables);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    /**
     * Calculate the value of the given expression using the specified variables.
     *
     * @param formula   The mathematical formula to evaluate
     * @param variables A map of variable names to values
     * @return The result of the calculation
     */
    public static double calculate(String formula, HashMap<String, Double> variables) {
        String[] tokens = tokenize(formula);
        Stack<Double> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    applyOperation(operandStack, operatorStack.pop());
                }
                operatorStack.pop(); // Remove '('
                if (!operatorStack.isEmpty() && mathFunctions.containsKey(operatorStack.peek())) {
                    String function = operatorStack.pop();
                    double value = operandStack.pop();
                    operandStack.push(mathFunctions.get(function).apply(value));
                }
            } else if (mathFunctions.containsKey(token) || precedence.containsKey(token)) {
                // Handle unary minus
                if (token.equals("-") && (operatorStack.isEmpty() || operatorStack.peek().equals("("))) {
                    operatorStack.push("~");
                } else {
                    while (!operatorStack.isEmpty() && (precedence.get(operatorStack.peek()) != null)
                            && (precedence.get(operatorStack.peek()) >= precedence(token))) {
                        applyOperation(operandStack, operatorStack.pop());
                    }
                    operatorStack.push(token);
                }
            } else if (Character.isDigit(token.charAt(0)) || Character.isLetter(token.charAt(0)) || token.charAt(0) == '-') {
                operandStack.push(parseValue(token, variables));
            }
        }

        while (!operatorStack.isEmpty()) {
            applyOperation(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }
    /**
     * The method parses our entire equation
     * Replaces alphabetic variables with numbers
     * @param line - Accepting incoming parameters
     */
    private static HashMap<String, Double> parsing(String[] line) {
        HashMap<String, Double> variables = new HashMap<>();
        for (int i = 1; i < line.length; i++) {
            String[] varValuePair = line[i].split("=");
            if (varValuePair.length == 2) {
                String varName = varValuePair[0].trim();
                try {
                    double value = Double.parseDouble(varValuePair[1].trim());
                    variables.put(varName, value);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid value for variable " + varName + ": " + varValuePair[1]);
                }
            } else {
                System.out.println("Invalid format for variable and value: " + line[i]);
                return null;
            }
        }
        return variables;
    }
    /**
     * Parse the value of the given token using the specified variables.
     *
     * @param token     The token to parse (can be a number or variable name)
     * @param variables A map of variable names to values
     * @return The parsed value of the token, or NaN if the token is not a valid number or variable
     */
    private static double parseValue(String token, HashMap<String, Double> variables) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return variables.getOrDefault(token, Double.NaN);
        }
    }

    /**
     * A method that performs a mathematical operation between two numbers
     * @param operator - We accept the sign of the operation that we must do
     * @param values - The meaning with which we perform a mathematical operation
     */
    private static void applyOperation(Stack<Double> values, String operator) {
        //Throw an error if the stack is empty
        if (values.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        if (operator.equals("~")) {
            // Unary minus operation
            double operand = values.pop();
            values.push(-operand);
        } else if (mathFunctions.containsKey(operator)) {
            // Mathematical function
            double operand = values.pop();
            values.push(mathFunctions.get(operator).apply(operand));
        } else {
            // Binary operation
            double rightNum = values.pop();
            double leftNum = values.isEmpty() ? 0 : values.pop();
            switch (operator) {
                case "+":
                    values.push(leftNum + rightNum);
                    break;
                case "-":
                    values.push(leftNum - rightNum);
                    break;
                case "*":
                    values.push(leftNum * rightNum);
                    break;
                case "/":
                    //You can't divide by zero
                    if (rightNum == 0) {
                        throw new IllegalArgumentException("Division by zero");
                    }
                    values.push(leftNum / rightNum);
                    break;
                case "^":
                    values.push(Math.pow(leftNum, rightNum));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + operator);
            }
        }
    }

    /**
     * Tokenize the given formula into a list of tokens.
     *
     * @param formula The mathematical formula to tokenize
     * @return An array of tokens
     */
    private static String[] tokenize(String formula) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean mayBeUnary = true;

        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (Character.isWhitespace(ch)) continue;

            if (Character.isDigit(ch) || (ch == '.')) {
                // Collect digits and decimal point
                currentToken.append(ch);
                mayBeUnary = false;
            } else if (Character.isLetter(ch)) {
                // Collect letters
                if (!currentToken.isEmpty() && !Character.isLetter(currentToken.charAt(0))) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                currentToken.append(ch);
                mayBeUnary = false;
            } else {
                // Process operators
                if (!currentToken.isEmpty()) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                if (ch == '-' && mayBeUnary) {
                    tokens.add("~"); // Unary minus
                } else {
                    tokens.add(String.valueOf(ch));
                }
                mayBeUnary = "+-*/^(".contains(String.valueOf(ch));
            }
        }
        if (!currentToken.isEmpty()) {
            tokens.add(currentToken.toString());
        }
        return tokens.toArray(new String[0]);
    }
    /**
     * A method that determines the sequence of actions performed on operators
     * @param operator - We accept a symbol that is a mathematical operator
     * @return We return a number that is the order in execution
     */
    private static int precedence(String operator) {
        return mathFunctions.containsKey(operator) ? 5 : switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "~" -> 4;
            default -> 0;
        };
    }
}