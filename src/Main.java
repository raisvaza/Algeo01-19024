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
            // else if (pilihan == 2) det();
            // else if (pilihan == 3) inv();
            // else if (pilihan == 4) interPol();
            // else if (pilihan == 5) reg();
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
                                        
                //Menuliskan solusi
                spl.solusiEliminasiGauss(M, solusi, jumlahSolusi);
                if(jumlahSolusi[0] == 0) System.out.println("Tidak ada solusi"); //Tidak ada solusi
                else {
                    System.out.println("Solusi Sistem Persamaan: ");
                    spl.tulisSolusi(solusi, M);
                }
                System.out.println();

                //Kembali ke menu utama
                System.out.println("Menuju menu utama....");
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

                //Menuliskan solusi
                spl.solusiEliminasiGaussJordan(M, solusi, jumlahSolusi);
                if(jumlahSolusi[0] == 0){
                    System.out.println("Tidak ada solusi"); //Tidak ada solusi
                } else {
                    System.out.println("Solusi Sistem Persamaan: ");
                    spl.tulisSolusi(solusi, M);
                }
                System.out.println();

                //Kembali ke menu utama
                System.out.println("Menuju menu utama....");
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
                    }else System.out.println("\nMetode Matriks Balikan tidak dapat menyelesaikan persamaan");
                }else System.out.println("\nMetode Matriks Balikan tidak dapat menyelesaikan persamaan");

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
                int jmlhTkrBrs = 0;
                M.segitigaBawah(jmlhTkrBrs);
                double detM = M.kaliDiagonal() * spl.pangkat(-1,jmlhTkrBrs);


                if (M.GetBrs() == M.GetKol()){
                    if ((detM*10)%10 == 0)  System.out.printf("det(M) = %.0f\n", detM);
                    else System.out.printf("det(M) = %.2f\n", detM);
    
                    //Menghitung Determinan D(i)
                    for(int j=1; j<=M.GetKol(); j++){
                        Mtemp.salinMatriks(M);
                        spl.ubahKol(M,konstanta,j);
                        int jumlahTkrBrs = 0;
                        M.segitigaBawah(jumlahTkrBrs);
                        detMi[j] = M.kaliDiagonal() * spl.pangkat(-1,jumlahTkrBrs);
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
                    }else System.out.println("\nKaidah Cramer tidak dapat menyelesaikan persamaan");
                } else System.out.println("\nKaidah Cramer tidak dapat menyelesaikan persamaan");

                System.out.println();
                System.out.println("Menuju menu utama....");
                utama();

            }else{
                System.out.print("Masukan salah. Silakan ulangi masukan! ");
                pilihan = in.nextInt();
                System.out.println();
            }
        }
    }

    static void interPol(){
               
    }
}