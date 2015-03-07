package com.mcd.wallet.db;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectSet;
import com.mcd.wallet.objects.Category;
import com.mcd.wallet.objects.Operation;
import com.mcd.wallet.objects.SubCategory;
import com.mcd.wallet.utils.Constants;
import com.mcd.wallet.utils.Util;

public class DBController implements IDBController{
	
	private static DBController instance = null;
	
	public static DBController getInstance() {
	      if(instance == null) {
	         instance = new DBController();
	      }
	      return instance;
	   }
	
	protected  DBController(){
		initConection();
	}
	
	private void initConection() {
		Util.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				Constants.DATABASE_FILENAME);
	}
	@Override
	public List<Category> selectAllCategories() {
		List<Category> categories = Util.db.query(Category.class);
		return categories;
	}

	@Override
	public List<SubCategory> selectAllSubCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operation> selectAllOperations() {
		List<Operation> operations = Util.db.query(Operation.class);
		return operations;
	}

	@Override
	public void saveObject(Object object) {
		Util.db.store(object);
		Util.db.commit();
	}
	
	@Override
	public ObjectSet search(Object obj){
		return Util.db.queryByExample(obj);
	}

	@Override
	public void deleteObject(Object object) {
		Util.db.delete(object);
		Util.db.commit();
	}
	
	public long getID(Object obj){
		return Util.db.ext().getID(obj);
	}
	
	public Object getObjectByID(Long id){
		return Util.db.ext().getByID(id);
	}

}
