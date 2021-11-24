package com.company;

import com.company.finance.Credito;
import com.company.finance.Solicitante;
import com.company.finance.SolicitanteBuilder;

public class Main {

    public static void main(String[] args) {
        // Utilizamos el patrón de diseño Façade dada la complejidad de creación del objeto
        // Ver: https://www.tutorialspoint.com/design_pattern/facade_pattern.htm
        Solicitante solicitante = SolicitanteBuilder.crearSolicitante();

        // Esta clase no ocupa una creacion tan compleja, por lo tanto se puede
        // implementar la lógica de creación del objeto dentro de la clase misma
        Credito credito = Credito.solicitarCredito(solicitante);
        solicitante.asignarCredito(credito); // No será asignado si el crédito no fue aprobado

        System.out.println(credito.obtenerDescripcion());
        System.out.println(credito.calcularFechas());
    }

}
