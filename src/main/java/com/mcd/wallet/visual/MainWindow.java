package com.mcd.wallet.visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.db4o.ObjectSet;
import com.mcd.wallet.db.DBController;
import com.mcd.wallet.db.IDBController;
import com.mcd.wallet.objects.Category;
import com.mcd.wallet.objects.Operation;
import com.mcd.wallet.utils.VisualStaticUtils;
import com.mcd.wallet.utils.WalletAction;

public class MainWindow {

	private JFrame mainWindow;
	private JMenuBar menuBar;
	private JTabbedPane tabbedPane;

	private TableCategories tCategories;
	private TableOperations tOperations;

	IDBController db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initializeGraphics();

		db = DBController.getInstance();

		fillCategories();
		fillOperations();
	}

	public void initializeGraphics() {
		mainWindow = new JFrame();
		mainWindow.setBounds(100, 100, 800, 600);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeMenu();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainWindow.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tOperations = new TableOperations();
		tCategories = new TableCategories();

		JPanel operationsPanel = initializeTab("Operations", tOperations);
		JPanel categoriesPanel = initializeTab("Categories", tCategories);
		JPanel subCategoriesPanel = initializeTab("SubCategories", null);

		JButton newOperationButton = VisualStaticUtils.newAddButton("New",
				operationsPanel);
		JButton modifyOperationButton = VisualStaticUtils.newModifyButton(
				"Modify", operationsPanel);
		JButton deleteOperationButton = VisualStaticUtils.newDeleteButton(
				"Delete", operationsPanel);

		JButton newCategoryButton = VisualStaticUtils.newAddButton("New",
				categoriesPanel);
		JButton modifyCategoryButton = VisualStaticUtils.newModifyButton(
				"Modify", categoriesPanel);
		JButton deleteCategoryButton = VisualStaticUtils.newDeleteButton(
				"Delete", categoriesPanel);

		newOperationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOperation();
			}
		});
		modifyOperationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyOperation();
			}
		});

		deleteOperationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteObject(tOperations);
			}
		});

		newCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCategory();
			}
		});
		modifyCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyCategory();
			}
		});
		deleteCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteObject(tCategories);
			}
		});
	}

	private void initializeMenu() {
		menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);

		// TODO cambiar a literales
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem mntmBuscar = new JMenuItem("Search");
		mntmBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// buscar();
			}
		});
		fileMenu.add(mntmBuscar);
	}

	private JPanel initializeTab(String tabName, JTable table) {
		JPanel tab = new JPanel();
		tabbedPane.addTab(tabName, null, tab, null);
		tab.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 760, 440);
		tab.add(scrollPane);

		if (table != null) {
			scrollPane.setViewportView(table);
		}

		return tab;
	}

	private void addOperation() {
		OperationView operationV = new OperationView(db.selectAllCategories());
		Operation operation = operationV.getOperation();

		db.saveObject(operation);

		fillOperations();
	}

	private void modifyOperation() {
		OperationView operationV = new OperationView(db.selectAllCategories(),
				(Operation) tOperations.getSelection());
		Operation operation = operationV.getOperation();

		db.saveObject(operation);

		fillOperations();
	}

	private void addCategory() {
		CategoryView categoryV = new CategoryView();
		Category category = categoryV.getCategory();

		db.saveObject(category);

		fillCategories();
	}

	private void modifyCategory() {
		CategoryView operationV = new CategoryView(
				(Category) tCategories.getSelection());
		if (operationV.showForm() == WalletAction.OK) {
			Category category = operationV.getCategory();

			db.saveObject(category);

			fillCategories();
		}
	}

	private void deleteObject(AbstractTable table) {
		db.deleteObject(table.getSelection());
		fillCategories();
	}

	private void fillCategories() {
		tCategories.fillTable(db.selectAllCategories());
	}

	private void fillOperations() {
		tOperations.fillTable(db.selectAllOperations());
	}

}
