package com.fyc.boot.controller;

import com.fyc.boot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123,"Gerardo López","gerardol","contraseña123"),
            new Customer(456,"Alejanda García","alegarcia","clave456"),
            new Customer(789,"Laura Sanchéz","lauras","secreto789"),
            new Customer(234,"Carlos Martínez","carlosm","password234")
    ));


    /**
     * traemos toda la lista de los clientes
     * @return retorna todos los clientes
     */

    //@GetMapping ("/clientes") -> sin usar RequestRequestMapping

    //@GetMapping -> usando RequestMapping en la clase

    //ahora usando RequestMapping en la los metodos
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers(){

        return ResponseEntity.ok(customers);
    }



    /**
     * obtenemos al cliente mediante el userName
     * @param userName
     * @return
     */
    //@GetMapping("/clientes/{userName}") -> sin usar RequestRequestMapping

    //@GetMapping("/{userName}") -> usando RequestMapping en la clase

    //ahora usando RequestMapping en la los metodos
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerUserName(@PathVariable String userName){
        for (Customer c : customers){
            if (c.getUserName().equalsIgnoreCase(userName)){
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no fue encontrado con el userName:" + userName);
    }


    /**
     *  create un nuevo cliente
     * @param customer
     * @return
     */
    //@PostMapping("/clientes") -> sin usar RequestRequestMapping

    //@PostMapping -> usando RequestMapping en la clase

    //ahora usando RequestMapping en la los metodos
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){

       /* para validar si en este caso existe ya el id antes de crearlo
       for (Customer c:customers){
            if (c.getId() == customer.getId()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente ya existe");
            }
        }
        */

        customers.add(customer);
        System.out.println("El cliente a sido creado correctamente");

        //envio de uri por cabecera o header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUserName())
                .toUri();

        //no retorna nada de los datos pasados en el body
        //return ResponseEntity.created(location).build();
        //nos retorna el json pasado en el body
        return ResponseEntity.created(location).body(customer);
    }


    /**
     * Update del cliente en su totalidad
     * @param customer
     * @return
     */
    //@PutMapping("/clientes") -> sin usar RequestRequestMapping

    //@PutMapping -> usando RequestMapping en la clase

    //ahora usando RequestMapping en la los metodos
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?>  putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if (c.getId() == customer.getId()){
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());
                System.out.println("El usuario a sido actualizado correctamente.");

                return ResponseEntity.noContent().build();
            }
        }

        //NOT_FOUND -> 404
        return ResponseEntity.notFound().build();
    }


    /**
     * delete del cliente por el id
     * @param id
     * @return
     */
    //@DeleteMapping("/clientes/{id}") -> sin usar RequestRequestMapping
    //@DeleteMapping("/{id}") -> usando RequestMapping en la clase

    //ahora usando RequestMapping en la los metodos
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        for (Customer c : customers){
            if(c.getId() == id){
                customers.remove(c);
                System.out.println("Se elimino el usuario correctamente");
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


    //@PatchMapping("/clientes") -> sin usar RequestRequestMapping
    //@PatchMapping -> usando RequestMapping en la clase

    //ahora usando RequestMapping en la los metodos
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if(c.getId() == customer.getId()){

                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }
                if (customer.getUserName() != null){
                    c.setUserName(customer.getUserName());
                }
                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }

                return ResponseEntity.ok("El cliente fue actualizado correctamente: " + c.getId());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado para actualizar con el id: " + customer.getId());
    }





}
