/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import java.util.List;

/**
 *
 * @author khmai
 */
public interface IService<T, R> {

    void add(T t);

    void delete(R r);

    T findById(R r);

    List<T> getAll();
}
