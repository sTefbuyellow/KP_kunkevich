package edu.bntu.service;

import edu.bntu.dao.interfaces.ObjectDAO;
import edu.bntu.model.PartObject;

import java.util.ArrayList;

public class ObjectsService {

    private final ObjectDAO objectDAO;

    public ObjectsService(ObjectDAO objectDAO) {
        this.objectDAO = objectDAO;
    }

    public ArrayList<PartObject> getAll(){
        return (ArrayList<PartObject>) objectDAO.getAll();
    }

    public ArrayList<PartObject> search(String statement){
        return (ArrayList<PartObject>) objectDAO.search(statement);
    }
}
