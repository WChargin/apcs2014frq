import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SeatingChartTest {

	/**
	 * The example student data given by the College Board.
	 */
	private static final Student[] EXAMPLE_DATA = { new Student("Karen", 3),
			new Student("Liz", 1), new Student("Paul", 4),
			new Student("Lester", 1), new Student("Henry", 5),
			new Student("Renee", 9), new Student("Glen", 2),
			new Student("Fran", 6), new Student("David", 1),
			new Student("Danny", 3) };

	/**
	 * The private {@code seats} field of the SeatingChart class. This is used
	 * with the Reflection API to get the value of `seats' at runtime.
	 * 
	 * @see #getSeats(SeatingChart)
	 */
	private static final Field seatsField;
	static {
		try {
			seatsField = SeatingChart.class.getDeclaredField("seats");
			seatsField.setAccessible(true);
		} catch (NoSuchFieldException nsfe) {
			throw new AssertionError("No field seats found", nsfe);
		} catch (SecurityException se) {
			throw new AssertionError("Reflection disallowed", se);
		}
	}

	/**
	 * Gets the value of the {@code seats} field from a seating chart.
	 * 
	 * @param chart
	 *            the seating chart
	 * @return the {@code seats} field value
	 */
	private static Student[][] getSeats(SeatingChart chart) {
		try {
			return (Student[][]) seatsField.get(chart);
		} catch (Exception e) {
			throw new AssertionError("Internal error accessing seats", e);
		}
	}

	@Test
	public void testConstructor() {
		// Test on example data from College Board
		testConstructorFor(EXAMPLE_DATA, 3, 4);
		testConstructorFor(EXAMPLE_DATA, 8, 2);

		// Test on random large data set
		final Student[] large = new Student[10000];
		for (int i = 0; i < large.length; i++) {
			large[i] = new Student("Student " + (i + 1),
					(int) (Math.random() * 5));
		}
		testConstructorFor(large, 100, 100);
		testConstructorFor(large, 1000, 10);
		testConstructorFor(large, 10000, 1);
		testConstructorFor(large, 100000, 1);
		testConstructorFor(large, 50, 200);
		testConstructorFor(large, 50, 2000);
	}

	/**
	 * Tests that the constructor executes correctly for the given data set.
	 * 
	 * @param students
	 *            the array of students to test
	 * @param rows
	 *            the number of rows
	 * @param cols
	 *            the number of columns
	 */
	private void testConstructorFor(Student[] students, int rows, int cols) {
		final List<Student> studentList = Arrays.asList(students);
		final List<Student> backup = new ArrayList<>(studentList);

		final SeatingChart chart = new SeatingChart(studentList, rows, cols);
		final Student[][] seats = getSeats(chart);

		assertNotNull("seats should not be null", seats);

		assertEquals("studentList must not be modified during constructor",
				backup, studentList);

		assertEquals("seats has wrong top-level dimension", rows, seats.length);
		for (Student[] row : seats) {
			assertEquals("row has wrong length", cols, row.length);
		}

		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				final Student here = seats[row][col];
				final int index = col * rows + row;
				if (index >= students.length) {
					assertEquals("Past boundary", null, here);
				} else {
					assertEquals(students[index], here);
				}
			}
		}
	}

	@Test
	public void testRemoveAbsentStudents() {
		// Test on example data from College Board
		testRemoveAbsentStudentsFor(EXAMPLE_DATA, 3, 4, 4);

		// Test on random large data set
		final Student[] large = new Student[10000];
		for (int i = 0; i < large.length; i++) {
			large[i] = new Student("Student " + (i + 1),
					(int) (Math.random() * 5));
		}
		// Test for different absence levels
		for (int i = -2; i < 5; i++) {
			testRemoveAbsentStudentsFor(large, 100, 100, i);
			testRemoveAbsentStudentsFor(large, 1000, 10, i);
			testRemoveAbsentStudentsFor(large, 10000, 1, i);
			testRemoveAbsentStudentsFor(large, 100000, 1, i);
			testRemoveAbsentStudentsFor(large, 50, 200, i);
			testRemoveAbsentStudentsFor(large, 50, 2000, i);
		}
	}

	private void testRemoveAbsentStudentsFor(Student[] list, int rows,
			int cols, int allowedAbsences) {
		SeatingChart chart = new SeatingChart(Arrays.asList(list), rows, cols);

		Student[][] before = getSeats(chart);

		Student[][] clone = new Student[before.length][];
		for (int i = 0; i < clone.length; i++) {
			clone[i] = before[i].clone();
		}

		final int result = chart.removeAbsentStudents(allowedAbsences);
		Student[][] after = getSeats(chart);

		assertNotNull("removeAbsentStudents shouldn't nullify seats", after);

		assertSame("seats reference should not change", before, after);
		// this implies dimensions the same, so no need to check

		int count = 0;
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone[i].length; j++) {
				Student original = clone[i][j];
				if (original == null) {
					continue;
				}
				if (original.getAbsenceCount() > allowedAbsences) {
					count++;
					assertNull(after[i][j]);
				} else {
					assertSame(clone[i][j], after[i][j]);
				}
			}
		}
		assertEquals(count, result);

	}
}
