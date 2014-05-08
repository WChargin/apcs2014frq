import static org.junit.Assert.*;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import org.junit.Test;

public class DirectorTest {

	@Test
	public void testAct() {
		Grid<Actor> gr = new BoundedGrid<>(10, 10);
		Map<Actor, Integer> directions = new HashMap<>();
		for (int row = 0; row < gr.getNumRows(); row++) {
			for (int col = 0; col < gr.getNumCols(); col++) {
				final Director director = new Director();
				final Location dloc = new Location(row, col);
				director.putSelfInGrid(gr, dloc);

				// Populate grid
				for (int i = 0; i < gr.getNumRows(); i++) {
					for (int j = 0; j < gr.getNumCols(); j++) {
						if (row == i && col == j) {
							// Overlap with Director object
							continue;
						}
						Actor actor;
						switch ((int) (Math.random() * 5)) {
						case 0:
							actor = new Actor();
							break;
						case 1:
							actor = new Bug();
							break;
						case 2:
							actor = new Flower();
							break;
						case 3:
							actor = new Rock();
							break;
						case 4:
							actor = new Director();
							break;
						default:
							actor = null;
							throw new AssertionError();
						}
						actor.setDirection((int) (Math.random() * 360));
						actor.putSelfInGrid(gr, new Location(i, j));
						directions.put(actor, actor.getDirection());
					}
				}

				// Cache a list of adjacent locations
				final ArrayList<Location> adj = gr
						.getOccupiedAdjacentLocations(dloc);

				// Act multiple times and observe behavior
				for (int n = 0; n < 100; n++) {
					director.act();
					for (Map.Entry<Actor, Integer> pair : directions.entrySet()) {
						final Actor actor = pair.getKey();
						final int oldDirection = pair.getValue();
						final int newDirection = actor.getDirection();

						final boolean isNeighbor = adj.contains(actor
								.getLocation());

						final int expectedDirection;
						if (isNeighbor && n % 2 != 0) {
							expectedDirection = oldDirection + Location.RIGHT;
						} else {
							expectedDirection = oldDirection;
						}

						assertEquals(expectedDirection % 360,
								newDirection % 360);

						// Store back in map
						pair.setValue(newDirection);
					}
				}

				// Clear grid, start again
				for (Location occupied : gr.getOccupiedLocations()) {
					gr.remove(occupied);
					directions.clear();
				}
			}
		}
	}

	@Test
	public void testAlternation() {
		final Grid<Actor> gr = new BoundedGrid<>(10, 10);

		// Test at every grid square
		for (int row = 0; row < gr.getNumRows(); row++) {
			for (int col = 0; col < gr.getNumCols(); col++) {
				final Director d = new Director();
				d.putSelfInGrid(gr, new Location(row, col));
				for (int i = 0; i < 100; i++) {
					assertEquals("Director should alternate red/green",
							i % 2 == 0 ? Color.RED : Color.GREEN, d.getColor());
					d.act();
				}
			}
		}
	}

	@Test
	public void testConstructorPasses() {
		new Director(); // no errors
	}

	@Test
	public void testConstructorSignatures() {
		final Constructor<?>[] constructors = Director.class
				.getDeclaredConstructors();
		assertEquals("Should only be one constructor", 1, constructors.length);

		final Constructor<?> c = constructors[0];
		int modifiers = c.getModifiers();
		assertTrue("Constructor should be public", Modifier.isPublic(modifiers));

		assertEquals("Constructor should take no parameters", 0,
				c.getParameterTypes().length);
	}

	@Test
	public void testCorrectInitialization() {
		Director d = new Director();
		assertEquals("Director should start out red", Color.RED, d.getColor());
	}

	@Test
	public void testHierarchy() {
		assertEquals("Director should be a subclass of Rock", Rock.class,
				Director.class.getSuperclass());
	}

}
