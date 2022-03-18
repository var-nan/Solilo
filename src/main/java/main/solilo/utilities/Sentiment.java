package main.solilo.utilities;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import main.solilo.bean.Quicky;

import java.util.List;
import java.util.Properties;

public class Sentiment {
    static StanfordCoreNLP pipeline;

    public static void init() {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(properties);
    }

    public static int getSentimentValue(String rawText) {
        float sentimentValue = 0;
        if (pipeline == null) {
            // initialize
            init();
        }

        Annotation annotation = pipeline.process(rawText);
        int i = 0;
        for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            sentimentValue  += RNNCoreAnnotations.getPredictedClass(tree);
            i++;
        }
        return Math.round(sentimentValue/i);
    }

    public static int getSentimentValue(List<Quicky> allQuickies) {
        // clean strings and build superstring
        StringBuilder text = new StringBuilder();
        for(Quicky q: allQuickies)
            text.append(q.getMessage().trim()).append(q.getMessage().endsWith(".") ? "" : ".");

        return getSentimentValue(text.toString());
    }
}
