/*
 * @(#)BookService.java 1.0 Nov 26, 2017
 */
package com.bar.service;



import com.bar.exception.BookNotFoundException;
import com.bar.exception.ValidationException;
import com.bar.model.BookData;

import java.util.List;

/**
 * <code>BookService</code> class is  Service For Book
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
public interface BookService {

    /**
     * Adds a book. this book is added with a default quantity of 1.
     * @param bookData  bookData
     * @return Book Data with newly created Id.
     */
    BookData addBook(BookData bookData) throws ValidationException;

    /**
     * Adds a book. You can call this method with quantity and it will create that many book copies.
     * @param bookData  bookData
     * @param quantity  quantity
     * @return
     * @throws ValidationException
     */
    BookData addBook(BookData bookData, int quantity) throws ValidationException;

    BookData deleteBook(BookData bookData, int[] bookCopies) throws ValidationException;


    /**
     * Adds book copies. Given a book id that exists, you can give how many new book copies you want to save.
     * @param bookId  bookId
     * @param  quantity quantity
     * @return Book Data with newly created Ids.
     */
    BookData addBookCopies(long bookId, int quantity) throws ValidationException;

    /**
     * Gets the total copy count of the bookId.
     * @param bookId
     * @return  number of total copies
     * @throws BookNotFoundException
     */
    int getNumberOfTotalCopies(Long bookId) throws BookNotFoundException;

    /**
     * Gets the available copy count of the bookId. Used for loan.
     * @param bookId  BookId
     * @return number of available copies
     * @throws BookNotFoundException
     */
    int getNumberOfAvailableCopies(Long bookId);

    /**
     * Selects book data based on serach criteria (Author or title partial)
     * @param srchCriteria search Criteria
     * @return book data list
     */
    List<BookData> searchBook(String srchCriteria);




}



