package com.sagiri.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sagiri.pojo.Address;
import com.sagiri.pojo.User;
import com.sagiri.service.AddressService;
import com.sagiri.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Controller
public class AddressController {
    @RequestMapping(value = "/getAddressByUName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAddress(HttpServletRequest req) throws JsonProcessingException {
        String uname = req.getParameter("uname");
        UserService userService = new UserService();
        long uid =  userService.getUser(uname).getUid();

        AddressService addressService = new AddressService();
        List<Address> addressesList = addressService.getAddressByUid(uid);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(addressesList);
    }
    //改地址
    class NewAddress{
        public long aid;
        public String aname;
        public String atext;
        public String aphonenNmber;
    }
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder buffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }
        String data = buffer.toString();

        // 使用JSON库解析数据
        Gson gson = new Gson();
        NewAddress newAddress = gson.fromJson(data, NewAddress.class);


        Address address = new Address(newAddress.aid,  newAddress.atext, newAddress.aphonenNmber, newAddress.aname);

        AddressService addressService = new AddressService();
        if(addressService.updateByAid(address) > 0){
            resp.getWriter().write("1");
        }else{
            resp.getWriter().write("0");
        }
    }
    //添加收货地址
    class AddAddress{
        String aname;
        String atext;
        String aphonenNmber;
        long uid;
    }
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void addAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder buffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }
        String data = buffer.toString();

        // 使用JSON库解析数据
        Gson gson = new Gson();
        AddAddress address = gson.fromJson(data, AddAddress.class);

        Address newAddress = new Address ();
        newAddress.setAName(address.aname);
        newAddress.setAText(address.atext);
        newAddress.setAPhonenNmber(address.aphonenNmber);
        newAddress.setUid(address.uid);

        AddressService addressService = new AddressService();
        if(addressService.addAddress(newAddress) > 0){
            resp.getWriter().write("1");
        }else{
            resp.getWriter().write("0");
        }
    }
    @RequestMapping(value = "/deleteAddress", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void deleteAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long aid = Long.parseLong(req.getParameter("aid"));

        AddressService addressService = new AddressService();
        if(addressService.deleteByAid(aid) > 0){
            resp.getWriter().write("1");
        }else{
            resp.getWriter().write("0");
        }
    }

}
