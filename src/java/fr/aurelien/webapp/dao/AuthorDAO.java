/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import fr.aurelien.webapp.entity.Author;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author formation
 */
public class AuthorDAO implements DAOInterface<Author, AuthorDAO> {

    private Connection DBCN;
    private PreparedStatement pstm;
    private ResultSet resultSet;

    public AuthorDAO(Connection dbConnection) {
        this.DBCN = dbConnection;

    }

    // un objet de type Student
    /**
     * Persistance de l'entité Student
     *
     * @param entity
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public int save(Author entity) throws SQLException {
        int affectedRows;
        if (entity.getId() == null) {
            //insertion
            affectedRows = this.insert(entity);
        } else {
            //mise a jour
            affectedRows = this.update(entity);
        }
        return affectedRows;
    }

    /**
     *
     * @param entity
     * @return
     * @throws SQLException
     */
    private int insert(Author entity) throws SQLException {
        String sql = "INSERT INTO auteurs ( nom, prenom) VALUES (?,?)";

        pstm = DBCN.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, entity.getName());
        pstm.setString(2, entity.getFirstName());
        
        
        // Récupération de la clef auto incrémentée
        ResultSet keyRs = pstm.getGeneratedKeys();
        if(keyRs.next()){
            entity.setId(keyRs.getInt("id"));
            
        }
        
        
        return pstm.executeUpdate();
    }

    /**
     *
     * @param entity
     * @return
     * @throws SQLException
     */
    private int update(Author entity) throws SQLException {

        String sql = "UPDATE auteurs SET nom=?, prenom=?";

        pstm = DBCN.prepareStatement(sql);
        pstm.setString(1, entity.getName());
        pstm.setString(2, entity.getFirstName());      
        pstm.setInt(3, entity.getId());

        return pstm.executeUpdate();
    }

    @Override
    public void deleteOneById(Author entity) throws SQLException {
        if (entity.getId() != null) {
            String sql = "DELETE FROM auteurs WHERE id=?";
            pstm = DBCN.prepareStatement(sql);
            pstm.setInt(1, entity.getId());
            pstm.executeUpdate();
        }
    }


    public AuthorDAO findOneById(int id) throws SQLException {
        String sql = "SELECT * FROM auteurs WHERE id=?";
        pstm = DBCN.prepareStatement(sql);
        pstm.setInt(1,id);
        pstm.executeUpdate();
        return this;
    }

    /**
     * Récupération d'une ligne de la table
     * @return
     * @throws SQLException 
     */
    public Author getOne() throws SQLException {
        Author entity = new Author();
        if (resultSet.next()) {
            entity.setName(resultSet.getString("nom"));
            entity.setFirstName(resultSet.getString("prenom"));
           

        }
        return entity;
    }
    
    
    /**
     * Récupération des rsultats d'ne requette sous la forme d'un tableau assiocatif
     * @return
     * @throws SQLException 
     */
    public Map<String, String> getOneAsMap() throws SQLException{
        Map<String, String> studentData = new HashMap<>();
        if (resultSet.next()) {
            studentData.put("nom", resultSet.getString("nom"));
            studentData.put("prenom", resultSet.getString("prenom"));

        }

        return studentData;
    }
    
    
       public AuthorDAO findAll() throws SQLException {
        String sql = "SELECT * FROM auteurs ";
        pstm = DBCN.prepareStatement(sql);

        resultSet = pstm.executeQuery();
        return this;
    }

public List<Author> getAll() throws SQLException{
    List<Author> studentList = new ArrayList<>();
    
    if (resultSet.isBeforeFirst()) {
        while (! resultSet.isLast()) {
        studentList.add(this.getOne());
    }
    
        
    }
    return studentList;
}

public List<Map<String, String>> getAllAsArray() throws SQLException{
     List<Map<String, String>> studentList = new ArrayList<>();
    
     if (resultSet.isBeforeFirst()) {
        while (! resultSet.isLast()) {
        studentList.add(this.getOneAsMap());
        
        
    }
    }
     
    
    return studentList;
}


}
