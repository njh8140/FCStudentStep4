package controls;

import java.util.Map;

import DAO.StudentDao;
import bind.DataBinding;
import jakarta.servlet.http.HttpSession;
import vo.Student;

public class LoginController implements Controller, DataBinding {
	StudentDao studentDao;
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"loginInfo", vo.Student.class};
	}

	public LoginController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Student loginInfo = (Student)model.get("loginInfo");
		
		//if(model.get("loginInfo") == null) {
		if(loginInfo.getEmail() == null) {
			return "/auth/LoginForm.jsp";
		} else {
			//Student loginInfo = (Student)model.get("loginInfo");
			Student student = studentDao.login(loginInfo.getEmail(), loginInfo.getPwd());
			
			if(student != null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("student", student);
				
				return "redirect:../student/list.do";
			}else {
				return "/auth/LoginFail.jsp";
			}
		}
	}
}