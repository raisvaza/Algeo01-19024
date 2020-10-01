import java.util.*;
import java.io.*;
import java.lang.*;

public class Determinan extends Matriks {
    public Scanner in = new Scanner(System.in);

    public double [] [] Matriks; //Matriks
    public int brs; //jumlah baris pada Matriks
    public int kol; //jumlah kolom pada Matriks

    //Banyaknya Elemen Matriks
    public int NBElmt(Matriks M){
        return M.GetBrs()*M.GetKol();
    }
    
    //Ubah ke Matriks Segitiga Bawah
    public void segitigaBawah(int jmlTukarBrs){
        if(this.GetKol() == this.GetBrs()){
            int brs = 1;
            int kol = 1;
            int indeksMaks;
            while(brs <= this.GetBrs() && kol<=this.GetKol()){
                indeksMaks = -1; 
                //Mencari baris yang tidak bernilai 0 dalam suatu kolom
                for(int i = brs; i <= this.GetBrs() && indeksMaks == -1; i++){
                    if(this.GetElmt(i,kol) != 0) indeksMaks = i;
                }
    
                //Jika tidak ada baris bernilai nol di sebuah kolom, lanjut ke kolom selanjutnya
                if(indeksMaks == -1) kol++;
    
                //Jika ada akan diproses
                else{
                    this.tukarBrs(brs,indeksMaks); //Tukar baris
                    
                    if(brs != indeksMaks) jmlTukarBrs+=1;   //Menghitung jumlah tukar baris
                    
                    //Mengurangi seluruh kolom di bawah 1 utama pada baris dengan rasio baris lain
                    for(int i = 1; i <= brs-1; i++){
                        double rasio = (-1*this.GetElmt(i, kol)) / this.GetElmt(brs,kol);
                        for (int j = kol; j <= this.GetKol(); j++){
                            this.SetElmt(i, j , this.GetElmt(i,j) + rasio*this.GetElmt(brs,j));
                        }
                    }
    
                    //Lanjut ke baris dan pengecekan kolom selajuntnya
                    brs++;
                    kol++;
                    
                }
            }
        }

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
