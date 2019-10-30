import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.BufferedWriter ;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FilenameFilter;

public class CreateLesson {

  public static void main(String[] args) throws IOException{

    System.out.println("Type <help> for commands");

    CreateLesson lesson = new CreateLesson();

    String command = "";
    while (!command.equals("quit")) {
     command = lesson.inputPrompt();
      if (!command.equals("quit")) {
        lesson.process(command, lesson);
      }
    }
  }




  void usage() {
    System.out.print("\n");
    System.out.println("Usage:    ");
    System.out.println("          help - displays this help page");
    System.out.println("          help <command> - displays more information about a command");
    System.out.println("          write (arg) <lesson name> - write to a existing file");
    System.out.println("          list - list all lessons in the Lessons Directory");
    System.out.println("          create <lesson#> - create a new lesson empty");
    System.out.println("          quit - exits the program");
    System.out.print("\n");


  }

  void usage(String help_command) {
    System.out.print("\n");
    if (help_command.equals("help")) {
      System.out.println("Usage:     help - displays the help page");

    } else if (help_command.equals("write")) {
      System.out.println("Usage:     write - provide the write command with a lesson name listed with the list command (try help list)");
      System.out.println("            you then can write a chinese word and a Corresponding english word to go with it to the lesson file");
    } else if (help_command.equals("list")) {
      System.out.println("Usage:     list - lists all the lessons within the lessons directory currently available");
    }  else if (help_command.equals("create")) {
      System.out.println("Usage:     create - create a new lesson only providing the lesson number, the new lesson will be empty");
    } else {
      System.out.println("Error: <" + help_command + "> not found");
    }
    System.out.print("\n");
  }

  String inputPrompt() throws IOException{
    System.out.print("Command: ");
    String command = read();
    return command;
  }





  static String read() throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String command = reader.readLine();
    return command;
  }





  void process(String command, CreateLesson l) throws IOException{
    String arguments[] = command.split(" ", 2);

    if (command.equals("help")) {
      l.usage();
    } else if (arguments[0].equals("help")){
      l.usage(arguments[1]);
    } else if (arguments[0].equals("write")) {
      l.write(arguments[1]);
    } else if (command.equals("list")) {
      l.list();
    } else if (arguments[0].equals("create")) {
      l.create(arguments[1]);
    } else if (arguments[0].equals("edit")) {
      l.edit(arguments[1]);
    }  else {
      System.out.print("\n");
      System.out.println("Error: <" + command + "> not found");
      System.out.print("\n");
    }
  }

  void create(String fname) throws IOException{
    File d = new File( System.getProperty("user.dir") + "/Lessons/" + "lesson" + fname);
    if (!d.exists()) {
      if (d.createNewFile()) {
        System.out.println("Created new file: " + d.getName());
        System.out.println("Path: " + d.getPath());
      } else {
        throw new IOException("Couldn't create new file");
      }
    } else {
      System.out.println("Error: <lesson" + fname + "> already exists in " + d.getPath());
    }
  }


  void edit(String name) {
    File f = new File( System.getProperty("user.dir") + "/Lessons/" +fname);
    if (!f.exists()) {
      System.out.println("File: <" + "> does not exist");
    } else {

  }

  void list() {

    File d = new File(System.getProperty("user.dir") + "/Lessons");
    File[] matchingFiles = d.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
          return name.startsWith("lesson");
        }
    });
    System.out.print("\n");

    for (int i = 0; i < matchingFiles.length; i++) {
      System.out.println(matchingFiles[i].getName());
      System.out.println("=============");
    }
    System.out.print("\n");
  }


  void write(String fname) throws IOException{
    File f = new File( System.getProperty("user.dir") + "/Lessons/" +fname);
    if (!f.exists()) {
      System.out.println("File: <" + "> does not exist");
    } else {
      String command = "";
      boolean prompt = true;

      ystem.out.print("\n");
      System.out.println("Type <quit> to exit");
      ystem.out.print("\n");

      while(!command.equals("quit")) {
        if (prompt) {
          System.out.print("Chinese Word: ");
        } else {
          System.out.print("Corresponding English Word: ");
        }
        command = read();
        if (!command.equals("quit")) {
            try {
                  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f.getPath(), true)));
                  if (prompt) {
                    out.println("%C%" + command);
                  } else {
                    out.println("%E%" + command);
                  }
                  out.close();
                } catch (IOException x) {
                  System.err.format("IOException: %s%n", x);
                }
            }
      prompt = !prompt;
      }
    }
  }
}
