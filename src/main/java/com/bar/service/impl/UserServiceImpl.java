/*
 * @(#)UserServiceImpl.java 1.0 Sep 22, 2017
 */
package com.bar.service.impl;


import com.bar.builder.UserProfileBuilder;
import com.bar.domain.User;
import com.bar.domain.repository.UserRepository;
import com.bar.exception.ValidationError;
import com.bar.exception.ValidationException;
import com.bar.model.RegistrationData;
import com.bar.model.UserProfile;
import com.bar.service.HashingService;
import com.bar.service.ServiceFactory;
import com.bar.service.UserService;

import java.util.HashMap;

/**
 * <code>UserServiceImpl</code> class is  Implementation class for UserService interface.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/22/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 22, 2017
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private HashingService hashingService = ServiceFactory.getHashingService();

    public UserServiceImpl() {
        userRepository = ServiceFactory.getUserRepository();
    }



    /**
     * This is a void class for now. But we will see how this evolves.
     *
     * @param data user Registration Data.
     */
    @Override
    public void registerUser(RegistrationData data) throws ValidationException {
        runBusinessValidation(data);
        //now we need to Make a user Objet. Lets use the Builder Class
        User user = new UserProfileBuilder().registrationData(data).buildUser();
        userRepository.save(user);
    }


    /**
     * Validates the user and return the user profile with goodlogin..
     *
     * @param username userName
     * @param password password
     * @throws ValidationException
     */
    @Override
    public UserProfile validateUser(String username, String password) throws ValidationException {

        User u = userRepository.findByEmailId(username);
        if (u != null) {

            String dbPassword = u.getPassword();
            String dbSalt = u.getSalt();
            String dbHashingAlgo = u.getHashingAlgo();
            //make a new hash
            try {

                if (hashingService.isValid(dbHashingAlgo, password, dbSalt, dbPassword)){
                    UserProfile profile = new UserProfileBuilder().user(u).build2();
                    return profile;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ValidationException("Login Failed", new ValidationError("username/password", "System", "Try Again..."));
            }
        }
        throw new ValidationException("Login Failed", new ValidationError("username/password", "mismatch", "provide a valid login"));
    }

    private void runBusinessValidation(RegistrationData data) throws ValidationException {
        if (userRepository.findByEmailId(data.getEmail()) != null) {
            throw new ValidationException("User is registered.",
                    new ValidationError("email", "registered", "User is registered."));
        }
    }

}



