package com.company;

public class VehiculoFactory {

    public static Vehiculo getInstanceAuto(int velocidad, int aceleracion, int anguloDeGiro, String patente){
        return new Vehiculo(velocidad, aceleracion, anguloDeGiro, patente, 3000, 4, TipoVehiculo.AUTO);
    }

    public static Vehiculo getInstanceMoto(int velocidad, int aceleracion, int anguloDeGiro, String patente){
        return new Vehiculo(velocidad, aceleracion, anguloDeGiro, patente, 1000, 2, TipoVehiculo.MOTO);
    }

    public static Socorrista getInstanceSocorrista(int velocidad, int aceleracion, int anguloDeGiro, String patente, TipoVehiculo socorro){
        return new Socorrista(velocidad, aceleracion, anguloDeGiro, patente, 1000, 2, socorro);
    }

}
