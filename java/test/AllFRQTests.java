import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ DirectorTest.class, ScramblerTest.class,
		SeatingChartTest.class, TrioTest.class })
public class AllFRQTests {

}
