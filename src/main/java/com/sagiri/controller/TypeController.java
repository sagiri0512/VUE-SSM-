package com.sagiri.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagiri.pojo.Type;
import com.sagiri.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TypeController {
    @RequestMapping(value = "/getType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getType() throws JsonProcessingException {
        TypeService typeService = new TypeService();
        List<Type> list = typeService.getAllType();

        ObjectMapper mapper = new ObjectMapper();
        String type = mapper.writeValueAsString(list);

        return type;
    }
}
