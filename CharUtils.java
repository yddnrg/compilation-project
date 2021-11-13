import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharUtils {
    private static CharUtils instance = new CharUtils();

    private CharUtils() {

    }

    public static CharUtils getInstance() {
        return instance;
    }

    public Scanner scanner = new Scanner(System.in);
    private TokenUtils tokenUtils = TokenUtils.getInstance();
    private List<Object> allText = TokenUtils.getInstance().getAllText();
    private String[] originalText;

    private void filter() {
        StringBuilder sb = new StringBuilder("");
        while (scanner.hasNextLine()){
            sb.append(scanner.nextLine());
            sb.append("\n");
        }
        String pattern = "\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/|\\/\\*(\\s|.)*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(sb.toString());
        originalText = m.replaceAll(" ").split("\\s+");
    }

    private void split(String string) {
        int len = string.length();
        for (int i = 0; i < len; i++) {
            Character ch = string.charAt(i);
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder("");
                while (i < len && Character.isDigit(string.charAt(i))) {
                    ch = string.charAt(i);
                    sb.append(ch);
                    i++;
                }
                addNumber(sb.toString());
                i--;
            } else if (Character.isLowerCase(ch) || Character.isUpperCase(ch) || ch == '_') {
                StringBuilder sb = new StringBuilder("");
                while (i < len && (Character.isLetterOrDigit(string.charAt(i)) || string.charAt(i) == '_')) {
                    ch = string.charAt(i);
                    sb.append(ch);
                    i++;
                }
                i--;
                if (sb.toString().equals("main")) {
                    allText.add(sb.toString());
                } else {
                    System.exit(2);
                }
            } else if (ch == '=' || ch == '>' || ch == '<') {
                if (i + 1 >= len || (string.charAt(i + 1) != '=')) {
                    allText.add(ch.toString());
                } else {
                    StringBuilder sb = new StringBuilder(ch.toString());
                    sb.append(string.charAt(i + 1));
                    allText.add(sb.toString());
                    i++;
                }
            } else if (tokenUtils.isSymbol(ch.toString())) {
                allText.add(ch.toString());
            } else {
                System.exit(3);
            }
        }
    }

    private void addNumber(String num) {
        try {
            if (num.charAt(0) == '0' && num.length() == 1) {
                allText.add(0);
            } else if (num.charAt(0) == '0' && (num.charAt(1) == 'x' || num.charAt(1) == 'X')) {
                allText.add(Integer.parseInt(num, 16));
            } else if (num.charAt(0) == '0') {
                allText.add(Integer.parseInt(num, 8));
            } else {
                allText.add(Integer.parseInt(num));
            }
        } catch (NumberFormatException e) {
            System.exit(1);
        }
    }

    public void read() {
        this.filter();
        for (String token : originalText) {
            if (tokenUtils.isReserved(token) || tokenUtils.isSymbol(token)) {
                allText.add(token);
            } else {
                split(token);
            }
        }
    }

}
