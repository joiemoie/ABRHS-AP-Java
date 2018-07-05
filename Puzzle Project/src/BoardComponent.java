import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;


//This class creates a JComponent representing the shape that can be drawn on the display
public class BoardComponent extends JComponent{
	private Display display;
	private int boardOffset;
	private static double frameWidth;
	private static double frameHeight;
	private static int boardSize;
	ArrayList<Point> nodes = new ArrayList<Point>();
	
	public BoardComponent(Display display, int boardOffset){
		this.display = display;
		frameWidth =  display.getFrameWidth();
		frameHeight = display.getFrameHeight();
		boardSize = (int)(frameHeight*16/32);
		this.boardOffset = boardOffset;

	}
	
	public static double getFrameWidth(){
		return frameWidth;
	}
	public static double getFrameHeight(){
		return frameHeight;
	}
	public static int getBoardSize(){
		return boardSize;
	}
	
	public ArrayList<Point> getNodes(){return nodes;}
	
	public ArrayList<Point> setNodes(ArrayList<Point> nodes){
		return this.nodes = nodes;
	}
	public void paintComponent(Graphics g){
		frameWidth =  display.getFrameWidth();
		frameHeight = display.getFrameHeight();
		Graphics2D g2 = (Graphics2D)g;
		boardSize = (int)(frameHeight*16/32);
		
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(boardOffset,boardOffset,boardSize,boardSize);
		for(int i = 0; i<3; i++){
			int gap = boardSize/3*i + boardOffset;
			g2.drawLine(gap,boardOffset,gap,boardSize + boardOffset);
		}
		for(int i = 0; i<3; i++){
			int gap = boardSize/3*i + boardOffset;
			g2.drawLine(boardOffset,gap,boardSize + boardOffset,gap);
		}
		
		int k = 0;
		for(int i = 1; i<7; i+=2){
			for(int j = 1; j<7; j+=2){
				int xCoord = (int) (display.getBoardX()+getBoardSize()*j/6 + display.getDockWidth() + boardOffset);
				int yCoord = (int) (display.getBoardY()+getBoardSize()*i/6 + boardOffset);
				nodes.set(k,new Point(xCoord,yCoord));
				k++;
			}
		}
	
		
	}
	
}
