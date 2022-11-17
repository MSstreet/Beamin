package com.example.demo.src.menuOption;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menuOption.model.PostMenuOptionReq;
import com.example.demo.src.menuOption.model.PostMenuOptionRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/menuoption")
public class MenuOptionController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MenuOptionService menuOptionService;

    @Autowired
    private final MenuOptionDao menuOptionDao;

    @Autowired
    private final MenuOptionProvider menuOptionProvider;
    @Autowired
    private final JwtService jwtService;

    public MenuOptionController(MenuOptionService menuOptionService, MenuOptionDao menuOptionDao, MenuOptionProvider menuOptionProvider,JwtService jwtService){
        this.menuOptionService = menuOptionService;
        this.menuOptionDao = menuOptionDao;
        this.menuOptionProvider = menuOptionProvider;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<PostMenuOptionRes> createMenuOption(@RequestBody PostMenuOptionReq postMenuOptionReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        try{

            PostMenuOptionRes postMenuOptionRes = menuOptionService.createMenuOption(postMenuOptionReq);

            return new BaseResponse<>(postMenuOptionRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PutMapping("/{menuOptionId}")
    public BaseResponse<String> modifyMenu(@PathVariable("menuOptionId") int menuOptionId, @RequestBody PostMenuOptionReq postMenuOptionReq){

        try{

            menuOptionService.modifyMenuOption(postMenuOptionReq);

            String result = "메뉴 옵션 변경 완료";

            return new BaseResponse<>(result);

        }catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/{menuOptionId}")
    public BaseResponse<String> deleteDeleteMenuOption(@PathVariable("menuOptionId") int menuOptionId){

        try {

            menuOptionDao.deleteMenuOption(menuOptionId);

            String result = "메뉴옵션 삭제.";

            return new BaseResponse<>(result);

        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}


