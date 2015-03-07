package com.mcd.wallet.visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import com.mcd.wallet.objects.Category;
import com.mcd.wallet.objects.Operation;

public class OperationView extends JDialog {

	private static final int LABEL_X = 10;
	private static final int FIELD_X = 110;

	private final JPanel contentPanel = new JPanel();
	JTextField textDescription;
	JTextField textQuantity;
	JComboBox comboCategories;
	JXDatePicker picker;

	private String description;
	private Float quantity;
	private Date date;
	private Category category;

	List<Category> listCategories;

	public Operation getOperation() {
		Operation operation = new Operation();

		operation.setDescription(description);
		operation.setDia(date);
		operation.setQuantity(quantity);
		operation.setCategory(category);

		return operation;
	}

	public OperationView(List<Category> listCategories) {
		this.listCategories = listCategories;
		initGraphics();
		

		setVisible(true);
	}
	
	public OperationView(List<Category> listCategories, Operation op) {
		this.listCategories = listCategories;
		initGraphics();
		
		textDescription.setText(op.getDescription());
		textQuantity.setText(op.getQuantity().toString());
		picker.setDate(op.getDia());
		
		int indexCategoryCombo = 0;
		for(Category cat: listCategories){
			if(cat.getName().equals(op.getCategory().getName())){
				break;
			}
			indexCategoryCombo++;
		}
		
		comboCategories.setSelectedIndex(indexCategoryCombo); 
		
		setVisible(true);
	}
	

	
	public void initGraphics() {	
		setModal(true);
		setBounds(100, 100, 455, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		// inicializar();

		// Adding Description
		JLabel lbDescription = new JLabel("Description");
		lbDescription.setBounds(LABEL_X, 10, 100, 15);
		contentPanel.add(lbDescription);

		textDescription = new JTextField();
		textDescription.setBounds(FIELD_X, 7, 250, 20);
		contentPanel.add(textDescription);

		// Adding Categories
		JLabel lbCategory = new JLabel("Category");
		lbCategory.setBounds(LABEL_X, 33, 100, 15);
		contentPanel.add(lbCategory);

		List<String> list = new ArrayList<String>();
		for (Category cat : listCategories) {
			list.add(cat.getDescription());
		}
		comboCategories = new JComboBox(list.toArray());
		comboCategories.setBounds(FIELD_X, 30, 250, 20);
		contentPanel.add(comboCategories);

		// Adding Quantity
		JLabel lbQuantity = new JLabel("Quantity");
		lbQuantity.setBounds(LABEL_X, 53, 100, 15);
		contentPanel.add(lbQuantity);

		textQuantity = new JTextField();
		textQuantity.setBounds(FIELD_X, 50, 250, 20);
		contentPanel.add(textQuantity);

		// Adding Date
		JLabel lbDate = new JLabel("Date");
		lbDate.setBounds(LABEL_X, 73, 100, 15);
		contentPanel.add(lbDate);

		picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		picker.setBounds(FIELD_X, 70, 100, 20);

		contentPanel.add(picker);

		// Adding buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cancelar();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void aceptar() {
		description = textDescription.getText();
		quantity = new Float(textQuantity.getText());
		date = picker.getDate();
		category = listCategories.get(comboCategories.getSelectedIndex());
		
		setVisible(false);
	}
}
