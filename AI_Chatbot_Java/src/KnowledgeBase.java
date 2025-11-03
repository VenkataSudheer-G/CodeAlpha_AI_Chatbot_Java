import java.io.*;
import java.nio.file.*;
import java.util.*;

public class KnowledgeBase {
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();

    public KnowledgeBase() {}

    public void loadFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        String q = null, a = null;
        for (String raw : lines) {
            String line = raw.trim();
            if (line.startsWith("Q:")) {
                q = line.substring(2).trim();
            } else if (line.startsWith("A:")) {
                a = line.substring(2).trim();
            } else if (line.isEmpty()) {
                if (q != null && a != null) {
                    questions.add(q);
                    answers.add(a);
                }
                q = a = null;
            }
        }
        if (q != null && a != null) {
            questions.add(q);
            answers.add(a);
        }
    }

    public int size() { return questions.size(); }
    public String getQuestion(int i) { return questions.get(i); }
    public String getAnswer(int i) { return answers.get(i); }
    public List<String> getQuestions() { return Collections.unmodifiableList(questions); }
    public List<String> getAnswers() { return Collections.unmodifiableList(answers); }
}
