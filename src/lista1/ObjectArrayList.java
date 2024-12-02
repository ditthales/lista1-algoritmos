package lista1;

public class ObjectArrayList implements IList<ObjectArrayList> {
	
	private Object[] objects;
	private int objectCounter;
	private int size;
	
	public ObjectArrayList() {
		this.size = 1;
		objects = new Object[size];
		objectCounter = 0;
	}
	
	@Override
	public String toString() {
		if (objectCounter == 0) {
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		for (int i = 0; i < objectCounter - 1; i++) {
			sb.append(objects[i]).append(", ");
		}
		
		sb.append(objects[objectCounter - 1]).append("]");
		
		return sb.toString();
	}
	
	public int size() {
		return objectCounter;
	}
	
	public Object getElement(int position) {
		if (!isBusyPosition(position)) {
			throw new IllegalArgumentException("Não é possivel pegar elemento no index " + position);
		}
		
		return objects[position];
	}
	
	public void add(Object obj) {
		
		if (objectCounter >= size) {
			resize();
		}
		
		objects[objectCounter] = obj;
		objectCounter++;
	}
	
	public void add(int position, Object obj) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Não foi possivel adicionar o elemento, posição invalida");
		}
		
		if (objectCounter >= size) {
			resize();
		}
		
		for (int i = objectCounter - 1; i >= position; i--) {
			objects[i + 1] = objects[i];
		}
		
		objects[position] = obj;
		objectCounter++;
	}
	
	public void addAll(int position, ObjectArrayList list) {
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Não foi possivel adicionar o elemento, posição invalida");
		}
		
		if (list.isEmpty()) {
			return;
		}
		
		int newListSize = list.size();
		
		if (newListSize + objectCounter >= this.size) {
			resize(newListSize);
		}
		
		for (int i = objectCounter - 1; i >= position; i--) {
			objects[i + newListSize] = objects[i];
		}
		
		int j = 0;
		for (int i = position; i < position + newListSize; i++) {
			objects[i] = list.getElement(j);
			j++;
		}
		
		objectCounter += newListSize;
	}
	
	public void remove() {
		objectCounter--;
	}
	
	public void removeAt(int position) {
		if (!isBusyPosition(position)) {
			throw new IllegalArgumentException("Não foi possível remover o elemento");
		}
		
		for (int i = position; i < objectCounter - 1; i++) {
			objects[i] = objects[i + 1];
		}
		
		objectCounter--;
	}
	
	public boolean isEmpty() {
		return objectCounter == 0;
	}
	
	private void resize() {
		Object[] newArrayList = new Object[2*objectCounter];
		
		for (int i = 0; i < objectCounter; i++) {
			newArrayList[i] = objects[i];
		}
		
		size = 2*objectCounter;
		
		objects = newArrayList;
	}
	
	private void resize(int size) {
		Object[] newArrayList = new Object[size + objectCounter];
		
		for (int i = 0; i < objectCounter; i++) {
			newArrayList[i] = objects[i];
		}
		
		this.size = size + objectCounter;
		
		objects = newArrayList;
	}
	
	public void clear() {
		objectCounter = 0;
	}
	
	public ObjectArrayList cloneEmpty() {
		return new ObjectArrayList();
	}
	
	private boolean isBusyPosition(int position) {
		return position >= 0 && position < objectCounter;
	}
	
	private boolean isValidPosition(int position) {
		return position >= 0 && position <= objectCounter;
	}

}
