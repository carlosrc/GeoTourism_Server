package com.mmontes.model.service;

import com.mmontes.model.entity.OSM.OSMType;
import com.mmontes.model.entity.TIP.TIPtype;
import com.mmontes.util.dto.OSMTypeDto;
import com.mmontes.util.dto.TIPtypeDto;
import com.mmontes.util.dto.TIPtypeOSMDto;
import com.mmontes.util.exception.DuplicateInstanceException;
import com.mmontes.util.exception.InstanceNotFoundException;

import java.util.List;

public interface TIPtypeService {

    List<TIPtype> findAllTypes();
    TIPtypeDto findById(Long TIPtypeId) throws InstanceNotFoundException;
    String findTypeName(Long TIPtypeId) throws InstanceNotFoundException;
    TIPtypeDto create(String name, String icon, List<OSMType> osmTypes) throws InstanceNotFoundException;
    TIPtypeDto update(Long TIPtypeID,String name, String icon, List<OSMType> osmTypes) throws InstanceNotFoundException;
    void delete(Long TIPtypeID) throws InstanceNotFoundException;
}
