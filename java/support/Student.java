/**
 * The class representing a student in a school. You do not need to make any
 * modifications to this class; it is provided for you.
 * 
 * @author William Chargin
 * 
 */
public class Student {

	/**
	 * The name of this {@code Student}.
	 */
	private final String name;

	/**
	 * The number of times this {@code Student} has missed class.
	 */
	private final int absenceCount;

	/**
	 * Creates the {@code Student} with all required fields.
	 * 
	 * @param name
	 *            the name of this student
	 * @param absenceCount
	 *            the absence count for this student
	 */
	public Student(String name, int absenceCount) {
		super();
		this.name = name;
		this.absenceCount = absenceCount;
	}

	/**
	 * Returns the number of times this {@code Student} has missed class.
	 * 
	 * @return this {@code Student}'s absence count
	 */
	public int getAbsenceCount() {
		return absenceCount;
	}

	/**
	 * Returns the name of this {@code Student}.
	 * 
	 * @return the name of this {@code Student}
	 */
	public String getName() {
		return name;
	}

}
