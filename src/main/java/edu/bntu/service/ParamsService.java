package edu.bntu.service;

import edu.bntu.dao.interfaces.ParamsDAO;
import edu.bntu.model.Parameter;

import java.util.ArrayList;

public class ParamsService {

    private final ParamsDAO paramsRepository;

    public ParamsService(ParamsDAO paramsRepository) {
        this.paramsRepository = paramsRepository;
    }

    public ArrayList<Parameter> getAllByObjectID(Integer objectId){
        return (ArrayList<Parameter>) paramsRepository.getAllByObjectId(objectId);
    }
}
