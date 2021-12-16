package edu.bntu.view.components;

import edu.bntu.view.MainFrame;
import edu.bntu.view.frames.HelpingFrame;
import edu.bntu.view.panels.SearchPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class MenuBar extends JMenuBar {

    private final MainFrame parentFrame;


    public MenuBar(MainFrame parent) {
        super();
        parentFrame = parent;
        add(searchMenu());
    }

    private JMenu searchMenu() {
        JMenu actions = new JMenu("Actions");
        JMenuItem searchMenu = new JMenuItem("Search");
        JMenuItem allMenu = new JMenuItem("All Parts");
        searchMenu.addActionListener(e -> crateSearchDialog());
        allMenu.addActionListener(e -> getAllParts());
        actions.add(searchMenu);
        actions.add(allMenu);
        return actions;
    }

    private void crateSearchDialog() {
        MainFrame.helpingFrame.dispatchEvent(new WindowEvent(MainFrame.helpingFrame, WindowEvent.WINDOW_CLOSING));
        MainFrame.helpingFrame = new HelpingFrame(500, 400, new CustomLabel("Search"), new SearchPanel(parentFrame));
    }

    private void getAllParts() {
        parentFrame.getAllPartObjects();
    }
}