package com.mcd.wallet.visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mcd.wallet.objects.Category;
import com.mcd.wallet.utils.WalletAction;

public class CategoryView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextField textName;
	JTextArea textArea;
	ButtonGroup group;
	Category category;
	private String name;
	private String description;
	private boolean isPositive;
	JRadioButton r1;
	JRadioButton r2;
	
	WalletAction action = WalletAction.CANCEL;

	public Category getCategory() {
		if (category == null)
			category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setPositive(isPositive);
		return category;
	}

	public CategoryView() {
		init();
		setVisible(true);
	}

	public CategoryView(Category category) {
		this.category = category;
		init();
		textName.setText(category.getName());
		textArea.setText(category.getDescription());
		if(category.isPositive()){
			r1.setSelected(true);
		}

		
	}

	
	public WalletAction showForm(){
		setVisible(true);
		return action;
	}
	public void init() {

		setModal(true);
		setBounds(100, 100, 455, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		// inicializar();

		JLabel lblCiudad = new JLabel("Name");
		lblCiudad.setBounds(10, 11, 46, 14);
		contentPanel.add(lblCiudad);

		textName = new JTextField();
		textName.setBounds(66, 8, 86, 20);
		contentPanel.add(textName);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 51, 46, 14);
		contentPanel.add(lblNombre);

		r1 = new JRadioButton("Invoice");
		r1.setActionCommand("true");
		r1.setBounds(10, 81, 100, 14);
		contentPanel.add(r1);

		r2 = new JRadioButton("Expenses");
		r2.setActionCommand("false");
		r2.setBounds(110, 81, 100, 14);
		r2.setSelected(true);
		contentPanel.add(r2);

		group = new ButtonGroup();
		group.add(r1);
		group.add(r2);
		

		textArea = new JTextArea();
		textArea.setBounds(10, 110, 400, 60);
		contentPanel.add(textArea);

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
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void aceptar() {

		if (textName.getText().equals(""))
			return;
		name = textName.getText();
		description = textArea.getText();
		isPositive = new Boolean(group.getSelection().getActionCommand());
		
		action = WalletAction.OK;
		
		setVisible(false);
	}
}
