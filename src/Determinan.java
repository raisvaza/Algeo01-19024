import java.util.*;
import java.io.*;

public class Determinan extends Matriks {
    public Scanner in = new Scanner(System.in);

    public double [] [] Matriks; //Matriks
    public int brs; //jumlah baris pada Matriks
    public int kol; //jumlah kolom pada Matriks

    //Banyaknya Elemen Matriks
    public int NBElmt(Matriks M){
        return M.GetBrs()*M.GetKol();
    }
    

    public void tranpose(Matriks M){
        Matriks transpose = new Matriks(M.GetBrs(), M.GetKol());
        int i,j;
        for (i = 1; i <= this.GetBrs() ; i++){
            for (j=1; i <= this.GetKol() ; j++){
                 transpose.SetElmt(j,i, this.GetElmt(i,j));
            }
        }
    }
}
