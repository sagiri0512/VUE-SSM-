package com.sagiri.service;

import com.sagiri.dao.CarouselDao;
import com.sagiri.pojo.Carousel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselService {
    public CarouselDao carousel;

    public CarouselService() {
        carousel = new CarouselDao();
    }
    public List<Carousel> getAllCCarousel () {
        return carousel.getAllCCarousel();
    }
}
