package com.example.demo.src.menu;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.menu.model.PostMenuReq;
import com.example.demo.src.menu.model.PostMenuRes;


import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/menu")
public class MenuController {


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuService menuService;

    private final MenuProvider menuProvider;

    private final MenuDao menuDao;

    private final JwtService jwtService;

    public MenuController(MenuService menuService, MenuProvider menuProvider, MenuDao menuDao, JwtService jwtService){

        this.menuService = menuService;
        this.menuProvider = menuProvider;
        this.menuDao = menuDao;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping("/{restaurantId}/join")
    public BaseResponse<PostMenuRes> createMenu(@PathVariable("restaurantId") int restaurantId, @RequestBody PostMenuReq postMenuReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!

        if(!(postMenuReq.getPrice() instanceof Integer)){
            return new BaseResponse<>(BaseResponseStatus.POST_MENU_INVALID_PRICE);
        }

        try{
            PostMenuRes PostMenuRes = menuService.createMenu(postMenuReq);
            return new BaseResponse<>(PostMenuRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/list") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetMenuRes>> getMenus(){
        try{
            List<GetMenuRes> getMenuRes = menuProvider.getMenus();
            return new BaseResponse<>(getMenuRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ResponseBody
    @GetMapping("/{restaurantId}")
    public GetMenuRes getRestaurantMenu(@PathVariable ("restaurantId") int restaurantId){

        try{
            GetMenuRes getMenuRes = menuProvider.getRestaurantMenu(restaurantId);
            return getMenuRes;
        } catch(BaseException exception){
            return null;
        }
    }



    @ResponseBody
    @PutMapping("/{menuId}")
    public BaseResponse<String> modifyMenu(@PathVariable("menuId") int menuId, @RequestBody PostMenuReq postMenuReq){

        try{

            menuService.modifyMenu(postMenuReq);

            String result = "";

            return new BaseResponse<>(result);

        }catch(BaseException exception) {

            return new BaseResponse<>((exception.getStatus()));

        }
    }

    @ResponseBody
    @PatchMapping("/{menuId}")
    public BaseResponse<String> deleteMenu(@PathVariable("menuId") int menuId){
        try {
            menuDao.deleteMenu(menuId);

            String result = "메뉴를 삭제하였습니다.";

            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}

//    @ResponseBody
//    @PatchMapping("/{restaurantId}")
//    public BaseResponse<String> deleteRestaurant(@PathVariable("restaurantId") int restaurantId){
//
//        try {
//
//            restaurantService.deleteRestaurant(getRestaurantRes);
//
//            String result = "레스토랑을 삭제하였습니다.";
//
//            return new BaseResponse<>(result);
//
//        }catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }