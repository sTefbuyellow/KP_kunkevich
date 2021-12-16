package edu.bntu.dao.interfaces;

import edu.bntu.model.Geometry;
import edu.bntu.model.Parameter;

import java.util.Collection;

public interface GeometryDAO {
    Geometry getByObjectId(Integer objectId);
}
