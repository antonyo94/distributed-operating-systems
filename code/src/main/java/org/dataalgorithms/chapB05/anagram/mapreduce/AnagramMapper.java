package org.dataalgorithms.chapB05.anagram.mapreduce;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;


//La classe AnagramMapper estende, secondo il concetto di ereditarieta', la classe Mapper.
//public class Mapper<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
//Il framwork Hadoop MapReduce genera un task di tipo map per ogni split dell'input, creata
//attraverso l'InputFormat per il job. Le implementazioni di Mapper possono accedere
//alla configurazione per il Job tramite JobContext.getConfiguration().
//Tutti i valori intermedi associati a una determinata chiave di output vengono
//successivamente raggruppati dal framework e passati a un Reducer,
//per determinare l'output finale.
public class AnagramMapper
        extends Mapper<LongWritable, Text, Text, Text> {

    //Impostare i place holders per  l'output key/value
    private final Text keyAsSortedText = new Text();
    private final Text valueAsOrginalText = new Text();

    private static final int DEFAULT_IGNORED_LENGTH = 3; // default
    private int N = DEFAULT_IGNORED_LENGTH;

    // chiamato una volta all'inizio del task, imposta il valore di N.
    @Override
    protected void setup(Context context)
            throws IOException, InterruptedException {
        this.N = context.getConfiguration().getInt("word.count.ignored.length", DEFAULT_IGNORED_LENGTH);
    }

    //Chiamata una volta per ogni coppia chiave valore nella suddivisione dell'input(una volta per ogni riga)
    //In input si ha la coppia chiave/valore oltre che context, ovvero il contesto durante il run in Hadoop.
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        //Se a riga e' nulla, ritorna.
        if (value == null) {
            return;
        }
        //Convertire la riga in String
        String valueAsString = value.toString();
        if (valueAsString == null) {
            return;
        }
        //Convertire la riga in minuscolo e senza spazi bianchi iniziali e finali
        String line = valueAsString.trim().toLowerCase();
        if ((line == null) || (line.length() < this.N)) {
            return;
        }
        //Estrarre le parole che compongono la riga
        String[] words = StringUtils.split(line);
        if (words == null) {
            return;
        }
        //Per ogni parola della riga
        for (String word : words) {
            if (word.length() < this.N) {
                //Ignorare la parola se il suo size e' minore di N
                continue;
            }
            if (word.matches(".*[,.;]$")) {
                // Rimuovere i caratteri speciali
                word = word.substring(0, word.length() - 1);
            }
            if (word.length() < this.N) {
                // Ignorare la parola se il suo size e' minore di N
                continue;
            }
            //Ordinare i caratteri che compongono la singola parola
            String sortedWord = sort(word);
            //Impostare i caratteri ordinati della parola come chiave
            keyAsSortedText.set(sortedWord);
            //Impostare la parola come valore
            valueAsOrginalText.set(word);
            //Scrivere la coppia <chiave,valore> nel contesto
            context.write(keyAsSortedText, valueAsOrginalText);
        }
    }
    //Funzione per l'ordinamento della parola
    static String sort(final String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sortedWord = String.valueOf(chars);
        return sortedWord;
    }        
}
