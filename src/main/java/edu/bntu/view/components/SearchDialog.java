package edu.bntu.view.components;

import edu.bntu.view.MainFrame;
import edu.bntu.view.panels.SearchPanel;

import javax.swing.*;

public class SearchDialog extends JDialog {

    public SearchDialog(MainFrame parent){
        super(parent);
        setTitle("Search");
        SearchPanel searchPanel = new SearchPanel(parent);
        add(searchPanel);

    }
}
