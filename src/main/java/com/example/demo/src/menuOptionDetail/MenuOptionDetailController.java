package com.example.demo.src.menuOptionDetail;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menuOptionDetail.model.GetMenuOptionDetailRes;
import com.example.demo.src.menuOptionDetail.model.PostMenuOptionDetailReq;

import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@Controller
@RequestMapping("/app/menu_option_detail")
public class MenuOptionDetailController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MenuOptionDetailService menuOptionDetailService;

    @Autowired
    private final MenuOptionDetailProvider menuOptionDetailProvider;
    @Autowired
    private final JwtService jwtService;

    public MenuOptionDetailController(MenuOptionDetailService menuOptionDetailService,MenuOptionDetailProvider menuOptionDetailProvider,JwtService jwtService){
        this.menuOptionDetailService = menuOptionDetailService;
        this.menuOptionDetailProvider = menuOptionDetailProvider;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<GetMenuOptionDetailRes> creatMenuOptionDetail(@RequestBody PostMenuOptionDetailReq postMenuOptionDetailReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        try{
            GetMenuOptionDetailRes getMenuOptionDetailRes = menuOptionDetailService.createMenuOptionDetail(postMenuOptionDetailReq);

            return new BaseResponse<>(getMenuOptionDetailRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
