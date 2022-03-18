package main.solilo.service;

import main.solilo.bean.Quicky;
import main.solilo.dao.QuickyDAOImpl;
import main.solilo.entity.QuickyEntity;
import main.solilo.exceptions.InvalidKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ArrayList<Quicky> getMessages(int n) throws RuntimeException {
        // convert all the quicky entity to quicky;

        List<QuickyEntity> quickyEntities = QuickyDAOImpl.getQuickyEntities(n);
        ArrayList<Quicky> quickies = new ArrayList<>();

        for(QuickyEntity qe: quickyEntities) {
            // create quicky and add to list
            quickies.add(
                    new Quicky(qe.getCreated(), qe.getMessage(),
                            qe.isVisible(), qe.isModified())
            );
        }
        return quickies;
    }

    public static List<Quicky> getTodayMessages() {
        // convert entity objects to bean objects
        return QuickyDAOImpl.getTodayQuickies().stream().map(
                e -> new Quicky(e.getCreated(), e.getMessage(),
                                e.isVisible(), e.isModified())).
                collect(Collectors.toList());
    }
}
