//This class makes a binary search tree. A binary search tree starts with a root, then
//those future values in one of its children. It stores it in the left child if the value
//is less than or equal to the value of the node, and the right child if its greater
//than the value of the node.
public class BinarySearchTree {

	//Stores the root and the size. The size increments whenever something is added.
	private TreeNode root;
	private int size;
	
	//adds a treenode to the tree at the appropriate position
	public void add(Integer x){
		add(x,root);
	}
	
	//this method recursively goes through the tree searching for a node whose appropriate
	//child currently does not exist, then sets the child to the new node
	public void add(Integer x, TreeNode node){
		//If the tree is empty, this just sets the root
		if(size == 0) {
			root = new TreeNode(x);
			size++;
		}
		
		else{
			//if the integer is less than or equal to the current node, this checks if a new node
			//can be inserted to the left. If it can, it will be. If it can't,
			//this recursively goes to the next left child node.
			if(x <= node.getValue()){
				if(node.getNextLeft() == null) {
					node.setNextLeft(new TreeNode(x));
					size++;
				}
				else add(x,node.getNextLeft());
			}
			
			//if the integer is greater than the current node, this checks if a new node
			//can be inserted to the right. If it can, it will be. If it can't,
			//this recursively goes to the next right child node.
			else{
				if(node.getNextRight() == null) {
					node.setNextRight(new TreeNode(x));
					size++;
				}
				else add(x,node.getNextRight());
			}
				
		}
	}
	
	//This checks if the tree contains a certain value
	public boolean contains(Integer x){return contains(x,root);}
	
	//This recursively goes through either the left or right nodes until the node
	//is either equal to the value being searched or is null.
	public boolean contains(Integer x, TreeNode node){
		if(node == null) return false;
		if(node.getValue() == x) return true;
		
		if(x <= node.getValue()){
			return contains(x,node.getNextLeft());
		}
		else{
			return contains(x,node.getNextRight());
		}
				
	}
	
	//This just returns the size.
	public int size(){return size;}
	
	//This prints an inorder traversal of the tree.
	public String toString(){
		return toString(root);
	}
	
	//This uses recursion to print the left side, then the value, then the right side
	public String toString(TreeNode node){
		if(node == null)return "";
		return toString(node.getNextLeft())+" " + node.getValue() + " " + toString(node.getNextRight());
	}
	
	//This prints a preorder traversal of the string.
	public String preorderToString(){return preorderToString(root);}
	
	//This uses recursion to print the value, then the left side, then the right side.
	public String preorderToString(TreeNode node){
		if(node == null)return "";
		return node.getValue() + " " + toString(node.getNextLeft()) + " " + toString(node.getNextRight());
	}
	
	//This prints a postorder traversal of the string.
	public String postorderToString(){return postorderToString(root);}
	
	//This uses recursion to print the left side, the right side, the the value.
	public String postorderToString(TreeNode node){
		if(node == null)return "";
		return toString(node.getNextLeft()) + " " + toString(node.getNextRight()) + " " + node.getValue();
	}
	
	public static void main(String[] args) {

	}

}
