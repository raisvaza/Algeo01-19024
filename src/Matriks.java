import java.util.*;
import java.io.*;

class Matriks {
    public static Scanner in = new Scanner(System.in); //Scanner
    
    public double [] [] Matriks; //Matriks
    public int brs; //jumlah baris pada Matriks
    public int kol; //jumlah kolom pada Matriks

    /* KONSTRUKTOR */
    //Alokasi Matriks 100x100
    Matriks(){ 
        Matriks = new double[100][100]; 
    }

    //Inisialisasi Matriks n x m
    Matriks(int n, int m){
        this.SetBrs(n);
        this.SetKol(m);
        Matriks = new double[n+1][m+1]; 
    }

    /* GETTER */
    //Mengambil jumlah baris pada Matriks
    public int GetBrs(){ 
        return brs; 
    }

    //Mengambil jumlah kolom pada Matriks
    public int GetKol(){ 
        return kol;
    }

    //Mengambil isi dari Matriks[n][m]
    public double GetElmt(int n, int m){ 
        return Matriks[n][m];
    }

    /* SETTER */
    //Mengubah jumlah baris menjadi i
    public void SetBrs (int i){ 
        this.brs = i;
    }
    
    //Mengubah jumlah kolom menjadi j
    public void SetKol (int j){ 
        this.kol = j;
    }

    //Mengubah isi elemen Matriks[i][j]
    public void SetElmt (int i, int j, double x){ 
        this.Matriks[i][j] = x;
    }

    //Prosedur isi matriks dengan keyboard
    public void isiMatriks(int N, int M){
        int i,j;

        for (i=1; i<=N; i++){
            for (j=1; j<=M; j++){
                this.Matriks[i][j] = in.nextDouble();
            }
        }
    }

    public void bacaFile(File inputfile){
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
            this.SetBrs(brs);
            this.SetKol(kol);

            in = new Scanner(inputfile);
            for(int i = 1; i <= brs; i++){
                for(int j = 1; j <= kol; j++) SetElmt(i,j,in.nextDouble());
            }
        } 
        catch (FileNotFoundException ex){
            System.out.println("File tidak ditemukan");
        }
    }



    //Menuliskan isi matriks
    public void tulisMatriks(){
        for(int i=1; i<= this.GetBrs(); i++){
            for (int j=1; j<= this.GetKol(); j++){
                if((this.GetElmt(i,j)*10)%10 == 0) System.out.printf("%.0f ", this.GetElmt(i,j));
                else System.out.printf("%.2f ", this.GetElmt(i,j));
            }
            System.out.println();
        }
    }

    //Menukar baris i1 dan i2 pada Matriks
    public void tukarBrs(int i1, int i2){
        double temp;
        for (int j=1; j<= this.kol; j++){
            temp = this.GetElmt(i1,j);
            SetElmt(i1, j, this.GetElmt(i2,j));
            SetElmt(i2, j, temp);
        }
    }

    //Mengalikan elemen diagonal Matriks
    public double kaliDiagonal(){
        double sum = 1;
        for(int i=1; i<=this.GetBrs(); i++){
            for(int j=1; j<=this.GetKol(); j++){
                if(j==i) sum *= this.GetElmt(i,j);
            }
        }
        return sum;
    }

    //Prosedur salin Matriks
    public void salinMatriks(Matriks Msal){
        for(int i = 1; i <= this.GetBrs(); i++){
            for(int j = 1; j <= this.GetKol(); j++){
                Msal.SetElmt(i,j,this.GetElmt(i,j));
            }
        }
    }

    //Mengecek apakah sebuah baris pada Matriks berisi semua 0 
    public boolean isAllZero(int i){
        boolean allzero=true;
        for (int j=1; j<= this.kol; j++){
            if(this.GetElmt(i,j) != 0) allzero = false;
        }
        return allzero;
    }

    //Mengecek apakah sebuah baris pada Matriksberisi semua 0 kecuali kolom terakhir
    public boolean isKoefZero(int i){
        boolean koefZero = false;
        int jumlah = 0;
        if (!this.isAllZero(i)){
            for(int j = 1; j<= this.kol-1; j++){
                if(this.GetElmt(i,j) == 0) jumlah+=1;
            }
        }
        if (jumlah == this.kol-1) koefZero = true;
        return koefZero;
        
    }

    //Eliminasi Gauss menghasilkan matriks eselon baris
    public void eliminasiGauss(){
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
                double lead = this.GetElmt(brs, kol); //Pembagi untuk membuat 1 utama

                //Membagi seluruh kolom pada baris dengan pembuat 1 utama
                for(int j = kol; j <= this.GetKol(); j++){
                    this.SetElmt(brs, j, this.GetElmt(brs,j)/lead);
                }

                //Mengurangi seluruh kolom di bawah 1 utama pada baris dengan rasio baris lain
                for(int i = brs+1; i <= GetBrs(); i++){
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

    //Eliminasi Gauss-Jordan menghasilkan Matriks Eselon Baris Tereduksi
    public void eliminasiGaussJordan(){
        int brs = 1;
        int kol = 1;

        
        while(brs <= this.GetBrs() && kol <= this.GetKol()){
            int indeksMaks = -1;
            //Mencari baris yang tidak bernilai 0 dalam suatu kolom
            for(int i = brs; i <= this.GetBrs() && indeksMaks == -1; i++){
                if(this.GetElmt(i, kol) != 0) {
                    indeksMaks = i;
                }
            }

            //Jika tidak ada baris bernilai nol di sebuah kolom, lanjut ke kolom selanjutnya
            if(indeksMaks == -1) kol++;

            //Jika ada akan diproses
            else{
                this.tukarBrs(brs, indeksMaks); //Tukar Baris
                double lead = this.GetElmt(brs, kol);
                //Membagi seluruh kolom pada baris dengan pembuat 1 utama
                for(int j = kol; j <= this.GetKol(); j++){
                    this.SetElmt(brs, j, this.GetElmt(brs,j)/lead);
                }
                //Mengurangi seluruh kolom pada baris dengan rasio baris lain
                for(int i = 1; i <= this.GetBrs(); i++){
                    if(i != brs){
                        double rasio = (-1*this.GetElmt(i, kol)) / this.GetElmt(brs,kol);
                        for(int j = kol; j <= this.GetKol(); j++){
                            this.SetElmt(i,j,this.GetElmt(i,j) + rasio*this.GetElmt(brs,j));
                        }
                    }
                }

                //Lanjut ke baris dan pengecekan kolom selajuntnya
                brs++;
                kol++;
            }
        }
    }

    //Ubah ke Matriks Segitiga Atas
    public void segitigaAtas(int jmlTukarBrs){
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
                    if(brs != indeksMaks){
                        jmlTukarBrs = jmlTukarBrs + 1;   //Menghitung jumlah tukar baris
                        this.tukarBrs(brs,indeksMaks); //Tukar baris
                    } 
                    
                    //Mengurangi seluruh kolom di bawah 1 utama pada baris dengan rasio baris lain
                    for(int i = brs+1; i <= this.GetBrs(); i++){
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

    //Menambahkan matriks identitas pada matriks augmented
    public void isiIdentitas(Matriks M){
        for(int i = 1; i<=this.GetBrs(); i++){
            for(int j = this.GetKol()+1 ; j <= M.GetKol(); j++){
                if(j-i == this.GetKol()) M.SetElmt(i,j,1);
                else M.SetElmt(i,j,0);
            }
        }
    }
}