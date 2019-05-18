package savedPrevious;

import java.util.Map;
import java.util.TreeMap;

public class SymbolCounter {
    private TreeMap<Character, Integer> frequencyMap;

    SymbolCounter() {
        this.frequencyMap = new TreeMap<>();
    }

    public void addChar(char ch) {
        if (frequencyMap.containsKey(ch)) {
            Integer currentValue = frequencyMap.get(ch);

            frequencyMap.put(ch, currentValue + 1);
        } else {
            frequencyMap.put(ch, 1);
        }
    }

    public void addCharArray(char[] array) {
        for (Character ch : array) {
            this.addChar(ch);
        }
    }

    public String toString() {
        String stringifiedMap = "";

        for (Map.Entry<Character, Integer> entry : this.frequencyMap.entrySet()) {
            stringifiedMap += entry;
            stringifiedMap += "\n";
        }

        return stringifiedMap;
    }
}
