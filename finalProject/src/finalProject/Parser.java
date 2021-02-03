//Martin King
package finalProject;

import java.io.*;

public class Parser {
	
  private static StreamTokenizer tokenizer;
  private static int token;
  
  public static void main(String argv[]) throws IOException {
	  
	InputStreamReader reader = new InputStreamReader(System.in);
    
    tokenizer = new StreamTokenizer(reader);
    
    tokenizer.ordinaryChar('-');
    tokenizer.ordinaryChar('/');
    
    getToken();
    int value = expression();
    if (token == (int)';')
      System.out.println("Output: " + value);
    else
      System.out.println("Input Error.");
    
  }
  
  private static void getToken() throws IOException {
    token = tokenizer.nextToken();
  }
  
  private static int expression() throws IOException {
    int x = term();
    return lastTerm(x);
  }
  
  private static int term() throws IOException {
    int x = factor();
    return lastFactor(x);
  }
  
  private static int lastTerm(int x) throws IOException {
    if (token == (int)'+') {
      getToken();
      int value = term();
      return lastTerm(x + value);
    }
    else if (token == (int)'-') {
      getToken();
      int value = term();
      return lastTerm(x - value);
    }
    else
      return x;
  }
  
  private static int factor() throws IOException {
    if (token == (int)'(') {
      getToken();
      int value = expression();
      if (token == (int)')')
        getToken();
      else
        System.out.println("Parenthesis error");
      return value;
    }
    else if (token == (int)'-') {
      getToken();
      return -factor();
    }
    else if (token == StreamTokenizer.TT_WORD) {
      getToken();
      return 0;
    }
    else if (token == StreamTokenizer.TT_NUMBER) {
      getToken();
      return (int)tokenizer.nval;
    }
    else {
      System.out.println("factor expected");
      return 0;
    }
  }
  
  private static int lastFactor(int x) throws IOException {
    if (token == (int)'*') {
      getToken();
      int factorvalue = factor();
      return lastFactor(x * factorvalue);
    }
    else if (token == (int)'/') {
      getToken();
      int factorvalue = factor();
      return lastFactor(x / factorvalue);
    }
    else {
      return x;
    }
  }
}
