package com.sagiri.service;

import com.sagiri.dao.TypeDao;
import com.sagiri.pojo.Type;

import java.util.List;

public class TypeService {
    public TypeDao typeDao;
    public TypeService() {
        typeDao = new TypeDao();
    }
    public List<Type> getAllType() {
        return typeDao.selectAllType();
    }
}
