/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author formation
 * @param <T>
 * @param <DAO>
 */
public interface DAOInterface <T, DAO>{
    
    
    int save(T entity) throws SQLException;
    
    void deleteOneById(T entity) throws SQLException;

    DAO findOneById(int id) throws SQLException;
    
    T getOne() throws SQLException;
    
    Map<String, String> getOneAsMap() throws SQLException;
    
    DAO findAll() throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    List<Map<String, String>> getAllAsArray() throws SQLException;
    
    
    
    
}
