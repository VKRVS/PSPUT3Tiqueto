package tiqueto.model;

import tiqueto.EjemploTicketMaster;

public class FanGrupo extends Thread {

    final WebCompraConciertos webCompra;
    int numeroFan;
    private String tabuladores = "\t\t\t\t";
    int entradasCompradas = 0;

    public FanGrupo(WebCompraConciertos web, int numeroFan) {
        super();
        this.numeroFan = numeroFan;
        this.webCompra = web;
    }

    @Override
    public void run() {
        //TODO

        while (this.entradasCompradas < EjemploTicketMaster.MAX_ENTRADAS_POR_FAN) {
            if (webCompra.hayEntradas()) {
                mensajeFan("Me dispongo a comprar entradas");
                webCompra.comprarEntrada();
                this.entradasCompradas++;
                mensajeFan("Soy el fan " + this.numeroFan + ", tengo en total: " + this.entradasCompradas);

                try {
                    Thread.sleep(EjemploTicketMaster.aleatorio(1000, 3000));
                } catch (InterruptedException e) {
                    System.out.println("El hilo fue interrumpido mientras dormía");
                }
            }
        }
    }

    public void dimeEntradasCompradas() {
        mensajeFan("Sólo he conseguido: " + entradasCompradas);
    }

    public int cuantasEntradas() {
        return this.entradasCompradas;
    }

    /**
     * Método a usar para cada impresión por pantalla
     *
     * @param mensaje Mensaje que se quiere lanzar por pantalla
     */
    private void mensajeFan(String mensaje) {
        System.out.println(System.currentTimeMillis() + "|" + tabuladores + " Fan " + this.numeroFan + ": " + mensaje);
    }
}
