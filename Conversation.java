import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

class Conversation implements Chatbot {

  // Attributes 
  private String transcript = "";

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
      transcript = transcript + userInput + "\n";

      transcript = transcript + respond(userInput) + "\n";
      System.out.println(respond(userInput));
    }

    transcript = transcript + "Bye! Thanks for chatting!";
    System.out.println("Bye! Thanks for chatting!\n");
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("Conversation Transcript: \nWhat's on your mind?");
    System.out.println(transcript);
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

    List<String> words = Arrays.asList(inputString.split(" "));
    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).equalsIgnoreCase("i")) {
        words.set(i, "you");
      } else if (words.get(i).equalsIgnoreCase("me")) {
        words.set(i, "you");
      } else if (words.get(i).equalsIgnoreCase("am")) {
        words.set(i, "are");
      } else if (words.get(i).equalsIgnoreCase("are")) {
        words.set(i, "am");
      } else if (words.get(i).equalsIgnoreCase("you")) {
        words.set(i, "I");
      } else if (words.get(i).equalsIgnoreCase("I")) {
        words.set(i, "you");
      } else if (words.get(i).equalsIgnoreCase("my")) {
        words.set(i, "your");
      } else if (words.get(i).equalsIgnoreCase("your")) {
        words.set(i, "my");
      } else if (words.get(i).equalsIgnoreCase("I'm")) {
        words.set(i, "you're");
      }
    }

    String response = String.join(" ", words);
    response += punctuation;

    if (!response.equals(inputString)) {
      return response;
    }

    Random random = new Random();
    String[] responses = {"Uh-huh", "How interesting", "Really?", "Hmm..."};
    int randomIndex = random.nextInt(responses.length);
    return responses[randomIndex];
    }
  

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
