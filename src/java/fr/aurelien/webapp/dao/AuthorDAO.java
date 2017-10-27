/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import fr.aurelien.webapp.entity.AuthorEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author formation
 */
public class AuthorDAO implements DAOInterface<AuthorEntity, AuthorDAO> {

    private Connection DBCN;
    private PreparedStatement pstm;
    private ResultSet resultSet;

    public AuthorDAO(Connection dbConnection) {
        this.DBCN = dbConnection;

    }

    // un objet de type author
    /**
     * Persistance de l'entité author
     *
     * @param entity
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public int save(AuthorEntity entity) throws SQLException {
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
    private int insert(AuthorEntity entity) throws SQLException {
        String sql = "INSERT INTO auteurs ( nom, prenom) VALUES (?,?)";

        pstm = DBCN.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, entity.getName());
        pstm.setString(2, entity.getFirstName());

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
    private int update(AuthorEntity entity) throws SQLException {

        String sql = "UPDATE auteurs SET nom=?, prenom=? WHERE id=?";

        pstm = DBCN.prepareStatement(sql);
        pstm.setString(1, entity.getName());
        pstm.setString(2, entity.getFirstName());
        pstm.setInt(3, entity.getId());

        return pstm.executeUpdate();
    }

    @Override
    public void deleteOneById(AuthorEntity entity) throws SQLException {
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
    public AuthorEntity getOne() throws SQLException {
        AuthorEntity entity = new AuthorEntity();
        if (resultSet.next()) {
            entity.setName(resultSet.getString("nom"));
            entity.setFirstName(resultSet.getString("prenom"));
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
    public Map<String, String> getOneAsMap() throws SQLException {
        Map<String, String> authorData = new HashMap<>();
        if (resultSet.next()) {
            authorData.put("name", resultSet.getString("nom"));
            authorData.put("firstName", resultSet.getString("prenom"));

        }

        return authorData;
    }

    public AuthorDAO findAll() throws SQLException {
        String sql = "SELECT * FROM auteurs ";
        pstm = DBCN.prepareStatement(sql);

        resultSet = pstm.executeQuery();
        return this;
    }

    public List<AuthorEntity> getAll() throws SQLException {
        List<AuthorEntity> authorList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                authorList.add(this.getOne());
            }

        }
        return authorList;
    }

    public List<Map<String, String>> getAllAsArray() throws SQLException {
        List<Map<String, String>> authorList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                authorList.add(this.getOneAsMap());

            }
        }

        return authorList;
    }

    public boolean hasBook(AuthorEntity entity) throws SQLException {
        String sql = "SELECT EXISTS("
                + "SELECT id FROM livres WHERE auteur_id=?)"
                + " as found ";
        
        Boolean found = false;
        
        pstm = DBCN.prepareStatement(sql);
        pstm.setInt(1, entity.getId());
        
        resultSet = pstm.executeQuery();
        
        if (resultSet.next()) {
            int rsFound = resultSet.getInt("found");
            found = rsFound > 0;
        }
;
        
        
        return found;
        
    }

}
