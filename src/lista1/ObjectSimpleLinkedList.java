package lista1;

public class ObjectSimpleLinkedList implements IList<ObjectSimpleLinkedList> {
	
	private int size;
	private Node head;
	
	public ObjectSimpleLinkedList() {
		size = 0;
		head = null;
	}
	
	private class Node {
		Object value;
		Node next;
		
		public Node(Object value) {
			this.value = value;
			next = null;
		}
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
	}
	
	public Object getElement(int position) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Posição invalida");
		}
		
		Node current = head;
		
		for (int i = 0; i < position; i++) {
            current = current.next;
        }
		
		return current.value;
	}
	
	public int size() {
		return size;
	}
	
	public void add(Object obj) {
		
		size++;
		
		if (head == null) {
			head = new Node(obj);
			return;
		}
		
		Node current = head;
		
		while (current.next != null) {
			current = current.next;
		}
		
		current.next = new Node(obj);
		return;
	}
	
	public void add(int position, Object obj) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Posição para adicionar inválida");
		}
		
		size++;
		
		Node newNode = new Node(obj);
		
		if (position == 0) {
			newNode.next = head;
			return;
		}
		
		Node current = head;
		
		for (int i = 0; i < position - 1; i++) {
			current = current.next;
		}
		
		newNode.next = current.next;
		current.next = newNode;
	}
	
	public void addAll(int position, ObjectSimpleLinkedList list) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Posição para adicionar inválida");
		}
		
		if (list.isEmpty()) {
			return;
		}
		
		size += list.size();
		
		if (position == 0) {
			Node tail = list.getTail();
			tail.next = head;
			head = list.head;
			return;
		}
		
		Node current = head;
		
		for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
		
		Node tail = list.getTail();
		tail.next = current.next;
		current.next = list.head;
	}
	
	public void remove() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Não é possivel remover de uma lista vazia");
		}
		
		size--;
		
		if (size == 1) {
			head = null;
			return;
		}
		
		Node current = head;
		
		while (current.next.next != null) {
			current = current.next;
		}
		
		current.next = null;
	}
	
	public void removeAt(int position) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Impossível remover, elemento inexistente");
		}
		
		size--;
		
		if (position == 0) {
			head = head.next;
			return;
		}
		
		Node current = head;
		
		for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
		
		current.next = current.next.next;
	}
	
	public void clear() {
		head = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public ObjectSimpleLinkedList cloneEmpty() {
		return new ObjectSimpleLinkedList();
	}
	
	private Node getTail() {
		Node current = head;
		
		while (current.next != null) {
            current = current.next;
        }
		
		return current;
	}
	
	private boolean isValidPosition(int position) {
        return position >= 0 && position <= size;
    }

}
