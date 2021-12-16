package edu.bntu.view.panels;

import edu.bntu.model.Geometry;
import edu.bntu.model.Parameter;
import edu.bntu.service.GeometryService;
import edu.bntu.service.ParamsService;
import edu.bntu.service.TableBuilder;
import edu.bntu.view.MainFrame;
import edu.bntu.view.components.CustomLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ParamsPanel extends JPanel {

    private final MainFrame mainFrame;
    private final GeometryService geometryService;
    private final ParamsService paramsService;
    private JScrollPane scrollPane;

    public ParamsPanel(MainFrame mainFrame, Integer objectID){
        this.mainFrame = mainFrame;
        this.geometryService = mainFrame.getGeometryService();
        this.paramsService = mainFrame.getParamsService();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(getGeometryPanel(objectID));
        add(getParamsPanel(objectID));
    }

    private JPanel getGeometryPanel(Integer objectID){
        JPanel geometryPanel = new JPanel();
        geometryPanel.setLayout(new GridLayout(5, 2, 20, 20));

        Geometry geometry = geometryService.getByObjectID(objectID);

        String height = "Not specified";
        String width = "Not specified";
        String length = "Not specified";
        String weight = "Not specified";

        if(geometry != null){
            height = geometry.getHeight().toString();
            width = geometry.getWidth().toString();
            length = geometry.getLength().toString();
            weight = geometry.getWeight().toString();
        }

        geometryPanel.add(new CustomLabel("Height"));
        geometryPanel.add(new CustomLabel(height));
        geometryPanel.add(new CustomLabel("Width"));
        geometryPanel.add(new CustomLabel(width));
        geometryPanel.add(new CustomLabel("Length"));
        geometryPanel.add(new CustomLabel(length));
        geometryPanel.add(new CustomLabel("Weight"));
        geometryPanel.add(new CustomLabel(weight));
        return geometryPanel;
    }

    private JPanel getParamsPanel(Integer objectID){
        JPanel paramsPanel = new JPanel();
        paramsPanel.setLayout(new BoxLayout(paramsPanel, BoxLayout.Y_AXIS));

        ArrayList<Parameter> params = paramsService.getAllByObjectID(objectID);

        Object[][] objects = toParamsList(params);
        TableMouseListener paramsTableMouseListener = new TableMouseListener();
        scrollPane = new JScrollPane( TableBuilder.buildTable(objects, new String[]
                {"Name", "Value"}, paramsTableMouseListener));
        paramsPanel.add(scrollPane);
        return paramsPanel;
    }

    public Object[][] toParamsList(ArrayList<Parameter> parameters) {
        Parameter[] params = parameters.toArray(new Parameter[0]);
        Object[][] objectsString = new String[params.length][2];
        for (int i = 0; i < params.length; i++) {
            objectsString[i][0] = params[i].getName();
            objectsString[i][1] = params[i].getValue();
        }
        return objectsString;
    }

    private class TableMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent mouseEvent) {

        }
    }
}
