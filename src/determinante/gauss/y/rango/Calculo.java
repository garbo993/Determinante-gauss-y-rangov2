package determinante.gauss.y.rango;

import java.util.ArrayList;
import javax.swing.JTextField;

public class Calculo {

    int c = 0;
    double matriz[][];
    String determinante;
    int rangue, tam;

    public double[][] CalcularDeterminante(int tamaño, ArrayList<JTextField> array, boolean rango) {
        Generarmatriz(tamaño, array);
        rangue = tamaño;
        tam = tamaño;
        Cambiar();
        hacerGauss(rango);
        return matriz;
    }

    private void Generarmatriz(int tamaño, ArrayList<JTextField> array) {
        matriz = new double[tamaño][tamaño];
        int index = 0;
        c = c + 2;
        for (int i = 0; i < tamaño; i++) {
            c = c + 2;
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = Integer.parseInt(array.get(index).getText());
                index++;
                c = c + 2;
            }
        }
    }

    private void hacerGauss(boolean rango) {
        //c=c+3;
        if (rango) {
            for (int i = 0; i < matriz.length; i++) {
                matriz[i] = hacerpivote(matriz[i], i);
                for (int j = 0; j < matriz.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    matriz[j] = restarnumero(matriz[j], matriz[i], i);
                    c = c + 6;
                }
            }
            Verificardiagonal();
        } else {
            for (int i = 0; i < matriz.length - 1; i++) {
                for (int j = i + 1; j < matriz.length; j++) {
                    matriz[j] = restarnumero(matriz[j], matriz[i], i);
                    imprimirmatriz();
                }

            }

            Verificardiagonal();
            determinante = "El determinante es: " + Double.toString(Determinante());
        }

    }

    private double[] restarnumero(double[] arreglo, double[] restar, int pos) {
        double factor = (arreglo[pos] * -1) / restar[pos];
        c = c + 8;//*
        if (restar[pos] == 0) {

        } else {
            for (int i = 0; i < arreglo.length; i++) {
                arreglo[i] += (factor * restar[i]);
                c = c + 7;
            }
        }
        return arreglo;
    }

    public double Determinante() {
        double det = 1;
        c = c + 3;
        for (int i = 0; i < matriz.length; i++) {
            det *= matriz[i][i];
            c = c + 6;
        }
        return det;
    }

    public int contador() {
        return c;
    }

    public int formula(int tamaño) {
        int n = tamaño;
        int form = 21 * n ^ 2 - 31 * n + ((32 * (n) ^ 3 - 159 * (n) ^ 2 + 223 * n - 96) / 6) + 10;

        return form;
    }

    private double[] hacerpivote(double[] arreglo, int pos) {

        double div = arreglo[pos];
        c = c + 4;
        if (arreglo[pos] == 0) {

        } else {
            for (int i = 0; i < arreglo.length; i++) {
                arreglo[i] /= div;
                c = c + 5;
            }
        }
        return arreglo;
    }

    private void Verificardiagonal() {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][i] == 0) {
                rangue--;
            }
        }

    }

    private void imprimirmatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public String getDeterminante() {

        if (rangue != tam) {
            determinante = "No es posible calcular el determinante";
        }

        return determinante;
    }

    private void Cambiar() {

        boolean cambiar = false;
        int indexx = 0;

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][i] == 0) {
                for (int j = 0; j < matriz.length; j++) {
                    if (matriz[i][j] == 0 && i < matriz.length-indexx) {
                        if(j ==matriz.length-1){
                            indexx++;
                            cambiar = true;
                        }else{
                            cambiar = false;
                        }
                    }
                }
            }
            double temp[];
            if (cambiar) {
                System.out.println("se va a vcambiar la fila "+i+" por la fila "+ (matriz.length-indexx));
                temp = matriz[i];
                matriz[i] = matriz[matriz.length-indexx];
                matriz[matriz.length-indexx] = temp;
                i=0;
                cambiar=false;
            }

        }
    }

}
