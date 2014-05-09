import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ScramblerTest {

	@Test
	public void testClassOK() {
		assertEquals("You may not declare any fields", 0,
				Scrambler.class.getDeclaredFields().length);
		assertEquals("You may not annotate the Scrambler class", 0,
				Scrambler.class.getDeclaredAnnotations().length);
	}

	@Test
	public void testScrambleOrRemove() {
		List<String> words = new ArrayList<String>(Arrays.<String> asList(
				"TAN", "ABRACADABRA", "WHOA", "APPLE", "EGGS"));
		List<String> expected = new ArrayList<String>(Arrays.<String> asList(
				"TNA", "BARCADABARA", "PAPLE"));
		Scrambler.scrambleOrRemove(words);
		assertEquals(expected, words);
	}

	@Test
	public void testScrambleWord() {
		assertEquals("TNA", Scrambler.scrambleWord("TAN"));
		assertEquals("BARCADABARA", Scrambler.scrambleWord("ABRACADABRA"));
		assertEquals("WHOA", Scrambler.scrambleWord("WHOA"));
		assertEquals("ARADVRAK", Scrambler.scrambleWord("AARDVARK"));
		assertEquals("EGGS", Scrambler.scrambleWord("EGGS"));
		assertEquals("A", Scrambler.scrambleWord("A"));
		assertEquals("", Scrambler.scrambleWord(""));
	}
}
