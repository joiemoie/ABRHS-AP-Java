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
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//This class creates the display for the Game of Life. It has a row slider, column slider, speed slider,
//restart button, prev gen button, run button, next gen button, clear button, and randomize button.
//The user can draw cells onto the board by holding and dragging.
public class Display extends JComponent{
	
	//These private fields contain information about the size of the JFrame
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 800;
	
	//These fields are useful for the row and column sliders
	private static final int NUM_ROWS_MIN = 0;
	private static final int NUM_ROWS_MAX = 100;
	private static final int NUM_ROWS_INIT = 20;
	private static final int NUM_COLS_MIN = 0;
	private static final int NUM_COLS_MAX = 100;
	private static final int NUM_COLS_INIT = 30;
	
	//These fields are useful for the speed sliders
	private static final int SPEED_MIN = 0;
	private static final int SPEED_MAX = 100;
	private static final int SPEED_INIT = 50;
	private static double speedValue = 50;
	
	//These fields are useful to create uninitialized timer and timer task variables. Whenever
	//the run button is clicked, the timer and timertask will initialize. However, when it is unclicked,
	//the timer and timertask will disappear.
	private static Timer t;
	private static TimerTask tt;
	
	//These fields are used so that if the user is moving the slider while the game of life is running,
	//the game of life will continue running at its normal pace and then change speed
	//once the user stops moving the slider. These are private variables placed here so that they can
	//be accessed by all the subclasses within display and modified.
	private static boolean speedIsAdjusting = false;
	private static boolean speedWasAdjusting = false;
	
	//This private field is here so that the frame can be accessed in the gameboardimage class
	private static JFrame frame;
	private static int numberOfRows;
	private static int numberOfCols;
	
	//This private field is here to keep track of which cells have already been dragged over while the
	//user is dragging over them. It is cleared each time the user unclicks. 
	private static boolean[][] hasBeenPressed;
	
	//This method returns the JFrame
	public static JFrame getFrame(){
		return frame;
	}
	public static void main(String[] args) {
		
		//creates the JFrame
		frame = new JFrame();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.setTitle("Conway's Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//paints a new GameBoard component with a new Game of Life
		GameBoardImage board = new GameBoardImage();
		board.getGameOfLife().randomize();
		board.repaint();
		
		//creates the bottom toolbar with buttons and labels
		JToolBar genButtons = new JToolBar();
		JButton beginning = new JButton("Beginning");
		JButton nextGen = new JButton("Next Generation");
		JButton prevGen = new JButton("Previous Generation");
		JToggleButton active = new JToggleButton("Run", false);
		JButton clear = new JButton("Clear");
		JButton randomize = new JButton("Randomize");
		JLabel genNumber = new JLabel("Generation Number:" + board.getGameOfLife().getGenNum());
		JLabel speedLabel = new JLabel("Speed");

		//adds all the buttons and labels to the toolbar
		genButtons.add(beginning);
		genButtons.add(prevGen);
		genButtons.add(active);
		genButtons.add(nextGen);
		genButtons.add(clear);
		genButtons.add(randomize);
		genButtons.add(genNumber);
		genButtons.add(speedLabel);
		genButtons.setVisible(true);
		
		//creates a listener which restarts the current Game of Life to the first generation
		class BeginningListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				board.getGameOfLife().restart();
				board.repaint();
				genNumber.setText("Generation Number:"+board.getGameOfLife().getGenNum());
			}
		}
		
		//adds the listener to the "Restart" button
		ActionListener restartListener = new BeginningListener();
		beginning.addActionListener(restartListener);
		
