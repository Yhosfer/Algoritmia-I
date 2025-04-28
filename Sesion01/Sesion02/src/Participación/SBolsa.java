package Participación;

import java.util.ArrayList;
import java.util.Iterator;

public class SBolsa {
    private ArrayList<BolsasNumeros> contenedor = new ArrayList<>();

    public void agregar(BolsasNumeros bolsa){
        contenedor.add(bolsa);
    }
    public void mostrar(){
        for (BolsasNumeros bol : contenedor) {
            bol.mostrar();
        }
    }

}
