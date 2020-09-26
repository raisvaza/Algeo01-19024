import java.util.*;
import java.io.*;

class Spl extends Matriks{

    //Prosedur input matriks dari keyboard khusus SPL
    public void bacaMatriks(Matriks M, int i, int j) {
        for(i=1; i<= M.GetBrs(); i++){
            System.out.println();
            System.out.println("Masukkan persamaan ke-" + i + " :");
            for (j = 1; j<= M.GetKol(); j++){
                if (j == M.GetKol()) System.out.print("Masukkan konstanta: ");
                else System.out.print("Masukkan koefisien x[" + j + "]: ");
                double x = in.nextDouble();
                M.SetElmt(i,j,x);
            }
        }
    }

    // Mencari solusi SIstem Persamaan Linier dari metode Eliminasi Gauss
    public void solusiEliminasiGauss(Matriks M, double solusi [][], int[] jumlahSolusi){
        boolean adaSolusi = true;   //Variabel penentu apakah sistem memiliki solusi
        int Brsnol = 0; //Jumlah baris seluruh elemen bernilai 0

        //Mengecek apakah terdapat solusi
        for(int i = 1; i <= M.GetBrs(); i++){
            if(M.isKoefZero(i)) adaSolusi = false;
            if(M.isAllZero(i)) Brsnol++;
        }

        //Jika tidak ada solusi, set nilai jumlahSolusi[0] menjadi 0
        if(!adaSolusi) jumlahSolusi[0] = 0; 

        //Jika ada, akan diproses
        else{
             if(M.GetBrs()-Brsnol == M.GetKol()-1) jumlahSolusi[0] = 1; //Jika solusi tunggal
             else jumlahSolusi[0] = 999;  //Jika solusi banyak

             boolean firstPivot = false; //apakah non-zero ditemukan
             int BrsPivot;  //indeks ditemukan elemen bukan 0 pertama pada baris
             int lastPivot = 0;

             //Mengisi array solusi
            for(int i = M.GetBrs(); i>=1; i--){
                if(M.isAllZero(i)) continue; //Jika isinya 0 semua, dilewatkan
                
                //Bukan 0, diproses
                else{
                   if(!firstPivot){
                       firstPivot = true;
                       int j = 1;

                       while(j <= M.GetKol()-1 && M.GetElmt(i,j) == 0) j++;
                       BrsPivot = j;
                       
                       for (int k = 1; k <= M.GetKol()-1; k++) solusi[BrsPivot][k] = 0;

                       j++;
                       while(j <= M.GetKol()-1){
                           if(M.GetElmt(i,j) != 0){
                               for (int k = 1; k <= M.GetKol()-1; k++){
                                   if (k!=j) solusi[j][k] = 0;
                                   else solusi[j][k] = 1;
                               }
                               solusi[j][M.GetKol()] = 0;
                               solusi[BrsPivot][j] = -1*M.GetElmt(i,j);
                           }
                           j++;
                       }
                       lastPivot = BrsPivot;
                       solusi[BrsPivot][M.GetKol()] = M.GetElmt(i, M.GetKol());
                   } else{
                        int j = 1;
                        while (j <= M.GetKol()-1 && M.GetElmt(i,j) == 0) j++;
                        BrsPivot = j;
                        for (int k =1; k <= M.GetKol()-1; k++) solusi[BrsPivot][k] = 0;
                        for(int k = BrsPivot+1; k <= lastPivot-1; k++){
                            for(int l=1; l <= M.GetKol()-1; l++){
                                if (l != k) solusi[k] [l] = 0;
                                else solusi[k][l] = 1;
                            }
                            solusi[k][M.GetKol()] = 0;
                        }
                        solusi[BrsPivot][M.GetKol()] = M.GetElmt(i, M.GetKol());
                        for (int k = BrsPivot+1; k <= M.GetKol()-1; k++){
                            for(int l = 1; l <= M.GetKol(); l++){
                                solusi[BrsPivot][l] += -1*M.GetElmt(i,k)*solusi[k][l];
                            }
                        }
                        lastPivot = BrsPivot;
                   } 
                }
            }
        } 
    }

    // Mencari solusi SIstem Persamaan Linier dari metode Eliminasi Gauss
    public void solusiEliminasiGaussJordan(Matriks M, double solusi [][], int[] jumlahSolusi){
        int brsnol = 0;
        boolean adaSolusi = true;

        //Mengecek apakah terdapat solusi
        for (int i = M.GetBrs(); i >= 1; i--){
            if (M.isAllZero(i)) brsnol++;
            if (M.isKoefZero(i)) adaSolusi = false;
        }

        //Jika tidak ada solusi, set nilai jumlahSolusi[0] menjadi 0
        if(!adaSolusi) jumlahSolusi[0] = 0;

        //Jika ada, akan diproses
        else{
            if(M.GetBrs() - brsnol == M.GetKol() - 1) jumlahSolusi[0] = 1; //Jika solusi tunggal
            else jumlahSolusi[0] = 999; //Jika solusi banyak

            //Mengisi Array solusi
            for (int i = 1; i <= M.GetKol()-1; i++){
                for (int j=1;j <= M.GetKol(); j++){
                    if(i == j)  solusi[i][j] = 1;   //Isi dengan 1 setiap, sebagai koefisien dari setiap elemen X
                    else solusi[i][j] = 0;  //Isi dengan 0 bukan diagonal
                }
            }
            
            int BrsPivot = 0;
            for(int i = M.GetBrs(); i>=1; i--){
                if(M.isAllZero(i)) continue;
                else{
                    int j=1;
                    while(j <= M.GetKol()-1 && M.GetElmt(i,j) == 0) j++;
                    BrsPivot = j;   //Nilai x-sekian yang akan diproses
                    solusi[BrsPivot][BrsPivot] = 0; //Meng-0-kan elemen diagonal tersebut
                    j++;
                    while(j <= M.GetKol()-1){
                        solusi[BrsPivot][j] = -1*M.GetElmt(i,j);
                        j++;
                    }
                    solusi[BrsPivot][M.GetKol()] = M.GetElmt(i,M.GetKol()); //Hasil x-sekian

                }
            }

        }
    }

    //Prosedur Tulis Solusi Sistem Persamaan Linier
    public void tulisSolusi(double[][] solusi, Matriks M){
        int variabel = M.GetKol()-1;
        for (int i = 1; i <= variabel; i++){
            System.out.print("x[" + i + "] = ");
            boolean tulisDulu = true;

            //Mengecek solusi banyak dari x-sekian
            for (int j = 1; j <= variabel; j++){
                if(solusi[i][j] != 0){
                    if(tulisDulu){
                        tulisDulu = false;
                        System.out.printf("(%.2f)T[%d]", solusi[i][j],j); 
                    } else System.out.printf(" + (%.2f)T[%d]", solusi[i][j],j);
                }
            }
            //Jika solusi di kolom ujung tidak 0
            if(solusi[i][variabel+1] != 0){
                if(tulisDulu) System.out.println(solusi[i][variabel+1]);    //Jika solusi x-sekian tunggal
                else System.out.printf(" + (%.2f)%n", solusi[i][variabel+1]); //Konstanta x-sekian banyak
            } else{
                if(tulisDulu) System.out.println((double)0); //Jika solusi x-sekian tunggal
                else System.out.println();
            }
        }
    }

}