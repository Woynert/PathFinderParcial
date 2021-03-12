package edu;

public class ListNode {

    private Object object;
    public ListNode next;
    public ListNode before;

    public ListNode() {
        this.object = null;
        this.next   = null;
        this.before = null;
    }

    public ListNode(Object object) {
        this.object = object;
        this.next   = null;
        this.before = null;
    }

    public ListNode(Object object, ListNode next) {
        this.object = object;
        this.next   = next;
        this.before = null;
    }

    /*public ListNode(Object object, ListNode next, ListNode before) {
        this.object = object;
        this.next   = next;
        //this.before = before;
    }*/

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

    public boolean isEquals(ListNode node) {
        if (this.toString().equals(node.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        
        String 
        _next = ""+next;
        
        return "ListNode{" +
                "object=" + object +
                ", next=" + _next +
                '}';
    }
}
