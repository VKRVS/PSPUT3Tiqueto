package tiqueto.model;

import org.w3c.dom.ls.LSOutput;
import tiqueto.EjemploTicketMaster;
import tiqueto.IOperacionesWeb;

public class WebCompraConciertos implements IOperacionesWeb {

    private int entradasDisponibles;

    public WebCompraConciertos() {
        super();
    }

    @Override
    public synchronized boolean comprarEntrada() {
        if (hayEntradas()) {
            this.entradasDisponibles--;
        }
        return hayEntradas();
    }


    @Override
    public synchronized int reponerEntradas(int numeroEntradas) {
        System.out.println("Toca reponer entradas. Quedan " + EjemploTicketMaster.recuento_entradas_restantes);
        if (numeroEntradas > EjemploTicketMaster.recuento_entradas_restantes) {
            for (int i = 0; i < EjemploTicketMaster.recuento_entradas_restantes; i++) {
                EjemploTicketMaster.recuento_entradas_restantes--;
                this.entradasDisponibles++;
            }
        } else {
            for (int i = 0; i < numeroEntradas; i++) {
                EjemploTicketMaster.recuento_entradas_restantes--;
                this.entradasDisponibles++;
            }
        }
        return this.entradasDisponibles;
    }


    @Override
    public synchronized void cerrarVenta() {
        Thread.currentThread().interrupt();
    }


    @Override
    public synchronized boolean hayEntradas() {
        return this.entradasDisponibles > 0;
    }


    @Override
    public synchronized int entradasRestantes() {
        return this.entradasDisponibles;
    }

    /**
     * Método a usar para cada impresión por pantalla
     *
     * @param mensaje Mensaje que se quiere lanzar por pantalla
     */
    private void mensajeWeb(String mensaje) {
        System.out.println(System.currentTimeMillis() + "| WebCompra: " + mensaje);

    }

}
