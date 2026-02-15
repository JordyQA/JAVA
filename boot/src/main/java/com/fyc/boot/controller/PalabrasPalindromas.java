package com.fyc.boot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalabrasPalindromas {

    @GetMapping("/palabra-palindromas/{palabra}")
    public String palabraPalindromas(@PathVariable String palabra){
        var resultado = isPalindromas(palabra);
        if(resultado){
            return "La palabra "+ palabra + " es palindroma.";
        }else{
            return "La palabra "+ palabra + " no es palindroma.";
        }

    }



    private boolean isPalindromas(String palabra){
        //colocamos toda la palabra invertida
        palabra = palabra.toLowerCase();
        String palabraInvertida = "";

        //creamos la palabra invertida
        for (int i = palabra.length()-1; i >= 0; i--){
            palabraInvertida += palabra.charAt(i);
        }

        if(palabra.equals(palabraInvertida)){
            return true;
        }else{
            return false;
        }


    }

}
