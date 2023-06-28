package com.example.caculatorr;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView working;
    private TextView result;
    private StringBuilder workings;
    private boolean isResultShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        working = findViewById(R.id.firstText);
        result = findViewById(R.id.result);

        workings = new StringBuilder();
        isResultShown = false;
    }

    public void clear(View view) {
        workings.setLength(0);
        working.setText("");
        result.setText("");
        isResultShown = false;
    }

    public void brackets(View view) {
        if (isResultShown) {
            clear(view);
        }

        int openBracket = 0;
        int closeBracket = 0;
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '(') {
                openBracket++;
            } else if (workings.charAt(i) == ')') {
                closeBracket++;
            }
        }

        if (openBracket > closeBracket) {
            workings.append(")");
        } else {
            workings.append("(");
        }

        working.setText(workings.toString());
    }

    public void powerOf(View view) {

        appendOperator("^");
    }

    public void division(View view) {

        appendOperator("/");
    }

    public void seven(View view) {

        appendNumber("7");
    }

    public void eight(View view) {

        appendNumber("8");
    }

    public void nine(View view) {

        appendNumber("9");
    }

    public void times(View view) {
        appendOperator("X");
    }

    public void four(View view) {

        appendNumber("4");
    }

    public void five(View view) {

        appendNumber("5");
    }

    public void six(View view) {

        appendNumber("6");
    }

    public void minus(View view) {

        appendOperator("-");
    }

    public void one(View view) {
        appendNumber("1");
    }

    public void two(View view) {

        appendNumber("2");
    }

    public void three(View view) {

        appendNumber("3");
    }

    public void plus(View view) {

        appendOperator("+");
    }

    public void decimal(View view) {
        if (isResultShown) {
            clear(view);
        }

        if (workings.length() == 0 || isOperator(workings.charAt(workings.length() - 1))) {
            workings.append("0");
        }

        if (!hasDecimalPoint()) {
            workings.append(".");
        }

        working.setText(workings.toString());
    }

    public void zero(View view) {
        appendNumber("0");
    }

    public void equals(View view) {
        if (workings.length() > 0 && !isOperator(workings.charAt(workings.length() - 1))) {
            String expression = workings.toString();
            double result;

            try {
                result = evaluateExpression(expression);
                String resultString = Double.toString(result);
                updateResultText(resultString);
            } catch (Exception ex) {
                updateResultText("Error");
            }

            isResultShown = true;
        }
    }

    private void appendNumber(String number) {
        if (isResultShown) {
            clear(null);
        }

        workings.append(number);
        working.setText(workings.toString());
    }

    private void appendOperator(String operator) {
        if (isResultShown) {
            clear(null);
        }

        if (workings.length() == 0) {
            if (operator.equals("-")) {
                workings.append(operator);
                working.setText(workings.toString());
            }
        } else if (!isOperator(workings.charAt(workings.length() - 1))) {
            workings.append(operator);
            working.setText(workings.toString());
        }
    }

    private void updateResultText(String text) {
        result.setText(text);
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == 'X' || ch == '/' || ch == '^';
    }

    private boolean hasDecimalPoint() {
        int startIndex = workings.length() - 1;
        while (startIndex >= 0 && !isOperator(workings.charAt(startIndex))) {
            if (workings.charAt(startIndex) == '.') {
                return true;
            }
            startIndex--;
        }
        return false;
    }

    private double evaluateExpression(String expression) {
        expression = expression.replace("X", "*"); // Replace 'X' with '*' for multiplication

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--;

                numbers.push(Double.parseDouble(sb.toString()));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double result = applyOperator(operators.pop(), numbers.pop(), numbers.pop());
                    numbers.push(result);
                }
                operators.pop(); // Pop the '(' symbol
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    double result = applyOperator(operators.pop(), numbers.pop(), numbers.pop());
                    numbers.push(result);
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            double result = applyOperator(operators.pop(), numbers.pop(), numbers.pop());
            numbers.push(result);
        }

        return numbers.pop();
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private boolean hasPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        }
        return (operator1 != '*' && operator1 != '/' && operator1 != '^') || (operator2 != '+' && operator2 != '-');
    }
}
