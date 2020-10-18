import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(Integer.parseInt(line) * 2);
      }
    }
  }

}
