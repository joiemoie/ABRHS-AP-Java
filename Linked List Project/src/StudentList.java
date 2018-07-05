//This class creates a Linked List of Students which can add, remove, and sort the list.
public class StudentList {
	private StudentNode head;
	private int length;
	
	//Inserts students in the list by last name, and first name if the last names match
	public void insertByLastName(Student s){
		
		//Checks if the list is empty. If it is, this puts the student at the head.
		if(length == 0){
			head = new StudentNode(s);
			length++;
		}
		else{
			int position = 0;
			
			//This is the node I am currently keeping track of
			StudentNode currentNode = head;
			
			//This is the node to be inserted
			StudentNode node = new StudentNode(s);
			
			//The if statements check the last names and first names if the last names match.
			//If the name comes alphabetically before the node after the one being observed,
			//it is inserted after the node currently being observed.
			
			//This checks if it should come before the head and inserts it if appropriate.
			if(s.getLastName().compareTo(currentNode.getValue().getLastName())<=0
					|| s.getLastName().compareTo(currentNode.getValue().getLastName())==0
							&& s.getFirstName().compareTo(currentNode.getValue().getFirstName())<0){
					head = node;
					node.setNext(currentNode);
					length++;
			}
			
			//If the insertion spot is in the middle of the linked list,
			//this finds the appropriate location in the middle to insert it.
			else{
				while(position < length-1 && (s.getLastName().compareTo(currentNode.getNext().getValue().getLastName())>=0
						|| s.getLastName().compareTo(currentNode.getNext().getValue().getLastName())==0
							&& s.getFirstName().compareTo(currentNode.getNext().getValue().getFirstName())<0)){
					position++;
					currentNode = currentNode.getNext();
			
				}
			
				//This is the inserting part.
				StudentNode nextNode = currentNode.getNext();
				node.setNext(nextNode);
				currentNode.setNext(node);
				length++;

			}
		}
		
	}
	
	//This method removes a student from the LinkedList given a first and last name.
	public void removeStudent(String firstName, String lastName){
		StudentNode currentNode = head;
		int position = 0;
		
		//This removes the student if the student is at the head.
		if(position == 0 &&
			currentNode.getValue().getFirstName().equals(firstName) && 
			currentNode.getValue().getLastName().equals(lastName)){
				head = currentNode.getNext();
				length--;
			}
		//This removes the student if the student is after the head.
		else{
			//This checks the node after the one being observed to see if there is a match.
			while(position < length-1 && 
					!(currentNode.getNext().getValue().getFirstName().equals(firstName) && 
					currentNode.getNext().getValue().getLastName().equals(lastName))){
				position++;
				currentNode = currentNode.getNext();
			}
			//If this does not reach the node at the end of the linked list,
			//then this links the current node to the one after the next one.
			if(position<length-1){
				currentNode.setNext(currentNode.getNext().getNext());
				length--;
			}
			//If this does reach the final node, this effectively sets the next
			//node to null. 
			else{
				currentNode.setNext(currentNode.getNext());
				length--;
			}
		}
	}
	
	//This sorts the linked list by people's GPA's. It uses a selection sort.
	public void sortByAverage(){
		
		//To do proper swapping later, this needs to keep track of
		//some starting node and the node before that.
		StudentNode node1 = head;
		StudentNode prevNode = null;
		
		//This first for loop shifts the starting node where the minimum
		//node will be inserted.
		for(int i = 0; i<length-1; i++){
			//This keeps track of the current minimum.
			int min = node1.getValue().getGPA();
			
			//This keeps track of the current minimum node and the one before it,
			//needed for swapping later.
			StudentNode minNode = node1;
			StudentNode prevMinNode = prevNode;
			
			//This other node will move through the linked list and search for
			//a node which contains the smallest GPA.
			StudentNode node2 = node1.getNext();
			StudentNode prevNode2 = node1;
			
			//This loop does the search for the smallest GPA.
			for(int j = i + 1; j<length; j++){
				
				//If the GPA is found, this swaps the current minimum GPA value
				//and reference to the node which contains it.
				if(node2.getValue().getGPA()<min){
					min = node2.getValue().getGPA();
					prevMinNode = prevNode2;
					minNode = node2;
				}
				prevNode2 = node2;
				node2=node2.getNext();
			}
			
			//If the node is inserted at the beginning, this sets the head.
			if(prevNode == null) head = minNode;
			
			//If the node is not inserted at the head, it links the previous nodes
			//to the inserted node.
			else prevNode.setNext(minNode);
			
			//This keeps track of the node following the node which contains the
			//minimum GPA.
			StudentNode afterMinNode = minNode.getNext();
			
			//If the replaced node and minimum node are right next to each other, this
			//swaps in a particular way unique for this situation.
			if(node1.getNext().equals(minNode)){
				minNode.setNext(node1);
				prevMinNode.setNext(afterMinNode);
			}
			
			//If the replaced node and minimum node are not right next to each other, this
			//swaps in a particular way unique for those situations.
			else {
				minNode.setNext(node1.getNext());
				prevMinNode.setNext(node1);
				node1.setNext(afterMinNode);
			}
			
			//This increments starting nodes.
			prevNode = minNode;
			node1 = prevNode.getNext();
		}
	}
	
