package Tree;

public class tes {                                       //inisialisasi kelas tes.
    public static void main(String[] args) {             //inisialisasi main method.
        BinaryTree a = new BinaryTree();                 //instansiasi objek BinaryTree bernama a.
        int arr[] =                                      //membuat array baru bernama arr untuk menyimpan data.
        {27,13,42,6,17}; 
        for (int i:arr){                                 //for each untuk mengulang perintah untuk setiap-
                                                         // elemen yang ada di dalam array arr.
            a.insert(i);                                 //memanggil method insert dari objek a (binary tree)-
                                                         // untuk memasukan elemen yang ada di dalam array.
        }

        a.inOrder();                                     //memanggil method inOrder.
        System.out.println("[In Order]");              //mencetak String '[In Order]'.
        a.preOrder();                                    //memanggil method preOrder.
        System.out.println("[Pre Order]");             //mencetak String '[Pre Order]'.
        a.postOrder();                                   //memanggil method postOrder.
        System.out.println("[Post Order]");            //mencetak String '[Post Order]'.
        a.levelOrder();                                  //memanggil method levelOrder.
        System.out.println("[Level Order]");           //mencetak String '[Level Order]'.

        System.out.println(a.search(16));           //memanggil method search untuk cari 16.
        System.out.println(a.search(63));           //memanggil method search untuk cari 63
    }
}
