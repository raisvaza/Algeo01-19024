import java.util.*;
import java.io.*;
import java.lang.*;


public class MatriksBalikan extends Matriks{
    public Scanner in = new Scanner(System.in);


    public void OBEInv (Matriks M){
        int i, j;

        for ( i= 1 ; i <= M.GetBrs(); i++){
            for ( j = M.GetKol()/2 + 1 ; i <= M.GetKol(); j++){
                System.out.print( M.GetElmt(i,j) + " ");
            }
        System.out.println();
        }

    }
    public void GetKofaktor(Matriks M ,double temp[][], int p, int q) 
    { 
        int i = 1, j = 1; 
      
        // Looping for each element of the matrix 
        for (int Brs = 1; Brs <= M.GetBrs(); Brs++) 
        { 
            for (int Kol = 1; Kol <= M.GetKol(); Kol++) 
            { 
                // Copying into temporary matrix only those element 
                // which are not in given row and column 
                if ( Brs != p && Kol != q) 
                { 
                    temp[i][j++] = M.GetElmt(Brs,Kol);
                    // Row is filled, so increase row index and 
                    // reset col index 
                    if (j == M.GetBrs() - 1) 
                    { 
                        j = 1; 
                        i++; 
                    } 
                } 
            } 
        } 
    } 
      
    /* Recursive function for finding determinant of matrix. 
    n is current dimension of A[][]. */
    public double determinant(Matriks M) 
    { 
        double D = 0; // Initialize result 
      
        // Base case : if matrix contains single element 
        if (M.GetBrs() == 1) 
            return M.GetElmt(1,1); 
      
        double [][] temp = new double [M.GetBrs()][M.GetKol()] ; // To store cofactors 
      
        int sign = 1; // To store sign multiplier 
      
        // Iterate for each element of first row 
        for (int f = 1; f <= M.GetBrs(); f++) 
        { 
            // Getting Cofactor of A[0][f] 
            GetKofaktor(M, temp, 1, f); 
            D += sign * M.GetElmt(1,f) * determinant(M); 
      
            // terms are to be added with alternate sign 
            sign = -sign; 
        } 
      
        return D; 
    } 
      
    // Function to get adjoint of A[N][N] in adj[N][N]. 
    public void adjoint(Matriks M,int [][]adj) 
    { 
        if (M.GetBrs() == 1) 
        { 
            adj[1][1] = 1; 
            return; 
        } 
      
        // temp is used to store cofactors of A[][] 
        int sign = 1; 
        double [][] temp = new double [M.GetBrs()] [M.GetKol()]; 
      
        for (int i = 1; i < M.GetBrs(); i++) 
        { 
            for (int j = 1; j < M.GetKol(); j++) 
            { 
                // Get cofactor of A[i][j] 
                GetKofaktor(M, temp, i, j); 
      
                // sign of adj[j][i] positive if sum of row 
                // and column indexes is even. 
                sign = ((i + j) % 2 == 0)? 1: -1; 
      
                // Interchanging rows and columns to get the 
                // transpose of the cofactor matrix 
                adj[j][i] = (sign)* (int)(determinant(M)); 
            } 
        } 
    } 
      
    // Function to calculate and store inverse, returns false if 
    // matrix is singular 
    public boolean inverse(Matriks M, float [][]inverse) 
    { 
        // Find determinant of A[][] 
        double det = determinant(M); 
        if (det == 0) 
        { 
            System.out.print("Singular matrix, can't find its inverse"); 
            return false; 
        } 
      
        // Find adjoint 
        int [][]adj = new int[M.GetBrs()][M.GetKol()]; 
        adjoint(M, adj); 
      
        // Find Inverse using formula "inverse(A) = adj(A)/det(A)" 
        for (int i = 1; i < M.GetBrs(); i++) 
            for (int j = 1; j < M.GetKol(); j++) 
                inverse[i][j] = adj[i][j]/(float)det; 
      
        return true; 
    } 
      
    // Generic function to display the matrix. We use it to display 
    // both adjoin and inverse. adjoin is integer matrix and inverse 
    // is a float. 
      
    public void display(int A[][], Matriks M) 
    { 
        for (int i = 1; i < M.GetBrs(); i++) 
        { 
            for (int j = 1; j < M.GetKol(); j++) 
                System.out.print(A[i][j]+ " "); 
            System.out.println(); 
        } 
    } 
    static void display(float A[][], Matriks M) 
    { 
        for (int i = 1; i < M.GetBrs(); i++) 
        { 
            for (int j = 1; j < M.GetKol(); j++) 
                System.out.printf("%.6f ",A[i][j]); 
            System.out.println(); 
        } 
    } 



}
