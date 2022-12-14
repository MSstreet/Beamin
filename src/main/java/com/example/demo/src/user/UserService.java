package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {

        //중복
        if(userProvider.checkEmail(postUserReq.getEmail()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        //Id 중복
        if(userProvider.checkId(postUserReq.getLoginId()) ==1){
            throw new BaseException(POST_USERS_EXISTS_ID);
        }

        if(userProvider.checkMobilePhone(postUserReq.getMobilePhone()) ==1){
            throw new BaseException(POST_USERS_EXISTS_MOBILEPHONE);
        }

        String pwd;

        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());

            postUserReq.setPassword(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try{
            int userIdx = userDao.createUser(postUserReq);

            //jwt 발급.
            //String jwt = jwtService.createJwt(userIdx);

            return new PostUserRes(userIdx);


        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostUserRes createUser1(PostSocialUserReq postSocialUserReq) throws BaseException {


        try{
            int userIdx = userDao.createUser1(postSocialUserReq);

            //jwt 발급.
            //String jwt = jwtService.createJwt(userIdx);
            System.out.println("createUser1 :" + userIdx);
            return new PostUserRes(userIdx);


        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserName(PutUserReq putUserReq) throws BaseException {

//        if(userProvider.checkMobilePhone(putUserReq.getMobilePhone()) ==1){
//            throw new BaseException(POST_USERS_EXISTS_MOBILEPHONE);
//        }

        try{
            int result = userDao.modifyUserName(putUserReq);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteUser(int userID) throws BaseException {
        try{
            int result = userDao.deleteUser(userID);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{

        User user = userDao.getPwd(postLoginReq);

        String encryptPwd;

        try {
            encryptPwd=new SHA256().encrypt(postLoginReq.getPassword());

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }


        //System.out.println(encryptPwd);

        if(user != null && user.getPassword().equals(encryptPwd)){
            int userIdx = user.getId();
            String jwt = jwtService.createJwt(userIdx);

            return new PostLoginRes(userIdx,jwt);
        }

        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

    public UserInfo getUserInfo(int memberId){
        return userDao.getUserInfo(memberId);
    }

}
