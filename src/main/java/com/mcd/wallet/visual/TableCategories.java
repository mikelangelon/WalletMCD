package com.mcd.wallet.visual;

import java.util.List;

import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mcd.wallet.db.DBController;
import com.mcd.wallet.objects.Category;
import com.mcd.wallet.utils.VisualStaticUtils;

public class TableCategories extends AbstractTable {

	private DefaultTableModel dataModel;

	public TableCategories() {
		super();

		init();
	}

	private void init() {

		dataModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		dataModel.addColumn("ID");
		dataModel.addColumn("Name");
		dataModel.addColumn("Description");
		dataModel.addColumn("Type");

		this.setModel(dataModel);
		
		VisualStaticUtils.hideColumn(this, 0);
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dataModel);

	    setRowSorter(sorter);
	}
	
	public void fillTable(List<Category> categories) {

		dataModel.setNumRows(0);
		for (Category cat : categories) {
			
			Object[] fila = new Object[] { DBController.getInstance().getID(cat), cat.getName(),
					cat.getDescription(), cat.isPositive()};

			dataModel.addRow(fila);
		}
	}
}
