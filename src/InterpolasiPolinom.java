import java.lang.Math;

public class InterpolasiPolinom extends Matriks{
    public int derajat; //derajat polinom
    public double [] solusi;

    public void setderajat(int x){
        this.derajat = x;
    }

    public int getderajat(){
        return this.derajat;
    }

    //KONSTRUKTOR
    public InterpolasiPolinom(int d){
        derajat = d;
        Matriks = new double[derajat+1][derajat+2];
        solusi = new double[derajat+2];
    }

    public void MatriksKordinatToPolinom(Matriks M){
        /*Mengubah Matriks kordinat ke matriks polinom */
        int i,j,k;
        double [] [] temporary;
        temporary = new double[this.derajat+2][this.derajat+3];
        k = 1;

        for (i = 1; i <= M.brs; i++)
        {
            for (j = 1; j <= M.kol; j++)
            {
                if (j == 1)
                {
                  for (k = 1; k <= derajat+1; k++)
                  {
                    temporary[i][k] = Math.pow(M.GetElmt(i, j),k-1);
                  }
                } else if (j == 2)
                {
                    temporary[i][derajat+2] = M.GetElmt(i, j);
                }
            }
        }
        M.SetBrs(this.derajat+1);
        M.SetKol(this.derajat+2);
        for (i = 1; i <= M.GetBrs(); i++){
            for (j = 1; j <= M.GetKol(); j++){
                M.SetElmt(i, j, temporary[i][j]);
            }
        }
    }

    public void PolinomSolusi(Matriks MGaussJordan){
        int i;
        solusi = new double [this.derajat+2];

        for (i = 1 ; i <= MGaussJordan.GetBrs(); i++){
            solusi[i] = MGaussJordan.GetElmt(i, MGaussJordan.GetKol());
        }
    }

    public double SolusiInterpolasi(float x){
        int i;
        double y;

        y = 0;
        for (i = 1; i <= derajat+1; i++){
            y += Math.pow(x,i-1)*solusi[i];
        }

        return y;

    }
}