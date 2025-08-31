package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "Question{" + "text=" + text + ", options=" + options + ", correctIndex=" + correctIndex + ", givenIndex=" + givenIndex + '}';
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
       
    public static Question fromJson(String json) {
        Question q = new Question();
        Map<String, String> map = parseJson(json);

        q.text = map.get("text");
        q.correctValue = map.get("correctValue");
        q.givenValue = map.get("givenValue");
        q.correctIndex = Integer.parseInt(map.get("correctIndex"));
        q.givenIndex = Integer.parseInt(map.get("givenIndex"));

        String optionsRaw = map.get("options");
        List<String> opts = new ArrayList<>();
        if (optionsRaw != null && optionsRaw.length() > 2) {
            String[] items = optionsRaw.substring(1, optionsRaw.length() - 1).split(",");
            for (String item : items) {
                opts.add(item.trim().replaceAll("^\"|\"$", ""));
            }
        }
        q.options = opts;

        return q;
    }
    
       private static Map<String, String> parseJson(String json) {
        Map<String, String> map = new HashMap<>();
        json = json.trim().substring(1, json.length() - 1);
        String[] pairs = json.split(",(?=(?:[^\"]\"[^\"]\")[^\"]$)");
            // rimuove le  { }, spezza e crea una coppia di attributi e valori
        for (String pair : pairs) {
            String[] kv = pair.split(":", 2);
            String key = kv[0].trim().replaceAll("^\"|\"$", "");
            String value = kv[1].trim();
            map.put(key, value);
        }
        return map;
    }
}
