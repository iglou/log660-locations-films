
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	public static void main(String[] args) {

		Session sessionHome = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = sessionHome.beginTransaction();
			Client client1 = new Client();
			Client client2 = new Client();
			
			/*Set<Course> courses = new HashSet<Course>();
			courses.add(course1);
			courses.add(course2);*/

			/*Student student1 = new Student(1, "Eswar", courses);
			Student student2 = new Student(2, "Joe", courses);
			sessionHome.save(course1);
			sessionHome.save(course2);
			sessionHome.save(student1);
			sessionHome.save(student2);*/

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			sessionHome.close();
		}

	}
}

