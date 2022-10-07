package EjerciciosUT01;

public class EjercicioMateo {

    public static void main(String[] args) {
        //Aqui creo un array con varios numeros
        int[] arrayDeNumeros = {5, 123, 432, 65, 1};
        //Declaro un numero muy bajo para que todos sean mas grandes que el
        int numeroMaximo = -99999999;
        //Declaro un numero muy alto para que todos sean mas bajos que el
        int numeroMinimo = 99999999;
        for (int i = 0; i < arrayDeNumeros.length; i++) {
            //En este if compruebo si el numero que tengo guardado en numeroMinimo
            //es mas pequeño que el arrayDeNumeros por el que voy
            if (arrayDeNumeros[i] < numeroMinimo) {
                //Si entra aqui quemo la variable numeroMinimo con el arrayDeNumeros 
                //por el que voy
                numeroMinimo = arrayDeNumeros[i];
            }
            //En este if es lo mismo pero con el maximo
            if (arrayDeNumeros[i] > numeroMaximo) {
                numeroMaximo = arrayDeNumeros[i];
            }
        }
        System.out.println("El numero maximo es " + numeroMaximo);
        System.out.println("El numero minimo es " + numeroMinimo);
    }
}
