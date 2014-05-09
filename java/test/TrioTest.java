import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class TrioTest {

	private static final Trio constructTrioFrom(Sandwich a, Salad b, Drink c) {
		try {
			return getTrioConstructor().newInstance(a, b, c);
		} catch (InvocationTargetException ite) {
			throw new AssertionError("Trio constructor threw an exception", ite);
		} catch (InstantiationException ie) {
			throw new AssertionError("Trio class is abstract", ie);
		} catch (Exception e) {
			throw new AssertionError(
					"Internal error while constructing Trio object", e);
		}
	}

	private static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append((char) ('a' + (int) (Math.random() * 26)));
		}
		return sb.toString();
	}

	private static final Constructor<Trio> getTrioConstructor() {
		try {
			return Trio.class.getConstructor(Sandwich.class, Salad.class,
					Drink.class);
		} catch (NoSuchMethodException nsme) {
			throw new AssertionError("No valid constructor to use for testing",
					nsme);
		}
	}

	@Test
	public void testConstructorParameters() {
		final Constructor<?>[] constructors = Trio.class
				.getDeclaredConstructors();
		assertEquals("There should be only one constructor", 1,
				constructors.length);

		final Constructor<?> c = constructors[0];
		final Class<?>[] params = c.getParameterTypes();
		assertEquals("Constructor should take three parameters", 3,
				params.length);

		Class<?>[] expected = { Sandwich.class, Salad.class, Drink.class };
		assertArrayEquals("Parameters should be Sandwich, Salad, Drink",
				expected, params);
	}

	@Test
	public void testGetName() {
		for (int i = 0; i < 100; i++) {
			final int len = Math.min(5, i); // from 0 to 5
			final Sandwich a = new Sandwich(generateRandomString(len), 1);
			final Salad b = new Salad(generateRandomString(len), 1);
			final Drink c = new Drink(generateRandomString(len), 1);
			final Trio t = constructTrioFrom(a, b, c);

			assertEquals(a.getName() + "/" + b.getName() + "/" + c.getName()
					+ " Trio", t.getName());
		}
	}

	@Test
	public void testGetPrice() {
		for (int mode = 0; mode < 4; mode++) {
			for (int i = 0; i < 100; i++) {
				double pa = (int) (Math.random() * 500) / 100d;
				double pb = (int) (Math.random() * 500) / 100d;
				double pc = (int) (Math.random() * 500) / 100d;

				switch (mode) {
				case 0: // all random; nothing to do
					break;
				case 1: // two same, one different
					int perm = (int) (Math.random() * 6);
					switch (perm) {
					case 0:
						pa = pb;
						break;
					case 1:
						pb = pc;
						break;
					case 2:
						pc = pa;
						break;
					case 3:
						pb = pa;
						break;
					case 4:
						pc = pb;
						break;
					case 5:
						pa = pc;
						break;
					}
					break;
				case 2: // all same
					pa = pb = pc;
				case 3: // can be zero
					int which = (int) (Math.random() * 3);
					switch (which) {
					case 0:
						pa = 0;
						break;
					case 1:
						pb = 0;
						break;
					case 2:
						pc = 0;
						break;
					}
				}

				final Sandwich a = new Sandwich("A", pa);
				final Salad b = new Salad("B", pb);
				final Drink c = new Drink("C", pc);

				final Trio t = constructTrioFrom(a, b, c);

				final double expected = pa + pb + pc
						- Math.min(Math.min(pa, pb), pc);

				// Check for equality within less than $0.01
				assertEquals(
						"Price incorrect (maximum allowable error less than $0.01)",
						expected, t.getPrice(), 0.09999);
			}
		}
	}
}
