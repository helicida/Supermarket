package Supermarket;

/**
 * Created by sergi on 22/11/15.
 */
public class Caja {

    private boolean[] arrayCaixes;  // Array que determina si la caja est� ocupada o no.
    private int numeroCajas;        // Cantidad de cajas disponibles
    private double ganancias;       // Variable en la que se almacenan las ganancias de todas las cajas
    private int numeroVentas = 0;   // Aqu� almacenamos cuantos clientes han pasado por la caja

    public Caja(int numCaixes) {    //Constructor

        this.arrayCaixes = new boolean[numCaixes]; // Le damos el tama�o que haya introducido el usuario a nuestro array

        for (int iterador = 0; iterador < numCaixes; iterador++) {  // Y creamos las cajas todas como paradas, ya que no tienen clientes
            arrayCaixes[iterador] = false;
        }
        this.numeroCajas = arrayCaixes.length;  // El numero de cajas ser� igual a lo largo que sea nuestro array
    }

    public synchronized void cobra(Cliente clientes) throws InterruptedException {

        if (arrayCaixes[clientes.getNumeroCajaCliente()] == false) {    // Si la caja no est� en uso
            arrayCaixes[clientes.getNumeroCajaCliente()] = true;    // La ponemos como que est� en uso
            System.out.println("- Caja numero " + clientes.getNumeroCajaCliente() + ": Ha atendido al cliente " + clientes.getNombreCliente());    // Se imprime que lo ha atendido
            ganancias = clientes.getDineroGastado();  // Asignamos a la posici�n del array correspondiente el dinero que ha gastado el cliente
            numeroVentas++; // Incrementamos en uno el numero de ventas
            arrayCaixes[clientes.getNumeroCajaCliente()] = false;   // Se ha cobrado al cliente as� que vuelve a estar disponible
        }
    }

    public boolean[] getArrayCaixes() {
        return arrayCaixes;
    }

    public double getGanancias (){
        return ganancias;
    }
}