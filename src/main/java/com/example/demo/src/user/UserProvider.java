package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    public List<GetUserRes> getUsers() throws BaseException{

        try{
            List<GetUserRes> getUserRes = userDao.getUsers();
            return getUserRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsersByEmail(String email) throws BaseException{

        try{
            List<GetUserRes> getUsersRes = userDao.getUsersByEmail(email);
            return getUsersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
                    }


    public GetUserRes getUser(int Id) throws BaseException {
        try {
            GetUserRes getUserRes = userDao.getUser(Id);

            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{

            System.out.println(userDao.checkEmail(email));

            return userDao.checkEmail(email);


        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkId(String LoginId) throws BaseException{

        try{
            return userDao.checkId(LoginId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkMobilePhone(String mobilePhone) throws BaseException{
        try{

            System.out.println(userDao.checkMobilePhone(mobilePhone));

            return userDao.checkMobilePhone(mobilePhone);


        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
