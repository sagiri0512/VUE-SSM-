package com.sagiri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagiri.pojo.Detail;
import com.sagiri.pojo.Product;
import com.sagiri.service.DetailService;
import com.sagiri.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class DetailController {
    @RequestMapping(value = "/getDetailByPID", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDetailByPID(HttpServletRequest req) throws IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));

        DetailService detailService = new DetailService();

        List<Detail> detailsList = detailService.getDetailByPID(pid);

        ObjectMapper mapper = new ObjectMapper();
        String deatail = mapper.writeValueAsString(detailsList);

        return deatail;
    }
}
