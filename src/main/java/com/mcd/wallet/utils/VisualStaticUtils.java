package com.mcd.wallet.utils;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class VisualStaticUtils {
	
	
	public static JButton newAddButton(String text, JPanel panel){
		return createNewButton(text, panel, new Rectangle(10, 460, 120, 23));
	}
	
	public static JButton newModifyButton(String text, JPanel panel){
		return createNewButton(text, panel, new Rectangle(140, 460, 120, 23));
	}
	
	public static JButton newDeleteButton(String text, JPanel panel){
		return createNewButton(text, panel, new Rectangle(270, 460, 120, 23));
	}
	
	private static JButton createNewButton(String text, JPanel panel, Rectangle bound){
		JButton newButton = new JButton(text);
		newButton.setBounds(bound);
		panel.add(newButton);
		return newButton;
	}
	
	public static void  hideColumn(JTable table, int columnIndex){
		table.getColumnModel().getColumn(columnIndex).setMinWidth(0);
		table.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
		table.getColumnModel().getColumn(columnIndex).setResizable(false);
	}
	
}
