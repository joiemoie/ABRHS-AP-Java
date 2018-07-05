//Joseph Liba
//This class makes a tree node for a binary search tree.
public class TreeNode {
	//contains the value of the node, the next left node, and next right node
	private Integer value;
	private TreeNode nextLeft;
	private TreeNode nextRight;
	
	//constructs a basic tree node not linked to anything
	public TreeNode(int s){
		value = s;
		nextLeft = null;
		nextRight = null;
	}
	
	//gets the value of the node
	public int getValue(){return value;}
	
	//gets the next left node
	public TreeNode getNextLeft(){return nextLeft;}
	
	//gets the next right now
	public TreeNode getNextRight(){return nextRight;}
	
	//sets the next left node
	public void setNextLeft(TreeNode nextLeft){this.nextLeft = nextLeft;}
	
	//sets the next right node
	public void setNextRight(TreeNode nextRight){this.nextRight = nextRight;} 

}
