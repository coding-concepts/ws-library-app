/*
 * @(#)BookRespositoryImpl.java 1.0 Nov 26, 2017
 */
package com.bar.domain.repository;


import com.bar.domain.Book;
import com.bar.exception.SystemException;
import com.bar.service.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>BookRespositoryImpl</code> class is  impl class.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    11/26/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Nov 26, 2017
 */
public class BookRespositoryImpl implements BookRespository {
    @Override
    public Book save(Book book) {
        String insQry = "INSERT INTO BOOK (TITLE, AUTHOR) VALUES (? , ?)";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(insQry);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.executeUpdate();
            DatabaseUtility.commitTransaction();

        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to insert a Book: ");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        return findByTitle(book.getTitle());
    }

    @Override
    public Book findByTitle(String title) {
        String query = "SELECT ID, TITLE, AUTHOR FROM BOOK WHERE TITLE = ? ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Book bk = new Book();
                bk.setId(rs.getLong("ID"));
                bk.setTitle(rs.getString("TITLE"));
                bk.setAuthor(rs.getString("AUTHOR"));
                return bk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to get a Book by Title ");
        }
        return null;
    }


    @Override
    public List<Book> findByPartialAuthor(String author) {
        String query = "SELECT ID, TITLE, AUTHOR FROM BOOK WHERE UPPER(AUTHOR) LIKE ? ";
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setString(1, "%"+author.toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Book bk = new Book();
                bk.setId(rs.getLong("ID"));
                bk.setTitle(rs.getString("TITLE"));
                bk.setAuthor(rs.getString("AUTHOR"));
                books.add(bk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to get a Book by PARTIAL Author ");
        }
        return books;
    }


    @Override
    public List<Book> findByPartialTitle(String title) {
        String query = "SELECT ID, TITLE, AUTHOR FROM BOOK WHERE UPPER(TITLE) LIKE ? ";
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setString(1, "%"+title.toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Book bk = new Book();
                bk.setId(rs.getLong("ID"));
                bk.setTitle(rs.getString("TITLE"));
                bk.setAuthor(rs.getString("AUTHOR"));
                books.add(bk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to get a Book by PARTIAL Title ");
        }
        return books;
    }

    @Override
    public Book findById(long bookId) {
        String query = "SELECT ID, TITLE, AUTHOR FROM BOOK WHERE ID = ? ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Book bk = new Book();
                bk.setId(rs.getLong("ID"));
                bk.setTitle(rs.getString("TITLE"));
                bk.setAuthor(rs.getString("AUTHOR"));
                return bk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to get a Book by ID ");
        }
        return null;
    }

}



