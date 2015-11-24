package bjs.task16.applications;

import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YM on 09.11.2015.
 */
public class Calculator extends Application{
    //Number of the operation which class performs
    private static final int OPERATION_NUM = 6;

    //Operation type constants
    private static final String SUM = "SUM";
    private static final String SUB = "SUB";
    private static final String MUL = "MUL";
    private static final String DIV = "DIV";
    private static final String SQRT = "SQRT";
    private static final String SIN = "SIN";

    //For unary operation only leftOperand will be used
    double leftOperand, rightOperand, resultValue;

    //Contains string representation of the results
    String[][] calculationResults;

    public Calculator() {
        setApplicationName("Calculator");
        setApplicationVersion("0.1");

        calculationResults = new String[OPERATION_NUM][1];
    }

    void printCalculationResults() {
        for (int i = 0; i < OPERATION_NUM; i++) {
            if (calculationResults[i][0] == null)
                continue;

            System.out.print(calculationResults[i][0] + ": ");

            for (int j = 1; j < calculationResults[i].length; j++) {
                String resultStr = calculationResults[i][j];
                if (j > 0)
                    resultStr += ", ";

                System.out.print(resultStr);
            }

            System.out.print("\n");
        }
    }

    void appendResult(String resultType) {
        int typeIndex = getNumericResultType(resultType);

        if (typeIndex >= 0) {
            calculationResults[typeIndex][0] = resultType;
            int resultNum = calculationResults[typeIndex].length + 1;

            //This is the code from the java.util.Arrays.copyOf()
            String[] copyArray = new String[resultNum];
            System.arraycopy(calculationResults[typeIndex], 0, copyArray, 0,
                    Math.min(calculationResults[typeIndex].length, resultNum));

            copyArray[resultNum - 1] = Double.toString(resultValue);
            calculationResults[typeIndex] = copyArray;
        }
    }

    int getNumericResultType(String resultType) {
        int result = -1;

        switch (resultType) {
            case SUM:
                result = 0;
                break;
            case SUB:
                result = 1;
                break;
            case MUL:
                result = 2;
                break;
            case DIV:
                result = 3;
                break;
            case SQRT:
                result = 4;
                break;
            case SIN:
                result = 5;
                break;
        }

        return result;
    }

    void setBinary(double leftOperand, double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public void calcExpression(String expression) {
        /**
         * Use String.matches(), String.split() instead of Pattern and Matcher
         */

        Pattern pattern = Pattern.compile("[\\+\\-\\*/]{1}");
        Matcher matcher = pattern.matcher(expression);

        //Operation symbols found, we have an expression
        if (matcher.find()) {
            String operation = matcher.group();

            //Extract operands
            pattern = Pattern.compile("\\d+\\.?\\d*");
            matcher = pattern.matcher(expression);

            double leftOperand = 0;
            if (matcher.find()) {
                leftOperand = Double.parseDouble(matcher.group());
            }
            else {
                System.out.println("Operands not found\n");
                return;
            }

            switch (operation) {
                case "+":
                    while (matcher.find()) {
                        setBinary(leftOperand, Double.parseDouble(matcher.group())); //result += Double.parseDouble(matcher.group());
                        getSum();
                    }
                    break;
                case "-":
                    while (matcher.find()) {
                        setBinary(leftOperand, Double.parseDouble(matcher.group())); //result -= Double.parseDouble(matcher.group());
                        getSub();
                    }
                    break;
                case "*":
                    while (matcher.find()) {
                        setBinary(leftOperand, Double.parseDouble(matcher.group())); //result *= Double.parseDouble(matcher.group());
                        getMul();
                    }
                    break;
                case "/":
                    while (matcher.find()) {
                        setBinary(leftOperand, Double.parseDouble(matcher.group())); //result /= Double.parseDouble(matcher.group());
                        getDiv();
                    }
                    break;
                default:
                    resultValue = 0;
                    System.out.println("Unknown operation found\n");
                    break;
            }

            System.out.println("Expression result: " + resultValue + "\n");
        }
        else
            System.out.println("String is not expression with single +-*/\n");
    }

    void setUnary(double leftOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = 0;
    }

    double getSum() {
        resultValue = leftOperand + rightOperand;
        appendResult(SUM);

        return resultValue;
    }

    double getSub() {
        resultValue = leftOperand - rightOperand;
        appendResult(SUB);

        return resultValue;
    }

    double getMul() {
        resultValue = leftOperand * rightOperand;
        appendResult(MUL);

        return resultValue;
    }

    double getDiv() {
        resultValue = leftOperand / rightOperand;
        appendResult(DIV);

        return resultValue;
    }

    double getSqrt() {
        //If the argument is NaN (not a number), then the result is NaN.
        resultValue = Math.sqrt(leftOperand);
        appendResult(SQRT);

        return resultValue;
    }

    double getSin() {
        resultValue = Math.sin(leftOperand);
        appendResult(SIN);

        return resultValue;
    }

    int getArrayMax(int array[]) {
        int returnValue = array[0];

        for (int element: array) {
            if (element>returnValue)
                returnValue = element;
        }

        return returnValue;
    }

    void printResult() {
        System.out.println("Result value: " + resultValue);
    }
}
