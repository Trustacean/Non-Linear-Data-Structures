package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeNode root;

    BinaryTree() {
        this.root = null;
    }

    BinaryTree(int data) {
        this.root = null;
        insert(data);
    }

    public int sesuatu2(int a) {
        TreeNode temp,result=root;
        if (a>getMaxValue()) {
            return getMaxValue();
        }
        while (true) {
            temp=search(a);
            if (search(a)!=null) {
                temp = search(a);
                break;
            }
            a++;
        }
        
        result = temp.getLeft();
        while (temp.getRight()!=null) {
            temp=temp.getRight();
        }
        return result.getData();
    }


    public void insert(int data) {
        this.root = check(root, data);
    }

    private TreeNode check(TreeNode pointer, int data) {
        TreeNode newNode = new TreeNode(data);
        if (pointer == null) {
            pointer = newNode;
            return pointer;
        } else if (data < pointer.getData()) {
            pointer.setLeft(check(pointer.getLeft(), data));
        } else {
            pointer.setRight(check(pointer.getRight(), data));
        }
        return pointer;
    }

    public TreeNode search(int data) {
        return look(data, root);
    }

    private TreeNode look(int data, TreeNode pointer) {
        if (pointer.getData() == data) {
            return pointer;
        }

        if (pointer.getRight() != null
                && data >= pointer.getData()) {
            return look(data, pointer.getRight());
        } else if (pointer.getLeft() != null
                && data < pointer.getData()) {
            return look(data, pointer.getLeft());
        }
        return null;
    }

    public boolean delete(int data) {
        TreeNode current = root,
                parent = root,
                successor = null,
                temp = null;
        while (current != null) {
            if (data < current.getData()) {
                parent = current;
                current = current.getLeft();
            } else if (data > current.getData()) {
                parent = current;
                current = current.getRight();
            } else {
                break;
            }
        }
        // delete
        if (current == null) {
            return false;
        }

        if (current.getLeft() != null && current.getRight() != null) {
            successor = current.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            temp = successor;
            delete(successor.getData());
            current.setData(temp.getData());
            return true;

        } else if (current.getLeft() != null) {
            temp = current.getLeft();
        } else if (current.getRight() != null) {
            temp = current.getRight();
        }

        if (parent.getLeft() == current) {
            parent.setLeft(temp);
        } else {
            parent.setRight(temp);
        }
        return true;

    }

    public int getMaxValue() {
        return getMaxValue(root);
    }

    public int getMaxValue(TreeNode node) {
        if (node.getRight() == null) return node.getData();
        else return getMaxValue(node.getRight());
    }

    public int getMinValue() {
        return getMinValue(root);
    }

    public int getMinValue(TreeNode node) {
        if (node.getLeft() == null) return node.getData();
        else return getMinValue(node.getLeft());
        
    }

    public TreeNode getPredecessor(int data) {
        return getPredecessor(search(data));
    }

    public TreeNode getPredecessor(TreeNode node) {
        node = node.getLeft();
        while (node.getRight() != null) node = node.getRight();
        return node;
    }

    public int getSize() {
        return getSize(root);
    }

    private int getSize(TreeNode node) {
        if (node==null) return 0;
        return getSize(node.getLeft())+getSize(node.getRight())+1;
    }

    public int getHeight(TreeNode node) {
        if (node==null) return 0;
        return Math.max(1+getHeight(node.getLeft()), 1+getHeight(node.getRight()));
    }

    public boolean isComplete() {
        if (getSize(root)==Math.pow(2,getHeight(root))-1) return true;
        return false;
    }

    public void levelOrder() {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode temp;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = queue.peek();
            System.out.print(queue.poll() + " ");
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode pointer) {
        if (pointer != null) {
            inOrder(pointer.getLeft());
            System.out.print(pointer + " ");
            inOrder(pointer.getRight());
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode pointer) {
        if (pointer != null) {
            System.out.print(pointer + " ");
            preOrder(pointer.getLeft());
            preOrder(pointer.getRight());
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(TreeNode pointer) {
        if (pointer != null) {
            postOrder(pointer.getLeft());
            postOrder(pointer.getRight());
            System.out.print(pointer + " ");
        }
    }

    public String toString() {
        return "";
    }

    public TreeNode getRoot() {
        return this.root;
    }

}

class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int data;

    TreeNode(int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public String toString() {
        return "" + data;
    }

    public void printDrawnStructure() {
        System.out.println(this.drawStructure(new StringBuilder(), true, new StringBuilder()).toString());
    }

    /* Penggambaran struktur secara rekursif */
    private StringBuilder drawStructure(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.drawStructure(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(data).append("\n");
        if (left != null) {
            left.drawStructure(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }
}
