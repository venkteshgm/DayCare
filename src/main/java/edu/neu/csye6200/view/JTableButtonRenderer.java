/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.view;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jasonpauldarivemula
 */
 public class JTableButtonRenderer implements TableCellRenderer {        
        @Override 
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setPreferredSize(new Dimension(120,30));
            return button;  
        }
    }
