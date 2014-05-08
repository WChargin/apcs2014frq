/**
 * A basic implementation of the {@link MenuItem} interface that provides the
 * {@code name} and {@code price} as final fields. You do not need to make any
 * modifications to this class; it is provided for you.
 * 
 * @author William Chargin
 * 
 */
public class SimpleMenuItem implements MenuItem {

	/**
	 * The name of this menu item.
	 */
	private final String name;

	/**
	 * The price of this menu item.
	 */
	private final double price;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPrice() {
		return price;
	}

	/**
	 * Constructs a {@code SimpleMenuItem} with the given name and price.
	 * 
	 * @param name
	 *            the name of this menu item
	 * @param price
	 *            the price of this menu item
	 */
	public SimpleMenuItem(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

}
