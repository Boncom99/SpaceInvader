import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class MaxScore{
    int score;

    MaxScore() {
          try {
            FileReader reader = new FileReader("MaxScore.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                score = Integer.parseInt(line);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void write() {
         try {
            FileWriter writer = new FileWriter("MaxScore.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.newLine();
 
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }


}