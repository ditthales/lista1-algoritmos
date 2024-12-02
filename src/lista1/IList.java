package lista1;

public interface IList<T extends IList<T>> {
	// Adiciona um elemento no final da lista
    void add(Object element);

    // Adiciona um elemento em uma posição específica
    void add(int position, Object element);
    
    // Adiciona uma lista em uma posicao especifica
    void addAll(int position, T list);

    // Retorna o elemento em uma posição específica
    Object getElement(int position);

    // Remove o último elemento da lista
    void remove();

    // Remove o elemento em uma posição específica
    void removeAt(int position);

    // Retorna o número de elementos na lista
    int size();

    // Limpa todos os elementos da lista
    void clear();

    // Verifica se a lista está vazia
    boolean isEmpty();

    // Representação em String da lista
    String toString();
    
    // Retorna uma instancia nova da classe
    T cloneEmpty();
}

