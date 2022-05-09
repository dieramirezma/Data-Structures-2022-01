import java.util.Scanner;
import java.util.Objects;

interface LinearList<T>{
    int size();
    T get(int index);
    void add (int index, T theElement);
    String toString();
}

class ArrayLinearList<T> implements LinearList<T>{
    protected T[] element;
    protected int size;

    @SuppressWarnings("unchecked")
    public ArrayLinearList (int initialCapacity) {
        if (initialCapacity < 1) 
            throw new IllegalArgumentException("initialCapacity must be >=1");

        element= (T[]) new Object[initialCapacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index = " + index + " size = " + size);

    }

    public T get(int index) {
        checkIndex(index);
        return element[index];
    }

    @SuppressWarnings("unchecked")
    public void add(int index, T theElement) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException("index = " + index + " size = " + size);

        if (size == element.length) {
            T[] old = element;
            element = (T[]) new Object[2 * size];
            System.arraycopy(old, 0, element, 0, size);
        }

        for (int i = size - 1; i >= index; i--) 
            element[i +1] = element[i];

        element[index] = theElement;

        size++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");

        for (int i = 0; i<size; i++)
            s.append(Objects.toString(element[i]) + ", ");

        if (size > 0) {
            s.setLength(s.length() - 2);
        }
        s.append("]");

        return new String(s);
    }

}


class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        ArrayLinearList<Integer> arrayOut = new ArrayLinearList<>(str.length());

        for (int i = 1; i<str.length()-1; i++) {
            int piv = 0;
            for (int j=1; j<str.length()-1; j++) {
                if (str.charAt(j) != '0') {
                    piv += Math.abs(j-i);
                }
            }
            arrayOut.add(i-1, piv);
        }
        System.out.println(arrayOut);
    }
}