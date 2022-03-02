package main.solilo.dao;

import main.solilo.bean.Quicky;
import main.solilo.exceptions.InvalidKeyException;

public interface QuickyDAO {
	boolean addQuicky(Quicky quicky) throws Exception;
	
	boolean deleteQuicky(String time) throws InvalidKeyException, Exception;
	
	boolean updateQuicky(Quicky quicky) throws InvalidKeyException, Exception;
	
	Quicky getQuicky(String time) throws InvalidKeyException, Exception;
}
