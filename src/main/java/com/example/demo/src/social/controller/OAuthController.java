package com.example.demo.src.social.controller;

import com.example.demo.config.BaseException;
import com.example.demo.src.social.service.OAuthService;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.PostSocialUserReq;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) throws BaseException {

        int check;

        System.out.println(code);

        String access_token = oAuthService.getKakaoAccessToken(code);

        System.out.println("###access_Token#### : " + access_token);

        HashMap<String, Object> userInfo = oAuthService.createKakaoUser(access_token);

        String email = (String)userInfo.get("email");
        String nickname = (String)userInfo.get("nickname");


        check = userProvider.checkEmail(email);

        PostSocialUserReq postSocialUserReq = new PostSocialUserReq(email,nickname);

        System.out.println("Controller nickname : " + nickname);
        System.out.println("Controller email : " + email);

        if(check == 1){
            System.out.println("이미있는아이디");
        }else{
            userService.createUser1(postSocialUserReq);
        }

    }
}
