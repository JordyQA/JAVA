package com.fyc.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoRestControllers {

    private int contador = 0;


    //generamos sin pasar parametros
    @GetMapping({"hola","saludos","pett"})
    public String saludarSinParametros(){
        contador++;
        System.out.println("Se ejecuto correctamente: "+contador);
        return "Hola como estas asdasq";
    }

    @GetMapping("/hello/{nombre}")
    public String saludarConParametros(@PathVariable String nombre){
        System.out.println("- El nombre ingresado es: "+nombre);
        return "Hola bienvenido "+nombre;
    }

}
