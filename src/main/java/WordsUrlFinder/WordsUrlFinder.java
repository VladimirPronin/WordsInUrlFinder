package WordsUrlFinder;

import java.io.*;
import java.net.URL;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsUrlFinder {
  private static final String filePath = "E:\\Sample3.txt";
  private static final String pageAddress = "https://habr.com/post/190548/";
  private static final Pattern WORD_PATTERN = Pattern.compile("\\w+[а-яА-Я]", Pattern.UNICODE_CHARACTER_CLASS | Pattern.UNICODE_CHARACTER_CLASS);

  public static void main(String[] args) throws IOException {
    // create Strims and StringBuilder
    StringBuilder sb = new StringBuilder();
    try
            (BufferedReader inPut = new BufferedReader(new InputStreamReader(new URL(pageAddress).openStream()));
              BufferedWriter outPut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))))){
        String note;
        //    Write Stream
        while ((note = inPut.readLine()) != null) {
          sb.append(note);
        }
        //    Use pattern to sort
        Matcher matcher = WORD_PATTERN.matcher(sb);
        // forget about similar words
        SortedSet<String> words = new TreeSet<>();
        while (matcher.find()) {
          words.add(matcher.group());
        }
        for (String word : words) {
          System.out.println("word = " + word);
          outPut.write("Word = " + word + "\n");
        }
        //    We don't need close streams, because use try-with-resources. Java 7 and above
    }
  }
}





