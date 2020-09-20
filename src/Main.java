import java.util.*;

class Main {
    public static Scanner in = new Scanner(System.in); //Scanner

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
        System.out.println("Masukkan pilihan: ");
        int pilihan = in.nextInt();

        while(true){
            if (pilihan == 1){
                spl();
            } else if (pilihan == 2){
                det();
            } else if (pilihan == 3){
                inv();
            } else if (pilihan == 4){
                interPol();
            } else if (pilihan == 5){
                reg();
            } else if(pilihan == 6){
                System.exit(0);
            } else{
                System.out.println("Masukan salah. Silakan masukkan ulang!");
                pilihan = in.nextInt();
            }
        }


    }

    //Prosedur menu Sistem Persamaan Linier
    static void spl(){
        System.out.println();
        System.out.println("=== Sistem Persamaan Linier ===");
        System.out.println("1. Bakal Program");
        System.out.println(".......");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.println("Masukkan pilihan: ");
        int pilihan = in.nextInt();

        while(true){
            if(pilihan == 99){
                utama();
            }else if(pilihan == 1){
                Matriks spl = new Matriks();
                spl.isimatriks(3,3);
                spl.tulismatriks(3,3);
                System.out.println("Menuju menu utama....");
                utama();
            }else{
                System.out.println("Masukan salah. Silakan ulangi masukan!");
                pilihan = in.nextInt();
            }
        }
    }

    //Prosedur menu Determinan
    static void det(){
        System.out.println();
        System.out.println("=== Determinan ===");
        System.out.println("1. Bakal Program");
        System.out.println(".......");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.println("Masukkan pilihan: ");
        int pilihan = in.nextInt();

        while(true){
            if(pilihan == 99){
                utama();
            }else if(pilihan == 1){
                Matriks det = new Matriks();
                det.isimatriks(3,3);
                det.tulismatriks(3,3);
                System.out.println("Menuju menu utama....");
                utama();
            }else{
                System.out.println("Masukan salah. Silakan ulangi masukan!");
                pilihan = in.nextInt();
            }
        }

    }

    //Prosedur menu Matriks Balikan
    static void inv(){
        System.out.println();
        System.out.println("=== Matriks Balikan ===");
        System.out.println("1. Bakal Program");
        System.out.println(".......");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.println("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        
        while(true){
            if(pilihan == 99){
                utama();
            }else if(pilihan == 1){
                Matriks inv = new Matriks();
                inv.isimatriks(3,3);
                inv.tulismatriks(3,3);
                System.out.println("Menuju menu utama....");
                utama();
            }else{
                System.out.println("Masukan salah. Silakan ulangi masukan!");
                pilihan = in.nextInt();
            }
        }

    }

    //Prosedur Interpolasi Polinom
    static void interPol(){
        System.out.println();
        System.out.println("=== Interpolasi Polinom ===");
        System.out.println("1. Bakal Program");
        System.out.println(".......");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.println("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        
        while(true){
            if(pilihan == 99){
                utama();
            }else if(pilihan == 1){
                Matriks interPol = new Matriks();
                interPol.isimatriks(3,3);
                interPol.tulismatriks(3,3);
                System.out.println("Menuju menu utama....");
                utama();
            }else{
                System.out.println("Masukan salah. Silakan ulangi masukan!");
                pilihan = in.nextInt();
            }
        }

    }
    
    //Prosedur Regresi Linier Berganda
    static void reg(){
        System.out.println();
        System.out.println("=== Regresi Linier Berganda ===");
        System.out.println("1. Bakal Program");
        System.out.println(".......");
        System.out.println("99. Kembali");
        System.out.println();
        System.out.println("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        
        while(true){
            if(pilihan == 99){
                utama();
            }else if(pilihan == 1){
                Matriks reg = new Matriks();
                reg.isimatriks(3,3);
                reg.tulismatriks(3,3);
                System.out.println("Menuju menu utama....");
                utama();
            }else{
                System.out.println("Masukan salah. Silakan ulangi masukan!");
                pilihan = in.nextInt();
            }
        }

    }
}