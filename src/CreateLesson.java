import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.BufferedWriter ;
import java.io.InputStreamReader;

public class CreateLesson {

  public static void main(String[] args) throws IllegalArgumentException, IOException{
    if (args.length == 0) {
      throw new IllegalArgumentException("Usage: newfile name or old <example: lesson1>");
    }
    String fname =args[0];
    System.out.println (System.getProperty("user.dir"));
    File f = new File( System.getProperty("user.dir") + "/Lessons/" +fname);
    if (!f.exists()) {
      if (f.createNewFile()) {
        System.out.println("Created new file: " + fname);
        System.out.println("Path: " + f.getPath());
      } else {
        throw new IOException("Couldn't create new file");
      }
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String command = "";
    boolean prompt = true;
    System.out.println("Type <help> for commands");


    CreateLesson lesson = new CreateLesson();

    while (command != "quit") {
      lesson.inputPrompt(prompt);
      command = reader.readLine();
      lesson.process(command, f);
      prompt = !prompt;
    }

  }

  void inputPrompt(boolean prompt) {
    if (prompt) {
      System.out.print("Chinese Word: ");
    } else {
      System.out.print("Corresponding English Word: ");
    }
  }

  void process(String s, File f) {
  Path path = FileSystems.getDefault().getPath("Lessons", f.getName());
  Charset cs  = Charset.forName("UTF-8");
  if (s != "quit" || s != "help")  {

    try (BufferedWriter writer = Files.newBufferedWriter(path, cs)) {
      writer.write(s, 0, s.length());
      } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
      }
    }
  }
}
