package Tree;

public class BinaryTree {
    //class variables
    private TreeNode root;


    BinaryTree() {  //constructor
        this.root = null;
    }

    BinaryTree(int data) {  //constructor (overloading)
        this.root = new TreeNode(data);
    }

    // to add a node to the tree
    public void insert(int data) {
        this.root=check(root, data);
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

    public String toString() {
        return super.toString();
    }

}

class TreeNode {
    //class variables
    private TreeNode left;
    private TreeNode right;
    private int data;

    TreeNode(int data) { //constructor
        this.data = data;
    }

    public void setData(int data) { //to set the data
        this.data = data;
    }

    public void setLeft(TreeNode left) { //to set this node's left child node
        this.left = left;
    }

    public void setRight(TreeNode right) {  //to set this node's right child node
        this.right = right;
    }

    public int getData() {  //to get the data
        return data;
    }

    public TreeNode getLeft() { //to get the node's left child node
        return left;
    }

    public TreeNode getRight() {    //to get this node's left child node
        return right;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ""+data;
    }
}
