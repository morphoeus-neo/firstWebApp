/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import fr.aurelien.webapp.entity.Editor;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author formation
 */
public class EditorDAO implements DAOInterface<Editor, EditorDAO> {

    private Connection DBCN;
    private PreparedStatement pstm;
    private ResultSet resultSet;

    public EditorDAO(Connection dbConnection) {
        this.DBCN = dbConnection;

    }

    // un objet de type Editor
    /**
     * Persistance de l'entité Editor
     *
     * @param entity
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public int save(Editor entity) throws SQLException {
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
    private int insert(Editor entity) throws SQLException {
        String sql = "INSERT INTO editeurs (nom) VALUES (?)";

        pstm = DBCN.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, entity.getNom());
        

        // Récupération de la clef auto incrémentée
        ResultSet keyRs = pstm.getGeneratedKeys();
        if (keyRs.next()) {
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
    private int update(Editor entity) throws SQLException {

        String sql = "UPDATE editeurs SET nom=? WHERE id=?";

        pstm = DBCN.prepareStatement(sql);
        pstm.setString(1, entity.getNom());        
        pstm.setInt(3, entity.getId());

        return pstm.executeUpdate();
    }

    @Override
    public void deleteOneById(Editor entity) throws SQLException {
        if (entity.getId() != null) {
            String sql = "DELETE FROM editeurs WHERE id=?";
            pstm = DBCN.prepareStatement(sql);
            pstm.setInt(1, entity.getId());
            pstm.executeUpdate();
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public EditorDAO findOneById(int id) throws SQLException {
        String sql = "SELECT * FROM editeurs WHERE id=?";
        pstm = DBCN.prepareStatement(sql);
        pstm.setInt(1, id);
        resultSet = pstm.executeQuery();
        return this;
    }

    /**
     * Récupération d'une ligne de la table
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Editor getOne() throws SQLException {
        Editor entity = new Editor();
        if (resultSet.next()) {
            entity.setNom(resultSet.getString("nom"));           
            entity.setId(resultSet.getInt("id"));

        }
        return entity;
    }

    /**
     * Récupération des rsultats d'ne requette sous la forme d'un tableau
     * assiocatif
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getOneAsMap() throws SQLException {
        Map<String, String> EditorData = new HashMap<>();
        if (resultSet.next()) {
            EditorData.put("nom", resultSet.getString("nom"));
           
            

        }

        return EditorData;
    }

    @Override
    public EditorDAO findAll() throws SQLException {
        String sql = "SELECT * FROM editeurs ";
        pstm = DBCN.prepareStatement(sql);

        resultSet = pstm.executeQuery();
        return this;
    }

    @Override
    public List<Editor> getAll() throws SQLException {
        List<Editor> EditorList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                EditorList.add(this.getOne());
            }

        }
        return EditorList;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getAllAsArray() throws SQLException {
        List<Map<String, String>> EditorList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                EditorList.add(this.getOneAsMap());

            }
        }

        return EditorList;
    }



}
