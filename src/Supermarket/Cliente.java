package Supermarket;

/**
 * Created by sergi on 22/11/15.
 */
public class Cliente implements Runnable{

    private String nombreCliente;   // Nombre del cliente (client 1, cliente 2...)
    private Caja cajaDeCliente;     // Caja en la que será atendido el cliente
    private int numeroCajaCliente;  // Numero de la caja en la que el cliente está siendo atendido
    boolean cajaOcupada = false;    // Booleano auxiliar
    double dineroGastado = 0;       // Dinero que ha gastado el cliente
    double tiempoMedio = 0;         // Tiempo del cliente que usaremos para calcular el tiempo medio
    private int numeroCliente = 0;

    public Cliente(String nombreCliente, int numeroCajaCliente, Caja cajaDeCliente){   //Constructor
        this.nombreCliente = nombreCliente;             // Asignamos el nombre
        this.numeroCajaCliente = numeroCajaCliente;     // Asignamos el numero de caja a la que ha de ir, que generamos con el random
        this.cajaDeCliente = cajaDeCliente;             // Y le asignamos el objeto caja que creamos antes
    }

    @Override
    public void run() {

        try {
            tiempoMedio = Math.random() * 10; // En esta variable generamos el tiempo de espera de manera aleatoria  que tambien aprovechamos para calcular la media

            Thread.sleep((long) tiempoMedio);   // Tiempo de compra del cliente aleatorio durante el cual paramos el hilo
            SuperMarketController.setTiempoMedio(tiempoMedio);  // Sumamos el tiempo de este cliente al tiempo total        
            dineroGastado = tiempoMedio * 200; // Generamos el dinero que se ha gastado el cliente de manera proporcional al tiempo de espera

            System.out.println("· El " + nombreCliente + " ha comprado y se dirige a la cola");   // Avisamos de que el cliente se dirige a la cola

            if(cajaDeCliente.getArrayCaixes()[numeroCajaCliente]){  // Si la cola está ocupada
                cajaOcupada = true;       // La marcamos como ocupada
                System.out.println( "· El " + nombreCliente + " está esperando a que avance la cola");  // Esperamos a que la cola avance para atender al cliente
            }

            cajaDeCliente.cobra(this);  // Le asignamos a la caja el cliente para que le cobre

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // Getters

    public int getNumeroCajaCliente() {
        return numeroCajaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getDineroGastado() {
        return dineroGastado;
    }

    public double getTiempoMedio(int numeroClientes) {
    	tiempoMedio = tiempoMedio / numeroClientes;
        return tiempoMedio;
    }
}