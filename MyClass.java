import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class MyClass {
    public static Map<String, String> printMap = new HashMap<String, String>();

    public static void initMap() {
        printMap.put("if", "If");
        printMap.put("else", "Else");
        printMap.put("while", "While");
        printMap.put("break", "Break");
        printMap.put("continue", "Continue");
        printMap.put("return", "Return");
        printMap.put(";", "Semicolon");
        printMap.put("(", "LPar");
        printMap.put(")", "RPar");
        printMap.put("{", "LBrace");
        printMap.put("}", "RBrace");
        printMap.put("+", "Plus");
        printMap.put("*", "Mult");
        printMap.put("/", "Div");
        printMap.put("<", "Lt");
        printMap.put(">", "Gt");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initMap();
        while (scanner.hasNext()) {
            String string = scanner.next();
            int len = string.length();
            for (int i = 0; i < len; i++) {
                Character ch = string.charAt(i);
                if (Character.isDigit(ch)) {
                    int num = 0;
                    while (i < len && Character.isDigit(string.charAt(i))) {
                        ch = string.charAt(i);
                        num *= 10;
                        num += (ch - '0');
                        i++;
                    }
                    i--;
                    System.out.println("Number(" + num + ")");
                } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch) || ch == '_') {
                    StringBuilder sb = new StringBuilder("");
                    while (i < len && (Character.isLetterOrDigit(string.charAt(i)) || ch == '_')) {
                        ch = string.charAt(i);
                        sb.append(ch);
                        i++;
                    }
                    i--;
                    String result = sb.toString();
                    if (printMap.get(result) != null) {
                        System.out.println(printMap.get(result));
                    } else {
                        System.out.println("Ident(" + result + ")");
                    }
                } else if (ch == '=') {
                    if (i+1>=len||(string.charAt(i + 1) != '=')) {
                        System.out.println("Assign");
                    } else {
                        System.out.println("Eq");
                        i++;
                    }
                } else if (printMap.get(ch.toString()) != null) {
                    System.out.println(printMap.get(ch.toString()));
                } else {
                    System.out.println("Err");
                    return;
                }
            }
        }
    }
}