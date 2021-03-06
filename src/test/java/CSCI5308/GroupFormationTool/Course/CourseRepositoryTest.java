package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.IUser;
import CSCI5308.GroupFormationTool.UserManager.Instructor;
import CSCI5308.GroupFormationTool.UserManager.UserMockDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseRepositoryTest {

    private ICourseRepository courseRepository;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        courseRepository = mock(CourseRepository.class);
        CourseAbstractFactory.instance().setCourseRepository(courseRepository);
    }

    @Test
    public void getUserDetailsOnCourse() throws Exception {
        IUser user = UserMockDB.setDefault();
        when(courseRepository.getUserDetailsOnCourse(user, "CSCI10")).thenReturn(false);
        assertEquals(false, courseRepository.getUserDetailsOnCourse(user, "CSCI10"));
    }

    @Test
    public void getEnrollCourse() {
        IUser iUser = UserMockDB.setDefault();
        when(courseRepository.enrollStudentForCourse(iUser, "CSCI10")).thenReturn(true);
        assertEquals(true, courseRepository.enrollStudentForCourse(iUser, "CSCI10"));
    }

    @Test
    public void getEnrollCourseFails() {
        IUser iUser = UserMockDB.setDefault();
        when(courseRepository.enrollStudentForCourse(iUser, "CSCI10")).thenReturn(false);
        assertEquals(false, courseRepository.enrollStudentForCourse(iUser, "CSCI10"));
    }

    @Test
    void createCourseRepo() throws SQLException {
        ICreateCourse createCourse = new CreateCourse();
        createCourse.setCourseName("testname");
        createCourse.setCourseId("testid");
        assertTrue(createCourse.getCourseName().length() < 200);
        assertTrue(createCourse.getCourseId().length() < 10);
        assertFalse(createCourse.getCourseName().isEmpty());
        assertFalse(createCourse.getCourseId().isEmpty());
        assertTrue(createCourse.getCourseId() instanceof String);
        assertTrue(createCourse.getCourseName() instanceof String);
    }

    @Test
    void deleteCourseRepo() throws Exception {
        IDeleteCourse deleteCourse = new DeleteCourse();
        deleteCourse.setSelectedCourseId("testname");
        assertTrue(deleteCourse.getSelectedCourseId().length() <= 200);
        assertFalse(deleteCourse.getSelectedCourseId().isEmpty());
        assertTrue(deleteCourse.getSelectedCourseId() instanceof String);
    }

    @Test
    void assignInstructorForCourse() {
        IInstructor assignInstructor = new Instructor("test");
        assignInstructor.setInstructorId("B00123456");
        assignInstructor.setSelectedInstructorCourseId("testname");
        assertTrue(assignInstructor.getSelectedInstructorCourseId().length() <= 200);
        assertFalse(assignInstructor.getSelectedInstructorCourseId().isEmpty());
        assertTrue(assignInstructor.getSelectedInstructorCourseId() instanceof String);
        assertTrue(assignInstructor.getInstructorId().length() <= 10);
        assertFalse(assignInstructor.getInstructorId().isEmpty());
        assertTrue(assignInstructor.getInstructorId() instanceof String);
    }
}
