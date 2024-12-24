package controls;

import java.util.Map;

import DAO.StudentDao;

public class StudentListController implements Controller {
	StudentDao studentDao;
	
	public StudentListController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		model.put("students", studentDao.selectAll());
		return "/student/StudentList.jsp";
	}
}