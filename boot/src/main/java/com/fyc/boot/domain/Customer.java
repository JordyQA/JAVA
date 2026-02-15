package com.fyc.boot.domain;

//clase pojo: clase que parece al modelo o al objeto

public class Customer {

    //Atributos
    private int id;

    private String name;
    private String userName;
    private String password;


    //Contructor
    public Customer(int id,String name,String userName, String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    //Metodos get and set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
