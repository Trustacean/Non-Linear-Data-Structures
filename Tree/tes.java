package Tree;

public class tes {
    public static void main(String[] args) {
        BinaryTree a = new BinaryTree();
        int arr[] = { 60, 55, 100, 107, 67, 105, 57, 45, 59, 106, 43 };
        for (int i : arr) {
            a.insert(i);
        }
        System.out.println("starting");
        a.inOrder();
        int arrDel[] = { 100, 55, 60, 57, 67, 105, 106, 59 };
        for (int i : arrDel) {
            a.delete(i);
            System.out.println();
            a.inOrder();
        }

    }
}
