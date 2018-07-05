import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GameBoardImage extends JComponent{
	private int numCols = 30;
	private int numRows = 20;
	private int sideLength = 20;
	private int tempSideLength=0;
	private GameOfLife game = new GameOfLife(20,30);
	
	//sets the number of columns in the display
	public int setNumCols(int numCols){
		return this.numCols = numCols;
	}
	
	//sets the number of rows in the display
	public int setNumRows(int numRows){
		return this.numRows = numRows;
	}
	
	//paints the game board and the graph
	public void paintComponent(Graphics g){
		
		//paints the game board
		if(numCols*20>5/8*Display.getFrame().getBounds().getWidth())
			sideLength = (int) (Display.getFrame().getBounds().getWidth()*5/8/numCols);
		if(numCols*20<5/8*Display.getFrame().getBounds().getWidth()){
			tempSideLength = 20;
			if(tempSideLength<sideLength)
				sideLength = tempSideLength;
		}
		if(numRows*20>3/4*Display.getFrame().getBounds().getHeight()){
			tempSideLength = (int) (Display.getFrame().getBounds().getHeight()*3/4/numRows);
			if(tempSideLength<sideLength)
				sideLength = tempSideLength;
		}
		if(numRows*20<7/8*Display.getFrame().getBounds().getHeight()){
			tempSideLength = 20;
			if(tempSideLength<sideLength)
				sideLength = tempSideLength;
		}
		for(int i = 0 ; i < numRows; i++)
			for(int j = 0; j < numCols; j++){
				if(game.getCell(i, j) == 1)
					g.fillRect(j*sideLength,i*sideLength, sideLength, sideLength);
				else g.drawRect(j*sideLength,i*sideLength, sideLength, sideLength);
			}
		
		//paints the graph of the number of cells alive vs generation number
		int graphLength = (int) (Display.getFrame().getBounds().getWidth()*1/4);
		int graphWidth = (int) (Display.getFrame().getBounds().getHeight()*1/4);
		g.setColor(Color.WHITE);
		g.fillRect(numCols*sideLength+50, 50, graphLength, graphWidth);
		g.setColor(Color.BLACK);
		g.drawRect(numCols*sideLength+50, 50, graphLength, graphWidth);
		for(int i = 1; i<10; i++)
			g.drawLine(numCols*sideLength+45, 50+i*graphWidth/10, numCols*sideLength+50, 50+i*graphWidth/10);
		for(int i = 1; i<10; i++)
			g.drawLine(50+numCols*sideLength+i*graphLength/10, 55+graphWidth,50+ numCols*sideLength+i*graphLength/10, 50+graphWidth);
		
		g.drawString("# living cells", numCols*sideLength+20, 45);
		g.drawString("generation", numCols*sideLength+graphLength-30, 70+graphWidth);
		
		//creates a line graph of the last 10 generations on the screen, with the
		//graph window sized accordingly to fit the past 10 generations and following
		//the graph as the values increase or decrease
		if(game.getArrayListCellsAlive().size()<=11){
			int middle = 0;
			int sum = 0;
			int size = game.getArrayListCellsAlive().size();
			for(int i = 0; i<size-1; i++){
				sum+= game.getArrayListCellsAlive().get(i);
			}
			middle = sum / 10;

			int max=0, min = game.getArrayListCellsAlive().get(0);
			for(int i = 0; i<size-1; i++){
				if(game.getArrayListCellsAlive().get(i)>max)
					max = game.getArrayListCellsAlive().get(i);
				if(game.getArrayListCellsAlive().get(i)<min)
					min = game.getArrayListCellsAlive().get(i);
			}
			int range = max - min;
			double windowSize = range*5*Math.pow(2/5, 1/10*size);
			
			if (range<50)
				range = 50;
			for(int i =0; i<size-1; i++){
				g.drawLine(numCols*sideLength+50+i*graphLength/10, 50+graphWidth-(int)((double)((game.getArrayListCellsAlive().get(i)-middle+windowSize/2))/windowSize*graphWidth),
					numCols*sideLength+50+(i+1)*graphLength/10, 50+graphWidth-(int)((double)((game.getArrayListCellsAlive().get(i+1)-middle+windowSize/2))/windowSize*graphWidth));
			}
			for(int i = 1; i<10; i++)
				g.drawString(min+range/10*i+"", numCols*sideLength+25, 50+graphWidth-i*graphWidth/10);
		}
		
		if(game.getArrayListCellsAlive().size()>11){
			int middle = 0;
			int sum = 0;
			int size = game.getArrayListCellsAlive().size();
			for(int i = 0; i<10; i++){
				sum+= game.getArrayListCellsAlive().get(size-11+i);
			}
			middle = sum / 10;

			int max=0, min = game.getArrayListCellsAlive().get(size-11);
			for(int i = 0; i<10; i++){
				if(game.getArrayListCellsAlive().get(size-11+i)>max)
					max = game.getArrayListCellsAlive().get(size-11+i);
				if(game.getArrayListCellsAlive().get(size-11+i)<min)
					min = game.getArrayListCellsAlive().get(size-11+i);
			}
			int range = max - min;
			
			if (range<10)
				range = 10;
			for(int i =0; i<10; i++){
				g.drawLine(numCols*sideLength+50+i*graphLength/10, 50+graphWidth-(int)((double)((game.getArrayListCellsAlive().get(size-11+i)-middle+range*2/2))/(range*2)*graphWidth),
					numCols*sideLength+50+(i+1)*graphLength/10, 50+graphWidth-(int)((double)((game.getArrayListCellsAlive().get(size-10+i)-middle+range*2/2))/(range*2)*graphWidth));
			}
			for(int i = 1; i<10; i++)
				if(range>10)
					g.drawString(min+range/10*i+"", numCols*sideLength+25, 50+graphWidth-i*graphWidth/10);
				else g.drawString(game.getArrayListCellsAlive().get(size-1)-5+range/10*i+"", numCols*sideLength+25, 50+graphWidth-i*graphWidth/10);
		}
				
	}
	
	//This method returns the board width in pixels
	public int getBoardWidth(){
		return numCols*sideLength;
	}
	
	//This method returns the board height in pixels
	public int getBoardHeight(){
		return numRows*sideLength;
	}
	
	//This method returns the side length
	public int getSideLength(){
		return sideLength;
	}
	
	//This method returns the game of life object being used
	public GameOfLife getGameOfLife(){
		return game;
	}
	
	//This method sets the game of life object
	public GameOfLife setGameOfLife(GameOfLife gameOfLife){
		return game = gameOfLife;
	}
	
}
