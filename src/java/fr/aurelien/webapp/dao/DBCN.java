/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton qui ne s'instancie qu'une seule fois ( pour info )
 *
 * @author formation
 */
public class DBCN {

    private static Connection instance;

    /**
     * Constructeur privé, empeche l'instanciation de la classe
     */
    private DBCN() {
    }

    /**
     * Méthode Statique qui retourne toujours la même instance
     * @return
     * @throws SQLException 
     */
    public static Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bibliotheque",
                    "root",
                    ""
            );
        }
        return instance;
    }

}
