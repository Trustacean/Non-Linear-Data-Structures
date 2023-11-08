package Tree;

public class tes {
    public static void main(String[] args) {
        BinaryTree a = new BinaryTree();
        int arr[] = { 100, 150, 50, 10, 60, 110, 160 };
        for (int i : arr) {
            a.insert(i);
        }
        a.getRoot().printDrawnStructure();
        System.out.println(a.sesuatu2(150));
    }
}
