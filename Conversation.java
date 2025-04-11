import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Conversation implements Chatbot {

  // Attributes 
  private List<String> transcript = new ArrayList<>();
  private static final String [] random_responses = {"Uh-huh", "How interesting", "Really?", "Hmm..."};

  /**
   * Constructor 
   */
  Conversation() {
    
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner input = new Scanner(System.in);
    System.out.println("How many rounds do you want to chat?");
    int rounds = input.nextInt();
    input.nextLine();

    System.out.println("\nWhat's on your mind?");

    for(int i = 0; i < rounds; i++) {
      String userInput = input.nextLine();
      transcript.add(userInput);

      String reply = respond(userInput);
      transcript.add(reply);
      System.out.println(reply);
    }

    transcript.add("Bye! Thanks for chatting!!!");
    System.out.println("Bye! Thanks for chatting!!!\n");
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("Conversation Transcript: \nWhat's on your mind?");
    for (String s : transcript) {
      System.out.println(s);
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input, deals with punctuation at the end of a sentence
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String punctuation = "";

    if (inputString.endsWith("!") || inputString.endsWith("?") || inputString.endsWith(".")) {
      punctuation = inputString.substring(inputString.length() -1);
      inputString = inputString.substring(0, inputString.length() -1);
    }

    List<String> words = new ArrayList<>(Arrays.asList(inputString.split(" "))); // I had to use stack exchange here to figure out the array list
    boolean mirrored = false; // so that we can tell if we used a mirror response or not

    for (int i = 0; i < words.size(); i++) {

      String word = words.get(i);
      String lower = word.toLowerCase();

      if (lower.equals("i")) {
        words.set(i, "you");
        mirrored = true;
      } else if (lower.equals("me")) {
        words.set(i, "you");
        mirrored = true;
      } else if (lower.equals("am")) {
        words.set(i, "are");
        mirrored = true;
      } else if (lower.equals("are")) {
        words.set(i, "am");
        mirrored = true;
      } else if (lower.equals("you")) {
        words.set(i, "I");
        mirrored = true;
      } else if (lower.equals("my")) {
        words.set(i, "your");
        mirrored = true;
      } else if (lower.equals("your")) {
        words.set(i, "my");
        mirrored = true;
      } else if (lower.equals("i'm")) {
        words.set(i, "you're");
        mirrored = true;
      } else if (lower.equals("you're")) {
        words.set(i, "i'm");
        mirrored = true;
      }
    }

    String response = String.join(" ", words) + punctuation;

    if (mirrored) {
      return response;
    } else {
      Random random = new Random();
      int randomIndex = random.nextInt(random_responses.length);
      return random_responses[randomIndex];
    }
  }
  

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
