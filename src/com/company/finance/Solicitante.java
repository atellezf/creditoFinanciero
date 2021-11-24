package com.company.finance;

import java.time.LocalDate;
import java.time.Period;

public class Solicitante {
    // Los atributos y el constructor están declarados como protected para que solo haya acceso desde el mismo paquete
    // requerido por la clase SolicitanteBuilder.
    protected String nombre, sexo, domicilio, curp;
    protected boolean comprobanteDomicilio, identificacionOficial;
    protected LocalDate fechaNac;
    // La edad siempre debería ser un dato calculado a partir de la fecha de nacimiento
    // Dado que es un valor que va cambiando con el tiempo (aunque hay sus excepciones, depende del diseño de la clase)
    protected int edad;
    private Credito creditoAsignado;

    // Declaramos protected el constructor predeterminado para evitar que se utilice para crear objetos desde
    // el exterior, proceso que se delega la clase SolicitanteBuilder.
    protected Solicitante() {}

    public boolean tieneDocumentos() {
        boolean status = curp != null && !curp.isEmpty();
        status &= nombre != null && !nombre.isEmpty();
        status &= this.comprobanteDomicilio;
        status &= this.identificacionOficial;
        return status;
    }

    public void asignarCredito(Credito credito) {
        if( credito.isAprobado() ) {
            this.creditoAsignado = credito;
        }
    }

    // Se elimina el atributo edad para reemplazarlo por un método para que siempre se calcule la edad en
    // base a la fecha actual.
    public int edad() {
       LocalDate ahora = LocalDate.now();
       Period periodo = Period.between(fechaNac, ahora);
       return periodo.getYears();
    }

    public String getCurp() {
        return curp;
    }

    public String getNombre() {
        return nombre;
    }
}