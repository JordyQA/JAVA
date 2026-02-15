package com.example.administracion_estudiantes.controller;

import com.example.administracion_estudiantes.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/alumnos")

public class StudentController {

    public List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(123,"Jordy Quispe","jquispac@emeal.com",27,"Ingenieria de sistemas"),
            new Student(456,"Paola Minaya","paola@emeal.com",29,"Contabilidad"),
            new Student(789,"Kriste Acedo","Kris@emeal.com",39,"Psicologia"),
            new Student(147,"Daniel Ventura","Dventu@emeal.com",45,"Fisico")
    ));


    //Mostramos a todos los alumnos

    @GetMapping
    public List<Student> getStudiantes(){
        return students;
    }


    //consultar un alumno por email
    @GetMapping("/{email}")
    public Student getStudentEmeal(@PathVariable String email){
        for (Student s : students){
            if (s.getEmail().equalsIgnoreCase(email)){
                return s;
            }
        }
        return null;
    }

    //Crear un nuevo alumno
    @PostMapping
    public Student postStudent(@RequestBody Student student){

        for (Student s:students){
           if (s.getId() == student.getId()){
               throw new ResponseStatusException(HttpStatus.CONFLICT, "El studiante ya esta registrado");
           }
        }

        students.add(student);
        System.out.println("El alumno fue creado exitosamente");
        return student;
    }

    //Modificar la informacion de un alumno(tanto de manera total como parcial)
    //para actualizar total PUT
    @PutMapping
    public Student updateTOtal(@RequestBody Student student){
        for (Student s:students){
            if (s.getId() == student.getId()){
                s.setNombre(student.getNombre());
                s.setEmail(student.getEmail());
                s.setEdad(student.getEdad());
                s.setCurso(student.getCurso());
                System.out.println("El usuario a sido actualizado con exito");
                return  s;
            }
        }
        return null;
    }
    //Para actualizar parcial PATCH
    @PatchMapping
    public Student updateParcial(@RequestBody Student student){
        for (Student s:students){
            if (s.getId() == student.getId()){

                if (student.getNombre() != null){
                    s.setNombre(student.getNombre());
                }
                if (student.getEmail() != null){
                    s.setEmail(student.getEmail());
                }
                if (student.getEdad() >= 0 && student.getEdad() <= 150 ){
                    s.setEdad(student.getEdad());
                }
                if (student.getCurso() != null){
                    s.setCurso(student.getCurso());
                }

                return s;
            }
        }
        return null;
    }

    //Eliminar un alumno por su ID
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable int id){
        for (Student s:students){
            if (s.getId() == id){
                students.remove(s);
                System.out.println("EL estudiante fue eliminado correctamente.");
                return s;
            }
        }
        return null;
    }

}
