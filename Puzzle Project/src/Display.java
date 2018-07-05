import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Display extends JFrame {
	
	private int startX = 0;
	private int startY = 0;
	private int pointX = 0;
	private int pointY = 0;
	private Puzzle puzzle;
	private JPanel dockPanel;
	private JPanel boardPanel;
	private BoardComponent board;
	private JPanel contentPane;
	private JLayeredPane layeredPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Graphics g = this.getGraphics();
		
		layeredPane = new JLayeredPane();
		
		
		// Creates the dock panel
		dockPanel = new JPanel();
		dockPanel.setBorder(new LineBorder(Color.GREEN, 3, true));
		dockPanel.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight()));
		
		GridLayout gl_dockPanel = new GridLayout();
		gl_dockPanel.setHgap(0);
		gl_dockPanel.setColumns(3);
		gl_dockPanel.setRows(3);
		dockPanel.setLayout(gl_dockPanel);
		
		// Creates the board panel
		boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setBorder(new LineBorder(Color.black, 3, true));
		final int BOARD_OFFSET = this.getWidth() / 8;
		board = new BoardComponent(this, BOARD_OFFSET);
		boardPanel.add(board);
		board.setBounds(0,0,(int)(BoardComponent.getBoardSize()),BoardComponent.getBoardSize());
		

		JButton reset = new JButton("Reset");
		JButton solve = new JButton("Solve");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(solve); 
		buttonPanel.add(reset);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		boardPanel.add(dockPanel, BorderLayout.WEST);
		boardPanel.setBounds(0, 0, this.getWidth(), this.getHeight() * 7/8);
		layeredPane.add(boardPanel, BorderLayout.CENTER);
		contentPane.add(layeredPane);
		
		int widthProportion = this.getWidth() / 8;
		
		// Creates the Piece list and the Puzzle
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces.add(new Piece(Side.CLUB_OUT,Side.HEART_OUT,Side.DIAMOND_IN,Side.CLUB_IN));
		pieces.add(new Piece(Side.SPADE_OUT,Side.DIAMOND_OUT,Side.SPADE_IN,Side.HEART_IN));
		pieces.add(new Piece(Side.HEART_OUT,Side.SPADE_OUT,Side.SPADE_IN,Side.CLUB_IN));
		pieces.add(new Piece(Side.HEART_OUT,Side.DIAMOND_OUT,Side.CLUB_IN,Side.CLUB_IN));
		pieces.add(new Piece(Side.SPADE_OUT,Side.SPADE_OUT,Side.HEART_IN,Side.CLUB_IN));
		pieces.add(new Piece(Side.HEART_OUT,Side.DIAMOND_OUT,Side.DIAMOND_IN,Side.HEART_IN));
		pieces.add(new Piece(Side.SPADE_OUT,Side.DIAMOND_OUT,Side.HEART_IN,Side.DIAMOND_IN));
		pieces.add(new Piece(Side.CLUB_OUT,Side.HEART_OUT,Side.SPADE_IN,Side.HEART_IN));
		pieces.add(new Piece(Side.DIAMOND_OUT,Side.CLUB_OUT,Side.CLUB_IN,Side.DIAMOND_IN));
		
		puzzle = new Puzzle(3,3, pieces);
		
		// Creates a list of the PieceComponents based on the Pieces in the previous list
		ArrayList<PieceComponent> pieceComps = new ArrayList<PieceComponent>();
		for(int i = 0; i < pieces.size(); i++){
			pieceComps.add(new PieceComponent(pieces.get(i), i + 1, widthProportion, widthProportion));
		}
		
		
		
		
		double dockWidth = dockPanel.getPreferredSize().getWidth();
		
		ArrayList<Point> nodes = new ArrayList<Point>();
		for(int i = 1; i<7; i+=2){
			for(int j = 1; j<7; j+=2){
				int xCoord = board.getX()+board.getWidth()*j/6 + (int)dockWidth + BOARD_OFFSET;
				int yCoord = board.getY()+board.getHeight()*i/6 + BOARD_OFFSET;
				nodes.add(new Point(xCoord,yCoord));
			}
		}
		
		board.setNodes(nodes);

		ArrayList<Point> pieceLocations = new ArrayList<Point>();
		for(int i = 0; i<pieceComps.size(); i++){
			PieceComponent pieceComp = pieceComps.get(i);
			pieceLocations.add(new Point((double)pieceComp.getX(),(double)pieceComp.getY()));
		}
		
		// Adds various listeners to allow for manipulation of the PieceComponents

		for(int i = 0; i < pieceComps.size(); i++){
			PieceComponent pieceComp = pieceComps.get(i);
			dockPanel.add(pieceComp);

			pieceComp.addMouseWheelListener(new MouseWheelListener(){
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					
						int rotations = e.getWheelRotation();
						if(rotations>0){
							for(int i = 0; i<rotations; i++){
								pieceComp.getPiece().rotateClockwise();
								int row = 0;
								int col = 0;
								boolean wasOnBoard = false;
								for(int k = 0; k<3; k++){
									for(int j = 0; j<3; j++){
										if(puzzle.getPiece(k, j)!=null&&puzzle.getPiece(k, j).equals(pieceComp.getPiece())){
											row = k;
											col = j;
											wasOnBoard = true;
										}
									}
								}
								if(wasOnBoard){
									puzzle.removePiece(row, col);
									if(!puzzle.doesFit(pieceComp.getPiece(), row, col))
										pieceComp.getPiece().rotateCounterClockwise();
									puzzle.setPiece(pieceComp.getPiece(), row, col);
								}
							}
							pieceComp.repaint();
						}
						else{
							for(int i = 0; i>rotations; i--){
								pieceComp.getPiece().rotateCounterClockwise();
								int row = 0;
								int col = 0;
								boolean wasOnBoard = false;
								for(int k = 0; k<3; k++){
									for(int j = 0; j<3; j++){
										if(puzzle.getPiece(k, j)!=null&&puzzle.getPiece(k, j).equals(pieceComp.getPiece())){
											row = k;
											col = j;
											wasOnBoard = true;
										}
									}
								}
								if(wasOnBoard){
									puzzle.removePiece(row, col);
									if(!puzzle.doesFit(pieceComp.getPiece(), row, col))
										pieceComp.getPiece().rotateClockwise();
									puzzle.setPiece(pieceComp.getPiece(), row, col);
								}
							}
							pieceComp.repaint();
						}
					
				}
				
			});
			
			class KeyRotateListener implements KeyListener{
				PieceComponent piece;
				public KeyRotateListener(PieceComponent piece){
					this.piece = piece;
				}
				
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == 37){
						piece.getPiece().rotateCounterClockwise();
						int row = 0;
						int col = 0;
						boolean wasOnBoard = false;
						for(int k = 0; k<3; k++){
							for(int j = 0; j<3; j++){
								if(puzzle.getPiece(k, j)!=null&&puzzle.getPiece(k, j).equals(piece.getPiece())){
									row = k;
									col = j;
									wasOnBoard = true;
								}
							}
						}
						if(wasOnBoard){
							puzzle.removePiece(row, col);
							if(!puzzle.doesFit(pieceComp.getPiece(), row, col))
								pieceComp.getPiece().rotateClockwise();
							puzzle.setPiece(pieceComp.getPiece(), row, col);
						}
						piece.repaint();
					}
					if(arg0.getKeyCode() == 39){
						piece.getPiece().rotateClockwise();
						int row = 0;
						int col = 0;
						boolean wasOnBoard = false;
						for(int k = 0; k<3; k++){
							for(int j = 0; j<3; j++){
								if(puzzle.getPiece(k, j)!=null&&puzzle.getPiece(k, j).equals(piece.getPiece())){
									row = k;
									col = j;
									wasOnBoard = true;
								}
							}
						}
						if(wasOnBoard){
							puzzle.removePiece(row, col);
							if(!puzzle.doesFit(pieceComp.getPiece(), row, col))
								pieceComp.getPiece().rotateCounterClockwise();
							puzzle.setPiece(pieceComp.getPiece(), row, col);
						}
						piece.repaint();
					}
				}
				public void keyReleased(KeyEvent arg0) {}
				public void keyTyped(KeyEvent arg0) {}
			}
			
			pieceComp.addKeyListener(new KeyRotateListener(pieceComp));
			
			pieceComp.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent arg0) {}

				public void mouseEntered(MouseEvent arg0) {
					pieceComp.requestFocusInWindow();
				}
					
				public void mouseExited(MouseEvent arg0) {}

				public void mousePressed(MouseEvent e) {
					startX = e.getXOnScreen();
					startY = e.getYOnScreen();
					
					pointX = pieceComp.getX();
					pointY = pieceComp.getY();
					
					dockPanel.remove(pieceComp);
			        boardPanel.remove(pieceComp);
			        layeredPane.add(pieceComp, JLayeredPane.DRAG_LAYER);
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					int index = pieceComps.indexOf(pieceComp);
					pieceLocations.set(index, new Point(pieceComp.getX()/getFrameHeight(),pieceComp.getY()/getFrameHeight()));
					int xCoord = pieceComp.getX()+pieceComp.getHeight()/2;
					int yCoord = pieceComp.getY()+pieceComp.getHeight()/2;
					
					if(pieceComp.isInDock() && pieceComp.getX() > dockPanel.getWidth()){
						layeredPane.remove(pieceComp);
			        	boardPanel.add(pieceComp, BorderLayout.CENTER);
			        	pieceComp.switchDockStatus();
			        } else if(!pieceComp.isInDock() && pieceComp.getX() < dockPanel.getWidth()){
			        	layeredPane.remove(pieceComp);
			        	dockPanel.add(pieceComp);
			        	pieceComp.switchDockStatus();
			        } else if(pieceComp.isInDock()){
			        	layeredPane.remove(pieceComp);
			        	dockPanel.add(pieceComp);
			        } else {
			        	layeredPane.remove(pieceComp);
			        	boardPanel.add(pieceComp, BorderLayout.CENTER);
			        }
					
					//These boolean variables check 3 different possibilities:
					//1) The piece was removed from the board.
					//2) The piece was placed on the board.
					//3) The piece was moved from a spot off the board to another spot off te board.
					boolean removedFromPlace = false;
					boolean placedOnBoard = false;
					
					boolean onlyMoved = true;
					for(Point node:board.getNodes()){
						int nodeIndex = board.getNodes().indexOf(node);
						if(Math.abs(xCoord-node.getX())<board.getWidth()/6&&
							Math.abs(yCoord-node.getY())<board.getHeight()/6)
							{
							
							//If the piece is already on the board, it is removed.
							for(int i = 0; i<3; i++){
								for(int j = 0; j<3; j++){
									if(puzzle.getPiece(i, j)!=null&&puzzle.getPiece(i, j).equals(pieceComp.getPiece())){
										puzzle.removePiece(i, j);
									}
								}
							}
								if(puzzle.doesFit(pieceComp.getPiece(), nodeIndex/3, nodeIndex%3)){
									
									
									puzzle.setPiece(pieceComp.getPiece(), nodeIndex/3, nodeIndex%3);
			
									placedOnBoard = true;
									onlyMoved=false;
									double setXLocation = node.getX()-pieceComp.getHeight()/2;
									double setYLocation = node.getY()-pieceComp.getHeight()/2;
									pieceLocations.set(index, new Point(setXLocation,setYLocation));
									pieceComps.get(index).setBounds((int)setXLocation,
											(int)setYLocation,pieceComp.getWidth(),pieceComp.getWidth());
//									if(puzzle.isSolved()){
//										System.out.println("Congratulations! You won!");
//									}
								}
							}
						
						//If the piece doesn't land on a node, this checks if the piece used to be on the board.
						else{
							if(puzzle.getUnused().contains(pieceComp.getPiece()))
								if(!placedOnBoard&&!onlyMoved)
									removedFromPlace = true;
								else
									removedFromPlace = false;
							else
								if(placedOnBoard)
									removedFromPlace = false;
								else removedFromPlace = true;

						}
					}
					
					//If the piece used to be on the board, this removes the piece from the board.
					if(removedFromPlace||onlyMoved){
						boardPanel.remove(pieceComp);
						boardPanel.repaint();
						dockPanel.removeAll();
						for(PieceComponent piece:pieceComps)
							if(puzzle.getUnused().contains(piece.getPiece())||piece.equals(pieceComp))
								dockPanel.add(piece);
						dockPanel.validate();
						dockPanel.repaint();
						for(int i = 0; i<3; i++){
							for(int j = 0; j<3; j++){
								if(puzzle.getPiece(i, j)!=null&&puzzle.getPiece(i, j).equals(pieceComp.getPiece())){
									puzzle.removePiece(i, j);
								}
							}
						}
					}
				}
			});
			
			pieceComp.addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent e) {
					int deltaX = e.getXOnScreen() - startX;
			        int deltaY = e.getYOnScreen() - startY;
			        
			        int newX = pointX + deltaX;
			        int newY = pointY + deltaY;
			        
			        
			        
			        pieceComp.setLocation(newX, newY);
				}

				@Override
				public void mouseMoved(MouseEvent arg0) {}
				
			});
			
		}
		
		reset.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				puzzle.reset();
				dockPanel.removeAll();
				for(int i = 0; i<pieceLocations.size(); i++){
					boardPanel.remove(pieceComps.get(i));
					
					dockPanel.add(pieceComps.get(i));
		
				}
				dockPanel.validate();
				boardPanel.repaint();
				dockPanel.repaint();
			}

		});
		
		
		solve.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				puzzle.solve();
				for(int i = 0; i<pieceComps.size(); i++){
					PieceComponent piece = pieceComps.get(i);
					int pieceIndex=0;
					for(int j = 0; j<pieceComps.size(); j++){
						if(puzzle.getPiece(j/3, j%3).equals(piece.getPiece())){
							pieceIndex = j;
							break;
						}
					}
					piece.repaint();
					double setXLocation = (board.getNodes().get(pieceIndex).getX()-piece.getHeight()/2);
					double setYLocation = (board.getNodes().get(pieceIndex).getY()-piece.getHeight()/2);
					dockPanel.remove(piece);
					boardPanel.add(piece);
					pieceLocations.get(i).setX(setXLocation);
					pieceLocations.get(i).setY(setYLocation);
					piece.setLocation((int)(setXLocation),(int)(setYLocation));
				}
				dockPanel.repaint();
			}

		});
		
		this.setResizable(false);
		
	}
	
	public double getFrameHeight(){
		return this.getBounds().getHeight();
	}
	
	public double getFrameWidth(){
		return this.getBounds().getWidth();
	}
	public double getDockWidth(){
		return dockPanel.getPreferredSize().getWidth();
	}
	
	public double getBoardX(){
		return board.getX()-getDockWidth();
	}
	public double getBoardY(){
		return board.getY();
	}
}
