public class LLNode<T>{ //linked list node
    public T value;
    public LLNode<T> next;
    public LLNode(T value){
        this.value = value;
        this.next = null;
    }
}