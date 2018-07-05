import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Display extends JComponent{
	
	//This creates the JFrame and JPanel variables
	private JFrame frame;
	private int WIDTH_INIT = 1000;
	private int HEIGHT_INIT = 800;
	private JPanel panel;
	
	//This stores the center as a fraction of the width and height of the screen
	private double centerFractionOfWidth;
	private double centerFractionOfHeight;
	
	public Display(){
		frame = new JFrame();
		frame.setSize(WIDTH_INIT,HEIGHT_INIT);
		frame.setTitle("Rotating Line");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new BorderLayout(20,20));
		centerFractionOfWidth = .50;
		centerFractionOfHeight = .50;
	}
	
	//This returns the width of the JFrame
	public double getFrameWidth(){
		return frame.getBounds().getWidth();
	}
	
	//This returns the height of the JFrame
	public double getFrameHeight(){
		return frame.getBounds().getHeight();
	}
	
	public static void main(String[] arg) {
		
		//Creates a new display, shape, and shapeGraphics
		Display display = new Display();
		Shape shape = new Line(new Point(display.getFrameWidth()/2,
				display.getFrameHeight()/2),0);
		shape.setCenter(new Point(display.getFrameWidth()/2,
				display.getFrameHeight()/2));
		ShapeGraphics shapeGraphics = new ShapeGraphics(shape, display);
		
		//creates the bottom toolbar with buttons and labels
		JToolBar buttons = new JToolBar();
		JButton recenterButton = new JButton("Recenter");
		JButton lineButton = new JButton("Line");
		JButton polygonButton = new JButton("Polygon");

		//adds all the buttons and labels to the toolbar
		buttons.add(recenterButton);
		buttons.add(lineButton);
		buttons.add(polygonButton);
		buttons.setVisible(true);
		
		//creates a slider which changes the run speed of the active button
		JLabel numberOfSides = new JLabel("Sides: ");
		buttons.add(numberOfSides);
		
		int SIDES_MIN = 3;
		int SIDES_MAX = 20;
		int SIDES_INIT = 3;
		
		JSlider sidesSlider = new JSlider(JSlider.HORIZONTAL, SIDES_MIN, SIDES_MAX, SIDES_INIT);
		sidesSlider.setMinorTickSpacing(1);
		sidesSlider.setMajorTickSpacing(1);
		sidesSlider.setPaintTicks(true);
		sidesSlider.setPaintLabels(true);
		
		//creates a listener which changes the number of sides
		class SidesListener implements ChangeListener{
			public void stateChanged(ChangeEvent e){
				if(sidesSlider.getValueIsAdjusting()){
					Polygon newShape = new Polygon(shape.getCenter(), shape.getTheta(), sidesSlider.getValue());
					shapeGraphics.setShape(newShape);
				}
			}
		}
		
		ChangeListener sidesListener = new SidesListener();
		sidesSlider.addChangeListener(sidesListener);
		buttons.add(sidesSlider);
		

		//creates a slider which changes the rotate speed
		JLabel speedLabel = new JLabel("Speed");
		buttons.add(speedLabel);
		int SPEED_MIN = -100;
		int SPEED_MAX = 100;
		int SPEED_INIT = 10;
		
		JSlider speed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		speed.setMinorTickSpacing(1);
		speed.setMajorTickSpacing(20);
		speed.setPaintTicks(false);
		speed.setPaintLabels(true);
		buttons.add(speed);
		
		//Creates a listener which allows the user to recenter the shape.
		//Functionally, all this class does is change the name of the "Recenter" button
		class RecenterListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				if(recenterButton.getLabel().equals("Click to Set")){
					recenterButton.setLabel("Recenter");
				}
				else recenterButton.setLabel("Click to Set");
			}
		}
		
		//Adds the listener to the Recenter button
		ActionListener recenterListener = new RecenterListener();
		recenterButton.addActionListener(recenterListener);
		
		//This listener changes the shape that shapeGraphics contains to a Line
		class LineListener implements ActionListener{	
			public void actionPerformed(ActionEvent event){
				Line newShape = new Line(shapeGraphics.getShape().getCenter(),
						shapeGraphics.getShape().getTheta());
				shapeGraphics.setShape(newShape);
			}
		}
		
		ActionListener lineListener = new LineListener();
		lineButton.addActionListener(lineListener);
		
		//This listener changes the shape that shapeGraphics contains to a Polygon
		class PolygonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Polygon newShape = new Polygon(shapeGraphics.getShape().getCenter(),
						shapeGraphics.getShape().getTheta(), sidesSlider.getValue());
				shapeGraphics.setShape(newShape);
			}
		}
		
		//Adds the listener to the "Polygon" button
		ActionListener polygonListener = new PolygonListener();
		polygonButton.addActionListener(polygonListener);
		
		/*When the mouse clicks on the JPanel, this will check if the user is trying
		to recenter the shape. If the user is trying to recenter the shape,
		it will rename the "Recenter button"
		*/
		class MouseAddListener implements MouseListener{
			public void mouseClicked(MouseEvent arg0) {
				if(recenterButton.getLabel().equals("Click to Set")){
					recenterButton.setLabel("Recenter");
				}	
			}
			
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		}
		
		display.frame.addMouseListener(new MouseAddListener());
		
		/* When the mouse enters the JPanel, if the user is trying to recenter the
		 * shape, then this will let the shape the mouse until the user clicks.
		 */
		class MouseDrawListener implements MouseMotionListener{
			public void mouseDragged(MouseEvent e) {}
			public void mouseMoved(MouseEvent e) {
				if(e.getX()>0&&e.getX()<display.frame.getBounds().getWidth()){
					if(e.getY()>0&&e.getY()<display.frame.getBounds().getHeight()){
						if(recenterButton.getLabel().equals("Click to Set")){
							shapeGraphics.getShape().setCenter(new Point(e.getX(),e.getY()));
							display.centerFractionOfWidth = shapeGraphics.getShape().getCenter().getX()/display.getFrameWidth();
							display.centerFractionOfHeight = shapeGraphics.getShape().getCenter().getY()/display.getFrameHeight();
						}
					}
				}
			}
			
		}
		
		display.frame.addMouseMotionListener(new MouseDrawListener());
		
		//This causes the shape to rotate by a certain amount based on the speed slider value
		class ActiveListenerTime extends TimerTask{
			public void run(){
				shapeGraphics.getShape().setTheta(shapeGraphics.getShape().getTheta()+speed.getValue()/10*2*Math.PI/2000);
				shapeGraphics.getShape().setCenter(new Point(display.centerFractionOfWidth*display.getFrameWidth(), display.centerFractionOfHeight*display.getFrameHeight()));
				shapeGraphics.repaint();
			}
		}
		
		//starts the rotation timer
		Timer t = new Timer();
		ActiveListenerTime tt = new ActiveListenerTime();
		t.schedule(tt, 0, (1));
		
		//the buttons, sliders, and graphics are all added to the JPanel
		display.panel.add(buttons,BorderLayout.SOUTH);
		display.panel.add(shapeGraphics,BorderLayout.CENTER);
		display.frame.add(display.panel);
		display.frame.setVisible(true);
	}

}
