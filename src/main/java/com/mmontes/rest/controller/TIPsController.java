package com.mmontes.rest.controller;

import com.mmontes.model.service.TIPService;
import com.mmontes.util.GeometryConversor;
import com.mmontes.util.dto.TIPSearchDto;
import com.mmontes.util.exception.GeometryParsingException;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TIPsController {

    @Autowired
    private TIPService tipService;

    @RequestMapping(value = "/tips", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<TIPSearchDto>>
    find(@RequestParam(value = "facebookUserId", required = false) Long facebookUserId,
         @RequestParam(value = "bounds", required = false) String boundsWKT,
         @RequestParam(value = "type", required = false) List<Long> typeIds,
         @RequestParam(value = "cityId", required = false) List<Long> cityIds,
         @RequestParam(value = "favouritedBy", required = false) Integer favouritedBy) {

        System.out.println("Find TIPs:");
        System.out.println(facebookUserId);
        System.out.println(boundsWKT);
        System.out.println(typeIds);
        System.out.println(cityIds);
        System.out.println(favouritedBy);

        //TODO: validate params
        Polygon bounds = null;
        try {
            if (boundsWKT != null) {
                bounds = (Polygon) GeometryConversor.geometryFromWKT(boundsWKT);
            }
        } catch (GeometryParsingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<TIPSearchDto> tips = tipService.find(facebookUserId, bounds, typeIds, cityIds, favouritedBy);

        return new ResponseEntity<>(tips, HttpStatus.OK);
    }
}
