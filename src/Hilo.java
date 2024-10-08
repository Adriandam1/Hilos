import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hilo extends Thread {
    private final int nivel;
    private final int numeroHilo; //Nuevo atributo para almacenar el número del hilo
    private static final List<String> mensajesFinalizacion = new ArrayList<>(); // Lista para almacenar mensajes de finalización

    public Hilo(int nivel, int numeroHilo) {
        this.nivel = nivel; //Guardamos el nivel del hilo
        this.numeroHilo = numeroHilo; //Almacenamos el número del hilo
    }

    @Override
    public void run() {
        //entiendo que tendria que usar la funcion Random para que se manden los hilos de otra manera pero no encontre el modo

        // Crear hilo hijo si el nivel es menor que 5
        if (nivel < 5) {
            Hilo hiloHijo = new Hilo(nivel + 1, numeroHilo + 1);
            hiloHijo.start(); //Iniciar hilo hijo

            try {
                hiloHijo.join(); //Esperar a que el hilo hijo termine
            } catch (InterruptedException e) {
                System.err.println("El hilo " + numeroHilo + " fue interrumpido.");
            }
        }        //Procesamiento en el hilo
        procesar();

        //Registrar el mensaje de finalización
        //cada hilo agrega un mensaje como un bloque a la lista mensajesFinalizacion
        synchronized (mensajesFinalizacion) {
            mensajesFinalizacion.add("Acabó hilo " + numeroHilo);
        }
    }

    private void procesar() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            // Espera aleatoria entre 100 y 600 milisegundos
            // ponemos 500 + 100 para asegurar minimo de 100 y maximo de 600ms
            try {
                Thread.sleep(random.nextInt(501) + 100); // 100 a600 milisegundos
            } catch (InterruptedException e) {
             System.err.println("El hilo " + numeroHilo + " fue interrumpido.");

            }
            System.out.println("Soy el hilo " + numeroHilo + " interación: " + i);
        }
    }

    // sacamos la lista completa que se ha ido generando cuando se cerraban los hilos
    public static List<String> obtenerMensajesFinalizacion() {
        return mensajesFinalizacion;
    }
}
