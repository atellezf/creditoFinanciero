package com.company.finance;

import java.util.Scanner;

public class Util {

    public static String solicitaCadena(String mensaje) {
        System.out.print(mensaje);
        Scanner scn = new Scanner(System.in); //Creación del lector de valores del input
        return scn.nextLine();  //Lee string y la regresa
    }

}
