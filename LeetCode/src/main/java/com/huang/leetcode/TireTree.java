package com.huang.leetcode;

public class TireTree {

    // Node for TireTree
    public class TireNode {
        private String value;
        private boolean end;
        private TireNode[] nodes;

        public TireNode() {

        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public TireNode[] getNodes() {
            return nodes;
        }

        public void setNodes(TireNode[] nodes) {
            this.nodes = nodes;
        }
    }

    public TireNode insertWord(String word) {
        return null;
    }

    public static void main(String[] args) {


    }
}
