package com.sagiri.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagiri.pojo.ShoppingCart;
import com.sagiri.service.ShoppingCartService;
import com.sagiri.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShoppingCartController {
    @RequestMapping(value = "/addShoppingCart", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addShoppingCart(HttpServletRequest req) {
        String uname = req.getParameter("uname");
        int pid = Integer.parseInt(req.getParameter("pid"));
        int sNum = 1;

        UserService userService = new UserService();
        long uid = userService.getUser(uname).getUid();

        ShoppingCart shoppingCart = new ShoppingCart(uid, pid, sNum);
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        int result = shoppingCartService.addShoppingCart(shoppingCart);
        if(result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
    @RequestMapping(value = "/getSumOfSNumByUid", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSumOfSNumByUid(HttpServletRequest req) {
        String uname = req.getParameter("uname");
        UserService userService = new UserService();
        long uid =  userService.getUser(uname).getUid();

        ShoppingCartService shoppingCartService = new ShoppingCartService();
        int sum = shoppingCartService.getSumOfSNumByUid(uid);

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(sum);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @RequestMapping(value = "/getShoppingCartByUid", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getShoppingCartByUidAndPid(HttpServletRequest req) throws JsonProcessingException {
        String uname = req.getParameter("uname");
        UserService userService = new UserService();
        long uid = userService.getUser(uname).getUid();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUid(uid);
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        List<ShoppingCart> shoppingCarts = shoppingCartService.selectShoppingCartByUid(shoppingCart);
        ObjectMapper mapper = new ObjectMapper();
        if(shoppingCarts!=null&& !shoppingCarts.isEmpty()){
            return mapper.writeValueAsString(shoppingCarts);
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/deleteShoppingCartById", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void deleteShoppingCartById(HttpServletRequest req) {
        Integer pid =Integer.parseInt(req.getParameter("pid"));
        Integer uid =Integer.parseInt(req.getParameter("uid"));
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUid(uid);
        shoppingCart.setPid(pid);
        ShoppingCartService shoppingCartService=new ShoppingCartService();
        shoppingCartService.deleteShoppingCartById(shoppingCart);
    }

    @RequestMapping(value = "/updateNum", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateNum(HttpServletRequest req) {
        Integer uid =Integer.parseInt(req.getParameter("uid"));
        Integer pid =Integer.parseInt(req.getParameter("pid"));
        Integer sNum =Integer.parseInt(req.getParameter("sNum"));
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setPid(pid);
        shoppingCart.setUid(uid);
        shoppingCart.setSNum(sNum);
        ShoppingCartService shoppingCartService=new ShoppingCartService();
        shoppingCartService.updateNum(shoppingCart);
    }

    @RequestMapping(value = "/selectShoppingCartById", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectShoppingCartById(HttpServletRequest req) {
        String uname = req.getParameter("uname");
        Integer pid =Integer.parseInt(req.getParameter("pid"));
        UserService userService = new UserService();
        long uid = userService.getUser(uname).getUid();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPid(pid);
        shoppingCart.setUid(uid);
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        List<ShoppingCart> shoppingCarts = shoppingCartService.selectShoppingCartById(shoppingCart);
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(shoppingCarts);
        }catch (Exception e)
        {
            return null;
        }
    }
}
