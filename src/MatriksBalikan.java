import java.util.*;
import java.io.*;

public class MatriksBalikan extends Matriks{
    public Scanner in = new Scanner(System.in);


    public void OBEInv (Matriks M){
        int i, j;

        for ( i= 1 ; i <= M.GetBrs(); i++){
            for ( j = M.GetKol()/2 + 1 ; j <= M.GetKol(); j++){
                System.out.print( M.GetElmt(i,j) + " ");
            }
        System.out.println();
        }

    }
    public void GetKofaktor(Matriks M ,double temp[][], int p, int q) 
    { 
        int i = 1, j = 1; 
      
    
        for (int Brs = 1; Brs <= M.GetBrs(); Brs++) 
        { 
            for (int Kol = 1; Kol <= M.GetKol(); Kol++) 
            { 
               
                if ( Brs != p && Kol != q) 
                { 
                    temp[i][j++] = M.GetElmt(Brs,Kol);
                  
                    if (j == M.GetBrs() - 1) 
                    { 
                        j = 1; 
                        i++; 
                    } 
                } 
            } 
        } 
    } 
      
    public double determinant(Matriks M) 
    { 
        double D = 0; 
      
       
        if (M.GetBrs() == 1) 
            return M.GetElmt(1,1); 
      
        double [][] temp = new double [M.GetBrs()][M.GetKol()] ; 
      
        int sign = 1; 
      
       
        for (int f = 1; f <= M.GetBrs(); f++) 
        { 
            
            GetKofaktor(M, temp, 1, f); 
            Spl spl = new Spl();
            D += sign * M.GetElmt(1,f) * spl.determinanM(M); 
      
           
            sign = -sign; 
        } 
      
        return D; 
    } 
      
    
    public void adjoint(Matriks M,int [][]adj) 
    { 
        if (M.GetBrs() == 1) 
        { 
            adj[1][1] = 1; 
            return; 
        } 
      
        int sign = 1; 
        double [][] temp = new double [M.GetBrs()] [M.GetKol()]; 
      
        for (int i = 1; i < M.GetBrs(); i++) 
        { 
            for (int j = 1; j < M.GetKol(); j++) 
            { 
                
                GetKofaktor(M, temp, i, j); 
      
                
                sign = ((i + j) % 2 == 0)? 1: -1; 
      
                adj[j][i] = (sign)* (int)(determinant(M)); 
            } 
        } 
    } 
      
    
    public boolean inverse(Matriks M, float [][]inverse) 
    { 
        
        double det = determinant(M); 
        if (det == 0) 
        { 
            System.out.print("matriks singular , tidak dapat di invers"); 
            return false; 
        } 
      
       
        int [][]adj = new int[M.GetBrs()][M.GetKol()]; 
        adjoint(M, adj); 
      
         
        for (int i = 1; i < M.GetBrs(); i++) 
            for (int j = 1; j < M.GetKol(); j++) 
                inverse[i][j] = adj[i][j]/(float)det; 
      
        return true; 
    } 
      
    
      
    public void display(int A[][], Matriks M) 
    { 
        for (int i = 1; i < M.GetBrs(); i++) 
        { 
            for (int j = 1; j < M.GetKol(); j++) 
                System.out.print(A[i][j]+ " "); 
            System.out.println(); 
        } 
    } 
    public void display(float A[][], Matriks M) 
    { 
        for (int i = 1; i < M.GetBrs(); i++) 
        { 
            for (int j = 1; j < M.GetKol(); j++) 
                System.out.printf("%.2f ",A[i][j]); 
            System.out.println(); 
        } 
    } 
}
