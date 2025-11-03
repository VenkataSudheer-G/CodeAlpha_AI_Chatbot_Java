import java.util.*;
import java.util.regex.*;

public class NLP {
    private static final Pattern TOK = Pattern.compile("\\w+");
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
        "a","an","the","is","are","was","were","in","on","at","for","to","of","and","or","i","you","it","that","this","be","have","has","do","does"
    ));

    public static List<String> tokenize(String s) {
        s = s.toLowerCase();
        Matcher m = TOK.matcher(s);
        List<String> t = new ArrayList<>();
        while (m.find()) {
            String w = m.group();
            if (!STOPWORDS.contains(w)) t.add(w);
        }
        return t;
    }

    public static Map<String, Double> tf(List<String> tokens) {
        Map<String, Double> m = new HashMap<>();
        for (String t : tokens) m.put(t, m.getOrDefault(t, 0.0) + 1.0);
        double len = tokens.size() > 0 ? tokens.size() : 1.0;
        for (String k : new ArrayList<>(m.keySet())) m.put(k, m.get(k) / len);
        return m;
    }

    public static Map<String, Double> idf(List<List<String>> corpus) {
        Map<String, Integer> docCount = new HashMap<>();
        int N = corpus.size();
        for (List<String> doc : corpus) {
            Set<String> used = new HashSet<>(doc);
            for (String w : used) docCount.put(w, docCount.getOrDefault(w, 0) + 1);
        }
        Map<String, Double> idf = new HashMap<>();
        for (Map.Entry<String, Integer> e : docCount.entrySet()) {
            idf.put(e.getKey(), Math.log((double) N / (1 + e.getValue())) + 1.0);
        }
        return idf;
    }

    public static Map<String, Double> tfidf(Map<String, Double> tf, Map<String, Double> idf) {
        Map<String, Double> out = new HashMap<>();
        for (Map.Entry<String, Double> e : tf.entrySet()) {
            double idfv = idf.getOrDefault(e.getKey(), Math.log(1 + 1.0));
            out.put(e.getKey(), e.getValue() * idfv);
        }
        return out;
    }

    public static double cosine(Map<String, Double> v1, Map<String, Double> v2) {
        double dot = 0.0;
        for (String k : v1.keySet()) {
            if (v2.containsKey(k)) dot += v1.get(k) * v2.get(k);
        }
        double n1 = 0.0, n2 = 0.0;
        for (double val : v1.values()) n1 += val * val;
        for (double val : v2.values()) n2 += val * val;
        if (n1 == 0 || n2 == 0) return 0.0;
        return dot / (Math.sqrt(n1) * Math.sqrt(n2));
    }
}
