package main.solilo.service;

import main.solilo.bean.Quicky;
import main.solilo.dao.QuickyDAOImpl;
import main.solilo.exceptions.InvalidKeyException;

public class QuickyService {
    //
    public static boolean addMessage(String message, boolean isVisible)
            throws Exception{
        // input - message, create a quicky object, and store in database
        Quicky q = new Quicky(message,isVisible);
        // call dao
        return QuickyDAOImpl.addQuicky(q);
    }

    public static boolean updateMessage(String time, String message)
            throws InvalidKeyException, Exception{
        // create new quicky and call it
        Quicky q = new Quicky(time, message);
        return QuickyDAOImpl.updateQuicky(q);
    }

    public static boolean deleteMessage(String time)
            throws InvalidKeyException, Exception {
        // call dao
        return QuickyDAOImpl.deleteQuicky(time);
    }
}
