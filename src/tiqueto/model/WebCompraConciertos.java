package tiqueto.model;

import tiqueto.EjemploTicketMaster;
import tiqueto.IOperacionesWeb;

import static tiqueto.EjemploTicketMaster.fans;

public class WebCompraConciertos implements IOperacionesWeb {

    private int entradasDisponibles;

    public WebCompraConciertos() {
        super();
    }

    @Override
    public synchronized boolean comprarEntrada() {
        if (hayEntradas()) {
            entradasDisponibles--;
        } else {
            mensajeWeb("No quedan entradas en esta remesa");
        }
        return hayEntradas();
    }


    @Override
    public synchronized int reponerEntradas(int numeroEntradas) {
        if (numeroEntradas > EjemploTicketMaster.recuento_entradas_restantes) {
            for (int i = 0; i <= EjemploTicketMaster.recuento_entradas_restantes; i++) {
                EjemploTicketMaster.recuento_entradas_restantes--;
                entradasDisponibles++;
            }
        } else {
            for (int i = 0; i < numeroEntradas; i++) {
                EjemploTicketMaster.recuento_entradas_restantes--;
                entradasDisponibles++;
            }
        }
        return this.entradasDisponibles;
    }


    @Override
    public synchronized void cerrarVenta() throws InterruptedException {
        for (FanGrupo fan : fans) {
            mensajeWeb(fan.numeroFan + " ha abandonado la web");
            fan.interrupt();
        }
    }


    @Override
    public synchronized boolean hayEntradas() {
        if (this.entradasDisponibles > 0) {
            mensajeWeb("Hay entradas. Aún quedan: " + entradasRestantes());
        }
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
