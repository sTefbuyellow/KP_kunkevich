package edu.bntu.view;

import edu.bntu.dao.implementations.GeometryDAOImpl;
import edu.bntu.dao.implementations.ObjectDAOImpl;
import edu.bntu.dao.implementations.ParamsDAOImpl;
import edu.bntu.dao.interfaces.GeometryDAO;
import edu.bntu.dao.interfaces.ObjectDAO;
import edu.bntu.dao.interfaces.ParamsDAO;
import edu.bntu.service.GeometryService;
import edu.bntu.service.ObjectsService;
import edu.bntu.service.ParamsService;
import edu.bntu.view.components.MenuBar;
import edu.bntu.view.frames.HelpingFrame;
import edu.bntu.view.panels.PartObjectsPanel;
import edu.bntu.view.panels.SearchResultPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    public static HelpingFrame helpingFrame;
    private ObjectsService objectsService;
    private GeometryService geometryService;
    private ParamsService paramsService;
    private JPanel currentPanel;

    public MainFrame() throws HeadlessException {
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 1, 10, 10));
        setLocationRelativeTo(null);

        setJMenuBar(new MenuBar(this));

        helpingFrame = new HelpingFrame(1, 1, new JLabel(), new JPanel());
        helpingFrame.setVisible(false);

        ObjectDAO objectRepository = new ObjectDAOImpl();
        GeometryDAO geometryRepository = new GeometryDAOImpl();
        ParamsDAO paramsRepository = new ParamsDAOImpl();
        objectsService = new ObjectsService(objectRepository);
        geometryService = new GeometryService(geometryRepository);
        paramsService = new ParamsService(paramsRepository);
        currentPanel = new PartObjectsPanel(this);
        add(currentPanel);
        setVisible(true);
    }

    public ObjectsService getObjectsService() {
        return objectsService;
    }

    public GeometryService getGeometryService() {
        return geometryService;
    }

    public ParamsService getParamsService() {
        return paramsService;
    }

    public void displayFoundParameters(SearchResultPanel searchResultPanel) {
        remove(currentPanel);
        currentPanel = searchResultPanel;
        add(currentPanel);
        revalidate();
    }

    public void getAllPartObjects() {
        remove(currentPanel);
        currentPanel = new PartObjectsPanel(this);
        add(currentPanel);
        revalidate();
    }
}
