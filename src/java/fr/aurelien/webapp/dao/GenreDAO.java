/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import fr.aurelien.webapp.entity.GenreEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author formation
 */
public class GenreDAO implements DAOInterface<GenreEntity, GenreDAO> {

    private Connection DBCN;
    private PreparedStatement pstm;
    private ResultSet resultSet;

    public GenreDAO(Connection dbConnection) {
        this.DBCN = dbConnection;

    }

    // un objet de type genre
    /**
     * Persistance de l'entité genre
     *
     * @param entity
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public int save(GenreEntity entity) throws SQLException {
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
    private int insert(GenreEntity entity) throws SQLException {
        String sql = "INSERT INTO genres ( genre) VALUES (?)";

        pstm = DBCN.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, entity.getGenre());
        

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
    private int update(GenreEntity entity) throws SQLException {

        String sql = "UPDATE genres SET genre=? WHERE id=?";

        pstm = DBCN.prepareStatement(sql);
        pstm.setString(1, entity.getGenre());        
        pstm.setInt(3, entity.getId());

        return pstm.executeUpdate();
    }

    @Override
    public void deleteOneById(GenreEntity entity) throws SQLException {
        if (entity.getId() != null) {
            String sql = "DELETE FROM genres WHERE id=?";
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
    public GenreDAO findOneById(int id) throws SQLException {
        String sql = "SELECT * FROM genres WHERE id=?";
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
    public GenreEntity getOne() throws SQLException {
        GenreEntity entity = new GenreEntity();
        if (resultSet.next()) {
            entity.setGenre(resultSet.getString("genre"));           
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
        Map<String, String> genreData = new HashMap<>();
        if (resultSet.next()) {
            genreData.put("genre", resultSet.getString("genre"));
            

        }

        return genreData;
    }

    @Override
    public GenreDAO findAll() throws SQLException {
        String sql = "SELECT * FROM genres ";
        pstm = DBCN.prepareStatement(sql);

        resultSet = pstm.executeQuery();
        return this;
    }

    @Override
    public List<GenreEntity> getAll() throws SQLException {
        List<GenreEntity> genreList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                genreList.add(this.getOne());
            }

        }
        return genreList;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, String>> getAllAsArray() throws SQLException {
        List<Map<String, String>> genreList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                genreList.add(this.getOneAsMap());

            }
        }

        return genreList;
    }



}
