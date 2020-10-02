import java.util.*;
import java.io.*;


class Main {
    //Scanner
    public static Scanner in = new Scanner(System.in); 

    //Method main
    public static void main (String[] args){
        utama();    
    }

    //Prosedur program utama
    static void utama(){
        System.out.println();
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linier Berganda");
        System.out.println("6. Keluar");
        System.out.println();
        System.out.print("Masukkan pilihan: ");
        int pilihan = in.nextInt();

        while(true){
            if (pilihan == 1) spl();
            else if (pilihan == 2) det();
            else if (pilihan == 3) inv();
            else if (pilihan == 4) Interpol();
            else if (pilihan == 5) RegresiLin();
            else if(pilihan == 6) System.exit(0);
            else{
                System.out.println("Masukan salah. Silakan masukkan ulang!");
                pilihan = in.nextInt();
            }
        }
    }

    //Prosedur menu Sistem Persamaan Linier
    static void spl(){
        System.out.println();
        System.out.println("=== Sistem Persamaan Linier ===");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.print("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        System.out.println();

        while(true){
            if(pilihan == 99) utama();
            else if(pilihan == 1){
                System.out.println("=== Metode Eliminasi Gauss ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Spl
                Spl spl = new Spl();
                //Membuat objek Matriks
                Matriks M = new Matriks();
               
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan jumlah persamaan: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan jumlah peubah: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m+1);
    
                        //Membaca persamaan
                        spl.bacaMatriks(M, n, m+1);
                        System.out.println();
                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;

                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }

                //Mengubah matriks augmented ke Matriks eselon
                M.eliminasiGauss();
                System.out.println("Matriks Eselon Baris: ");
                
                //Tulis Matriks eselon
                M.tulisMatriks();
                System.out.println();
                                        
                //Variabel solusi
                double[][] solusi = new double[100][100];
                int[] jumlahSolusi = new int[1];
                Matriks Mmanip = new Matriks(M.GetBrs(),M.GetKol());
                M.salinMatriks(Mmanip);
                Mmanip.eliminasiGaussJordan();

                //Scanner output file
                Scanner filex = new Scanner(System.in);
                                        
                //Menuliskan solusi
                spl.solusiEliminasiGauss(M, solusi, jumlahSolusi);
                spl.solusiEliminasiGaussJordan(Mmanip, solusi, jumlahSolusi);

                if(jumlahSolusi[0] == 0) {
                    System.out.println("Tidak ada solusi"); //Tidak ada solusi
                    System.out.print("\nMasukkan nama file output: ");
                    String namafile = filex.nextLine();
                    try{
                        Formatter file = new Formatter(namafile);
                        file.format("Tidak ada solusi");
                        file.close();
                    } catch(FileNotFoundException ex){
                        System.out.println("Error terjadi");
                    }
                    
                }
                else {
                    System.out.println("Solusi Sistem Persamaan: ");
                    spl.tulisSolusi(solusi, M);					
                    System.out.print("\nMasukkan nama file output: ");
                    String namafile = filex.nextLine();
                    spl.tulisFileElimGauss(namafile, solusi, M);
                }

                //Kembali ke menu utama
                System.out.println("\nMenuju menu utama....");
                utama();

            }else if (pilihan == 2){
                System.out.println("=== Metode Eliminasi Gauss-Jordan ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Spl
                Spl spl = new Spl();
                //Membuat objek Matriks
                Matriks M = new Matriks();
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan jumlah persamaan: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan jumlah peubah: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m+1);
    
                        //Membaca persamaan
                        spl.bacaMatriks(M, n, m+1);
                        System.out.println();

                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;
                    }
                    else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }

                //Mengubah matriks augmented ke Matriks eselon tereduksi
                M.eliminasiGaussJordan();
                System.out.println("Matriks Eselon Baris Tereduksi: ");

                //Tulis Matriks eselon tereduksi
                M.tulisMatriks();
                System.out.println();
                
                //Variabel solusi
                double[][] solusi = new double[100][100];
                int[] jumlahSolusi = new int[1];

                //Scanner output file
                Scanner filex = new Scanner(System.in);

                //Menuliskan solusi
                spl.solusiEliminasiGaussJordan(M, solusi, jumlahSolusi);
                if(jumlahSolusi[0] == 0){
                    System.out.println("Tidak ada solusi"); //Tidak ada solusi
                    System.out.print("\nMasukkan nama file output: ");
                    String namafile = filex.nextLine();
                    try{
                        Formatter file = new Formatter(namafile);
                        file.format("Tidak ada solusi");
                        file.close();
                    }catch(FileNotFoundException ex){
                        System.out.println("Error terjadi");
                    }
                    
                } else {
                    System.out.println("Solusi Sistem Persamaan: ");
                    spl.tulisSolusi(solusi, M);					
                    System.out.print("\nMasukkan nama file output: ");
                    String namafile = filex.nextLine();
                    spl.tulisFileElimGaussJordan(namafile, solusi, M);
                }

                //Kembali ke menu utama
                System.out.println("\nMenuju menu utama....");
                utama();

            }else if (pilihan == 3){
                System.out.println("=== Metode Matriks Balikan ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Spl
                Spl spl = new Spl();
                //Membuat objek Matriks
                Matriks M = new Matriks();
                Matriks Mtemp = new Matriks();
                Matriks Mhasil = new Matriks();
                //Membuat array yang berisi konstanta persamaan
                double[] konstanta = new double[100];

                //Scanner output file
                Scanner filex = new Scanner(System.in);

                while(true){
                    if(masukan == 1){
                        //Membaca masukan dari keyboard
                        System.out.print("Masukkan jumlah persamaan: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan jumlah peubah: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
                        spl.bacaKoefKeyboard(M, konstanta, n, m+1);
                        break;
                    }else if (masukan == 2){
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        spl.bacaKoefFile(file, M, konstanta);
                        break;
                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt(); 
                    }
                }

                if(M.GetBrs() == M.GetKol()){
                    //Menerapkan eliminasi Gauss-Jordan untuk memindahkan matriks identitas ke kiri
                    Mtemp.SetBrs(M.GetBrs());
                    Mtemp.SetKol(2*M.GetKol());
                    M.salinMatriks(Mtemp);
                    M.isiIdentitas(Mtemp);
                    Mtemp.eliminasiGaussJordan();

                    if (spl.cekDiagonalInvers(Mtemp)){
                        System.out.println("\nMatriks hasil operasi baris elementer: ");
                        Mtemp.tulisMatriks();
                        System.out.println();
                        
                        //Menuliskan hasil sistem persamaan
                        Mhasil.SetBrs(Mtemp.GetBrs());
                        Mhasil.SetKol(1);
                        spl.kaliMatriksKonstanta(Mtemp,konstanta,Mhasil);
                        spl.tulisHasilSPLIvnvers(Mhasil);
                        System.out.print("\nMasukkan nama file output: ");
                        String namafile = filex.nextLine();
                        spl.tulisFileSPLInvers(namafile, Mhasil, Mtemp);
                    }else {
                        System.out.println("\nMetode Matriks Balikan tidak dapat menyelesaikan persamaan.");
                        System.out.print("\nMasukkan nama file output: ");
                        String namafile = filex.nextLine();
                        try{
                            Formatter file = new Formatter(namafile);
                            file.format("Metode Matriks Balikan tidak dapat menyelesaikan persamaan.");
                            file.close();
                        }catch(FileNotFoundException ex){
                            System.out.println("Error terjadi");
                        }  
                    } 
                }else {
                    System.out.println("\nMetode Matriks Balikan tidak dapat menyelesaikan persamaan.");
                    System.out.print("\nMasukkan nama file output: ");
                    String namafile = filex.nextLine();
                    try{
                        Formatter file = new Formatter(namafile);
                        file.format("Metode Matriks Balikan tidak dapat menyelesaikan persamaan.");
                        file.close();
                    } catch(FileNotFoundException ex){
                        System.out.println("Error terjadi");
                    }
                }

                //Kembali ke menu utama
                System.out.println("\nMenuju menu utama....");
                utama();

            }else if (pilihan == 4){
                System.out.println("=== Kaidah Cramer ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Spl
                Spl spl = new Spl();
                //Membuat objek Matriks
                Matriks M = new Matriks();
                Matriks Mtemp = new Matriks();
                //Membuat array yang berisi konstanta persamaan
                double[] konstanta = new double[100];

                //Scanner output file
                Scanner filex = new Scanner(System.in);

                while(true){
                    if(masukan == 1){
                        //Masukan dari keyboard
                        System.out.print("Masukkan jumlah persamaan: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan jumlah peubah: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
                        spl.bacaKoefKeyboard(M, konstanta, n, m+1);
                        System.out.println();
                        break;
                    }else if (masukan == 2){
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        spl.bacaKoefFile(file, M, konstanta);

                        System.out.println();
                        break;
                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt(); 
                    }
                }

                Mtemp.SetBrs(M.GetBrs());
                Mtemp.SetKol(M.GetKol());
                double[] detMi = new double[M.GetKol()+1];

                //Menghitung deteminan M
                M.salinMatriks(Mtemp);
                double detM = spl.determinanM(M);

                if (M.GetBrs() == M.GetKol()){
                    if ((detM*10)%10 == 0)  System.out.printf("det(M) = %.0f\n", detM);
                    else System.out.printf("det(M) = %.2f\n", detM);
    
                    //Menghitung Determinan D(i)
                    for(int j=1; j<=M.GetKol(); j++){
                        Mtemp.salinMatriks(M);
                        spl.ubahKol(M,konstanta,j);
                        detMi[j] = spl.determinanM(M);
                        if((detMi[j]*10)%10 == 0) System.out.printf("det(M%d) = %.0f\n", j, detMi[j]);
                        else System.out.printf("det(M%d) = %.2f\n", j, detMi[j]);
                    }

                    //Menuliskan solusi tunggal jika ada
                    if (detM != 0){
                        System.out.println("\nSolusi Sistem Persamaan: ");
                        for(int j=1; j<=M.GetKol(); j++){
                            if(((detMi[j]/detM)*10)%10 == 0) System.out.printf("x[%d] = %.0f\n", j, (detMi[j]/detM)); 
                            else System.out.printf("x[%d] = %.2f\n", j, (detMi[j]/detM));
                        }

                        System.out.print("\nMasukkan nama file output: ");
                        String namafile = filex.nextLine();

                        try{
                            Formatter file = new Formatter(namafile);
                            spl.tulisFileSPLCramer(namafile, detM, detMi, M);
                            file.close();
                        }catch(FileNotFoundException ex){
                            System.out.println("Error terjadi");
                        }

                    }else {
                        System.out.println("\nKaidah Cramer tidak dapat menyelesaikan persamaan");
                        System.out.print("\nMasukkan nama file output: ");
                        String namafile = filex.nextLine();
                        try{
                            Formatter file = new Formatter(namafile);
                            file.format("Kaidah Cramer tidak dapat menyelesaikan persamaan");
                            file.close();
                        }catch(FileNotFoundException ex){
                            System.out.println("Error terjadi");
                        }  
                    } 
                } else {
                    System.out.println("Kaidah Cramer tidak dapat menyelesaikan persamaan");
                    System.out.print("\nMasukkan nama file output: ");
                    String namafile = filex.nextLine();
                    try{
                        Formatter file = new Formatter(namafile);
                        file.format("Kaidah Cramer tidak dapat menyelesaikan persamaan");
                        file.close();
                    }catch(FileNotFoundException ex){
                        System.out.println("Error terjadi");
                    }  
                } 

                System.out.println("\nMenuju menu utama....");
                utama();

            }else{
                System.out.print("Masukan salah. Silakan ulangi masukan! ");
                pilihan = in.nextInt();
                System.out.println();
            }
        }
    }
    
        static void det(){
        System.out.println();
        System.out.println("=== Determinan ===");
        System.out.println("1. Metode Segitiga Atas");
        System.out.println("2. Metode Segitiga Bawah");
        System.out.println("3. Metode Ekspansi Kofaktor");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.print("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        System.out.println();

        while(true){
            if(pilihan == 99) utama();
            else if(pilihan == 1){
                System.out.println("=== Metode Segitiga Atas ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Spl
                Spl spl = new Spl();
                //Membuat objek Matriks
                Matriks M = new Matriks();
               
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan Banyaknya Baris: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan Banyaknya Kolom: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
    
                        //Membaca persamaan
                        M.isiMatriks(n, m);
                        System.out.println();
                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;

                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }
                int jmlhTkrBrs = 0;
                //Mengubah matriks augmented ke Matriks eselon
                M.segitigaAtas(jmlhTkrBrs);
                System.out.println("Matriks Segitiga Atas: ");
                
                //Tulis Matriks segitiga atas
                M.tulisMatriks();
                System.out.println();
                double detM = M.kaliDiagonal() * spl.pangkat(-1,jmlhTkrBrs);

                if (M.GetBrs() == M.GetKol()){
                    if ((detM*10)%10 == 0)  System.out.printf("det(M) = %.0f\n", detM);
                    else System.out.printf("det(M) = %.2f\n", detM);
                }
                System.out.println("\nMenuju menu utama....");
                utama();

            }else if(pilihan == 2){
                System.out.println("=== Metode Segitiga Bawah ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Spl
                Spl spl = new Spl();
                //Membuat objek Matriks
                Matriks M = new Matriks();
               
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan Banyaknya Baris: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan Banyaknya Kolom: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
    
                        //Membaca persamaan
                        M.isiMatriks(n, m);
                        System.out.println();
                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;

                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }
                int jmlhTkrBrs = 0;
                //Mengubah matriks augmented ke Matriks eselon
                M.segitigaBawah(jmlhTkrBrs);
                System.out.println("Matriks Segitiga Bawah: ");
                
                //Tulis Matriks segitiga atas
                M.tulisMatriks();
                System.out.println();
                double detM = M.kaliDiagonal() * spl.pangkat(-1,jmlhTkrBrs);

                if (M.GetBrs() == M.GetKol()){
                    if ((detM*10)%10 == 0)  System.out.printf("det(M) = %.0f\n", detM);
                    else System.out.printf("det(M) = %.2f\n", detM);
                }
                System.out.println("\nMenuju menu utama....");
                utama();

            }else if (pilihan == 3){
                System.out.println("=== Metode Ekspansi Kofaktor ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();

                //Membuat objek Matriks
                Matriks M = new Matriks();
               
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan Banyaknya Baris: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan Banyaknya Kolom: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
    
                        //Membaca persamaan
                        M.isiMatriks(n, m);
                        System.out.println();
                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;

                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }
                System.out.println("Hasil Ekpansi Kofaktor");

                Spl EK = new Spl();
                
                System.out.printf("det(M) = "+EK.determinanM(M));

                System.out.println("\nMenuju menu utama....");
                utama();

            }      
            else{
                System.out.print("Masukan salah. Silakan ulangi masukan! ");
                pilihan = in.nextInt();
                System.out.println();
            }       
        }
    }

    static void inv(){
        System.out.println();
        System.out.println("=== Matriks Balikan ===");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.print("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        System.out.println();

        while(true){
            if(pilihan == 99) utama();
            else if(pilihan == 1){
                System.out.println("=== Metod Eliminasi Gauss ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();
                //Membuat objek Matriks
                Matriks M = new Matriks();
               
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan Banyaknya Baris: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan Banyaknya Kolom: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
    
                        //Membaca persamaan
                        M.isiMatriks(n, m);
                        System.out.println();
                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;

                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }
                Matriks Mtemp = new Matriks();
                if(M.GetBrs() == M.GetKol()){
                    //Menerapkan eliminasi Gauss-Jordan untuk memindahkan matriks identitas ke kiri
                    Mtemp.SetBrs(M.GetBrs());
                    Mtemp.SetKol(2*M.GetKol());
                    M.salinMatriks(Mtemp);
                    M.isiIdentitas(Mtemp);
                    Mtemp.eliminasiGaussJordan();
                    System.out.println();

                    MatriksBalikan MB = new MatriksBalikan();
                    System.out.println("Matriks Hasil");
                    MB.OBEInv(Mtemp);
                }
                System.out.println("\nMenuju menu utama....");
                utama();
            
            }else if (pilihan==2){
                System.out.println("=== Metod Ekspansi Kofaktor ===");
                System.out.println("1. Masukan keyboard");
                System.out.println("2. Masukan file");
                System.out.print("Masukkan pilihan: ");
                int masukan = in.nextInt();
                System.out.println();


                //Membuat objek Matriks
                Matriks M = new Matriks();
               
                
                while(true){
                    if(masukan == 1){
                        //Meminta input jumlah persamaan dan jumlah peubah
                        System.out.print("Masukkan Banyaknya Baris: ");
                        int n = in.nextInt();
                        System.out.print("Masukkan Banyaknya Kolom: ");
                        int m = in.nextInt();
                        M.SetBrs(n);
                        M.SetKol(m);
    
                        //Membaca persamaan
                        M.isiMatriks(n, m);
                        System.out.println();
                        break;

                    } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                        String namaFile = in.nextLine();
                        File file  = new File(namaFile);

                        while(!file.exists()){
                            System.out.print("Masukkan nama file: ");
                            namaFile = in.nextLine();
                            file = new File(namaFile);
                        }

                        M.bacaFile(file);
                        System.out.println();
                        break;

                    }else{
                        System.out.print("Masukan salah. Silakan masukkan ulang! ");
                        masukan = in.nextInt();   
                    }
                }
                MatriksBalikan MInv = new MatriksBalikan();
                float [][]inv = new float[M.GetBrs()][M.GetBrs()]; 

                System.out.println("Matriks Balikan");
                if (MInv.inverse(M, inv)){
                    MInv.display(inv, M);
                }
                System.out.println("\nMenuju menu utama....");
                utama();
            }
            else{
                System.out.print("Masukan salah. Silakan ulangi masukan! ");
                pilihan = in.nextInt();
                System.out.println();
            }     

        }   

    }

    static void Interpol(){
        System.out.println("=== Interpolasi Polinom ===");
        System.out.println("1. Masukan keyboard");
        System.out.println("2. Masukan file");
        System.out.print("Masukkan pilihan: ");
        int masukan = in.nextInt();
        System.out.println();

        Matriks M = new Matriks();
             
        while(true){
            if(masukan == 1){
                //Meminta input jumlah persamaan dan jumlah peubah
                System.out.print("Masukkan jumlah titik diketahui: ");
                int n = in.nextInt();
                M.SetBrs(n);
                M.SetKol(2);
    
                //Membaca persamaan
                M.isiMatriks(n, 2);
                System.out.println();
                break;

            } else if (masukan == 2) {
                //Membaca matriks yang ada di file
                String namaFile = in.nextLine();
                File file  = new File(namaFile);

                while(!file.exists()){
                    System.out.print("Masukkan nama file: ");
                    namaFile = in.nextLine();
                    file = new File(namaFile);
                }

                M.bacaFile(file);
                System.out.println();
                break;

            } else {
                System.out.print("Masukan salah. Silakan masukkan ulang! ");
                masukan = in.nextInt();   
            }
        }

        int d = M.GetBrs()-1;
        float x = in.nextInt();
        InterpolasiPolinom InPol = new InterpolasiPolinom(d);
        M.tulisMatriks();
        InPol.MatriksKordinatToPolinom(M);
        M.tulisMatriks();
        System.out.println();
        M.eliminasiGaussJordan();
        M.tulisMatriks();
        System.out.println();
        InPol.PolinomSolusi(M);
        for (int i =1; i <= d+1; i++){
            System.out.println(InPol.solusi[i]);
        }
        double y = InPol.SolusiInterpolasi(x);
        System.out.println("y = " + y);
    }

    static void RegresiLin(){
        System.out.println("=== Regresi Linear ===");
        System.out.println("1. Masukan keyboard");
        System.out.println("2. Masukan file");
        System.out.print("Masukkan pilihan: ");
        int masukan = in.nextInt();
        System.out.println();

        Matriks M = new Matriks();
             
        while(true){
            if(masukan == 1){
                //Meminta input jumlah persamaan dan jumlah peubah
                System.out.print("Masukkan jumlah variabel: ");
                int var = in.nextInt();
                System.out.print("Masukkan jumlah sampel: ");
                int n = in.nextInt();
                
                M.SetBrs(n);
                M.SetKol(var+1);
    
                //Membaca persamaan
                M.isiMatriks(n, var+1);
                System.out.println();
                break;

            } else if (masukan == 2) {
                //Membaca matriks yang ada di file
                String namaFile = in.nextLine();
                File file  = new File(namaFile);

                while(!file.exists()){
                    System.out.print("Masukkan nama file: ");
                    namaFile = in.nextLine();
                    file = new File(namaFile);
                }

                M.bacaFile(file);
                System.out.println();
                break;

            } else {
                System.out.print("Masukan salah. Silakan masukkan ulang! ");
                masukan = in.nextInt();   
            }
        }
        int var = M.GetKol()-1;
        int n = M.GetBrs();
        M.tulisMatriks();
        RegresiLinier Linier = new RegresiLinier(n, var);
        Linier.SampelToRegresi(M);
        M.tulisMatriks();
        M.eliminasiGaussJordan();
        M.tulisMatriks();
        Linier.KoefisienSolusi(M);
        for (int i =1; i <= var+1; i++){
            System.out.println(Linier.solusi[i]);
        }
        double y = Linier.Solusi();
        System.out.println("y = " + y);
    }
}
