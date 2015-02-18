package message.utils;



public class CircularDoubleLinkedList <T>  {
	
	@SuppressWarnings("hiding")
	public class DLNode<T> {
	    private T data;
	    private DLNode<T> next;
	    private DLNode<T> prev;
	    DLNode(){
	        next=null;
	        prev=null;
	        data=null;
	    }
	    DLNode(T data) {
	        this(data, null, null);
	    }
	    DLNode(T data, DLNode<T> next, DLNode<T> prev) {
	        this.data = data;
	        this.next = next;
	        this.prev = prev;
	    }
	    T getData() {
	        return data;
	    }
	    public void setNextNode(DLNode<T> next) {
	        this.next = next;
	    }
	    public DLNode<T> getPrevNode() {
	        return prev;
	    }
	    public void setPrevNode(DLNode<T> prev) {
	        this.prev = prev;
	    }
	    public void setData(T data) {
	        this.data = data;
	    }
	    public DLNode<T> getNextNode() {
	        return next;
	    }
	}

	// main class 
	
	
	    private DLNode<T> head;
	    // Returns the no. of nodes in circular Doubly linked list
	    public int getSize() {
	        int count = 0;
	        if (head == null)
	            return count;
	        else {
	            DLNode<T> temp = head;
	            do {
	                temp = temp.getNextNode();
	                count++;
	            } while (temp != head);
	        }
	        return count;
	    }
	    // Traversal of circular doubly linked list
	    public void traverse() {
	        if (head == null) {
	            System.out.println("List is empty!");
	        } else {
	            DLNode<T> temp = head;
	            do {
	                System.out.print(temp.getData() + " ");
	                temp = temp.getNextNode();
	            } while (temp != head);
	        }
	    }
	    /* methods related to insertion in circular doubly linked list starts. */
	    public void add (T data){addFirst(data);}
	    
	    public void addFirst(T data) {
	        DLNode<T> newnode = new DLNode<T>(data);
	        if (head == null) {
	            newnode.setNextNode(newnode);
	            newnode.setPrevNode(newnode);
	            head = newnode;
	        } else {
	            DLNode<T> temp = head.getPrevNode();
	            temp.setNextNode(newnode);
	            newnode.setPrevNode(temp);
	            newnode.setNextNode(head);
	            head.setPrevNode(newnode);
	            head = newnode;
	        }
	    }
	    public void addEnd(T data) {
	        DLNode<T> newnode = new DLNode<T>(data);
	        if (head == null) {
	            newnode.setNextNode(newnode);
	            newnode.setPrevNode(newnode);
	            head = newnode;
	        } else {
	            DLNode<T> temp = head.getPrevNode();
	            temp.setNextNode(newnode);
	            newnode.setNextNode(head);
	            head.setPrevNode(newnode);
	            newnode.setPrevNode(temp);
	        }
	    }
	    public void add(int position ,T data) {
	        if(position<0||position==0){
	            addFirst(data);
	        }    
	    else if(position>getSize()||position==getSize()){
	            addEnd(data);
	        }else{
	            
	            DLNode<T>temp=head;
	            DLNode<T> newnode=new DLNode<T>(data);
	            for(int i=0;i<position;i++){
	                temp=temp.getNextNode();
	            }
	            
	            newnode.setNextNode(temp.getNextNode());
	            temp.getNextNode().setPrevNode(newnode);
	            temp.setNextNode(newnode);
	            newnode.setPrevNode(temp);
	        }
	    }
	    /* methods related to insertion in circular doubly linked list ends. */
	    /* methods related to deletion in circular doubly linked list starts. */
	    // Removal based on a given node for internal use only ** now is public for general use

	    public boolean remove(DLNode<T> node) {
	        if(node.getPrevNode()==node||node.getNextNode()==node)
	            head=null;
	        else{
	            DLNode<T> temp=node.getPrevNode();
	            temp.setNextNode(node.getNextNode());
	            node.getNextNode().setPrevNode(temp);
	            }
	        node=null;
	        return true;
	    }
	    public boolean remove (T data){
	    	DLNode<T> node = head;
	    
	    	while (node.getData() != data && node != head) node = node.getNextNode();
	    	if (node.getData() == data){
	    		remove(node);
	    		return true;
	    	}
	    	else return false;
	    	
	    }
	    public T removeFirst(){
	    	T data = null;
	    	
	        if(head==null)
	            System.out.println("List is already empty!");
	        else{
	        	data = head.getData();
	            DLNode<T> temp=head.getNextNode();
	            head.getPrevNode().setNextNode(temp);
	            temp.setPrevNode(head.getPrevNode());
	            head=temp;
	        }
	        return data;
	    }
	    public T removeLast(){
	    	T data = null;
	    	
	        if(head==null)
	            System.out.println("List is already empty!");
	        else{
	        	data = head.getData();
	            DLNode<T> temp=head.getPrevNode();
	            temp.getPrevNode().setNextNode(head);
	            head.setPrevNode(temp.getPrevNode());
	            temp=null;
	        }
	        return data;
	    }
	    // Removal based on a given position
	    public T remove(int position) {
	        T data = null;
	        if (position == 0) {
	            data = head.getData();
	            removeFirst();
	        } else if (position == getSize()-1) {
	            data=head.getPrevNode().getData();
	            removeLast();
	        } else {
	            DLNode<T> temp = head;
	            for (int i = 0; i < position; i++) {
	                temp = temp.getNextNode();
	            }
	            data=temp.getData();
	            DLNode<T> node = temp.getNextNode();
	            node.setPrevNode(temp.getPrevNode());
	            temp.getPrevNode().setNextNode(node);
	            temp = null;
	        }
	        return data;// Deleted node's data
	    }
	    public T remove(){ return removeFirst();}
	    public void clear(){
	    	DLNode<T> node = head;
	    	while (node != null) {
	    		remove(node);
	    		node = node.getNextNode();
	    	}
	    }
	    /* methods related to deletion in circular doubly linked list ends. */
	    // metods related to get data nodes
	    public T getFirst(){ return head.getData();}
	    public T getLast() { return head.getPrevNode().getData();}
	    public T get() { return getFirst();}
	}

