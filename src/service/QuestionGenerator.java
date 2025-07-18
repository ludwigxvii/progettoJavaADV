package service;

import model.Question;

import java.util.*;

public class QuestionGenerator {

    private final DocumentAnalyzer analyzer;
    private final Random random = new Random();

    public QuestionGenerator(DocumentAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

   public List<Question> generateQuestions(int count) {
    List<Question> questions = new ArrayList<>();
    List<String> allWords = new ArrayList<>(analyzer.getGlobalFrequencies().keySet());
    Set<String> paroleUsate = new HashSet<>();
    Random random = new Random();

    while (questions.size() < count && allWords.size() >= 4) {
        Collections.shuffle(allWords);
        String chosen = allWords.get(0);

        if (paroleUsate.contains(chosen)) {
            continue;
        }
        paroleUsate.add(chosen);

        int correctFreq = analyzer.getGlobalFrequencies().getOrDefault(chosen, 1);
        Set<String> opzioniUniche = new HashSet<>();

        while (opzioniUniche.size() < 3) {
            int fakeFreq = Math.max(1, correctFreq + random.nextInt(8) - 3);
            if (fakeFreq != correctFreq) {
                opzioniUniche.add(String.valueOf(fakeFreq));
            }
        }

        List<String> options = new ArrayList<>(opzioniUniche);
        int correctIndex = random.nextInt(4);
        options.add(correctIndex, String.valueOf(correctFreq));

        Question q = new Question(
            "Quante volte appare la parola \"" + chosen + "\"?",
            options,
            correctIndex
        );
        questions.add(q);
    }

    return questions;
}

}
