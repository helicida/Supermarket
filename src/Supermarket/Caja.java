package Supermarket;

/**
 * Created by sergi on 22/11/15.
 */
public class Caja {

    private boolean[] arrayCaixes;  // Array que determina si la caja está ocupada o no.
    private int numeroCajas;        // Cantidad de cajas disponibles
    private double ganancias;       // Variable en la que se almacenan las ganancias de todas las cajas
    private int numeroVentas = 0;   // Aquí almacenamos cuantos clientes han pasado por la caja

    public Caja(int numCaixes) {    //Constructor

        this.arrayCaixes = new boolean[numCaixes]; // Le damos el tamaño que haya introducido el usuario a nuestro array

        for (int iterador = 0; iterador < numCaixes; iterador++) {  // Y creamos las cajas todas como paradas, ya que no tienen clientes
            arrayCaixes[iterador] = false;
        }

        this.numeroCajas = arrayCaixes.length;  // el numero de cajas será igual a lo largo que sea nuestro array
    }

    public synchronized void cobra(Cliente clientes) throws InterruptedException {

        if (arrayCaixes[clientes.getNumeroCajaCliente()] == false) {    // Si la caja no está en uso
            arrayCaixes[clientes.getNumeroCajaCliente()] = true;    // La ponemos como que está en uso
            System.out.println("    - Caja numero " + clientes.getNumeroCajaCliente() + ": Ha atendido al cliente " + clientes.getNombreCliente());    // Se imprime que lo ha atendido
            ganancias = clientes.getDineroGastado();  // Asignamos a la posición del array correspondiente el dinero que ha gastado el cliente
            numeroVentas++; // Incrementamos en uno el numero de ventas
            arrayCaixes[clientes.getNumeroCajaCliente()] = false;   // Se ha cobrado al cliente así que vuelve a estar disponible
        }
    }

    public boolean[] getArrayCaixes() {
        return arrayCaixes;
    }

    public double getGanancias (){
        return ganancias;
    }
}