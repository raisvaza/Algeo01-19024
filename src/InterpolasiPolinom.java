import java.lang.Math;

public class InterpolasiPolinom extends Matriks{
    private int derajat; //derajat polinom
    private double [] solusi;

    public void setderajat(int x){
        this.derajat = x;
    }

    public int getderajat(){
        return this.derajat;
    }

    //KONSTRUKTOR
    public InterpolasiPolinom(int d, double x){
        derajat = d;
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

    public void PolinomSolusi(Matriks MGaussJordan){
        int i;
        solusi = new double [this.derajat+1];

        for (i = 0 ; i <= MGaussJordan.GetBrs()-1; i++){
            solusi[i] = MGaussJordan.GetElmt(i, MGaussJordan.GetKol()-1);
        }
    }

    public double SolusiInterpolasi(float x){
        int i;
        double y;

        y = 0;
        for (i = 0; i < derajat+1; i++){
            y += Math.pow(x,i)*solusi[i];
        }

        return y;

    }
}