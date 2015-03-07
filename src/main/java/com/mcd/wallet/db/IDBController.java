package com.mcd.wallet.db;

import java.util.List;

import com.db4o.ObjectSet;
import com.mcd.wallet.objects.Category;
import com.mcd.wallet.objects.Operation;
import com.mcd.wallet.objects.SubCategory;

public interface IDBController {

	public List<Category> selectAllCategories();
	public List<SubCategory> selectAllSubCategories();
	public List<Operation> selectAllOperations();
	
	public void saveObject(Object object);
	public void deleteObject(Object object);
	public ObjectSet search(Object obj);
}
