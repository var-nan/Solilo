package main.solilo.uitest;

import main.solilo.bean.Quicky;
import main.solilo.dao.QuickyDAOImpl;

public class UITester {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//QuickyDAOImpl daoImpl = new QuickyDAOImpl();
		Quicky quicky = new Quicky("Just checking with all static methods", true);
		try {
			QuickyDAOImpl.addQuicky(quicky);
			System.out.println("Quicky added at "+quicky.getCreated());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("unable to add quicky");
			e.printStackTrace();
		}
		
		// update quicky
		try {
			quicky.setMessage("just checking");
			QuickyDAOImpl.updateQuicky(quicky);
			System.out.println(quicky);
			System.out.println("quicky updated");
		}
		catch (Exception e) {
			System.out.println("unable to update quicky");
			e.printStackTrace();
		}
		
		try {
			QuickyDAOImpl.deleteQuicky(quicky.getCreated());
			System.out.println(quicky);
			System.out.println("quicky deleted successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("unable to delete quicky");
			e.printStackTrace();
		}
	}

}