public class BallLinkedList {
    public BallNode head;
    public BallNode tail;
    public BallLinkedList() {
        this.head = null;
        this.tail = null;
    }
    public void add(Ball ball) {
        if (this.head == null) {
            this.head = new BallNode(ball);
            this.tail = this.head;
        }
        else {
            tail.next = new BallNode(ball);
            tail = tail.next;
        }
    }
    public BallNode get(int index) {
        BallNode temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
    public boolean isEmpty() {
        return head == null;
    }
}
