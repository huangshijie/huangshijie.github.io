package com.huang.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static class Node {
        private String key;
        private String val;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public Node(String key) {
            this.key = key;
        }
    }

    public static List<Node> setValue(List<Node> nodes) {
        for(Node node: nodes) {
            node.setVal("v");
        }
        return nodes;
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("ak"));
        nodes.add(new Node("bk"));
        nodes = setValue(nodes);
        for(Node node: nodes) {
            System.out.println("key: " + node.getKey() + ", value: "+ node.getVal());
        }
    }
}
