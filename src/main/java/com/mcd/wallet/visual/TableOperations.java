package com.mcd.wallet.visual;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mcd.wallet.db.DBController;
import com.mcd.wallet.objects.Operation;
import com.mcd.wallet.utils.VisualStaticUtils;

public class TableOperations extends AbstractTable {

	private DefaultTableModel dataModel;

	public TableOperations() {
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
		dataModel.addColumn("Description");
		dataModel.addColumn("Category");
		dataModel.addColumn("Quantity");
		dataModel.addColumn("Date");

		this.setModel(dataModel);

		VisualStaticUtils.hideColumn(this, 0);
	}

	public void fillTable(List<Operation> operations) {

		dataModel.setNumRows(0);
		for (Operation op : operations) {

			Object[] fila = new Object[] {
					DBController.getInstance().getID(op), op.getDescription(),
					op.getCategory(), op.getQuantity(), op.getDia() };

			dataModel.addRow(fila);
		}
	}
}
