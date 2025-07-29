package Tests.ExpTestes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ring<T> implements Iterable<T> {

    //Classe aninhada para representar cada nó no anel
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> current;    //O elemento atual do anel
    private int size;           //O número de elementos no anel

    public Ring() {
        this.current = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //Adiciona um elemento após o elemento atual
    public void add(T element) {
        assert element != null : "Ring.add: Elemento não pode ser nulo (pré-condição falhou)";

//        if (element == null) {
//            throw new IllegalArgumentException("Element cannot be null");
//        }

        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            current = newNode;
            current.next = current;
            current.previous = current;
        } else {
            //Insere newNode entre current e current.next
            newNode.next = current.next;
            newNode.previous = current;
            current.next.previous = newNode;
            current.next = newNode;
        }
        size++;
    }

    //Recupera o elemento atual
    public T getCurrent() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring is empty, no current element.");
        }
        return current.data;
    }

    //Remove o elemento atual
    public T removeCurrent() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring is empty, cannot remove.");
        }

        T removeData = current.data;

        if (size == 1) {
            current = null;
        } else {
            //Remove o nó 'current' ajustando os ponteiros
            current.previous.next = current.next;
            current.next.previous = current.previous;
            current = current.next; //Move o ponteiro atual para próximo elemento
        }
        size--;
        return removeData;
    }

    //Avança o ponteiro atual em uma posição
    public void advance() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring is empty, cannot advance.");
        }
        current = current.next;
    }

    //Recua o ponteiro atual em uma posição
    public void backUp() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring is empty, cannot back up.");
        }
        current = current.previous;
    }

    //Suporte para iteração for-each
    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }

    private class RingIterator implements Iterator<T> {
        private Node<T> startNode = current;
        private Node<T> currentNode = null;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (currentNode == null) { //Primeira chamada
                currentNode = startNode;
            } else {
                currentNode = currentNode.next;
            }
            count++;
            return currentNode.data;
        }
    }
}
