package Supermarket;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by sergi on 22/11/15.
 */
public class SuperMarketController {

    public static int numeroClientes = 0;   // Numero de clientes de nuestro supermercado
    private static int numeroCajas = 0;     // Numero de cajas
    private static double tiempoMedio = 0;  // Tiempo medio de compra de cada cliente
    public static Caja caja;                // Objeto cajas de nuestro supermercado

    public static void main(String[] args) throws InterruptedException {

        Scanner teclat = new Scanner(System.in);   // Creamos un teclado

        System.out.println("Entra numero de clientes:");
            numeroClientes = teclat.nextInt(); // Pedimos el numero de clientes por teclado

        System.out.println("Entra numero de cajas:");
            numeroCajas = teclat.nextInt(); // Pedimos el numero de cajas por teclado

        teclat.close(); // Cerramos el teclado

        caja = new Caja(numeroCajas);  // Creamos un objeto cajas

        Random randomNumber = new Random(); // Usamos un random para asignar el cliente a nuestras cajas

        for(int iterador = 0; iterador < numeroClientes; iterador++){
            String nombreCliente = "Cliente ".concat(String.valueOf(iterador));    // Le da nombre al cliente con la misma posición del iterador
            Cliente clientes = new Cliente(nombreCliente, randomNumber.nextInt(numeroCajas), caja);   // Le asigna una caja usando el random
            Thread hilo = new Thread(clientes); // Creamos el hilo
            hilo.start();  // Y lo empezamos
        }
        
        Thread.sleep(3000);	// Esperamos unos segundos hasta que acaben todas las operaciones
        
        System.out.println("-----------------------------------------------------------------------------");
        
        System.out.println("La caja de hoy ha sido: " + SuperMarketController.getCaja().getGanancias());
        System.out.println("El tiempo medio de espera por cliente ha sido: " + tiempoMedio / numeroClientes);
        
        System.out.println("-----------------------------------------------------------------------------");
    }

    // Getters

    public static int getNumeroClientes() {
        return numeroClientes;
    }

    public static int getNumeroCajas() {
        return numeroCajas;
    }

    public static double getTiempoMedio() {
        return tiempoMedio;
    }

    public static Caja getCaja() {
        return caja;
    }
    
    
    // Setters
    
    public static void setTiempoMedio(double tiempo){
    	tiempoMedio = tiempoMedio + tiempo;
    }
}