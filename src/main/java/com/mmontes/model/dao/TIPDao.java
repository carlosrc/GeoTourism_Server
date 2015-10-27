package com.mmontes.model.dao;

import com.mmontes.model.entity.TIP;
import com.mmontes.model.util.dao.GenericDao;
import com.vividsolutions.jts.geom.Geometry;

import java.util.List;

public interface TIPDao extends GenericDao<TIP, Long>{

    List<TIP> find(Geometry bounds,String type,Long cityId,List<Long> facebookUserIds,Double radius);
    void markAsFavourite(Long TIPId, Long facebookUserId);
}
