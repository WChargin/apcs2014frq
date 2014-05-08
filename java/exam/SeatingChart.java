import java.util.List;

/**
 * The class designed for Question 3 of the APCS FRQs. The class
 * {@code SeatingChart}, shown below, uses a two-dimensional array to represent
 * the seating arrangement of students in a classroom. The seats in the
 * classroom are in a rectangular arrangement of rows and columns.
 * 
 */
public class SeatingChart {

	/**
	 * The students in the classroom. {@code seats[r][c]} represents the
	 * {@code Student} in row {@code r} and column {@code c} in the classroom.
	 */
	private Student[][] seats;

	/**
	 * Creates a seating chart with the given number of rows and columns from
	 * the students in {@code studentList}. Empty seats in the seating chart are
	 * represented by {@code null}.
	 * 
	 * <p>
	 * 
	 * <em>Preconditions:</em>
	 * <ul>
	 * <li>{@code rows > 0}</li>
	 * <li>{@code cols > 0}</li>
	 * <li>{@code rows * cols >= studentList.size()}</li>
	 * </ul>
	 * 
	 * <em>Postconditions:</em>
	 * <ul>
	 * <li>Students appear in the seating chart in the same order as they appear
	 * in {@code studentList}, starting at {@code seats[0][0]}.</li>
	 * <li>{@code seats} is filled column by column from {@code studentList},
	 * followed by any empty seats (represented by {@code null}).</li>
	 * <li>{@code studentList} is unchanged.</li>
	 * </ul>
	 * 
	 * @param studentList
	 *            the list of students in classroom
	 * @param rows
	 *            the number of rows of seats in the classroom
	 * @param cols
	 *            the number of columns of seats in the classroom
	 */
	public SeatingChart(List<Student> studentList, int rows, int cols) {
		// TODO Complete this method
	}

	/**
	 * Removes students who have more than a given number of absences from the
	 * seating chart, replacing those entries in the seating chart with
	 * {@code null} and returns the number of students removed.
	 * 
	 * <p>
	 * 
	 * <em>Postconditions:</em>
	 * <ul>
	 * <li>All students with allowedAbsences or fewer are in their original
	 * positions in seat</li>
	 * <li>No student in seats has more than allowedAbsences absences.</li>
	 * <li>Entries without students contain null.</li>
	 * </ul>
	 * 
	 * @param allowedAbsences
	 *            an integer {@code >= 0}
	 * @return number of students removed from seats
	 */
	public int removeAbsentStudents(int allowedAbsences) {
		// TODO Complete this method
		return 0;
	}

}
