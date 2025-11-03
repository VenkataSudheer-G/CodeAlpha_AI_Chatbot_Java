import java.io.*;
import java.util.*;

public class ChatbotCore {
    private final KnowledgeBase kb;
    private final List<Map<String, Double>> questionTfidf = new ArrayList<>();
    private Map<String, Double> idf;
    private final double SIM_THRESHOLD = 0.40;

    public ChatbotCore(KnowledgeBase kb) {
        this.kb = kb;
    }

    public void train() {
        List<List<String>> tokenized = new ArrayList<>();
        for (int i = 0; i < kb.size(); i++) {
            tokenized.add(NLP.tokenize(kb.getQuestion(i)));
        }
        idf = NLP.idf(tokenized);
        questionTfidf.clear();
        for (List<String> tok : tokenized) {
            Map<String, Double> tf = NLP.tf(tok);
            questionTfidf.add(NLP.tfidf(tf, idf));
        }
    }

    public String respond(String input) {
        String cleaned = input.trim();
        if (cleaned.isEmpty()) return "Please type something so I can help.";

        String lower = cleaned.toLowerCase();
        if (lower.matches(".*\\b(hi|hello|hey|good morning|good afternoon|good evening)\\b.*")) {
            return "Hello! How can I help you today?";
        } else if (lower.matches(".*\\b(thank|thanks)\\b.*")) {
            return "You're welcome! Anything else I can help with?";
        } else if (lower.matches(".*\\b(help|support|assist)\\b.*")) {
            return "Sure — tell me your question or type a short summary of the issue.";
        }

        List<String> tokens = NLP.tokenize(cleaned);
        Map<String, Double> tf = NLP.tf(tokens);
        Map<String, Double> tfidf = NLP.tfidf(tf, idf == null ? new HashMap<>() : idf);

        double best = 0.0;
        int bestIdx = -1;
        for (int i = 0; i < questionTfidf.size(); i++) {
            double sim = NLP.cosine(tfidf, questionTfidf.get(i));
            if (sim > best) {
                best = sim;
                bestIdx = i;
            }
        }
        if (bestIdx >= 0 && best >= SIM_THRESHOLD) {
            return kb.getAnswer(bestIdx) + " (confidence: " + String.format("%.2f", best) + ")";
        }
        return "Sorry, I couldn't find an exact answer. Could you rephrase or try a keyword?";
    }
}