	//This inserts a student by his GPA. Refer to the comments in the method
	//insertByLastName() for an explanation of the process. The only difference is that
	//this uses numerical rather than string comparison.
	public void insertByGPA(Student s){
		if(length == 0){
			head = new StudentNode(s);
			length++;
		}
		else{
			int position = 0;
			StudentNode currentNode = head;
			StudentNode node = new StudentNode(s);
			if(s.getGPA()<=currentNode.getValue().getGPA()){
					head = node;
					node.setNext(currentNode);
					length++;
			}
			else{
				while(position < length-1 && s.getGPA()>=currentNode.getNext().getValue().getGPA()){
					position++;
					currentNode = currentNode.getNext();
				}
				StudentNode nextNode = currentNode.getNext();
				node.setNext(nextNode);
				currentNode.setNext(node);
				length++;
			}
		}
		
	}
	
	//This sorts the students by their last name. This uses a selection sort.
	//For an explanation, refer to the comments in the method sortByGPA(). The only
	//difference is that this uses string comparison rather than numerical comparison.
	public void sortByLastName(){
		StudentNode node1 = head;
		StudentNode prevNode = null;
		for(int i = 0; i<length-1; i++){
			String minLast = node1.getValue().getLastName();
			String minFirst = node1.getValue().getFirstName();
			StudentNode minNode = node1;
			StudentNode prevMinNode = prevNode;
			StudentNode node2 = node1.getNext();
			StudentNode prevNode2 = node1;
			for(int j = i + 1; j<length; j++){
				if(node2.getValue().getLastName().compareTo(minLast)<=0
						||node2.getValue().getLastName().compareTo(minLast)==0
								&& node2.getValue().getFirstName().compareTo(minFirst)<0){
					minLast = node2.getValue().getLastName();
					minFirst = node2.getValue().getFirstName();
					prevMinNode = prevNode2;
					minNode = node2;
				}
				prevNode2 = node2;
				node2=node2.getNext();
			}
			if(prevNode == null) head = minNode;
			else prevNode.setNext(minNode);
			StudentNode afterMinNode = minNode.getNext();
			if(node1.getNext().equals(minNode)){
				minNode.setNext(node1);
				prevMinNode.setNext(afterMinNode);
			}
			else {
				minNode.setNext(node1.getNext());
				prevMinNode.setNext(node1);
				node1.setNext(afterMinNode);
			}
			prevNode = minNode;
			node1 = prevNode.getNext();
		}
	}
	
	//This prints the students names and GPAs out, separating the students by semicolons.
	public String toString(){
		String str = "";
		StudentNode currentNode = head;
		int position = 0;
		while(position < length){
			position++;
			str += currentNode.getValue().toString() + "; ";
			currentNode = currentNode.getNext();
		}
		return str;
	}
	
	public static void main(String[] args) {
		StudentList list = new StudentList();
		list.insertByLastName(new Student("Thomas","Edgars",89));
		list.insertByLastName(new Student("Jennifer","Smith",86));
		list.insertByLastName(new Student("Harold","Umberton",78));
		list.insertByLastName(new Student("Frank","Martin",60));
		list.insertByLastName(new Student("Jeremy","Andrews",83));
		list.insertByLastName(new Student("Laura","Roberts",93));
		list.insertByLastName(new Student("Adele","Lincoln",85));
		list.insertByLastName(new Student("Peter","Smith",91));
		list.insertByLastName(new Student("Larry","Peterson",72));
		System.out.println(list);
		list.removeStudent("Frank", "Martin");
		System.out.println(list);
		list.sortByAverage();
		System.out.println(list);
		list.insertByGPA(new Student("Alice","Henderson",96));
		System.out.println(list);
		list.sortByLastName();
		System.out.println(list);
	}

}
