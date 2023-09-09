package oof;

public class BinaryTreeCopy {
    private TreeNode root;
    //constructor
    BinaryTreeCopy() {
        this.root  = null;
    }

    //to add a node to the tree
    public void insert(int data) {
        root=check(root, data);
    }

    private TreeNode check(TreeNode pointer, int data) {
        TreeNode newNode = new TreeNode(data);
        //if the tree is empty
        if (pointer == null) {
            pointer = newNode;
            return pointer;
        }
        //if the data is less than the pointer's data then recurse into the tree
        else if (data < pointer.getData()) {
            pointer.setLeft(check(pointer.getLeft(),data));
        } else {
            pointer.setRight(check(pointer.getRight(),data));
        }
        return pointer;
    }

    //to search a node in the tree
    public TreeNode search(int data) {
        TreeNode pointer = root;
        while (true) {
            if (pointer.getData()==data) {
                return pointer;
            }

            if (pointer.getData()<data) {
                if (pointer.getRight()==null) {
                    return null;
                }
                pointer = pointer.getRight();
            } else {
                if (pointer.getLeft()==null) {
                    return null;
                }
                pointer = pointer.getLeft();
            }
        }
    }


    public TreeNode getRoot() {
        return this.root;
    }

    public void setRoot(TreeNode root) {
        this.root=root;
    }

    public String toString() {
        return "";
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
        return ""+data;
    }

    public static void main(String[] args) {
        BinaryTreeCopy a = new BinaryTreeCopy();
        a.insert(12);
        a.insert(22);
        a.insert(40);
        a.insert(39);
        a.insert(9);
        a.insert(10);
        System.out.println(a.search(11));
        
    }
}
