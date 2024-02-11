import java.util.Scanner;

public class AnnoyingCalculator {
    static String msg_0 = "Enter an equation";
    static String msg_1 = "Do you even know what numbers are? Stay focused!";
    static String msg_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";
    static String msg_3 = "Yeah... division by zero. Smart move...";
    static String msg_4 = "Do you want to store the result? (y / n):";
    static String msg_5 = "Do you want to continue calculations? (y / n):";
    static String msg_6 = " ... lazy";
    static String msg_7 = " ... very lazy";
    static String msg_8 = " ... very, very lazy";
    static String msg_9 = "You are";
    static String msg_10 = "Are you sure? It is only one digit! (y / n)";
    static String msg_11 = "Don't be silly! It's just one number! Add to the memory? (y / n)";
    static String msg_12 = "Last chance! Do you really want to embarrass yourself? (y / n)";

    static double memory = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(msg_0);
            String[] input = scanner.nextLine().split(" ");
            String x = input[0];
            String oper = input[1];
            String y = input[2];

            try {
                if (x.equals("M")) {
                    x = String.valueOf(memory);
                }
                double xValue = Double.parseDouble(x);

                if (y.equals("M")) {
                    y = String.valueOf(memory);
                }
                double yValue = Double.parseDouble(y);

                if (!(oper.equals("+") || oper.equals("-") || oper.equals("*") || oper.equals("/"))) {
                    System.out.println(msg_2);
                    continue;
                }
                check(xValue, yValue, oper);
                if (oper.equals("/") && yValue == 0) {
                    System.out.println(msg_3);
                } else {
                    double result = switch (oper) {
                        case "+" -> xValue + yValue;
                        case "-" -> xValue - yValue;
                        case "*" -> xValue * yValue;
                        case "/" -> xValue / yValue;
                        default -> 0; // Invalid operation, set result to 0
                    };
                    System.out.println(result);

                    System.out.println(msg_4);
                    String answer = scanner.nextLine();
                    if (answer.equals("y")) {
                        if (isOneDigit(result)) {
                            memory = result;
                        } else {
                            int msgIndex = 10;
                            while (true) {
                                if (msgIndex == 13) {
                                    memory = result;
                                    break;
                                } else if (msgIndex == 10) {
                                    System.out.println(msg_10);
                                } else if (msgIndex == 11) {
                                    System.out.println(msg_11);
                                } else if (msgIndex == 12) {
                                    System.out.println(msg_12);
                                }
                                answer = scanner.nextLine();
                                if (answer.equals("y")) {
                                    msgIndex++;
                                    continue;
                                }
                                break;
                            }
                        }

                    } else {
                        memory = 0;
                    }
                    System.out.println(msg_5);
                    answer = scanner.nextLine();
                    if (answer.equals("n")) {
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(msg_1);
            }
        }
        scanner.close();
    }

    static void check(double v1, double v2, String v3) {
        StringBuilder msg = new StringBuilder();
        if (isOneDigit(v1) && isOneDigit(v2)) {
            msg.append(msg_6);
        }
        if ((v1 == 1 || v2 == 1) && v3.equals("*")) {
            msg.append(msg_7);
        }
        if ((v1 == 0 || v2 == 0) && v3.matches("[*+-]")) {
            msg.append(msg_8);
        }
        if (!msg.toString().isEmpty()) {
            System.out.println(msg_9 + msg);
        }
    }

    static boolean isOneDigit(double v) {
        return -10 < v && v < 10 && v == (int) v;
    }
}
