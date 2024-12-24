package controls;

import java.util.Map;

import DAO.StudentDao;
import bind.DataBinding;
import vo.Student;

public class StudentUpdateController implements Controller, DataBinding {
	StudentDao studentDao;
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"no", Integer.class, "student", vo.Student.class};
	}

	public StudentUpdateController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Student student =(Student)model.get("student");
			
		//if(model.get("student") == null) {
		if(student.getEmail() == null) {
			
			model.put("student", studentDao.selectOne((Integer)model.get("no")));
			
			return "/student/StudentUpdate.jsp";
		} else {
			//Student student = (Student)model.get("student");
			studentDao.update(student);
				
			return "redirect:list.do";
		}
	}
	}