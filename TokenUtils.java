import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenUtils {
    //单例模式
    private static TokenUtils instance = new TokenUtils();

    private TokenUtils() {
        init();
    }

    public static TokenUtils getInstance() {
        return instance;
    }

    private String[] reservedWord = {"const", "void", "int", "if", "else", "while", "break", "continue", "return"};
    private List<Object> allText = new ArrayList<Object>();
    private Map<String, Object> identifiers = new HashMap<String, Object>();
    private String[] symbols = {",", ";", "[", "]", "(", ")", "{", "}", "=", "+", "-", "!", "*", "/", "%", "<", ">", "<=", ">=", "==", "!=", "&&", "||"};
    private Map<String, String> printMap = new HashMap<String, String>();
    private int currentPosition = -1;

    public void init() {
        printMap.put("int","i32");
        printMap.put("return","ret");
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public List<Object> getAllText() {
        return allText;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public String getPrint(String key){
        return printMap.get(key);
    }


    public Object getCurrent() {
        if (currentPosition >= allText.size()) {
            System.exit(8);
            return null;
        } else {
            return allText.get(currentPosition);
        }
    }

    public Object getNext() {
        if (currentPosition >= allText.size()) {
            System.exit(8);
            return null;
        } else {
            return allText.get(++currentPosition);
        }
    }

    public boolean isReserved(String string) {
        for (String reserved : reservedWord) {
            if (string.equals(reserved)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSymbol(String string) {
        for (String symbol : symbols) {
            if (string.equals(symbol)) {
                return true;
            }
        }
        return false;
    }


}