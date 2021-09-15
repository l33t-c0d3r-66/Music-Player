package com.l33t_c0d3r_66.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractController<T> implements Initializable
{
    protected T model;

    public AbstractController(T nModel){
        model = nModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComponents();
        addComponents();
        addBinding();
    }

    public abstract void addBinding();
    public abstract void addComponents();
    public abstract void initializeComponents();


}
