package Tree;

import java.util.LinkedList;                //mengimpor LinkedList dari java utilities.
import java.util.Queue;                     //mengimpor Queue dari java utilities.

public class BinaryTree {                   //inisialisasi kelas bernama BinaryTree.
    private TreeNode root;                  //variabel root sebagai akar dari tree.


    BinaryTree() {                          //constructor.
        this.root = null;                   //mengubah nilai akar menjadi null (kosong).
    }

    BinaryTree(int data) {                  //constructor (overloading).
        this.root = null;                   
        insert(data);                       //memanggil method insert dengan nilai masukkan data.
    }

    public void insert(int data) {          //method insert untuk memasukkan data ke dalam tree.
        this.root=check(root, data);        //memanggil method check dan menyimpan nilai balikannya-
                                            // ke dalam variabel root.
    }

    private TreeNode check(TreeNode pointer, int data) {      //method check untuk mengecek-
                                                              // dan membandingkan nilai data.
        TreeNode newNode = new TreeNode(data);                //membuat node baru yang berisi-
                                                              // data dari parameter.
        if (pointer == null) {                                //percabangan untuk mengecek apakah pointer masih kosong.
            pointer = newNode;                                //membuat pointer menunjuk node baru yang telah dibuat.
            return pointer;                                   //mengembalikan nilai pointer.
        }
        else if (data < pointer.getData()) {                  //mengecek apakah nilai data parameter-
                                                              // lebih kecil dari data yang dimiliki pointer.
            pointer.setLeft(check(pointer.getLeft(),data));   //memanggil method check (rekursif) dengan parameter-
                                                              // masukan pointer left dan data parameter sebelumnya
                                                              // dan menjadikan nilai balik sebagai Left dari pointer.
        } else {                                              //jika nilai data parameter lebih besar dari data pointer.
            pointer.setRight(check(pointer.getRight(),data)); //hampir sama seperti method rekursif sebelumnya,- 
                                                              // digunakan untuk sisi Right.
        }
        return pointer;         //mengembalikan nilai pointer yang belum diubah agar pointer rekursif sebelumnya konsisten.
    }

    public TreeNode search(int data) {      //inisialisasi method search.
        return look(data, root);            //memanggil method look dan mengembalikan nilai balikannya.
    }

    private TreeNode look(int data, TreeNode pointer) { //inisialisasi method look.
        if (pointer.getData()==data){                   //kondisi jika data milik pointer sama dengan data yang dicari.
            return pointer;                             //mengembalikan nilai pointer.
        }

        if (pointer.getRight()!=null                    //kondisi jika child node kanan tidak kosong,
        &&data>=pointer.getData()) {                    //kondisi jika data yang dicari lebih besar dari data milik pointer.
                                                                        
            return look(data, pointer.getRight());      //memanggil method look secara rekursif dan mengembalikan nilai balikannya.
        } else if (pointer.getLeft()!=null              //kondisi jika child node kiri tidak kosong,
        &&data < pointer.getData()) {                   //kondisi jika data yang dicari lebih besar dari data milik pointer.
                                                                        
            return look(data, pointer.getLeft());       //memanggil method look secara rekursif dan mengembalikan nilai balikannya.
        }

        return null;                                    //mengembalikan nilai kosong jika data yang dicari tidak ditemukan.
    }
    
    public void levelOrder() {                                  //inisialisasi method levelOrder.
        Queue<TreeNode> queue = new LinkedList<TreeNode>();     //membuat queue baru bernama queue.
        TreeNode temp;                                          //membuat variabel temporary sebagai penyimpanan sementara.
        queue.add(root);                                        //menambah nilai root ke dalam queue.
        while (!queue.isEmpty()) {                              //perulangan yang akan berhenti saat queue kosong.
            temp=queue.peek();                                  //menyimpan urutan pertama dalam queue ke dalam variabel temp
            System.out.print(queue.poll()+" ");                 //mencetak urutan pertama dan mengeluarkannya dari queue
            if (temp.getLeft() != null) {                       //kondisi jika child node kiri dari temp tidak kosong.
                queue.add(temp.getLeft());                      //menambahkan child node kiri dari temp ke dalam queue.
            }
            if (temp.getRight() != null) {                      //kondisi jika child node kanan dari temp tidak kosong.
                queue.add(temp.getRight());                     //menambahkan child node kanan dari temp ke dalam queue.
            }
        }
    }

    public void inOrder() {                     //inisialisasi method inOrder.
        inOrder(root);                          //memanggil method inOrder dengan parameter root.
    }

    private void inOrder(TreeNode pointer) {    //inisialisasi method inOrder.
        if (pointer!=null) {                    //kondisi jika pointer tidak kosong.
            inOrder(pointer.getLeft());         //memanggil method inOrder secara rekursif.
            System.out.print(pointer+" ");      //mencetak pointer.
            inOrder(pointer.getRight());        //memanggil method inOrder secara rekursif.
        }
    }

    public void preOrder() {                    //inisialisasi method preOrder.
        preOrder(root);                         //memanggil method preOrder dengan parameter root.
    }

    private void preOrder(TreeNode pointer) {   //inisialisasi method preOrder.
        if (pointer!=null) {                    //kondisi jika pointer tidak kosong.
            System.out.print(pointer+" ");      //mencetak pointer.
            preOrder(pointer.getLeft());        //memanggil method preOrder secara rekursif.
            preOrder(pointer.getRight());       //memanggil method preOrder secara rekursif.
        }
    }

    public void postOrder() {                    //inisialisasi method postOrder.
        postOrder(root);                         //memanggil method postOrder dengan parameter root.
    }

    private void postOrder(TreeNode pointer) {   //inisialisasi method postOrder.
        if (pointer!=null) {                     //kondisi jika pointer tidak kosong.
            postOrder(pointer.getLeft());        //memanggil method postOrder secara rekursif.
            postOrder(pointer.getRight());       //memanggil method postOrder secara rekursif.
            System.out.print(pointer+" ");       //mencetak pointer.
        }
    }

    public String toString() {
        return "";
    }

}

class TreeNode {                            //inisialisasi kelas bernama TreeNode.
    private TreeNode left;                  //variabel left untuk menunjuk child node bagian kiri.
    private TreeNode right;                 //variabel right untuk menunjuk child node bagian kanan.
    private int data;                       //variabel data untuk menyimpan data.

    TreeNode(int data) {                    //constructor.
        this.data = data;                   //menyimpan nilai data parameter ke dalam variabel data.
    }

    public void setData(int data) {         //inisialisasi method setData.
        this.data = data;                   //menyimpan nilai data parameter ke dalam variabel data.
    }

    public void setLeft(TreeNode left) {    //inisialisasi method setLeft.
        this.left = left;                   //membuat variabel left menunjuk node parameter masukan.
    }

    public void setRight(TreeNode right) {  //inisialisasi method setRight.
        this.right = right;                 //membuat variabel right menunjuk node parameter masukan.
    }

    public int getData() {                  //inisialisasi method getData.
        return data;                        //mengembalikan nilai variabel data.
    }

    public TreeNode getLeft() {             //inisialisasi method getLeft.
        return left;                        //mengembalikan nilai variabel left.
    }

    public TreeNode getRight() {            //inisialisasi method getRight.
        return right;                       //mengembalikan nilai variabel right.
    }

    public String toString() {              //inisialisasi method toString
        return ""+data;                     //mengembalikan nilai variabel data dengan format string;
    }
}
