package org.dataalgorithms.chapB05.anagram.mapreduce;

import org.apache.log4j.Logger;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;


public class AnagramDriver  extends Configured implements Tool {

   private static Logger theLogger = Logger.getLogger(AnagramDriver.class);

   @Override
   public int run(String[] args) throws Exception {
          
      //Creazione di un nuovo Job con rlativa configurazione
      Job job = new Job(getConf());
      job.setJarByClass(AnagramDriver.class);
      job.setJobName("AnagramDriver");
      
      //Nella configurazione del Job setta il valore della proprieta' word.count.ignored.length all'intero N
      int N = Integer.parseInt(args[0]);
      job.getConfiguration().setInt("word.count.ignored.length", N);   
      
      //Impostare il formato dell'input come TextInputFormat, un formato semplice per i file testuali.
      //In questo modo il file di input e' diviso in linee. 
      //Sia il linefeed che il ritorno a capo sono utilizati per indicare la fine della liea.
      //Viene impostato quindi <Key: posizione nel file, Value: riga di testo>
      job.setInputFormatClass(TextInputFormat.class); 

      //Impostare il formato dell'output   
      job.setOutputFormatClass(TextOutputFormat.class);
      
      //Impostare la classe chiave per i dati di output del Job
      job.setOutputKeyClass(Text.class);

      //Impostare la classe valore per i dati di output del Job
      job.setOutputValueClass(Text.class);    
          
      //Impostare la classe Mapper
      job.setMapperClass(AnagramMapper.class);

      //Impostare la classe Reducer
      job.setReducerClass(AnagramReducer.class);
      
      //Impostare il path dei dati di input, args[1] = input directory
      FileInputFormat.setInputPaths(job, new Path(args[1]));
      
      //Impostare il path dei dati di output, args[2] = output directory
      FileOutputFormat.setOutputPath(job, new Path(args[2]));

      //Attendere il completamento del job
      boolean status = job.waitForCompletion(true);
      
      //Restituire lo stato ottenuto dopo il completamento del job
      theLogger.info("run(): status="+status);
      return status ? 0 : 1;
   }

   
   //Il metodo main per il programma anagram finder in map/reduce.
   //@throws Exception Quando vi e' un problema do comunicazione con il JobTracker.
   public static void main(String[] args) throws Exception {
      //Verificare che siano impostati esattamente 3 parametri da riga di comando
      if (args.length != 3) {
         throw new IllegalArgumentException("usage: <N> <input> <output>");
      }

      //String N = args[0];
      theLogger.info("N="+args[0]);
      
      //String inputDir = args[1];
      theLogger.info("inputDir="+args[1]);

      //String outputDir = args[2];
      theLogger.info("outputDir="+args[2]);

      int returnStatus = submitJob(args);
      theLogger.info("returnStatus="+returnStatus);
      
      System.exit(returnStatus);
   }

   //Questo metodo sottomette il job map/reduce al JobTracker.
   //@throws Exception Quando vi e' un problema do comunicazione con il JobTracker.
   public static int submitJob(String[] args) throws Exception {
      int returnStatus = ToolRunner.run(new AnagramDriver(), args);
      return returnStatus;
   }
}
