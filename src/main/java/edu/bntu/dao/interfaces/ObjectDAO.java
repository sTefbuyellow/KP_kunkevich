package edu.bntu.dao.interfaces;

import edu.bntu.model.PartObject;

import java.util.Collection;
import java.util.List;

public interface ObjectDAO {

    PartObject getById(Integer id);
    Collection<PartObject> getAll();
    Collection<PartObject> search(String searchString);
}
