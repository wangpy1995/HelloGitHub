package org.wpy.leetcode.offer;

import java.util.LinkedList;

public class Question09 {

    private LinkedList<Integer> stack1=new LinkedList<>();
    private LinkedList<Integer> stack2=new LinkedList<>();

    public Question09(){
    }

    public void appendTail(int value) {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(value);
    }

    public int deleteHead() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        if(!stack2.isEmpty()) {
            return stack2.pop();
        }else {
            return -1;
        }
    }
}
