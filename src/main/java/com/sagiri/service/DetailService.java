package com.sagiri.service;

import com.sagiri.dao.CarouselDao;
import com.sagiri.dao.DetailDao;
import com.sagiri.pojo.Detail;

import java.util.List;

public class DetailService {
    public DetailDao detailDao;

    public DetailService() {
        detailDao = new DetailDao();
    }

    public List<Detail> getDetailByPID(int pid) {
        return detailDao.getDetailByPID(pid);
    }
}
