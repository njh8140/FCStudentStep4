package listeners;


import javax.naming.InitialContext;
import javax.sql.DataSource;

import DAO.StudentDao;
import controls.LoginController;
import controls.LogoutController;
import controls.StudentAddController;
import controls.StudentDeleteController;
import controls.StudentListController;
import controls.StudentSearchController;
import controls.StudentUpdateController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("Listener init();");
    	try{
			ServletContext sc = sce.getServletContext();
			
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/student"); 
			
			StudentDao studentDao = new StudentDao();
			studentDao.setDataSource(ds);
			sc.setAttribute("/student/list.do", new StudentListController().setStudentDao(studentDao));
			sc.setAttribute("/student/add.do", new StudentAddController().setStudentDao(studentDao));
			sc.setAttribute("/student/update.do", new StudentUpdateController().setStudentDao(studentDao));
			sc.setAttribute("/student/delete.do", new StudentDeleteController().setStudentDao(studentDao));
			sc.setAttribute("/auth/login.do", new LoginController().setStudentDao(studentDao));
			sc.setAttribute("/auth/logout.do", new LogoutController());
			sc.setAttribute("/student/search.do", new StudentSearchController().setStudentDao(studentDao));
			
		} catch(Exception e){
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Listener destroy();");
    	
    }
}