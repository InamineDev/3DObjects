package Part_7_3D_GUI;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Acts as the "main line" of the JPanel. Adds some shapes to the screen.
 * 
 * @author Russ
 *
 */
@SuppressWarnings("serial")
public class DrawHere extends JPanel implements ActionListener {

	/**
	 * List of shapes to display
	 */
	private List<shapeBase> shapes = new ArrayList<shapeBase>();

	/**
	 * Initializes the screen, sets up shapes.
	 */
	public DrawHere() {

		// Set background
		this.setBackground(Color.BLACK);

		// 3D Shape object
		shapeBase wire_1 = new MyWire(Color.BLUE, new Pos3D(0, 0, 0), new Pos3D(0.12, 0.30, 0.0), new Pos3D(0, 0, 0),
				new Pos3D(0.01, 0.02, 0.03));

		shapeBase wire_2 = new MyWire(Color.RED, new Pos3D(2, 0, 0), new Pos3D(0.20, -0.13, 0.0), new Pos3D(0, 0, 0),
				new Pos3D(-0.03, 0.02, 0.03));

		shapeBase cube_1 = new Cube(Color.GREEN, new Pos3D(1.5, 1.5, 1.5), new Pos3D(0.0, 0.0, 0.13),
				new Pos3D(0, 0, 0), new Pos3D(-0.03, 0.02, 0.03));

		shapeBase cube_2 = new Cube(Color.ORANGE, new Pos3D(1.5, 1.5, 1.5), new Pos3D(0.20, 0.10, 0.13),
				new Pos3D(0, 0, 0), new Pos3D(-0.03, 0.08, 0.03));

		shapeBase cube_3 = new Cube(Color.RED, new Pos3D(1.5, 1.5, 1.2), new Pos3D(0.20, 0.15, 0.13),
				new Pos3D(0, 0, 0), new Pos3D(-0.03, 0.08, 0.03));
		shapeBase un = new Unknown(Color.PINK, new Pos3D(1.5, 1.5, 1.2), new Pos3D(0.20, 0.15, 0.13),
				new Pos3D(0, 0, 0), new Pos3D(-0.03, 0.08, 0.03));

		// put the shapes into the shapes array.
		shapes.add(wire_1);
		shapes.add(wire_2);
		shapes.add(cube_1);
		shapes.add(cube_2);
		shapes.add(cube_3);
		shapes.add(un);

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	/**
	 * Refresh the screen by calling this method. Ensure there is some mechanism
	 * to repeatedly call this method as needed.
	 * 
	 * This method loops through the shapes to redraw each one.
	 */
	public void paintComponent(Graphics g) {

		// System.out.println("w=" + this.getWidth() + " H=" +
		// this.getHeight());
		Pos2D screenSize = new Pos2D(this.getWidth(), this.getHeight());

		// Setup and clear the buffer
		BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics2D g2d = bufferedImage.createGraphics();

		// fill with background color
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Draw the shapes on the screen buffer.
		for (shapeBase s : shapes) {
			s.paintShape(g2d, screenSize);
		}

		// Set the buffer to be visible
		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);

	}

	/**
	 * This method is called when the timer fires. The Timer sets off an
	 * "actionPerformed" event every so many milliseconds.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();

		// Take focus if we don't have it
		if (!this.isFocusOwner()) {
			this.requestFocusInWindow();
		}
	}

	Random R = new Random();

	/**
	 * Small random between -.5 and .5
	 * 
	 * @return
	 */
	private double sm() {
		return R.nextDouble() - 0.5;
	}

	/**
	 * medium random between -2 and 2
	 * 
	 * @return
	 */
	private double med() {
		return (R.nextDouble() * 4) - 2;
	}

	/**
	 * 
	 */
	public void addCube(){
		shapeBase cube = new Cube(Color.BLUE, 
				new Pos3D(med(), med(), med()), 
				new Pos3D(sm(), sm(), sm()),
				new Pos3D(sm(), sm(), sm()), 
				new Pos3D(sm(), sm(), sm()));
		
		// add to shape arraylist
		shapes.add(cube);
	}

	/**
	 * 
	 */
	public void addWire(){
		shapeBase wire = new MyWire(Color.RED,
				new Pos3D(med(), med(), med()), 
				new Pos3D(sm(), sm(), sm()),
				new Pos3D(sm(), sm(), sm()), 
				new Pos3D(sm(), sm(), sm()));
		
		// add to shape arraylist
		shapes.add(wire);
	}


	/**
	 * 
	 */
	public void addPyramid(){
		shapeBase pyramid = new Pyramid(Color.GREEN,
				new Pos3D(med(), med(), med()), 
				new Pos3D(sm(), sm(), sm()),
				new Pos3D(sm(), sm(), sm()), 
				new Pos3D(sm(), sm(), sm()));
		
		// add to shape arraylist
		shapes.add(pyramid);
	}
	public void addUnknown(){
		shapeBase unknown = new Unknown(Color.GREEN,
				new Pos3D(med(), med(), med()), 
				new Pos3D(sm(), sm(), sm()),
				new Pos3D(sm(), sm(), sm()), 
				new Pos3D(sm(), sm(), sm()));
		
		// add to shape arraylist
		shapes.add(unknown);
	}

	/**
	 * 
	 */
	public void clear() {
		shapes.clear();

	}

}
