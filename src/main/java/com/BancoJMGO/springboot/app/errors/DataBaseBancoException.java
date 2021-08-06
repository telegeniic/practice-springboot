package com.BancoJMGO.springboot.app.errors;

public class DataBaseBancoException extends RuntimeException {

    public DataBaseBancoException() {
        super("Contacte con la administracion, hubo un error con la base de datos");
    }
}
