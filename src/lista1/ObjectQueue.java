package lista1;

public class ObjectQueue {

	private ObjectArrayList list;
	
	public ObjectQueue() {
		list = new ObjectArrayList();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int size() {
		return list.size();
	}
	
	public void enqueue(Object element) {
        list.add(element);
    }
	
	public Object dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("Não pode fazer uma fila vazia andar");
		}
		
		Object frontElement = list.getElement(0);
		
		list.removeAt(0);
		
		return frontElement;
	}
	
	public Object front() {
		if (isEmpty()) {
			throw new IllegalStateException("Não existe primeiro em uma fila vazia");
		}
		return list.getElement(0);
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
