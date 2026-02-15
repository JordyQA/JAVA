package com.example.administracion_estudiantes.domain;

public class Student {

    private int id;
    private String nombre;
    private String email;
    private int edad;
    private String curso;

    public Student(int id,String nombre, String email, int edad, String curso){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.curso = curso;
    }


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
