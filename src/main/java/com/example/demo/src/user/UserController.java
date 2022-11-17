package com.example.demo.src.user;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;
import static com.example.demo.utils.ValidationRegex.isRegexNumber;



@RestController
@RequestMapping("/app/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;

    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/list") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {

        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);
            }

            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userIdx
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{Id}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public GetUserRes getUser(@PathVariable("Id") int Id) {
        // Get Users
        try{
            GetUserRes getUserRes = userProvider.getUser(Id);
            return getUserRes;
        } catch(BaseException exception){
            return null;
        }
    }

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        //이메일 null 확인
        if(postUserReq.getEmail() == null || postUserReq.getEmail().length() == 0) {
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }

        //이메일 정규표현
        if(!isRegexEmail(postUserReq.getEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }

        //아이디 null 확인
        if(postUserReq.getLoginId().length() == 0 || postUserReq.getLoginId() == null){
            return new BaseResponse<>(USERS_EMPTY_USER_ID);
        }

        //아이디 정규표현
        if(!isRegexId(postUserReq.getLoginId())){
            return new BaseResponse<>(POST_USERS_INVALID_ID);
        }

        //비밀번호 널 여부
        if(postUserReq.getPassword() == null || postUserReq.getPassword().length() == 0) {
            return new BaseResponse<>(USERS_EMPTY_USER_PASSWORD);
        }

        //비밀번호 정규 표현
        if(!isRegexPassword(postUserReq.getPassword())) {
            return new BaseResponse<>(POST_USERS_INVALID_PASSWORD);
        }

        //닉네임 널 여부
        if(postUserReq.getNickName() == null || postUserReq.getNickName().length() == 0) {
            return new BaseResponse<>(USERS_EMPTY_USER_NICKNAME);
        }

        //폰 번호 널 여부
        if(postUserReq.getMobilePhone() == null || postUserReq.getMobilePhone().length() == 0) {
            return new BaseResponse<>(USERS_EMPTY_USER_MOBILEPHONE);
        }

        //폰 번호 정규표현
        if(!isRegexNumber(postUserReq.getMobilePhone())) {
            return new BaseResponse<>(POST_USERS_INVALID_MOBILEPHONE);
        }

        //주소 널 여부
        if(postUserReq.getAddress() == null || postUserReq.getAddress().length() == 0) {
            return new BaseResponse<>(USERS_EMPTY_USER_ADDRESS);
        }

        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



    /**
     * 로그인 API
     * [POST] /users/logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/logIn")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
        try{
            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.

            //아이디 null 확인
            if(postLoginReq.getLogin_id() == null || postLoginReq.getLogin_id().length() == 0){
                return new BaseResponse<>(USERS_EMPTY_USER_ID);
            }


            //아이디 정규표현
            if(!isRegexId(postLoginReq.getLogin_id())){
                return new BaseResponse<>(POST_USERS_INVALID_ID);
            }

            //비밀번호 정규 표현
            if(!isRegexPassword(postLoginReq.getPassword())) {
                return new BaseResponse<>(POST_USERS_INVALID_PASSWORD);
            }

            //비밀번호 널 여부
            if(postLoginReq.getPassword() == null || postLoginReq.getPassword().length() == 0) {
                return new BaseResponse<>(USERS_EMPTY_USER_PASSWORD);
            }


            PostLoginRes postLoginRes = userService.logIn(postLoginReq);
            return new BaseResponse<>(postLoginRes);

        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PutMapping("/{Id}")
    public BaseResponse<String> modifyUserName(@PathVariable("Id") int Id, @RequestBody PutUserReq putUserReq){

        try {
            int userIdxByJwt = jwtService.getUserIdx();

            if(Id != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

//            GetUserRes getUserRes = getUser(Id);
//
//            if(!(getUserRes.getPassword().equals(putUserReq.getPassword()))){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }

            //닉네임 널 여부
            if(putUserReq.getNickName() == null || putUserReq.getNickName().length() == 0) {
                return new BaseResponse<>(USERS_EMPTY_USER_NICKNAME);
            }

            //폰 번호 널 여부
            if(putUserReq.getMobilePhone() == null || putUserReq.getMobilePhone().length() == 0) {
                return new BaseResponse<>(USERS_EMPTY_USER_MOBILEPHONE);
            }

            //폰 번호 정규표현
            if(!isRegexNumber(putUserReq.getMobilePhone())) {
                return new BaseResponse<>(POST_USERS_INVALID_MOBILEPHONE);
            }

            //이메일 null 확인
            if(putUserReq.getEmail() == null || putUserReq.getEmail().length() == 0) {
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }

            //이메일 정규표현
            if(!isRegexEmail(putUserReq.getEmail())) {
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
            }


            userService.modifyUserName(putUserReq);

            String result = "유저 정보를 변경하였습니다.";


            return new BaseResponse<>(result);


        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/{Id}")
    public BaseResponse<String> deleteUser(@PathVariable("Id") int Id,  @RequestBody User user){

        try {

            GetUserRes getUserRes = getUser(Id);

            if(!(getUserRes.getPassword().equals(user.getPassword()))){ //String 비교 방법 컴퓨터가 우리한테 보여주기 위해 표시되는것과 그 안?에 내용은 다를 수 있다
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            userService.deleteUser(getUserRes);

            String result = "계정을 삭제하였습니다.";

            return new BaseResponse<>(result);


        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
