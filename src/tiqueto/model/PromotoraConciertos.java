package tiqueto.model;

import tiqueto.EjemploTicketMaster;

public class PromotoraConciertos extends Thread {

    final WebCompraConciertos webCompra;

    public PromotoraConciertos(WebCompraConciertos webCompra) {
        super();
        this.webCompra = webCompra;
    }

    @Override
    public void run() {

        while ((EjemploTicketMaster.recuento_entradas_restantes > 0) || (webCompra.hayEntradas())) {
            if (!webCompra.hayEntradas()) {
                webCompra.reponerEntradas(EjemploTicketMaster.REPOSICION_ENTRADAS);
                try {
                    Thread.sleep(EjemploTicketMaster.aleatorio(3000, 8000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        webCompra.cerrarVenta();

    }

    /**
     * Método a usar para cada impresión por pantalla
     *
     * @param mensaje Mensaje que se quiere lanzar por pantalla
     */
    private void mensajePromotor(String mensaje) {
        System.out.println(System.currentTimeMillis() + "| Promotora: " + mensaje);

    }
}
