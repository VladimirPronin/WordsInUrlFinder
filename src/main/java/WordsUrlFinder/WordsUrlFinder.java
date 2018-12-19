package WordsUrlFinder;

import java.io.*;
import java.net.URL;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsUrlFinder {

    private static String streameString;

    public static void main(String[] args) throws IOException {
// create Strims and StringBuilder
      StringBuilder sb = new StringBuilder();
      BufferedReader bf = new BufferedReader(new InputStreamReader(new URL("https://habr.com/post/190548/").openStream()));
      BufferedWriter outPut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:\\Sample3.txt"))));
      String note;
//    Write Stream
      while ((note = bf.readLine()) != null) {
        sb.append(bf.readLine());
      }
//    Form String from stream
      String book = sb.toString();
//
      char[] cyrillic = new char[book.toCharArray().length];
      for (int i = 0; i < book.toCharArray().length; i++) {
        if (Character.UnicodeBlock.of(book.toCharArray()[i]).equals(Character.UnicodeBlock.CYRILLIC)) {
          cyrillic[i] = book.toCharArray()[i];
        }
      }
      streameString = String.copyValueOf(cyrillic);
//Search for unique words / formating strings
      Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.UNICODE_CHARACTER_CLASS);
// create "sorter"
      Matcher matcher = pattern.matcher(streameString);
      // forget about similar words
      SortedSet<String> words = new TreeSet<>();
      while (matcher.find())
        words.add(matcher.group());
      for (String word : words)
        System.out.println("word = " + word);
      outPut.write(String.valueOf(words));

//    Close Streams
      bf.close();
      outPut.close();

    }

  }



