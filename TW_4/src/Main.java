public class Main {
    public static void main(String[] args) {

        Node head = new Node(12);
        BlockedList blockedList = new BlockedList(head, 5);
        Node sec = new Node(2);
        blockedList.add(sec);
        Node third = new Node(4);
        blockedList.add(third);

        blockedList.printList();
        System.out.println("removal");
        blockedList.remove(head);
        blockedList.printList();
    }
}