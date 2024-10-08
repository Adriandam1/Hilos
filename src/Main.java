import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear el hilo principal
        Hilo hiloPadre = new Hilo(1, 1); // Empezamos el hilo 1 el maximo es 5
        // .start para llamar a run()
        hiloPadre.start(); // Iniciar el hilo principal

        try {
            hiloPadre.join(); // Esperar a que el hilo principal termine
        } catch (InterruptedException e) {
            System.err.println("El hilo principal fue interrumpido.");
        }

        // Imprimir los mensajes de finalización al final
        synchronized (Hilo.obtenerMensajesFinalizacion()) {
            for (String mensaje : Hilo.obtenerMensajesFinalizacion()) {
                System.out.println(mensaje);
            }
        }
        System.out.println("Acabó main");
    }
}
