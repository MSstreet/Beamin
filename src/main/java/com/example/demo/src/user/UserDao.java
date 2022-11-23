package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){

        String getUsersQuery = "select * from user";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("ID"),
                        rs.getString("nick_name"),
                        rs.getString("login_id"),
                        rs.getString("email"),
                        rs.getString("password"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from user where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("ID"),
                        rs.getString("nick_name"),
                        rs.getString("login_id"),
                        rs.getString("email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from user where ID = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("ID"),
                        rs.getString("nick_name"),
                        rs.getString("login_id"),
                        rs.getString("email"),
                        rs.getString("password")),
                getUserParams);
    }


    public int createUser1(PostSocialUserReq postSocialUserReq){

        String createUserQuery = "insert into user1 (email,nick_name) VALUES (?,?)";

        System.out.println("===================== :"+ postSocialUserReq.getEmail());

        Object[] createUserParams = new Object[]{postSocialUserReq.getEmail(), postSocialUserReq.getNickname()};

        for(int i = 0; i < createUserParams.length; i++){
            System.out.println(createUserParams[i]);
        }

        this.jdbcTemplate.update(createUserQuery, createUserParams);

       String lastInsertIdQuery = "select last_insert_id()";

//        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int createUser(PostUserReq postUserReq){

        String createUserQuery = "insert into user (login_id,password,nick_name,mobile_phone,address,email,grade,mail_status,sms_status, profile_image) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Object[] createUserParams = new Object[]{postUserReq.getLoginId(), postUserReq.getPassword(),postUserReq.getNickName()
                ,postUserReq.getMobilePhone(),postUserReq.getAddress(),postUserReq.getEmail(),postUserReq.getGrade(), postUserReq.getMailStatus(),postUserReq.getSmsStatus(),postUserReq.getProfileImage()};

        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";

//        System.out.println(lastInsertIdQuery);

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from user where email = ?)";

        System.out.println(checkEmailQuery);

        String checkEmailParams = email;

        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int checkMobilePhone(String mobilePhone){
        String checkMobilePhoneQuery = "select exists(select mobile_phone from user where mobile_phone = ?)";

        System.out.println(checkMobilePhoneQuery);

        String checkParams = mobilePhone;

        return this.jdbcTemplate.queryForObject(checkMobilePhoneQuery,
                int.class,
                checkParams);

    }

    public int checkId(String loginId){

        String checkIdQuery = "select exists(select login_id from user where login_id = ?)";

        String checkIdParams = loginId;

        System.out.println(checkIdQuery);

        return this.jdbcTemplate.queryForObject(checkIdQuery,
                int.class,
                checkIdParams);

    }

    public int modifyUserName(PutUserReq putUserReq){
        String modifyUserNameQuery = "update user set nick_name = ?, mobile_phone = ?, email = ?, mail_status = ?, sms_status = ?, profile_image =? where ID = ? ";


        Object[] modifyUserNameParams = new Object[]{putUserReq.getNickName(), putUserReq.getMobilePhone(),putUserReq.getEmail()
        ,putUserReq.getMailStatus(), putUserReq.getSmsStatus(), putUserReq.getProfileImage(),putUserReq.getId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);

    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select ID, password,email,nick_name,login_id,mobile_phone,address,grade,mail_status,sms_status,profile_image from user where login_id = ?";

        String getPwdParams = postLoginReq.getLogin_id();

            return this.jdbcTemplate.queryForObject(getPwdQuery,
                    (rs, rowNum) -> new User(
                            rs.getInt("ID"),
                            rs.getString("login_id"),
                            rs.getString("password"),
                            rs.getString("nick_name"),
                            rs.getString("mobile_phone"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("grade"),
                            rs.getBoolean("mail_status"),
                            rs.getBoolean("sms_status"),
                            rs.getString("profile_image")),
                    getPwdParams);
    }

    public int deleteUser(int userId){
        String deleteUserQuery = "delete from user where Id = ?";

        //int getUserParams = getUserRes.getID();

        return this.jdbcTemplate.update(deleteUserQuery,userId);
    }

    public UserInfo getUserInfo(int UserId){
        String getUserInfoQuery = "select ID, nick_name,mobile_phone, email, address from user where Id = ?";

        return this.jdbcTemplate.queryForObject(getUserInfoQuery,
                (rs, rowNum) -> new UserInfo(
                        rs.getInt("ID"),
                        rs.getString("nick_name"),
                        rs.getString("mobile_phone"),
                        rs.getString("email"),
                        rs.getString("address")),
                UserId);
    }
}
