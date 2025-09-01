package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Question {
    private String text;
    private List<String> options;
    private int correctIndex;
    private int givenIndex;
    String correctValue;
    String givenValue;

    public Question() {
    }
    public Question(String text, List<String> options, int correctIndex) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect() {
        return givenIndex == correctIndex;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setGivenIndex(int givenIndex) {
        this.givenIndex = givenIndex;
    }

    public String getCorrectValue() {
        return options.get(correctIndex);
    }

    public String getGivenValue() {
        return options.get(givenIndex);
    }

    @Override
    
public String toString() {
    return "Question{" +
           "text='" + text + '\'' +
           ", options=" + options +
           ", correctIndex=" + correctIndex +
           ", givenIndex=" + givenIndex +
           ", correctValue='" + correctValue + '\'' +
           ", givenValue='" + givenValue + '\'' +
           '}';
}

    
       public String toJson() {
        StringBuilder json = new StringBuilder("{");

        json.append("\"text\":\"").append(text).append("\",");
        json.append("\"options\":[");
        for (int i = 0; i < options.size(); i++) {
            json.append("\"").append(options.get(i)).append("\"");
            if (i < options.size() - 1) json.append(",");
        }
        json.append("],");
        json.append("\"correctIndex\":").append(correctIndex).append(",");
        json.append("\"givenIndex\":").append(givenIndex).append(",");
        json.append("\"correctValue\":\"").append(correctValue).append("\",");
        json.append("\"givenValue\":\"").append(givenValue).append("\"");

        json.append("}");
        return json.toString();
    }
       private static String unescapeJsonString(String s) {
    if (s == null) return null;
    s = s.trim();
    if (s.startsWith("\"") && s.endsWith("\"")) {
        s = s.substring(1, s.length() - 1);
    }
    s = s.replace("\\\"", "\"");
    s = s.replace("\\\\", "\\");
    s = s.replace("\\n", "\n");
    s = s.replace("\\t", "\t");
    return s;
}
public static Question fromJson(String json) {
    Question q = new Question();
    Map<String, String> map = parseJson(json);

    q.text = unescapeJsonString(map.get("text"));


    // Parsing delle opzioni
    q.options = extractOptions(json);

    // Parsing degli indici
    q.correctIndex = parseSafeInt(map.get("correctIndex"), -1);
    q.givenIndex = parseSafeInt(map.get("givenIndex"), -1);

    // Parsing dei valori (che possono essere "null" come stringa)
    q.correctValue = map.get("correctValue");
    q.givenValue = map.get("givenValue");

    return q;
}
private static List<String> extractOptions(String json) {
    List<String> options = new ArrayList<>();
    Pattern pattern = Pattern.compile("\"options\"\\s*:\\s*\\[(.*?)\\]");
    Matcher matcher = pattern.matcher(json);
    if (matcher.find()) {
        String arrayContent = matcher.group(1);
        String[] items = arrayContent.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (String item : items) {
            options.add(item.trim().replaceAll("^\"|\"$", ""));
        }
    }
    return options;
}


// Metodo di supporto per parsing sicuro
private static int parseSafeInt(String value, int defaultValue) {
    if (value == null || value.equals("null") || value.isEmpty()) {
        return defaultValue;
    }
    try {
        return Integer.parseInt(value.replaceAll("\"", ""));
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

    
private static Map<String, String> parseJson(String json) {
    Map<String, String> map = new HashMap<>();

    // Rimuove le graffe iniziali e finali
    json = json.trim();
    if (json.startsWith("{") && json.endsWith("}")) {
        json = json.substring(1, json.length() - 1);
    }

    // Regex per trovare tutte le coppie chiave:valore
    Pattern pattern = Pattern.compile("\"(.*?)\"\\s*:\\s*(\".*?\"|\\[.*?\\]|[^,]+)");
    Matcher matcher = pattern.matcher(json);

    while (matcher.find()) {
        String key = matcher.group(1);
        String value = matcher.group(2).trim();

        // Rimuove virgolette esterne se presenti
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }

        map.put(key, value);
    }

    return map;
}

}
