import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ATest {

  TestCaseRunner tcRunner = new TestCaseRunner();

  public static int[] testCaseNumbers() {
    return new int[]{1};
  }

  @ParameterizedTest
  @MethodSource("testCaseNumbers")
  public void testPlatform(int testCaseNumber) {
    tcRunner.testSingle(A.class, testCaseNumber);
  }

}
