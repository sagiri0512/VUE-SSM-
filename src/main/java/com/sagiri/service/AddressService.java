package com.sagiri.service;

import com.sagiri.dao.AddressDao;
import com.sagiri.pojo.Address;

import java.util.List;

public class AddressService {
    AddressDao addressDao;
    public AddressService(){
        addressDao = new AddressDao();
    }
    public List<Address> getAddressByUid(long uid){
        return addressDao.getAddressByUid(uid);
    }
    public int updateByAid(Address address){
        return addressDao.updateByAid(address);
    }
    public int addAddress(Address address){
        return addressDao.addAddress(address);
    }
    public int deleteByAid(long aid){
        return addressDao.deleteByAid(aid);
    }

}
