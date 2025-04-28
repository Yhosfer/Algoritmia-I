package Participación;

import java.util.ArrayList;
import java.util.Iterator;

public class BolaNumeros_v2 {
    ArrayList<Integer> bolsa =  new ArrayList<>();

    public void agregar(int num){
        bolsa.add(num);
    }
    public void ordenamiento(){
        for (int i=0; i<bolsa.size()-1; i++){

            for (int j=0; j<bolsa.size()-1-i; j++){
                if(bolsa.get(i) > bolsa.get(j+1)) {
                    int temp = bolsa.get(i);
                    bolsa.set(i, bolsa.get(j + 1));
                    bolsa.set(j + 1, temp);
                }
            }
        }
    }
    public void mostrar(){
        for (Integer contenido : bolsa) {
            System.out.println(contenido);
        }
    }
    public static void main(String[] args) {
        BolaNumeros_v2 bola = new BolaNumeros_v2();
        bola.agregar(5);
        bola.agregar(3);
        //bola.agregar(2);
        bola.ordenamiento();
        bola.mostrar();


    }

}
