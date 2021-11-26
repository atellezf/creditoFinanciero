package com.company.finance;

import java.time.LocalDate;

public class Credito {
//     En realidad la lógica debe mostrarnos que un solicitante tiene un crédito
//     por lo que debemos romper esta relación circular.
//     private final Solicitante solicitante;

//    Se reemplazan las constantes por la enumeración StatusCredito
//    private static final int CREDITO_RECHAZADO_POR_DOCS = -100;
//    private static final int CREDITO_RECHAZADO_POR_EDAD = -200;

    private LocalDate fechaSolicitud;
    private StatusCredito status;
    private String empleado;

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
            status = switch (cliente.edad()) {
                case 30, 31, 32, 33, 34, 35 -> StatusCredito.APROBADO_CLASE_A;
                case 36, 37, 38, 39, 40 -> StatusCredito.APROBADO_CLASE_B;
                case 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 -> StatusCredito.APROBADO_CLASE_C;
                case 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64 -> StatusCredito.APROBADO_CLASE_D;
                default -> StatusCredito.RECHAZADO_POR_EDAD;
            };
        } else status = StatusCredito.RECHAZADO_POR_DOCS;
    }

    // Método para calcular fechas de pagos
    public String calcularFechas() {
        StringBuilder sb = new StringBuilder("Las fechas de sus pagos son:\n");
        for (int i = 3; i <= 12; i += 3) {
            LocalDate fechaPago = fechaSolicitud.plusMonths(i);
            sb.append("\t • %d Pago:".formatted(i/3));
            sb.append(" %1$td / %1$tm / %1$tY ".formatted(fechaPago));
            sb.append("\t $%,.2f\n".formatted(status.monto / 4)); // La coma indica mostrar con separador de miles
        }
        return sb.toString();
//        LocalDate primerPago = fechaSolicitud.plusMonths(3).withDayOfMonth(fechaSolicitud.getDayOfMonth());
//        LocalDate segundoPago = fechaSolicitud.plusMonths(6).withDayOfMonth(fechaSolicitud.getDayOfMonth());
//        LocalDate tercerPago = fechaSolicitud.plusMonths(9).withDayOfMonth(fechaSolicitud.getDayOfMonth());
//        LocalDate cuartoPago = fechaSolicitud.plusMonths(12).withDayOfMonth(fechaSolicitud.getDayOfMonth());
//        return """
//            Las fechas de sus pagos son:
//                • 1 Pago: %1$td / %1$tm / %1$tY     $ %5$.2f
//                • 2 Pago: %2$td / %2$tm / %2$tY     $ %5$.2f
//                • 3 Pago: %3$td / %3$tm / %3$tY     $ %5$.2f
//                • 4 Pago: %4$td / %4$tm / %4$tY     $ %5$.2f
//            """.formatted(primerPago, segundoPago, tercerPago, cuartoPago, monto / 4);
    }

    public String obtenerDescripcion() {
        return switch(status) {
            case RECHAZADO_POR_DOCS -> "Lamento informarle que no se le puede otorgar el crédito ya que no cuenta con los documentos necesarios";
            case RECHAZADO_POR_EDAD -> "Lamento informarle que no cuenta con la edad requerida para poder obtener un crédito";
            default -> "Felicidades usted es acreedor a un crédito con un monto de $ %,.2f".formatted(status.monto);
        };
    }

    public boolean isAprobado() {
        return status.aprobado;
    }
}