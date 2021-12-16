package edu.bntu.dao.interfaces;

import edu.bntu.model.Parameter;
import edu.bntu.model.PartObject;

import java.util.Collection;

public interface ParamsDAO {
    Collection<Parameter> getAllByObjectId(Integer objectId);
}
