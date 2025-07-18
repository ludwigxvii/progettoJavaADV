package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class DocumentAnalyzer {

    private Set<String> stopwords;
    private List<String> documents;
    private List<Map<String, Integer>> wordFrequencies = new ArrayList<>();

    public DocumentAnalyzer(List<String> docPaths, String language) {
        loadStopwords(language);
        loadDocuments(docPaths);
        generate_freq();
    }

    private void loadStopwords(String lang) {
        stopwords = new HashSet<>();
        Path stopwordPath = Paths.get(System.getProperty("user.dir"),
                                      "src", "resources", "stopwords",
                                      "stopwords_" + lang + ".txt");

        System.out.println("Caricamento stopwords da: " + stopwordPath.toAbsolutePath());

        try (BufferedReader br = Files.newBufferedReader(stopwordPath, StandardCharsets.UTF_8)) {
            br.lines().map(String::trim).forEach(stopwords::add);
            stopwords.forEach((s)->System.out.println(s));
        } catch (IOException e) {
            System.err.println("Errore caricamento stopwords: " + e.getMessage());
        }
    }

    private void loadDocuments(List<String> paths) {
        documents = new ArrayList<>();

        for (String fileName : paths) {
            Path docPath = Paths.get(System.getProperty("user.dir"),
                                     "src", "resources", "texts", fileName);

            System.out.println("Caricamento documento da: " + docPath.toAbsolutePath());

            //StringBuilder content = new StringBuilder();

            try (BufferedReader br = Files.newBufferedReader(docPath, StandardCharsets.UTF_8)) {
                //String line;
                String content = br.lines().collect(Collectors.joining(" "));
                documents.add(content.trim());
                documents.forEach((s)->System.out.println("documents: "+s+"\n"));
            } catch (IOException e) {
                System.err.println("Errore caricamento documento: " + fileName + " → " + e.getMessage());
            }
        }
    }
    
    void generate_freq(){
    for (String text : documents) {
            Map<String, Integer> freq = new HashMap<>();
            String[] words = text.toLowerCase().replaceAll("[^a-zA-Zàèìòùáéíóú']", " ").split("\\s+");
            for (String w : words) {
                if (!stopwords.contains(w) && !w.isEmpty()) {
                    freq.put(w, freq.getOrDefault(w, 0) + 1);
                }
            }
            wordFrequencies.add(freq);
            //wordFrequencies.forEach((s)->System.out.println("frequenze: "+s+"\n"));
        }
    
    
    }
    
    public List<Map<String, Integer>> getWordFrequencies() {
        return wordFrequencies;
    }

    public Map<String, Integer> getGlobalFrequencies() {
        Map<String, Integer> total = new HashMap<>();
        for (Map<String, Integer> docFreq : wordFrequencies) {
            for (Map.Entry<String, Integer> entry : docFreq.entrySet()) {
                total.put(entry.getKey(), total.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
        return total;
    }

    public List<String> getAllWords() {
        return getGlobalFrequencies().keySet().stream().collect(Collectors.toList());
    }

    public int getFrequencyInDocument(String word, int docIndex) {
        return wordFrequencies.get(docIndex).getOrDefault(word.toLowerCase(), 0);
    }

    public boolean appearsInAnyDocument(String word) {
        return wordFrequencies.stream().anyMatch(map -> map.containsKey(word.toLowerCase()));
    }
}

   