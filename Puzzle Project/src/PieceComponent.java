import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PieceComponent extends JComponent {
	private Piece piece;
	private BufferedImage image;
	private double width;
	private double height;
	private boolean inDock = true;
	private final double SIZE_BOARD_RATIO = .56;
	
	public PieceComponent(Piece piece, int number, double width, double height){
		this.piece = piece;
		try {
			image = ImageIO.read(getClass().getResource("piece_" + number + ".png"));
		} catch(IOException e){}
		this.width = width;
		this.height = height;
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		width = BoardComponent.getBoardSize() * SIZE_BOARD_RATIO;
		height = BoardComponent.getBoardSize() * SIZE_BOARD_RATIO;
		this.setBounds(this.getX(), this.getY(), (int)width, (int)width);
		g2.drawImage(image, rotateClockwise(), null);
	}

	public Image getImage() {
		return (Image)image;
	}
	
	public AffineTransform rotateClockwise(){
		AffineTransform at = new AffineTransform();
		at.translate(width/2, width/2);
		for(int i = 0; i<piece.getOrientation()%4;i++)
			at.rotate(Math.PI/2);
		for(int i = piece.getOrientation(); i<0; i++)
			at.rotate(-Math.PI/2);
		at.scale(width/image.getHeight(),width/image.getHeight());
		at.translate(-image.getHeight()/2, -image.getHeight()/2);
		return at;
	}
	
	public boolean isInDock(){
		return inDock;
	}
	
	public void switchDockStatus(){
		inDock = !inDock;
	}

	public Piece getPiece() {
		return piece;
	}
}
