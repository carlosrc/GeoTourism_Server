package com.mmontes.model.dao;

import com.mmontes.model.entity.City;
import com.mmontes.model.entity.TIP.TIP;
import com.mmontes.model.util.genericdao.GenericDaoHibernate;
import com.mmontes.util.GeometryUtils;
import com.mmontes.util.dto.CityDto;
import com.mmontes.util.dto.CityEnvelopeDto;
import com.mmontes.util.exception.InstanceNotFoundException;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("CityDao")
@SuppressWarnings("unchecked")
public class CityDaoHibernate extends GenericDaoHibernate<City, Long> implements CityDao {

    @Override
    public City getCityFromLocation(Geometry location) {
        String queryString = "SELECT c FROM City c WHERE within(:location,c.geom) = true";
        return (City) getSession()
                .createQuery(queryString)
                .setParameter("location", location)
                .uniqueResult();
    }

    @Override
    public List<City> findAll() {
        String queryString = "SELECT c FROM City c";
        return (List<City>) getSession().createQuery(queryString).list();
    }

    @Override
    public City findByOsmId(Long osmId) throws InstanceNotFoundException {
        String queryString = "SELECT c FROM City c WHERE c.osmId = :osmId";
        City city = (City) getSession().createQuery(queryString).setParameter("osmId", osmId).uniqueResult();
        if (city == null) {
            throw new InstanceNotFoundException(osmId, City.class.getName());
        } else {
            return city;
        }
    }

    @Override
    public List<City> getCityEnvelopes() {
        String queryString = "SELECT id ,name,ST_Envelope(geom) as geom,osmid FROM city";
        return (List<City>) getSession().createSQLQuery(queryString).addEntity(City.class).list();
    }
}
