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
                        if((solusi[i][j]*10)%10 == 0) System.out.printf("(%.0f)T[%d]", solusi[i][j],j); 
                        else System.out.printf("(%.2f)T[%d]", solusi[i][j],j); 
                    } else {
                        if ((solusi[i][j]*10)%10 == 0) System.out.printf(" + (%.0f)T[%d]", solusi[i][j],j);
                        else System.out.printf(" + (%.2f)T[%d]", solusi[i][j],j);
                    }
                }
            }
            //Jika solusi di kolom ujung tidak 0
            if(solusi[i][variabel+1] != 0){
                if(tulisDulu){ //Jika solusi x-sekian tunggal
                    if ((solusi[i][variabel+1]*10)%10 == 0) System.out.printf("%.0f\n",solusi[i][variabel+1]);
                    else System.out.printf("%.2f\n",solusi[i][variabel+1]);
                }   
                else { //Konstanta x-sekian banyak
                    if ((solusi[i][variabel+1]*10)%10 == 0) System.out.printf(" + (%.0f)%n", solusi[i][variabel+1]);
                    else System.out.printf(" + (%.2f)%n", solusi[i][variabel+1]);
                }
            } else{
                if(tulisDulu) System.out.println((double)0); //Jika solusi x-sekian tunggal
                else System.out.println();
            }
        }
    }

    //Membaca koefisien dari x-sekian melalui file
    public void bacaKoefFile(File inputfile, Matriks M, double konstanta[]){
        int brs = 0, kol = 0;

        try{
            Scanner in = new Scanner(inputfile);
            while(in.hasNextLine()){
                String baris = in.nextLine();
                brs++;

                Scanner inLine = new Scanner(baris);
                while(inLine.hasNextDouble() && brs == 1){
                    inLine.nextDouble();
                    kol++;
                }
            }

            in.close();
            M.SetBrs(brs);
            M.SetKol(kol-1);

            in = new Scanner(inputfile);
            for(int i = 1; i <= brs; i++){
                for(int j = 1; j <= kol; j++) {
                    if(j == kol){
                        konstanta[i] = in.nextDouble();
                    } else M.SetElmt(i,j,in.nextDouble());
                }
            }
        } 
        catch (FileNotFoundException ex){
            System.out.println("File tidak ditemukan");
        }
    }

    //Membaca koefisien x-sekian melalui keyboard
    public void bacaKoefKeyboard(Matriks M, double[] konstanta, int n, int m){
        for(int i=1; i<= n; i++){
            System.out.println();
            System.out.println("Masukkan persamaan ke-" + i + " :");
            for (int j = 1; j<= m; j++){
                if (j == m) {
                    System.out.print("Masukkan konstanta: ");
                    double x = in.nextDouble();
                    konstanta[i] = x;
                }else {
                    System.out.print("Masukkan koefisien x[" + j + "]: ");
                    double x = in.nextDouble();
                    M.SetElmt(i,j,x);
                }
            }
        }
    }

    //Ubah Matriks untuk SPL menggunakan Kaidah Cramer
    public void ubahKol(Matriks M, double konstanta [], int j ){
        for(int i = 1; i<= M.GetBrs(); i++){
            M.SetElmt(i,j, konstanta[i]);
        }
    }

    //Perpangkatan bilangan bulat dengan hasil tipe double
    public double pangkat(int x, int y){
        double result = 1;
        for(int i=1; i<=y; i++){
            result *= x;
        }
        return result;
    }

    //Kali Matriks koefisien x-sekian dengan matriks konstanta
    public void kaliMatriksKonstanta(Matriks M1, double[] M2, Matriks Mhasil){
        for(int i = 1; i<=M1.GetBrs(); i++){
            for(int j = 1; j<=1; j++){
                for(int k = (M1.GetKol()/2)+1; k<=M1.GetKol(); k++){
                    Mhasil.SetElmt(i,j,Mhasil.GetElmt(i,j)+M1.GetElmt(i,k)*M2[k-(M1.GetKol()/2)]);
                }
            }
        }
    }

    //Menuliskan hasil dari siste persamaan dengan metode matriks balikan
    public void tulisHasilSPLIvnvers(Matriks M){
        System.out.println("Solusi Sistem Persamaan: ");
        for(int i = 1; i<=M.GetBrs(); i++){
            if((M.GetElmt(i,1)*10)%10 == 0) System.out.printf("x[%d] = %.0f\n", i,M.GetElmt(i,1));
            else System.out.printf("x[%d] = %.2f\n", i,M.GetElmt(i,1));
        }
    }

    //Mengecek apakah matriks yang telah dilakukan OBE dapat memberikan solusi tunggal dalam metode matriks balikan
    public boolean cekDiagonalInvers(Matriks M){
        boolean cek = true;
        for(int i = 1; i<=M.GetBrs(); i++){
            for (int j = 1; j<=M.GetKol(); j++){
                if(i==j){
                    if(M.GetElmt(i,j) != 1) cek = false;
                }
            }
        }
        return cek;
    }

    //Menulis output file untuk SPL Eliminasi Gauss
    public void tulisFileElimGauss(String namafile, double[][] solusi, Matriks M){
        try{
            Formatter file = new Formatter(namafile);
            file.format("Matriks Eselon Baris: %n");
            for(int i=1; i<= M.GetBrs(); i++){
                for (int j=1; j<= M.GetKol(); j++){
                    if((M.GetElmt(i,j)*10)%10 == 0) file.format("%.0f ", M.GetElmt(i,j));
                    else file.format("%.2f ", M.GetElmt(i,j));
                }
                file.format("%n");
            }
            file.format("%nSolusi dari sistem persamaan adalah: %n");
            int variabel = M.GetKol()-1;
            for (int i = 1; i <= variabel; i++){
                file.format("x[" + i + "] = ");
                boolean tulisDulu = true;
    
                //Mengecek solusi banyak dari x-sekian
                for (int j = 1; j <= variabel; j++){
                    if(solusi[i][j] != 0){
                        if(tulisDulu){
                            tulisDulu = false;
                            if((solusi[i][j]*10)%10 == 0) file.format("(%.0f)T[%d]", solusi[i][j],j); 
                            else file.format("(%.2f)T[%d]", solusi[i][j],j); 
                        } else {
                            if ((solusi[i][j]*10)%10 == 0) file.format(" + (%.0f)T[%d]", solusi[i][j],j);
                            else file.format(" + (%.2f)T[%d]", solusi[i][j],j);
                        }
                    }
                }
                //Jika solusi di kolom ujung tidak 0
                if(solusi[i][variabel+1] != 0){
                    if(tulisDulu){ //Jika solusi x-sekian tunggal
                        if ((solusi[i][variabel+1]*10)%10 == 0) file.format("%.0f%n",solusi[i][variabel+1]);
                        else file.format("%.2f%n",solusi[i][variabel+1]);
                    }   
                    else { //Konstanta x-sekian banyak
                        if ((solusi[i][variabel+1]*10)%10 == 0) file.format(" + (%.0f)%n", solusi[i][variabel+1]);
                        else file.format(" + (%.2f)%n", solusi[i][variabel+1]);
                    }
                } else{
                    if(tulisDulu) file.format("0.0 %n"); //Jika solusi x-sekian tunggal
                    else file.format("%n");
                }
            }
            file.close();
        } catch(FileNotFoundException ex){
            System.out.println("Error terjadi");
        }
    }

    //Menuliskan output file untuk SPL Eliminasi Gauss-Jordan
    public void tulisFileElimGaussJordan(String namafile, double[][] solusi, Matriks M){
        try{
        Formatter file = new Formatter(namafile);
        file.format("Matriks Eselon Baris Tereduksi: %n");
        for(int i=1; i<= M.GetBrs(); i++){
            for (int j=1; j<= M.GetKol(); j++){
                if((M.GetElmt(i,j)*10)%10 == 0) file.format("%.0f ", M.GetElmt(i,j));
                else file.format("%.2f ", M.GetElmt(i,j));
            }
            file.format("%n");
        }
        file.format("%nSolusi dari sistem persamaan adalah: %n");
        int variabel = M.GetKol()-1;
        for (int i = 1; i <= variabel; i++){
            file.format("x[" + i + "] = ");
            boolean tulisDulu = true;

            //Mengecek solusi banyak dari x-sekian
            for (int j = 1; j <= variabel; j++){
                if(solusi[i][j] != 0){
                    if(tulisDulu){
                        tulisDulu = false;
                        if((solusi[i][j]*10)%10 == 0) file.format("(%.0f)T[%d]", solusi[i][j],j); 
                        else file.format("(%.2f)T[%d]", solusi[i][j],j); 
                    } else {
                        if ((solusi[i][j]*10)%10 == 0) file.format(" + (%.0f)T[%d]", solusi[i][j],j);
                        else file.format(" + (%.2f)T[%d]", solusi[i][j],j);
                    }
                }
            }
            //Jika solusi di kolom ujung tidak 0
            if(solusi[i][variabel+1] != 0){
                if(tulisDulu){ //Jika solusi x-sekian tunggal
                    if ((solusi[i][variabel+1]*10)%10 == 0) file.format("%.0f\n",solusi[i][variabel+1]);
                    else file.format("%.2f%n",solusi[i][variabel+1]);
                }   
                else { //Konstanta x-sekian banyak
                    if ((solusi[i][variabel+1]*10)%10 == 0) file.format(" + (%.0f)%n", solusi[i][variabel+1]);
                    else file.format(" + (%.2f)%n", solusi[i][variabel+1]);
                }
            } else{
                if(tulisDulu) file.format("0.0"); //Jika solusi x-sekian tunggal
                else file.format("%n");
                }
            }
            file.close();
        } catch(FileNotFoundException ex){
            System.out.println("Error terjadi");
        }
    } 

    //Menuliskan output file untuk SPL Metode Matriks Balikan
    public void tulisFileSPLInvers(String namafile, Matriks Mhasil, Matriks Mobe){
        try{
            Formatter file = new Formatter(namafile);
            file.format("Matriks hasil operasi baris elementer: %n");
            for(int i=1; i<= Mobe.GetBrs(); i++){
                for (int j=1; j<= Mobe.GetKol(); j++){
                    if((Mobe.GetElmt(i,j)*10)%10 == 0) file.format("%.0f ", Mobe.GetElmt(i,j));
                    else file.format("%.2f ", Mobe.GetElmt(i,j));
                }
                file.format("%n");
            }
            file.format("%nSolusi Sistem Persamaan: ");
            file.format("%n");
            for(int i = 1; i<=Mhasil.GetBrs(); i++){
                if((Mhasil.GetElmt(i,1)*10)%10 == 0) file.format("x[%d] = %.0f%n", i,Mhasil.GetElmt(i,1));
                else file.format("x[%d] = %.2f%n", i,Mhasil.GetElmt(i,1));
            }
            file.close();
        } catch(FileNotFoundException ex){
            System.out.println("Error terjadi");
        }

    }

    public void tulisFileSPLCramer(String namafile, double detM, double[] detMi,Matriks M){
        try{
            Formatter file = new Formatter(namafile);
            if ((detM*10)%10 == 0)  file.format("det(M) = %.0f%n", detM);
            else file.format("det(M) = %.2f%n", detM);
            for(int j=1; j<=M.GetKol(); j++){
                if((detMi[j]*10)%10 == 0) file.format("det(M%d) = %.0f%n", j, detMi[j]);
                else file.format("det(M%d) = %.2f%n", j, detMi[j]);
            }
            file.format("%nSolusi Sistem Persamaan: %n");
            for(int j=1; j<=M.GetKol(); j++){
                if(((detMi[j]/detM)*10)%10 == 0) file.format("x[%d] = %.0f%n", j, (detMi[j]/detM)); 
                else file.format("x[%d] = %.2f%n", j, (detMi[j]/detM));
            }
            file.close();
        } catch(FileNotFoundException ex){
            System.out.println("Error terjadi");
        }
    }
}