package com.company.finance;

import java.time.LocalDate;

public class Credito {
    // En realidad la lógica debe mostrarnos que un solicitante tiene un crédito
    // por lo que debemos romper esta relación circular.
    // private final Solicitante solicitante;

    // Utilizamos constantes para determinar la razón del rechazo del crédito, aunque se puede
    // trabajar una enumeración para conocer el estado de la aprobación, y quedaría mas elegante el código.
    private static final int CREDITO_RECHAZADO_POR_DOCS = -100;
    private static final int CREDITO_RECHAZADO_POR_EDAD = -200;

    private LocalDate fechaSolicitud;
    private String empleado;
    private boolean aprobado;
    private float monto;

    private Credito(String empleado, Solicitante cliente) {
        fechaSolicitud = LocalDate.now();
        this.calcularMonto(cliente);
        this.empleado = empleado;
    }

    public static Credito solicitarCredito(Solicitante cliente) {
        System.out.println("""
                ##########################################################
                ###          GENERANDO SOLICITUD DE CRÉDITO            ###
                ##########################################################
                """);
        String empleado = Util.solicitaCadena("Nombre del empleado que captura: ");
        return new Credito(empleado, cliente);
    }

    //Métodos para asignar la cantidad del crédito dependiendo de la edad
    private void calcularMonto(Solicitante cliente) {
        if (cliente.tieneDocumentos()) {
            monto = switch (cliente.edad()) {
                case 30, 31, 32, 33, 34, 35 -> 4500f;
                case 36, 37, 38, 39, 40 -> 5000f;
                case 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 -> 5500f;
                case 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64 -> 7000f;
                default -> CREDITO_RECHAZADO_POR_EDAD;
            };
        } else monto = CREDITO_RECHAZADO_POR_DOCS;
        aprobado = monto > 0;
    }

    //Método para calcular fechas de pagos
    public String calcularFechas() {
        LocalDate primerPago = fechaSolicitud.plusMonths(3).withDayOfMonth(fechaSolicitud.getDayOfMonth());
        LocalDate segundoPago = fechaSolicitud.plusMonths(6).withDayOfMonth(fechaSolicitud.getDayOfMonth());
        LocalDate tercerPago = fechaSolicitud.plusMonths(9).withDayOfMonth(fechaSolicitud.getDayOfMonth());
        LocalDate cuartoPago = fechaSolicitud.plusMonths(12).withDayOfMonth(fechaSolicitud.getDayOfMonth());
        return """
            Las fechas de sus pagos son:
                • 1 Pago: %1$td / %1$tm / %1$tY     $ %5$.2f
                • 2 Pago: %2$td / %2$tm / %2$tY     $ %5$.2f
                • 3 Pago: %3$td / %3$tm / %3$tY     $ %5$.2f
                • 4 Pago: %4$td / %4$tm / %4$tY     $ %5$.2f
            """.formatted(primerPago, segundoPago, tercerPago, cuartoPago, monto / 4);
    }

    public String obtenerDescripcion() {
        if( monto == CREDITO_RECHAZADO_POR_DOCS ) {
            return "Lamento informarle que no se le puede otorgar el crédito ya que no cuenta con los documentos necesarios";
        } else if( monto == CREDITO_RECHAZADO_POR_EDAD ) {
            return "Lamento informarle que no cuenta con la edad requerida para poder obtener un crédito";
        }
        return "Felicidades usted es acreedor a un crédito con un monto de $ %.2f".formatted(monto);
    }

    public boolean isAprobado() {
        return aprobado;
    }
}