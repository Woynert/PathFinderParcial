package edu;

public class StackNode {

    private Object object;
    public StackNode next;

    public StackNode() {
        this.object = null;
        this.next = null;
    }

    public StackNode(Object object) {
        this.object = object;
        this.next = null;
    }

    public StackNode(Object object, StackNode next) {
        this.object = object;
        this.next = next;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isEquals(Object object) {
        if (this.getObject().toString().equals(object.toString())) {
            return true;
        }
        return false;
    }

    public boolean isEquals(StackNode node) {
        if (this.toString().equals(node.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "object=" + object +
                ", next=" + next +
                '}';
    }
}
