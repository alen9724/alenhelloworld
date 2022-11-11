
import java.util.*;
import java.io.IOException;

    public class Main {
        public static void main(String[] args) throws IOException {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите пример: ");
            String input = in.nextLine();

            Calculator calculator = new Calculator(input);
            System.out.println(input + " = " + calculator.calculate());
        }
    }


    class Calculator {
        private String[] tokens;
        private int current;

        public Calculator(String input) {
            this.tokens = input.split(" ");
            this.current = 0;
        }

        public double calculate() {
            double first = multiply();

            while (current < tokens.length) {
                String operator = tokens[current];

                if (!operator.equals("+") && !operator.equals("-")) {
                    break;
                } else {
                    current++;
                }

                double second = multiply();

                if (operator.equals("+")) {
                    first += second;
                } else {
                    first -= second;
                }
            }

            return first;
        }

        public double multiply() {
            double first = Double.parseDouble(tokens[current++]);

            while (current < tokens.length) {
                String operator = tokens[current];
                this.validateOperator(operator);

                if (!operator.equals("*") && !operator.equals("/")) {
                    break;
                } else {
                    current++;
                }

                double second = Double.parseDouble(tokens[current++]);
                this.validateNumber(second);

                if (operator.equals("*")) {
                    first *= second;
                } else {
                    first /= second;
                }
            }

            return first;
        }

        public void validateOperator(String operator) {
            if (!operator.equals("*") && !operator.equals("/") &&
                    !operator.equals("+") && !operator.equals("-")) {

                throw new RuntimeException("Недопустимый оператор");
            }
        }

        public void validateNumber(Double number) {
            if (number % 1 != 0) {
                throw new RuntimeException("Введите целое число !");
            }
            if (number > 10.0) {
                throw new RuntimeException("Вы ввели число больше 10 !");
            }
        }
    }



