package com.example.demo.src.menu;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menu.model.PostMenuReq;
import com.example.demo.src.menu.model.PostMenuRes;
import com.example.demo.src.restautant.model.PostRestaurantReq;
import com.example.demo.src.restautant.model.PostRestaurantRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/menu")
public class MenuController {


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuService menuService;

    private final MenuProvider menuProvider;

    private final JwtService jwtService;

    public MenuController(MenuService menuService, MenuProvider menuProvider, JwtService jwtService){

        this.menuService = menuService;
        this.menuProvider = menuProvider;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<PostMenuRes> createMenu(@RequestBody PostMenuReq postMenuReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        try{
            PostMenuRes PostMenuRes = menuService.createMenu(postMenuReq);
            return new BaseResponse<>(PostMenuRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
