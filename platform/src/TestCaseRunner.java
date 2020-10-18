import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCaseRunner {

  public void testSingle(Class<?> targetClass, int testNumber) {
    try {
      testSingleChecked(targetClass, testNumber);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public void testSingleChecked(Class<?> targetClass, int testNumber) throws Exception {
    InputStream stdin = System.in;
    PrintStream stdout = System.out;

    ByteArrayOutputStream fakeOutArr = new ByteArrayOutputStream();
    PrintStream fakeOut = new PrintStream(fakeOutArr);

    // String prefix = System.getProperty("user.dir");

    String input = Files.readString(Path.of(String.format("src/data/%d_in.txt", testNumber)));

    System.setIn(new ByteArrayInputStream(input.getBytes()));
    System.setOut(fakeOut);

    Method main = targetClass.getMethod("main", String[].class);
    main.invoke(null, (Object) new String[0]);

    // patient.accept(new String[0]);
    System.out.flush();

    String result = fakeOutArr.toString(UTF_8).trim();

    String expected = Files.readString(Path.of(String.format("src/data/%d_out.txt", testNumber))).trim();
    assertEquals(expected, result);

    System.setOut(stdout);
    System.setIn(stdin);
  }

}
