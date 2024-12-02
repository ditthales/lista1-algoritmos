package lista1;

public class Main {
	
	static int passedTests = 0;
	static int failedTests = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testObjectList(new ObjectArrayList());
		testObjectList(new ObjectSimpleLinkedList());
		testObjectList(new ObjectDoubleLinkedList());
		testObjectStack();
		testObjectQueue();

        System.out.println("Testes concluídos!");
        
        int testesTotais = failedTests + passedTests;
        
        System.out.println("Testes totais: " + testesTotais);
        System.out.println("Testes aprovados: " + passedTests);
        System.out.println("Testes reprovados: " + failedTests);
	}
	
	private static void testObjectQueue() {
		System.out.println("Iniciando testes para ObjectQueue...");

        ObjectQueue queue = new ObjectQueue();

        // Teste isEmpty() com fila vazia
        test("isEmpty (fila vazia)", queue.isEmpty(), true);

        // Teste enqueue() e size()
        queue.enqueue("Elemento1");
        test("isEmpty após enqueue (não vazio)", queue.isEmpty(), false);
        test("size após enqueue (1 elemento)", queue.size(), 1);

        queue.enqueue("Elemento2");
        queue.enqueue("Elemento3");
        test("size após vários enqueue", queue.size(), 3);
        test("toString após vários enqueue", queue.toString(), "[Elemento1, Elemento2, Elemento3]");

        // Teste front()
        test("front (início da fila)", queue.front(), "Elemento1");
        test("size após front (não altera fila)", queue.size(), 3);

        // Teste dequeue()
        test("dequeue (remover início da fila)", queue.dequeue(), "Elemento1");
        test("size após dequeue", queue.size(), 2);
        test("toString após dequeue", queue.toString(), "[Elemento2, Elemento3]");

        // Teste limpar fila
        queue.dequeue();
        queue.dequeue();
        test("isEmpty após remover todos os elementos", queue.isEmpty(), true);

        // Teste dequeue em fila vazia (deve lançar exceção)
        try {
            queue.dequeue();
            System.out.println("Teste dequeue em fila vazia: FALHOU (esperado exceção)");
        } catch (IllegalStateException e) {
            System.out.println("Teste dequeue em fila vazia: PASSOU");
        }

        // Teste front em fila vazia (deve lançar exceção)
        try {
            queue.front();
            System.out.println("Teste front em fila vazia: FALHOU (esperado exceção)");
        } catch (IllegalStateException e) {
            System.out.println("Teste front em fila vazia: PASSOU");
        }

        System.out.println("Testes para ObjectQueue concluídos!");
	}
	
	private static void testObjectStack() {
		System.out.println("Iniciando testes para ObjectStack...");

        ObjectStack stack = new ObjectStack();

        // Teste isEmpty() com pilha vazia
        test("isEmpty (pilha vazia)", stack.isEmpty(), true);

        // Teste push() e size()
        stack.push("Elemento1");
        test("isEmpty após push (não vazio)", stack.isEmpty(), false);
        test("size após push (1 elemento)", stack.size(), 1);

        stack.push("Elemento2");
        stack.push("Elemento3");
        test("size após vários push", stack.size(), 3);
        test("toString após vários push", stack.toString(), "[Elemento1, Elemento2, Elemento3]");

        // Teste peek()
        test("peek (topo da pilha)", stack.peek(), "Elemento3");
        test("size após peek (não altera pilha)", stack.size(), 3);

        // Teste pop()
        test("pop (remover topo da pilha)", stack.pop(), "Elemento3");
        test("size após pop", stack.size(), 2);
        test("toString após pop", stack.toString(), "[Elemento1, Elemento2]");

        // Teste limpar pilha
        stack.pop();
        stack.pop();
        test("isEmpty após remover todos os elementos", stack.isEmpty(), true);

        // Teste pop em pilha vazia (deve lançar exceção)
        try {
            stack.pop();
            System.out.println("Teste pop em pilha vazia: FALHOU (esperado exceção)");
        } catch (IllegalStateException e) {
            System.out.println("Teste pop em pilha vazia: PASSOU");
        }

        // Teste peek em pilha vazia (deve lançar exceção)
        try {
            stack.peek();
            System.out.println("Teste peek em pilha vazia: FALHOU (esperado exceção)");
        } catch (IllegalStateException e) {
            System.out.println("Teste peek em pilha vazia: PASSOU");
        }

        System.out.println("Testes para ObjectStack concluídos!");
	}
	
	private static <T extends IList<T>> void testObjectList(T list) {
		System.out.println("Iniciando testes para " + list.getClass().getSimpleName() + "...");

        // Testando o construtor e o método toString()
        //ObjectArrayList list = new ObjectArrayList();
        test("toString (lista vazia)", list.toString(), "[]");

        // Testando size()
        test("size (lista vazia)", list.size(), 0);

        // Testando add(Object obj)
        list.add("Elemento1");
        test("toString após add (primeiro elemento)", list.toString(), "[Elemento1]");
        test("size após add (primeiro elemento)", list.size(), 1);

        // Testando add(Object obj) com resize automático
        list.add("Elemento2");
        list.add("Elemento3");
        test("toString após add (com resize)", list.toString(), "[Elemento1, Elemento2, Elemento3]");
        test("size após add (com resize)", list.size(), 3);

        // Testando add(int position, Object obj)
        list.add(1, "NovoElemento");
        test("toString após add em posição específica", list.toString(), "[Elemento1, NovoElemento, Elemento2, Elemento3]");
        test("size após add em posição específica", list.size(), 4);

        T otherList = list.cloneEmpty();
        
        otherList.add("Outro1");
        otherList.add("Outro2");
        list.addAll(2, otherList);
        test("toString após addAll", list.toString(), "[Elemento1, NovoElemento, Outro1, Outro2, Elemento2, Elemento3]");
        test("size após addAll", list.size(), 6);

        // Testando remove()
        list.remove();
        test("toString após remove (último elemento)", list.toString(), "[Elemento1, NovoElemento, Outro1, Outro2, Elemento2]");
        test("size após remove", list.size(), 5);

        // Testando removeAt(int position)
        list.removeAt(2);
        test("toString após removeAt", list.toString(), "[Elemento1, NovoElemento, Outro2, Elemento2]");
        test("size após removeAt", list.size(), 4);

        // Testando isEmpty()
        test("isEmpty (não vazio)", list.isEmpty(), false);
        list.clear();
        test("isEmpty (após clear)", list.isEmpty(), true);

        // Testando clear()
        list.add("ElementoNovo");
        list.clear();
        test("toString após clear", list.toString(), "[]");
        test("size após clear", list.size(), 0);
        
        System.out.println("Testes para " + list.getClass().getSimpleName() + " concluídos!");
	}

    private static void test(String description, Object actual, Object expected) {
        if (actual.equals(expected)) {
            System.out.println("Teste " + description + ": PASSOU");
            passedTests++;
        } else {
            System.out.println("Teste " + description + ": FALHOU (esperado: " + expected + ", atual: " + actual + ")");
            failedTests++;
        }
	}

}
