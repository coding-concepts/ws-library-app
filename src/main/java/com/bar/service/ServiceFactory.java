/*
 * @(#)ServiceFactory.java 1.0 Sep 28, 2017
 */
package com.bar.service;


import com.bar.domain.repository.BookCopyRepository;
import com.bar.domain.repository.BookCopyRepositoryImpl;
import com.bar.domain.repository.BookRespository;
import com.bar.domain.repository.BookRespositoryImpl;
import com.bar.domain.repository.LoanRepository;
import com.bar.domain.repository.LoanRepositoryImpl;
import com.bar.domain.repository.UserRepository;
import com.bar.domain.repository.UserRepositoryImpl;
import com.bar.service.impl.BookServiceImpl;
import com.bar.service.impl.EncryptionServiceImpl;
import com.bar.service.impl.HashingServiceImpl;
import com.bar.service.impl.LoanServiceImpl;
import com.bar.service.impl.UserServiceImpl;

/**
 * <code>ServiceFactory</code> class is  Factory class to give out Services.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/28/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 28, 2017
 */

public class ServiceFactory {
    public static  UserService getUserService() {
        return new UserServiceImpl();
    }



    public static HashingService getHashingService(){
        return new HashingServiceImpl();
    }

    public static UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

    public static EncryptionService getEncryptionService() {
        return new EncryptionServiceImpl();
    }

    public static LoanService getLoanService(){
        return new LoanServiceImpl();
    }

    public static BookService getBookService(){
        return new BookServiceImpl();
    }


    public static BookCopyRepository getBookCopyRepository(){
        return new BookCopyRepositoryImpl();
    }

    public static BookRespository getBookRepository(){
        return new BookRespositoryImpl();
    }

    public static LoanRepository getLoanRepository() {
        return new LoanRepositoryImpl();
    }

}



