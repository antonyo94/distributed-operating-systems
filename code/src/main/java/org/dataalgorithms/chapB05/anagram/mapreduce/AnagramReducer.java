package org.dataalgorithms.chapB05.anagram.mapreduce;

import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
//
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class AnagramReducer
        extends Reducer<Text, Text, Text, Text> {

    // Questo metoto e' chiamato una volta per ogni chiave
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        //Creare un nuovo HashSet
        Set<String> set = new HashSet<>();
        //Per ogni parola associata alla chiave in input
        for (Text value : values) {
            //Convertire la parola in String
            String word = value.toString();
            //Inserire la parola nell'HashSet
            set.add(word);
        }
        //Se vi e' piu' di una parola per ogni chiave(parola ordinata)
        //allora si ha un anagramma
        if (set.size() > 1) {
            context.write(key, new Text(set.toString()));
        }
    }
}
