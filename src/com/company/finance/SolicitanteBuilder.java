package com.company.finance;

import java.time.LocalDate;

public class SolicitanteBuilder {

    // Ocupo un método estático para crear un objeto de Solicitante, debido a que es un proceso
    // complejo la creación de dicho objeto. Así simplificamos la creación de Solicitantes en la clase
    // principal.
    // En la práctica profesional este método y los de capturar deberían estar en una clase externa conocida como
    // Façade, la cual es un patrón de diseño que facilita la creación de objetos complejos.
    // Revisar tutorial: https://www.tutorialspoint.com/design_pattern/facade_pattern.htm
    public static Solicitante crearSolicitante() {
        Solicitante cliente = new Solicitante();
        SolicitanteBuilder builder = new SolicitanteBuilder();
        System.out.println("""
                ##########################################################
                ###         DATOS DEL SOLICITANTE DEL CRÉDITO          ###
                ##########################################################
                """);
        builder.capturarNombre(cliente);
        builder.capturarSexo(cliente);
        builder.capturarFechaNac(cliente);
        builder.capturarDomicilio(cliente);
        builder.capturarCurp(cliente);
        builder.capturarComprobanteDomicilio(cliente);
        builder.capturarIdentificacionOficial(cliente);
        return cliente;
    }

    //Métodos para capturar los datos del solicitante del crédito
    private void capturarNombre(Solicitante cliente) {
        cliente.nombre = Util.solicitaCadena("Nombre completo: ");
    }

    private void capturarFechaNac(Solicitante cliente) {
        System.out.println("Por favor ingresa la fecha de nacimiento con el formato Año-Mes-Día, ejemplo: 1999-08-16");
        String res = Util.solicitaCadena("Fecha de Nacimiento: ");
        cliente.fechaNac = LocalDate.parse(res);
    }

    private void capturarSexo(Solicitante cliente) {
        cliente.sexo = Util.solicitaCadena("Sexo: ");
    }

    private void capturarDomicilio(Solicitante cliente) {
        cliente.domicilio = Util.solicitaCadena("Domicilio: ");
    }

    private void capturarCurp(Solicitante cliente) {
        System.out.println("¿Cuenta con CURP? Si la respuesta es SI, por favor ingreselo a continuación");
        cliente.curp = Util.solicitaCadena("Curp: ");
    }

    private void capturarComprobanteDomicilio(Solicitante cliente) {
        System.out.println("¿Cuenta con comprobante de domicilio?");
        String res = Util.solicitaCadena("Por favor escribe la letra S o N: "); //Guarda lo que el usuario ingreso en la variable res
        // String resLower = res.toLowerCase(); //Convierte el string a minusculas
        // comprobanteDomicilio = resLower.contains("s"); //Guardará true si usuario cuenta con la identificación, de lo contrario será false
        // Cambiamos contains() por equalsIgnoreCase() se ahorra una instrucción y evitamos cadenas como "este ... no"
        // que regresarían un falso si
        cliente.comprobanteDomicilio= res.equalsIgnoreCase("s");
    }

    private void capturarIdentificacionOficial(Solicitante cliente) {
        System.out.println("¿Cuenta con identificación Oficial?");
        String res = Util.solicitaCadena("Por favor escribe la letra S o N: ");
        cliente.identificacionOficial = res.equalsIgnoreCase("s");
    }

}
