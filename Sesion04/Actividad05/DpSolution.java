package Sesion04.Actividad05;

public class DpSolution {
    public static int getValue(int[]values,int rodLength) {
        int[]subSolutions=new int[rodLength+1];

        for(int i=1;i<=rodLength;i++) {
            int tmpMax=-1;
            for(int j=0;j<i;j++)
                tmpMax=Math.max(tmpMax,values[j]+subSolutions[i-j-1]);
            subSolutions[i]=tmpMax;
        }
        return subSolutions[rodLength];
    }
    public static void main(String[]args) {
        int[] values = new int[]{
            3, 7, 1, 3, 9
        } ;
        int rodLength = values.length;
        System.out.println("El valor maximo es: " + getValue(values, rodLength));
    }
}
