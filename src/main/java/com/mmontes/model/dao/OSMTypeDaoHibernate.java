package com.mmontes.model.dao;

import com.mmontes.model.entity.OSM.OSMType;
import com.mmontes.model.util.genericdao.GenericDaoHibernate;
import com.mmontes.util.exception.InstanceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OSMTypeDao")
@SuppressWarnings("all")
public class OSMTypeDaoHibernate extends GenericDaoHibernate<OSMType,Long> implements OSMTypeDao {
    @Override
    public List<OSMType> getOSMTypes(Boolean tipTypeSetted) {
        String queryString = "SELECT * FROM osmtype o ";
        if (tipTypeSetted != null && tipTypeSetted){
            queryString += "JOIN tiptype t ON o.tiptypeid = t.id";
        }
        return (List<OSMType>)getSession().createSQLQuery(queryString).addEntity(OSMType.class).list();
    }

    @Override
    public OSMType findByOSMvalueIdAndTIPtypeId(Long osmValueId, Long tipTypeId) throws InstanceNotFoundException {
        String queryString = "SELECT o FROM OSMType o WHERE o.osmValue.id = : osmValueId AND o.TIPtype.id = :tipTypeId";
        OSMType osmType = (OSMType) getSession().createQuery(queryString).uniqueResult();
        if (osmType == null){
            throw new InstanceNotFoundException();
        }else{
            return osmType;
        }
    }
}
