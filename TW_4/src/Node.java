
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Node extends Object {

    Object value;
    public ReentrantReadWriteLock lock;
    Node next;

    Node(Object value){
        this.value = value;
        this.lock = new ReentrantReadWriteLock();
    }

    Object getValue(){
        return value;
    }

}
