package com.sagiri.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sagiri.pojo.User;
import com.sagiri.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {

    class LoginData {
        String userName;
        String userPWD;
        String checkCode;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        LoginData loginData = gson.fromJson(data, LoginData.class);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");

        User user = userService.getUser(loginData.userName);

        String answer = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");

        if (!loginData.checkCode.equalsIgnoreCase(answer)){
            resp.getWriter().write("2");
            return;
        }
        if (user != null && user.getUpwd().equals(loginData.userPWD)) {
            // 密码匹配，登录成功
            resp.getWriter().write("1");
        } else {
            // 密码不匹配，登录失败
            resp.getWriter().write("0");
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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
        LoginData loginData = gson.fromJson(data, LoginData.class);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");

        User user = new User(loginData.userName,loginData.userPWD);

        String answer = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");

        if (!loginData.checkCode.equalsIgnoreCase(answer)){
            resp.getWriter().write("2");
            return;
        }
        if (userService.addUser(user) > 0){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }
    @RequestMapping(value = "/getUserInfo", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfo(HttpServletRequest req) throws JsonProcessingException {
        String uname = req.getParameter("uname");

        UserService userService = new UserService();
        User user = userService.getUser(uname);

        ObjectMapper mapper = new ObjectMapper();
        String userinfo = mapper.writeValueAsString(user);

        return userinfo;
    }
    @RequestMapping(value = "/phoneChange", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void phoneChange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uname = req.getParameter("uname");
        String phone = req.getParameter("phone");

        UserService userService = new UserService();
        User user = new User();

        user.setUName(uname);
        user.setUPhoneNmber(phone);

        if (userService.phoneChange(user) > 0){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }
    @RequestMapping(value = "/emailChange", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void emailChange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uname = req.getParameter("uname");
        String email = req.getParameter("email");

        UserService userService = new UserService();
        User user = new User();

        user.setUName(uname);
        user.setUMailBox(email);

        if (userService.emailChange(user) > 0){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }
    class PwdChangeData {
        String uname;
        String upwd;
    }
    @RequestMapping(value = "/pwdChange", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void pwdChange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        PwdChangeData pwdChangeData = gson.fromJson(data, PwdChangeData.class);

        UserService userService = new UserService();
        User user = new User();
        user.setUName(pwdChangeData.uname);
        user.setUpwd(pwdChangeData.upwd);

        if (userService.pwdChange(user) > 0){
            resp.getWriter().write("1");
        }else{
            resp.getWriter().write("0");
        }
    }
    @RequestMapping(value = "/updateUserNickName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateUserNickName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uname = req.getParameter("uname");
        String nickname = req.getParameter("unickname");
        UserService userService = new UserService();
        User user = new  User();
        user.setUNickName(nickname);
        user.setUName(uname);
        if (userService.updateUserNickNameByUname(user) > 0){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }
    @RequestMapping(value = "/updateUserAgeByUName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateUserAgeByUName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uname = req.getParameter("uname");
        int age = Integer.parseInt(req.getParameter("uage"));
        UserService userService = new UserService();
        User user = new  User();
        user.setUage(age);
        user.setUName(uname);
        if (userService.updateUserAgeByUName(user) > 0){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }

    @RequestMapping(value = "/updateUserSexByUName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateUserSexByUName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uname = req.getParameter("uname");
        int sex = Integer.parseInt(req.getParameter("usex"));
        UserService userService = new UserService();
        User user = new  User();
        user.setUsex(sex);
        user.setUName(uname);
        if (userService.updateUserSexByUName(user) > 0){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }
    @PostMapping(value = "/updateUserHeadByUID", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void upload(@RequestParam("selectedFile") MultipartFile dropzFile, @RequestParam("uid") long uid, HttpServletResponse resp) throws IOException {
        String fileName = uid + ".png";
        String filePath = "/www/wwwroot/sagirinoinu.top/firstjob/public/images/" + fileName;

        User user = new User();
        String head = "images/" + fileName;
        user.setUHeadImage(head);
        user.setUid(uid);

        UserService userService = new UserService();

        userService.updateUserHeadByUID(user);
        try {
            // 将上传文件保存到目标文件
            dropzFile.transferTo(new File(filePath));
            resp.getWriter().write("1");
        } catch (IOException e) {
            resp.getWriter().write("0");
        }
    }
}
