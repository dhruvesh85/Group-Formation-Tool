package CSCI5308.GroupFormationTool.Course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.Injector;

@Controller
public class CourseController {
  
	private ICourseService courseService;

	@RequestMapping("/course")
	public ModelAndView course(@RequestParam(name="userType") String userType,@RequestParam(name="courseId") String courseId,@RequestParam(name="courseName") String courseName,@RequestParam(name="userId") String userId) throws Exception {
		courseService = Injector.instance().getCourseService();
		ModelAndView model=new ModelAndView("course");
		model.addObject("courseId",courseId);
		model.addObject("userId",userId);
		model.addObject("courseName",courseName);
		model.addObject("userType",userType);
		model.addObject("checkRole",courseService.checkRole(userType));
		model.setViewName("course");
		return model;
	}

	@RequestMapping("/courseadmin")
	public ModelAndView courseAdmin(@RequestParam(name="userType") String userType,
									@RequestParam(name="courseId") String courseId,
									@RequestParam(name="courseName") String courseName,
									@RequestParam(name="userId") String userId,
									@RequestParam(name="checkRole") String checkRole) throws Exception {
		courseService = Injector.instance().getCourseService();
		ModelAndView model=new ModelAndView("courseadmin");
		model.addObject("ta",new TA());
		model.addObject("userId",userId);
		model.addObject("courseId",courseId);
		model.addObject("courseName",courseName);
		model.addObject("userType",courseService.checkUserType(userType));
		model.addObject("checkRole",checkRole);
		model.setViewName("courseadmin");
		return model;
	}

	@RequestMapping("/addta")
	public ModelAndView addta( @RequestParam(name="taId") String taId,@RequestParam(name="courseId") String courseId, @RequestParam(name="courseName") String courseName, @RequestParam(name="userId") String userId ) throws Exception {
		courseService = Injector.instance().getCourseService();
		ModelAndView model=new ModelAndView("courseadmin");
		model.addObject("userId",userId);
		model.addObject("courseId",courseId);
		model.addObject("courseName",courseName);
		model.addObject("result",courseService.addTa(taId,courseId));
		model.setViewName("courseadmin");
		return model;
	}
}
