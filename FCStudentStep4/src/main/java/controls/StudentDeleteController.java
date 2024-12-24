package controls;

import java.util.Map;

import DAO.StudentDao;
import bind.DataBinding;

public class StudentDeleteController implements Controller, DataBinding {
	StudentDao studentDao;
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"no", Integer.class};
	}

	public StudentDeleteController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
			studentDao.delete((Integer)model.get("no"));
			
			return "redirect:list.do";
		}
	}