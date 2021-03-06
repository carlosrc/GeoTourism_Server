package com.mmontes.model.service;

import com.mmontes.model.entity.route.Route;
import com.mmontes.util.dto.FeatureSearchDto;
import com.mmontes.util.dto.RouteDetailsDto;
import com.mmontes.util.dto.TIPRouteDto;
import com.mmontes.util.exception.InstanceNotFoundException;
import com.mmontes.util.exception.InvalidRouteException;
import com.vividsolutions.jts.geom.Geometry;

import java.util.List;

public interface RouteService {
    RouteDetailsDto create(Long facebookUserId, String name, String description, String travelMode, List<Geometry> partialGeoms, List<Long> tipIds)
            throws InstanceNotFoundException, InvalidRouteException;
    RouteDetailsDto edit(Long routeId, Long facebookUserId, String name, String description, String travelMode, List<Geometry> partialGeoms, List<Long> tipIds)
            throws InstanceNotFoundException, InvalidRouteException;
    void updateRouteFromTIPs(Route route)
            throws InstanceNotFoundException, InvalidRouteException;
    RouteDetailsDto findById(Long routeId,Long facebooUserId)
            throws InstanceNotFoundException;
    List<FeatureSearchDto> find(String boundsWKT, List<String> travelModes, List<Long> facebookUserIds)
            throws InstanceNotFoundException;
    void remove(Long routeId,Long facebookUserId)
            throws InstanceNotFoundException;
    List<TIPRouteDto> getTIPsInOrder(Long routeID)
            throws InstanceNotFoundException;
    Geometry getShortestPath(Long TIPIdOrigin,Long TIPIdDestination,String travelMode) throws InstanceNotFoundException, InvalidRouteException;
}
