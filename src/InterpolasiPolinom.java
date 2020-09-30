import java.lang.Math;

public class InterpolasiPolinom extends Matriks{
    private int derajat; //derajat polinom
    private double nilaidicari; //x yang dicari y-nya
    private double [] solusi;

    public void setderajat(int x){
        this.derajat = x;
    }
    
    public void setnilaidicari(float x){
        this.nilaidicari = x;
    }

    public int getderajat(){
        return this.derajat;
    }

    public double getnilaidicari(){
        return this.nilaidicari;
    }

    //KONSTRUKTOR
    public InterpolasiPolinom(int d, double x){
        derajat = d;
        nilaidicari = x;
        Matriks = new double[derajat+1][derajat+2];
        solusi = new double[derajat];
    }

    public void MatriksKordinatToPolinom(Matriks M){
        /*Mengubah Matriks kordinat ke matriks polinom */
        int i,j,k;
        double [] [] temporary;
        temporary = new double[this.derajat+1][this.derajat+2];
        k = 0;

        for (i = 0; i < M.brs; i++)
        {
            for (j = 0; j < M.kol; j++)
            {
                if (j == 0)
                {
                  for (k = 0; k < derajat+1; k++)
                  {
                    temporary[i][k] = Math.pow(M.GetElmt(i, j),k);
                  }
                } else if (j == 1)
                {
                    temporary[i][derajat+1] = M.GetElmt(i, j);
                }
            }
        }

        M.SetBrs(this.derajat+1);
        M.SetKol(this.derajat+2);
        for (i = 0; i < M.GetBrs(); i++){
            for (j = 0; j < M.GetKol(); j++){
                M.SetElmt(i, j, temporary[i][j]);
            }
        }
    }

    public void PolinomSolusi(Matriks M){
        int i,j;
        solusi = new double [this.derajat+1];

        for (i = 0 ; i <= M.GetBrs()-1; i++){
            solusi[i] = M.GetElmt(i, M.GetKol()-1);
        }
    }

    public int SolusiInterpolasi(float x){
        int i,y;

        y = 0;
        for (i = 0; i < derajat+1; i++){
            y += Math.pow(x,i)*solusi[i];
        }

        return y;

    }
}