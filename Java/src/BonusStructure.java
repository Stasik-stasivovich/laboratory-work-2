public class BonusStructure {
    public BonusNode head;
    public BonusNode tail;
    public BonusStructure() {
        this.head = null;
        this.tail = null;
    }
    public void add(Bonus bonus) {
        if (this.head == null) {
            this.head = new BonusNode(bonus);
            this.tail = this.head;
        }
        else {
            tail.next = new BonusNode(bonus);
            tail = tail.next;
        }
    }
    public BonusNode get(int index) {
        BonusNode temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
    public boolean isEmpty() {
        return head == null;
    }
}
