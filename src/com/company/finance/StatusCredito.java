package com.company.finance;

public enum StatusCredito {

    APROBADO_CLASE_A(4500),
    APROBADO_CLASE_B(5000),
    APROBADO_CLASE_C(5500),
    APROBADO_CLASE_D(7000),
    RECHAZADO_POR_DOCS(-100),
    RECHAZADO_POR_EDAD(-200);

    public final float monto;
    public final boolean aprobado;

    // Los constructores en una enumeración son privados automáticamente
    StatusCredito(float monto) {
        this.aprobado = monto > 0;
        this.monto = monto;
    }


}
