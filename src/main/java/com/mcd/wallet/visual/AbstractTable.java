package com.mcd.wallet.visual;

import javax.swing.JTable;

import com.mcd.wallet.db.DBController;
import com.mcd.wallet.objects.Category;

public class AbstractTable extends JTable {
	
	public Object getSelection() {
		int index = 0;

		index = this.getSelectedRow();
		if (index == -1)
			return null;

		Long id = (Long) getValueAt(index, 0);

		return DBController.getInstance().getObjectByID(id);
	}
}
