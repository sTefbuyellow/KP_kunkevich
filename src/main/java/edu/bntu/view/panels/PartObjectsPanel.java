package edu.bntu.view.panels;

import edu.bntu.model.PartObject;
import edu.bntu.service.ObjectsService;
import edu.bntu.service.TableBuilder;
import edu.bntu.view.components.CustomLabel;
import edu.bntu.view.MainFrame;
import edu.bntu.view.frames.HelpingFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class PartObjectsPanel extends JPanel {

    private final MainFrame mainFrame;
    private final ObjectsService objectsService;
    private JScrollPane scrollPane;


    public PartObjectsPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.objectsService = mainFrame.getObjectsService();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new CustomLabel("Objects"));
        ArrayList<PartObject> roomArrayList = objectsService.getAll();
        Object[][] objects = toObjectsList(roomArrayList);
        TableMouseListener roomsTableMouseListener = new TableMouseListener();
        scrollPane = new JScrollPane( TableBuilder.buildTable(objects, new String[]{"№",
                "Name", "Series", "Manufacturer", "force_H"}, roomsTableMouseListener));
        add(scrollPane);
    }

    public Object[][] toObjectsList(ArrayList<PartObject> roomArrayList) {
        PartObject[] objects = roomArrayList.toArray(new PartObject[0]);
        Object[][] objectsString = new String[objects.length][5];
        for (int i = 0; i < objects.length; i++) {
            objectsString[i][0] = Integer.toString(objects[i].getID());
            objectsString[i][1] = objects[i].getName();
            objectsString[i][2] = objects[i].getSeries();
            objectsString[i][3] = objects[i].getManufacturer();
            objectsString[i][4] = Integer.toString(objects[i].getForceH());

        }
        return objectsString;
    }

    private class TableMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent mouseEvent) {
            JTable table = (JTable) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                int column = 0;
                int row = table.getSelectedRow();
                String value = table.getModel().getValueAt(row, column).toString();
                MainFrame.helpingFrame.dispatchEvent(new WindowEvent(MainFrame.helpingFrame, WindowEvent.WINDOW_CLOSING));
                MainFrame.helpingFrame = new HelpingFrame(500, 400, new CustomLabel("Object № " + value),
                        new ParamsPanel(mainFrame, Integer.parseInt(value)));
            }
        }
    }

}
