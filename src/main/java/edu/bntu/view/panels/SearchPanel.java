package edu.bntu.view.panels;

import edu.bntu.dao.interfaces.ObjectDAO;
import edu.bntu.service.ObjectsService;
import edu.bntu.view.MainFrame;
import edu.bntu.view.components.CustomJTextField;
import edu.bntu.view.components.CustomLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    private final MainFrame mainFrame;
    private final CustomJTextField name;
    private final CustomJTextField series;
    private final CustomJTextField manufacturer;
    private final CustomJTextField force_H;

    public SearchPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(5, 2, 20, 20));


        name = new CustomJTextField("");
        series = new CustomJTextField("");
        manufacturer = new CustomJTextField("");
        force_H = new CustomJTextField("");

        fieldsPanel.add(new CustomLabel("Name"));
        fieldsPanel.add(name);
        fieldsPanel.add(new CustomLabel("Series"));
        fieldsPanel.add(series);
        fieldsPanel.add(new CustomLabel("Manufacturer"));
        fieldsPanel.add(manufacturer);
        fieldsPanel.add(new CustomLabel("Force (H)"));
        fieldsPanel.add(force_H);

        Button searchButton = new Button("Search");
        searchButton.addActionListener(new SearchActionListener());

        add(fieldsPanel);
        add(searchButton);
    }

    private class SearchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder stringBuilder = new StringBuilder("");
            if(!"".equals(name.getText())){
                if (!"".equals(stringBuilder.toString()))
                    stringBuilder.append("and ");
                stringBuilder.append("name like '%").append(name.getText()).append("%' ");
            }
            if(!"".equals(series.getText())){
                if (!"".equals(stringBuilder.toString()))
                    stringBuilder.append("and ");
                stringBuilder.append("series like '%").append(series.getText()).append("%'");
            }
            if(!"".equals(manufacturer.getText())){
                if (!"".equals(stringBuilder.toString()))
                    stringBuilder.append("and ");
                stringBuilder.append("manufacturer like '%").append(manufacturer.getText()).append("%'");
            }
            if(!"".equals(force_H.getText())){
                int force;
                try{
                    force = Integer.parseInt(force_H.getText());
                }
                catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(SearchPanel.this,
                            "Incorrect format (Force)",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!"".equals(stringBuilder.toString()))
                    stringBuilder.append("and ");
                stringBuilder.append("force_H > ").append(force - 100).append(" and force_H <").append(force - 100);
            }

            mainFrame.displayFoundParameters(new SearchResultPanel(mainFrame ,stringBuilder.toString()));
        }
    }
}
