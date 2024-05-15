package com.sagiri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagiri.pojo.Carousel;
import com.sagiri.service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CraouselController {
    @RequestMapping(value = "/getCarousel", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCarousel() throws IOException {
        CarouselService carouselService = new CarouselService();
        List<Carousel> carouselList = carouselService.getAllCCarousel(); // 假设这里返回了 Carousel 对象列表

        // 使用 Jackson ObjectMapper 将对象转换为 JSON 字符串
        ObjectMapper mapper = new ObjectMapper();
        String carousel = mapper.writeValueAsString(carouselList);

        return  carousel;
    }
}
