package lista1;

public class ObjectStack {

	private ObjectArrayList list;
	
	public ObjectStack() {
		list = new ObjectArrayList();
	}
	
	public boolean isEmpty() {
        return list.isEmpty();
    }
	
	public void push(Object element) {
		list.add(element);
	}
	
	public Object pop() {
		if (isEmpty()) {
			throw new IllegalStateException("Não pode excluir de uma pilha vazia");
		}
		
		Object topElement = list.getElement(list.size() - 1);
		
		list.remove();
		
		return topElement;
	}
	
	public Object peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Não pode pegar o ultimo elemento de uma pilha vazia");
		}
		
		return list.getElement(list.size() - 1);
	}
	
	public int size() {
		return list.size();
	}
	
	@Override
    public String toString() {
        return list.toString();
    }
	
}
