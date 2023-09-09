package oof;

public class Test {
    public static void main(String[] args) {
        BinaryTreeCopy a = new BinaryTreeCopy();
        int arr[] = {42,21,38,27,71,82,55,63,6,2,40,12};
        for (int i:arr){
            a.insert(i);
        }

        System.out.println(a.search(72));

    }
}
