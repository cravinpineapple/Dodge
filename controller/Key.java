package controller;

public class Key {
	
	public boolean isDown = false;

	// Creating the keys as simply variables
	public static Key up = new Key();
	public static Key down = new Key();
	public static Key left = new Key();
	public static Key right = new Key();
	public static Key l = new Key();

	public static void resetKeys() {
		up.isDown = false;
		right.isDown = false;
		down.isDown = false;
		left.isDown = false;
		l.isDown = false;
	}
}
