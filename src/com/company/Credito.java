package com.company;

import java.time.LocalDate;
import java.util.Scanner;

public class Credito {
    private String nomEmpleado, productoFinanciero;
    private int monto;
    private LocalDate fechaSolicitud, fechasPagos;

    public Credito(String productoFinanciero, int monto) {
        this.productoFinanciero = productoFinanciero;
        this.monto = monto;
    }

    public String capturarNomEmpleado() {
        System.out.print("Por favor escribe el nombre de la persona capturando los datos: ");
        Scanner scn = new Scanner(System.in); //System.in is a standard input stream
       return nomEmpleado = scn.nextLine(); //reads string
    }

    Solicitante solicitante = new Solicitante();

    //Métodos para verificar si cuenta con los documentos completos
    public boolean verificarCurp() {
        return !solicitante.capturarCurp().isEmpty(); //Lógica para comprobar si tiene CURP
    }
    public boolean verificarDocu() {
        //Lógica para comprobar si tiene todos los documentos completos
        return solicitante.capturarComprobanteDomicilio() && solicitante.capturarIdentificacionOficial() && verificarCurp();
    }

    //Métodos para asignar la cantidad del crédito dependiendo de la edad
    public String calcularCredito() {
        return switch(solicitante.calcularEdad()) {
            case 30, 31, 32, 33, 34, 35 -> "$4,500 (cuatro mil quinientos pesos)";
            case 36, 37, 38, 39, 40 -> "$5,000 (cinco mil pesos)";
            case 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 -> "$5,500 (cinco mil quinientos pesos)";
            default -> "No cuenta con la edad requerida para poder obtener un crédito";
        };
    }


}