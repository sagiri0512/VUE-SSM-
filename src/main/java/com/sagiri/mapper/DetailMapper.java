package com.sagiri.mapper;

import com.sagiri.pojo.Detail;

import java.util.List;

public interface DetailMapper {
    List<Detail> selectDetailByPID(int pid);
}
