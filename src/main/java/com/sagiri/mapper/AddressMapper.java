package com.sagiri.mapper;

import com.sagiri.pojo.Address;

import java.util.List;

public interface AddressMapper {
    List<Address> selectAddressByUid(long uid);
    int updateByAid(Address address);
    int addAddress(Address address);
    int deleteByAid(long aid);
}
