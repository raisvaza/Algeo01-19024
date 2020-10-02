
public class RegresiLinier extends Matriks{
    public int NSampel;
    public int NVar;
    public double[] solusi;
    
    public RegresiLinier(int N, int var){
        this.NSampel = N;
        this.NVar = var;
        this.solusi = new double[var+2];
        this.Matriks = new double[N+1][var+2];
    }

    public void SampelToRegresi(Matriks M){
        int brs,kol,i,j,k,sum;
        double[][] temp;

        brs = M.GetBrs();
        kol = M.GetKol();
        temp = new double[kol+1][kol+2];

        //nilai matriks M pada indeks baris = 0 atau indeks kolom = 0 dibuat 1
        for (i = 0; i <= brs; i++){
            for (j = 0; j <= kol; j++){
                if (i == 0 || j == 0){
                    M.SetElmt(i, j, 1);
                }
            }
        }

        //Membuat matriks Regresi Linear
        for (i = 0; i <= kol; i++){
            for (j = 0; j <= kol+1; j++){
                if (i == 0 || j == 0){
                    temp[i][j] = 0;
                } else if (i == 1 && j == 1){
                    temp[i][j] = M.GetBrs();
                } else {
                    sum = 0;
                    for (k = 1; k <= brs; k++){
                        sum += M.GetElmt(k, i-1) * M.GetElmt(k, j-1);
                    }
                    temp[i][j] = sum;
                }
            }
        }

        M.SetBrs(kol);
        M.SetKol(kol+1);
        for (i = 0; i <= kol; i++){
            for (j = 0; j <= kol+1; j++){
                M.SetElmt(i, j, temp[i][j]);
            }
        }


    }

    public void KoefisienSolusi(Matriks MGaussJordan){
        int i;

        for (i = 1 ; i <= MGaussJordan.GetBrs(); i++){
            solusi[i] = MGaussJordan.GetElmt(i, MGaussJordan.GetKol());
        }
    }

    public double Solusi(){
        int i;
        double y;

        double[] dicari = new double[this.NVar+2];

        for (i = 1; i <= this.NVar+1; i++){
            if (i ==1){
                dicari[i] = 1;
            }
            else {
                dicari[i] = in.nextDouble();
            }
        }

        y = 0;
        for (i = 1; i <= this.NVar+1; i++){
            y += dicari[i]*solusi[i];
        }

        return y;
    }
}