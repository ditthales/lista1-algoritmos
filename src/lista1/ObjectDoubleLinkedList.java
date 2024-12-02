package lista1;

public class ObjectDoubleLinkedList implements IList<ObjectDoubleLinkedList> {
	
	
	private int size;
	private Node head;
	private Node tail;
	
	public ObjectDoubleLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	private class Node {
		Object value;
		Node next;
		Node previous;
		
		public Node(Object value) {
			this.value = value;
			next = null;
			previous = null;
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

	@Override
	public void add(Object element) {
		Node newNode = new Node(element);
		
		size++;
		
		if (head == null && tail == null) {
			head = newNode;
			tail = newNode;
			return;
		}
		
		tail.next = newNode;
		newNode.previous = tail;
		tail = newNode;
		
	}

	@Override
	public void add(int position, Object element) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Não é possivel adicionar nesse posicao");
		}
		
		Node newNode = new Node(element);
		
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
			size++;
			return;
		}
		
		if (position == 0) {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
			size++;
			return;
		}
		
		if (position == size) {
			add(element);
			size++;
			return;
		}
		
		Node current = getNodeAt(position);
		Node previous = current.previous;
		
		previous.next = newNode;
		newNode.previous = previous;
		newNode.next = current;
		current.previous = newNode;
		size++;
	}

	@Override
	public void addAll(int position, ObjectDoubleLinkedList list) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Não é possivel adicionar nesse posicao");
		}
		
		if (list.isEmpty()) {
			return;
		}
		
		Node newListHead = list.head;
		Node newListTail = list.tail;
		
		if (head == null) {
			head = newListHead;
			tail = newListTail;
			size += list.size();
			return;
		}
		
		if (position == 0) {
			newListTail.next = head;
			head.previous = newListTail;
			head = newListHead;
			size += list.size();
			return;
		}
		
		if (position == size) {
			tail.next = newListHead;
			newListHead.previous = tail;
			tail = newListTail;
			size += list.size();
			return;
		}
		
		Node current = getNodeAt(position);
		Node previous = current.previous;
		
		previous.next = newListHead;
		newListHead.previous = previous;
		newListTail.next = current;
		current.previous = newListTail;
		size += list.size();
	}

	@Override
	public Object getElement(int position) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Posição invalida");
		}
		
		Node current = getNodeAt(position);
		
		return current.value;
	}

	@Override
	public void remove() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Não é possivel remover de uma lista vazia");
		}
		
		size--;
		
		if (size == 1) {
			head = null;
			tail = null;
			return;
		}
		
		Node newLast = tail.previous;
		newLast.next = null;
		tail = newLast;
	}

	@Override
	public void removeAt(int position) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Impossível remover, elemento inexistente");
		}
		
		if (size == 1) {
			head = null;
			tail = null;
			size--;
			return;
		}
		
		if (position == 0) {
			head = head.next;
			head.previous = null;
			size--;
			return;
		}
		
		if (position == size - 1) {
			tail = tail.previous;
			tail.next = null;
			size--;
			return;
		}
		
		Node current = getNodeAt(position);
		Node previous = current.previous;
		Node next = current.next;
		
		previous.next = next;
		next.previous = previous;
		size--;
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
		tail = null;
		head = null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public ObjectDoubleLinkedList cloneEmpty() {
		return new ObjectDoubleLinkedList();
	}
	
	private boolean isValidPosition(int position) {
        return position >= 0 && position <= size;
    }
	
	private Node getNodeAt(int position) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Posicao invalida");
		}
		
		Node current;
		
		// Otimização: decidir por onde começar a percorrer
		if (position < size/2) {
			current = head;
			for (int i = 0; i < position; i++) {
	            current = current.next;
	        }
			return current;
		}
		
		current = tail;
		for (int i = size - 1; i > position; i--) {
            current = current.previous;
        }
		return current;
	}

}
