package edu.bntu.service;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TableBuilder {

    public static JScrollPane buildTable(Object[][] data, String[] columnNames, MouseListener mouseListener){
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sorter.setSortKeys(sortKeys);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(650, 500));
        table.setRowHeight(30);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setIntercellSpacing(new Dimension(10, 10));
        table.addMouseListener(mouseListener);
        return scrollPane;
    }
}