		//creates a listener which goes back to the previous generation
		class PrevGenListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				board.getGameOfLife().prevGen();
				board.repaint();
				genNumber.setText("Generation Number: "+board.getGameOfLife().getGenNum());
			}
		}
		
		//adds the listener to the "Previous Generation" button
		ActionListener prevGenListener = new PrevGenListener();
		prevGen.addActionListener(prevGenListener);
		
		//creates a listener which automatically runs through each generation
		class ActiveListenerTime extends TimerTask{
			public void run(){
				board.getGameOfLife().nextGen();
				if(!speedIsAdjusting){
					board.repaint();
					genNumber.setText("Generation Number: "+board.getGameOfLife().getGenNum());
				}
				if(speedWasAdjusting&&!speedIsAdjusting){
					speedWasAdjusting = false;
					t.cancel();
					t.purge();
					t = new Timer();
					tt = new ActiveListenerTime();
					t.schedule(tt, 0, (long)(1000/Math.pow(10, speedValue/50)));
				}
				speedIsAdjusting = false;
			}
		}
		
		//adds a new listener to the "Active" button which toggles the run state on and off
		active.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(active.isSelected()){
					t = new Timer();
					tt = new ActiveListenerTime();
					active.setText("Pause");
					t.schedule(tt, 0, (long)(1000/Math.pow(10, speedValue/50)));
					

				}
				else{
					active.setText("Run");
					t.cancel();
					t.purge();

				}
			}
		});
		
		//creates a listener which moves the board to the next generation
		class NextGenListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				board.getGameOfLife().nextGen();
				board.repaint();
				genNumber.setText("Generation Number:"+board.getGameOfLife().getGenNum());
			}
		}
		
		//adds the listener to the "Next Generation" button
		ActionListener nextGenListener = new NextGenListener();
		nextGen.addActionListener(nextGenListener);
		
		//creates a listener which clears the board and resets the generation to zero
		class clearListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				board.getGameOfLife().getBoard().clear();
				board.repaint();
				board.getGameOfLife().clear();
				genNumber.setText("Generation Number: 0");
			}
		}
		
		//adds the listener to to the "Clear" button
		ActionListener clearListener = new clearListener();
		clear.addActionListener(clearListener);
		
		//creates a listener which randomizes the board
		class RandomizeListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				board.getGameOfLife().randomize();
				board.repaint();
				genNumber.setText("Generation Number: "+board.getGameOfLife().getGenNum());
			}
		}
		
		//adds the listener to the "Randomize" button
		ActionListener randomizeListener = new RandomizeListener();
		randomize.addActionListener(randomizeListener);
		

		//creates a slider on the bottom which changes the number of rows
		JSlider numRows = new JSlider(JSlider.VERTICAL, NUM_ROWS_MIN, NUM_ROWS_MAX, NUM_ROWS_INIT);
		numRows.setMinorTickSpacing(1);
		numRows.setMajorTickSpacing(10);
		numRows.setPaintTicks(false);
		numRows.setPaintLabels(true);
		
		//creates a listener which changes the number of rows
		class RowSliderListener implements ChangeListener{

			public void stateChanged(ChangeEvent e){
				if(numRows.getValueIsAdjusting()){
					int numberOfRows = board.getGameOfLife().getBoard().getRows();
					int numberOfCols = board.getGameOfLife().getBoard().getCols();
					int[][] tempBoardState = new int[numberOfRows][numberOfCols];
					for(int i = 0; i<numberOfRows; i++)
						for(int j = 0; j<numberOfCols; j++)
							tempBoardState[i][j] = board.getGameOfLife().getCell(i, j);
					board.setNumRows(numRows.getValue());
					board.setGameOfLife(new GameOfLife(numRows.getValue(),numberOfCols));
					if(numRows.getValue()>numberOfRows){	
						for(int i = 0; i<numberOfRows; i++)
							for(int j = 0; j<numberOfCols; j++){
								board.getGameOfLife().setCell(i, j, tempBoardState[i][j]);
								board.getGameOfLife().addCellsAlive(tempBoardState[i][j]);
							}
						for(int i = 0; i<numberOfCols; i++)
							for(int j = tempBoardState.length; j<numRows.getValue(); j++){
								board.getGameOfLife().setCell(j, i, 0);
							}
						board.repaint();
					}
					else{
						for(int i = 0; i<board.getGameOfLife().getBoard().getRows(); i++)
							for(int j = 0; j<board.getGameOfLife().getBoard().getCols(); j++){
								board.getGameOfLife().setCell(i, j, tempBoardState[i][j]);
								board.getGameOfLife().addCellsAlive(tempBoardState[i][j]);
							}
						board.repaint();
					}
				}
			}
		}
		
		//adds the listener to the row slider
		ChangeListener rowSliderListener = new RowSliderListener();
		numRows.addChangeListener(rowSliderListener);
		
		//creates a slider which changes the number of columns
		JSlider numCols = new JSlider(JSlider.HORIZONTAL, NUM_COLS_MIN, NUM_COLS_MAX, NUM_COLS_INIT);
		numCols.setMinorTickSpacing(1);
		numCols.setMajorTickSpacing(10);
		numCols.setPaintTicks(false);
		numCols.setPaintLabels(true);
		
		//creates a listener which changes the number of columns
		class ColSliderListener implements ChangeListener{
			public void stateChanged(ChangeEvent e){
				if(numCols.getValueIsAdjusting()){
					int numberOfRows = board.getGameOfLife().getBoard().getRows();
					int numberOfCols = board.getGameOfLife().getBoard().getCols();
					int[][] tempBoardState = new int[numberOfRows][numberOfCols];
					for(int i = 0; i<numberOfRows; i++)
						for(int j = 0; j<numberOfCols; j++)
							tempBoardState[i][j] = board.getGameOfLife().getCell(i,j);
					board.setNumCols(numCols.getValue());
					board.setGameOfLife(new GameOfLife(numberOfRows,numCols.getValue()));
					if(numCols.getValue()>numberOfCols){	
						for(int i = 0; i<numberOfRows; i++)
							for(int j = 0; j<numberOfCols; j++){
								board.getGameOfLife().setCell(i, j, tempBoardState[i][j]);
								board.getGameOfLife().addCellsAlive(tempBoardState[i][j]);
							}
						for(int i = 0; i<numberOfRows; i++)
							for(int j = tempBoardState[0].length; j<numCols.getValue(); j++){
								board.getGameOfLife().setCell(i, j, 0);
							}
						board.repaint();
					}
					else{
						for(int i = 0; i<board.getGameOfLife().getBoard().getRows(); i++)
							for(int j = 0; j<board.getGameOfLife().getBoard().getCols(); j++){
								board.getGameOfLife().setCell(i, j, tempBoardState[i][j]);
								board.getGameOfLife().addCellsAlive(tempBoardState[i][j]);
							}
						board.repaint();
					}
				}
			}
		}
		
		//adds the listener to the column slider
		ChangeListener colSliderListener = new ColSliderListener();
		numCols.addChangeListener(colSliderListener);
		
		//creates a slider which changes the run speed of the active button
		JSlider speed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		speed.setMinorTickSpacing(1);
		speed.setMajorTickSpacing(10);
		speed.setPaintTicks(false);
		speed.setPaintLabels(true);
		
		//creates a listener which changes the run speed of the active button
		class SpeedListener implements ChangeListener{

			public void stateChanged(ChangeEvent e){
				if(speed.getValueIsAdjusting()){
					speedIsAdjusting = true;
					speedWasAdjusting = true;
					speedValue = speed.getValue();
				}
				
			}
		}
		
		//adds the listener to the speed slider
		ChangeListener speedListener = new SpeedListener();
		speed.addChangeListener(speedListener);
		genButtons.add(speed);
		
		//lets the user click to change the cells in the board
		class MouseAddListener implements MouseListener{
			
			//if the mouse clicks on a cell, the cell changes state
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getX()<board.getBoardWidth())
					if(arg0.getY()<board.getBoardHeight()){
						int rowNum = arg0.getY()/board.getSideLength();
						int colNum = arg0.getX()/board.getSideLength();
						if(board.getGameOfLife().getCell(rowNum,colNum) == 1){
							board.getGameOfLife().setCell(rowNum, colNum, 0);
							board.getGameOfLife().addCellsAlive(-1);
						}
						else {
							board.getGameOfLife().setCell(rowNum,colNum, 1);
							board.getGameOfLife().addCellsAlive(1);
						}
					}
				board.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			//if the mouse click is being held, a new 2D array of booleans is creates which tells whether
			//a cell has already been dragged over when the mouse was held down
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				numberOfRows = board.getGameOfLife().getBoard().getRows();
				numberOfCols = board.getGameOfLife().getBoard().getCols();
				hasBeenPressed = new boolean[numberOfRows][numberOfCols];
			}

			//when the mouse click lets go, the 2D array of booleans is cleared
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				hasBeenPressed = new boolean[numberOfRows][numberOfCols];
			}
			
		}
		//this adds the mouse listener to the Game Board
		board.addMouseListener(new MouseAddListener());
		
		//this creates a mouse listener which changes cells if you click and drag across them
		class MouseDrawListener implements MouseMotionListener{
			

			@Override
			public void mouseDragged(MouseEvent e) {
				if(e.getX()>0&&e.getX()<board.getBoardWidth())
					if(e.getY()>0&&e.getY()<board.getBoardHeight()){
						int rowNum = e.getY()/board.getSideLength();
						int colNum = e.getX()/board.getSideLength();
						if(!hasBeenPressed[rowNum][colNum]){
							if(board.getGameOfLife().getCell(rowNum,colNum) == 1){
								board.getGameOfLife().setCell(rowNum, colNum, 0);
								board.getGameOfLife().addCellsAlive(-1);
							}
							else {
								board.getGameOfLife().addCellsAlive(-1);
								board.getGameOfLife().setCell(rowNum,colNum, 1);
							}
							hasBeenPressed[rowNum][colNum] = true;
						}
					}
				board.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
		}
		
		//adds the mouse listener to the game board
		board.addMouseMotionListener(new MouseDrawListener());
		
		//creates a new JPanel which adds the panel, toolbar, board, and sliders
		JPanel panel = new JPanel(new BorderLayout(20,20));
		frame.add(numCols,BorderLayout.SOUTH);
		panel.add(genButtons,BorderLayout.SOUTH);
		panel.add(board,BorderLayout.CENTER);
		panel.add(numRows,BorderLayout.EAST);
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
