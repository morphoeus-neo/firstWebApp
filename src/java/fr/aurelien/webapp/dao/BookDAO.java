/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.dao;

import fr.aurelien.webapp.entity.AuthorEntity;
import fr.aurelien.webapp.entity.BookEntity;
import fr.aurelien.webapp.entity.EditorEntity;
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
public class BookDAO implements DAOInterface< BookEntity, BookDAO> {

    private Connection DBCN;
    private PreparedStatement pstm;
    private ResultSet resultSet;

    public BookDAO(Connection dbConnection) {
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
    public int save(BookEntity entity) throws SQLException {
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
    private int insert(BookEntity entity) throws SQLException {
        String sql = "INSERT INTO livres ( titre, prix, editeur_id, auteur_id, genre_id)"
                + " VALUES (?,?,?,?,?)";

        pstm = DBCN.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, entity.getTitle());
        pstm.setDouble(2, entity.getPrice());
        pstm.setInt(3, entity.getEditor().getId());
        pstm.setInt(4, entity.getAuthor().getId());
        pstm.setInt(5, entity.getGenre().getId());

        // persistance de l'auteu lors de la sauvegarde d'un livre
        if (entity.getAuthor().getId() == null) {

            AuthorDAO author = new AuthorDAO(DBCN);
            author.save(entity.getAuthor());

        }

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
    private int update(BookEntity entity) throws SQLException {

        String sql = "UPDATE livres SET titre=?, prix=?, editeur_id=?, auteur_id=?, genre_id=? WHERE id=?";

        // Persistance des entitées associées
        AuthorDAO author = new AuthorDAO(DBCN);
        author.save(entity.getAuthor());
        GenreDAO genre = new GenreDAO(DBCN);
        genre.save(entity.getGenre());
        EditorDAO editor = new EditorDAO(DBCN);
        editor.save(entity.getEditor());

        pstm = DBCN.prepareStatement(sql);
        pstm.setString(1, entity.getTitle());
        pstm.setDouble(2, entity.getPrice());
        pstm.setInt(3, entity.getEditor().getId());
        pstm.setInt(4, entity.getAuthor().getId());
        pstm.setInt(5, entity.getGenre().getId());
        pstm.setInt(6, entity.getId());

        return pstm.executeUpdate();
    }

    @Override
    public void deleteOneById(BookEntity entity) throws SQLException {
        if (entity.getId() != null) {
            String sql = "DELETE FROM livres WHERE id=?";
            pstm = DBCN.prepareStatement(sql);
            pstm.setInt(1, entity.getId());
            pstm.executeUpdate();
        }
    }

    public BookDAO findOneById(int id) throws SQLException {
        String sql = "SELECT l.* , a.prenom as prenom_auteur, a.nom as nom_auteur,"
                + "e.nom as editeur, g.genre"
                + "FROM livres as l "
                + "INNER JOIN auteurs as a ON a.id = l.auteur_id"
                + "INNER JOIN editeurs as e ON e.id = l.editeur_id"
                + "INNER JOIN genres as g ON g.id = l.genre_id"
                + "WHERE id=?";
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
    public BookEntity getOne() throws SQLException {
        BookEntity entity = new BookEntity();
        if (resultSet.next()) {
            entity.setTitle(resultSet.getString("titre"));
            entity.setPrice(resultSet.getDouble("prix"));
            entity.setId(resultSet.getInt("id"));

            // Récupération  des associations
            //Transformation d'une réfé&rence ( la FK ) 
            // en une instance d'Entity hydratée par la requête pour l'éditeur
            Integer editorId = resultSet.getInt("editeur_id");
            EditorDAO editordao = new EditorDAO(DBCN);
            EditorEntity editor = editordao.findOneById(editorId).getOne();
            entity.setEditor(editor);

            Integer genreId = resultSet.getInt("genre_id");
            GenreDAO genredao = new GenreDAO(DBCN);
            GenreEntity genre = genredao.findOneById(genreId).getOne();
            entity.setGenre(genre);

            Integer authorId = resultSet.getInt("auteur_id");
            AuthorDAO authordao = new AuthorDAO(DBCN);
            AuthorEntity author = authordao.findOneById(editorId).getOne();
            entity.setAuthor(author);

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
        Map<String, String> bookData = new HashMap<>();
        if (resultSet.next()) {
            bookData.put("title", resultSet.getString("title"));
            bookData.put("price", resultSet.getString("price"));
            bookData.put("id", resultSet.getString("id"));
            bookData.put("auteur_id", resultSet.getString("auteur_id"));
            bookData.put("editeur_id", resultSet.getString("editeur_id"));
            bookData.put("genre_id", resultSet.getString("genre_id"));
            bookData.put("nom_auteur", resultSet.getString("nom_auteur"));
            bookData.put("editeur", resultSet.getString("editeur"));
            bookData.put("genre", resultSet.getString("genre"));
            

        }

        return bookData;
    }

    public BookDAO findAll() throws SQLException {
        String sql = "SELECT l.*, auteurs.prenom as prenom_auteur, a.nom as nom_auteur,"
                + "e.nom as editeur, g.genre"
                + "FROM livres as l "
                + "INNER JOIN auteurs as a ON a.id = l.auteur_id"
                + "INNER JOIN editeurs as e ON e.id = l.editeur_id"
                + "INNER JOIN genres as g ON g.id = l.genre_id"
                + "WHERE id=?";
        pstm = DBCN.prepareStatement(sql);

        resultSet = pstm.executeQuery();
        return this;
    }

    public List<BookEntity> getAll() throws SQLException {
        List<BookEntity> bookList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                bookList.add(this.getOne());
            }

        }
        return bookList;
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
