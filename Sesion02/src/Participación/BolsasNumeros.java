package Participación;

import java.util.ArrayList;
import java.util.Iterator;

public class BolsasNumeros<T extends Comparable<T>>  {
    private ArrayList<T> bolsa = new ArrayList<>();

    public void agregar(T num) {
        bolsa.add(num);
    }
    public void mostrar(){
        for (T num : bolsa) {
            System.out.println(num);
        }
    }

    public void ordenamiento(){
        for (int i=0; i<bolsa.size()-1; i++){

            for (int j=0; j<bolsa.size()-1-i; j++){
                if(bolsa.get(i).compareTo(bolsa.get(j))>0) {
                    T temp = bolsa.get(i);
                    bolsa.set(i, bolsa.get(j + 1));
                    bolsa.set(j + 1, temp);
                }
            }
        }
    }


}
