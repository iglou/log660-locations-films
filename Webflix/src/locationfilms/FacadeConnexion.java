package locationfilms;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class FacadeConnexion {

	private Session uneSession;
	
	public FacadeConnexion() throws Exception{
		
		try {
		
			uneSession = HibernateUtil.getSessionFactory().openSession();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			//closeSession();
		}
	}
	
	public Boolean connexion(String email, String password){

		CourtierBDClient CourtierBDClient = new CourtierBDClient(uneSession);
		
		return CourtierBDClient.connection(email, password);
	}
	
	public void closeSession(){
		if(uneSession.isOpen())
			uneSession.close();
	}
	
}