import java.util.*;
import java.lang.Math;

public class InterpolasiPolinom extends Matriks{
    public int derajat; //derajat polinom
    public float nilaidicari; //x yang dicari y-nya
    
    public void setderajat(int x){
        this.derajat = x;
    }
    
    public void setnilaidicari(float x){
        this.nilaidicari = x;
    }

    public int getderajat(){
        return this.derajat;
    }

    public float getnilaidicari(){
        return this.nilaidicari;
    }

    public void MatriksKordinatToPolinom(){
        int i,j,k;
        double [] [] temporary;
        temporary = new double[this.derajat+1][this.derajat+2];

        for (i = 0; i < this.brs; i++){
            for (j = 0; j < this.kol; j++){
                if (j != 1){
                  for (k = 0; k < derajat+1; k++){
                    temporary[i][k] = Math.pow(this.Matriks[i][j],k);
                  }
                } else {
                    temporary[i][k] = this.Matriks[i][1];
                }
            }
        }

        this.SetBrs(this.derajat+1);
        this.SetKol(this.derajat+2);
        for (i = 0; i <= this.GetBrs(); i++){
            for (j = 0; j <= this.GetKol(); j++){
                this.Matriks[i][j] = temporary[i][j];
            }
        }
    }
}