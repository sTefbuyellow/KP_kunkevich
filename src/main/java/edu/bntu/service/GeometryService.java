package edu.bntu.service;

import edu.bntu.dao.interfaces.GeometryDAO;
import edu.bntu.model.Geometry;

import java.util.ArrayList;

public class GeometryService {

    private final GeometryDAO geometryRepository;

    public GeometryService(GeometryDAO geometryRepository) {
        this.geometryRepository = geometryRepository;
    }

    public Geometry getByObjectID(Integer objectID){
        return geometryRepository.getByObjectId(objectID);
    }
}
