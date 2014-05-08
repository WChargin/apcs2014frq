import java.util.List;

/**
 * Encapsulating class for the Question 1 of the 2014 APCS FRQs. This question
 * involves reasoning about strings made up of uppercase letters. You will
 * implement two related methods that appear in the same class (not shown). The
 * first method takes a single string parameter and returns a scrambled version
 * of that string. The second method takes a list of strings and modifies the
 * list by scrambling each entry in the list. Any entry that cannot be scrambled
 * is removed from the list.
 * 
 */
public class Scrambler {

	/**
	 * Scrambles a given word.
	 * 
	 * <p>
	 * 
	 * <em>Precondition:</em> {@code word} is either an empty string or contains
	 * only uppercase letters.
	 * 
	 * <p>
	 * 
	 * <em>Postcondition</em>: the string returned was created from word as
	 * follows:
	 * 
	 * <ul>
	 * <li>the word was scrambled, beginning at the first letter and continuing
	 * from left to right</li>
	 * <li>two consecutive letters consisting of "A" followed by a letter that
	 * was not "A" were swapped</li>
	 * <li>letters were swapped at most once</li>
	 * </ul>
	 * 
	 * @param word
	 *            the word to be scrambled
	 * @return the scrambled word (possibly equal to word)
	 */
	public static String scrambleWord(String word) {
		// TODO Complete this method
		return null;
	}

	/**
	 * Modifies {@code wordList} by replacing each word with its scrambled
	 * version, removing any words that are unchanged as a result of scrambling.
	 * 
	 * <p>
	 * 
	 * <em>Precondition</em>: {@code wordList} contains only non-{@code null}
	 * objects
	 * 
	 * <p>
	 * 
	 * <em>Postconditions:</em>
	 * <ul>
	 * <li>all words unchanged by scrambling have been removed from
	 * {@code wordList}</li>
	 * <li>each of the remaining words has been replaced by its scrambled
	 * version</li>
	 * <li>the relative ordering of the entries in {@code wordList} is the same
	 * as it was before the method was called</li>
	 * </ul>
	 * 
	 * @param wordList
	 *            the list of words
	 */
	public static void scrambleOrRemove(List<String> wordList) {
		// TODO Complete this method
	}

}
