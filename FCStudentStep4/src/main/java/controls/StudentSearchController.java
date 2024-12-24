package controls;

import java.util.Map;

import DAO.StudentDao;
import bind.DataBinding;

public class StudentSearchController implements Controller, DataBinding {
	
	StudentDao studentDao;

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"keyword", String.class};
	}

	public StudentSearchController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String keyword = (String)model.get("keyword");
		model.put("students", studentDao.searchName(keyword));
		
		return "/student/StudentList.jsp";
		}
	}