package com.example.demo.src.social.controller;

import com.example.demo.src.social.service.OAuthService;
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

    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) throws Exception {
        System.out.println(code);

        String access_token = oAuthService.getKakaoAccessToken(code);

        System.out.println("###access_Token#### : " + access_token);

        oAuthService.createKakaoUser(access_token);

    }
}
