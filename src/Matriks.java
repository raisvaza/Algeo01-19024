class Matriks {
    //Mengalokasikan matriks dengan ukuran 10x10
    int [] [] Matriks = new int[10][10];

    Matriks(){ // Konstruktor
        int i, j;
        for (i=1; i<10; i++){
            for(j=1; j<10; j++){
                this.Matriks [i][j] = 0;
            }
        }
    }

    //Prosedur isi matriks (masih contoh)
    void isimatriks(int N, int M){
        int i,j;

        for (i=0; i<N; i++){
            for (j=0; j<M; j++){
                this.Matriks[i][j] = i+j;
            }
        }
    }

    //Prosedur tulis matriks
    void tulismatriks(int N, int M){
        int i,j;

        for (i=0; i<N; i++){
            for (j=0; j<M; j++){
                System.out.print(this.Matriks[i][j]);
            }
            System.out.println();
        }
    }
}