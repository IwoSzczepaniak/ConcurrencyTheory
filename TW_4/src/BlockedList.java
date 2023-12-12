public class BlockedList {
    Node head;
    Node last;

    int length = 0;

    int max_length;

    BlockedList(Node head, int max_length){
        this.head = head;
        this.last = head;
        length++;
        this.max_length = max_length;
    }

    boolean contains(Object o){

        Node current = head;
        while(! current.equals(last)) {
            current.lock.readLock().lock();
            try {
                if (current.getValue().equals(o)) return true;
            }
            finally {
                current.lock.readLock().unlock();
                current = current.next; // czy to nie powinno być w try?
            }
        }
        return false;
    }


    void add(Node nextElement){
        Node oldLast = last;
        oldLast.lock.writeLock().lock();
        try {
            if (length<=max_length) {
                this.last.next = nextElement;
                this.last = nextElement;
                length++;
            }
        }
        finally {
            oldLast.lock.writeLock().unlock();
        }

    }


    boolean remove(Object o){
        if (length == 1) {
            head.lock.writeLock().lock();
            try {
                length--;
            }finally {
                head.lock.writeLock().unlock();
            }

        }

        head.lock.writeLock().lock();

        try {
            if (head.equals(o)) {
                head.next.lock.writeLock().lock();
                Node oldHead = head;
                head = head.next;
                head.next = head;
                length--;
                oldHead.lock.writeLock().unlock();
            }
        }finally{
                head.lock.writeLock().unlock();
        }


        Node current = head.next;
        Node previous = head;

        while(length > 0 && ! current.equals(last)) {
            current.lock.writeLock().lock();
            previous.lock.writeLock().lock(); // maybe should be inside if
            try {
                if (current.getValue().equals(o)) {
                    previous.next = current.next;
                    length--;
                    return true;
                }
            }finally {
                previous.lock.writeLock().unlock();
                current.lock.writeLock().unlock();
            }
            previous = current;
            current = current.next;
        }

        last.lock.writeLock().lock();
        try {
            if (last.getValue().equals(o)) {
                Node oldLast = last;
                previous.lock.writeLock();
                this.last = previous;
                length--;
                oldLast.lock.writeLock().unlock();
                return true;

            }
        }
        finally{
            last.lock.writeLock().unlock();
        }
        return false;
    }

    void printList(){
        Node current = head;
        while(! current.equals(last)){
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println(last.value);
    }
}


//    void addAtIndex(int n, Node nextElement){
//        Node current = head;
//        for(int i = 0; i < n; i++) current = current.next;
//        Node oldNextElement = current.next;
//        current.next = nextElement;
//        nextElement.next = oldNextElement;
//    }
