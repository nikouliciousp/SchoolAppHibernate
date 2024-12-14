package gr.perisnik.cj.schoolapp.service;

import gr.perisnik.cj.schoolapp.dao.ITeacherDAO;
import gr.perisnik.cj.schoolapp.dto.TeacherDTO;
import gr.perisnik.cj.schoolapp.model.Teacher;
import gr.perisnik.cj.schoolapp.service.exceptions.ServiceException;
import gr.perisnik.cj.schoolapp.service.util.JPAHelper;
import gr.perisnik.cj.schoolapp.service.util.LoggerUtil;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
@Named("teacherServiceImpl")
public class TeacherServiceImpl implements ITeacherService {

    @Inject
    private ITeacherDAO teacherDAO;

    @Override
    public TeacherDTO insertTeacher(TeacherDTO teacherDTO) throws ServiceException {
        try {
            beginTransaction();

            Teacher teacher = new Teacher();
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher = teacherDAO.insert(teacher);

            commitTransaction();
            return convertToDTO(teacher);
        } catch (Exception e) {
            rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Insert teacher - " +
                    "rollback - entity already exist");
            throw new ServiceException("Failed to insert teacher", e);
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO) throws ServiceException {
        try {
            beginTransaction();

            Teacher teacher = teacherDAO.getTeacherById(teacherDTO.getId());
            if (teacher == null) {
                throw new ServiceException("Teacher not found");
            }
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher = teacherDAO.update(teacher);

            commitTransaction();
            return convertToDTO(teacher);
        } catch (Exception e) {
            rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Update teacher - " +
                    "rollback - entity not found");
            throw new ServiceException("Failed to update teacher", e);
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public void deleteTeacher(Long id) throws ServiceException {
        try {
            beginTransaction();
            teacherDAO.delete(id);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Delete teacher - " +
                    "rollback - entity not found");
            throw new ServiceException("Failed to delete teacher", e);
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public TeacherDTO getTeacherById(Long id) throws ServiceException {
        try {
            Teacher teacher = teacherDAO.getTeacherById(id);
            if (teacher == null) {
                throw new ServiceException("Teacher not found");
            }
            return convertToDTO(teacher);
        } catch (Exception e) {
            LoggerUtil.getCurrentLogger().warning("Get teacher - " +
                    "entity not found");
            throw new ServiceException("Failed to retrieve teacher", e);
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public List<TeacherDTO> getAllTeachers() throws ServiceException {
        try {
            List<Teacher> teachers = teacherDAO.getAllTeachers();
            if (teachers.isEmpty()) {
                throw new ServiceException("No teachers found");
            }
            return teachers.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LoggerUtil.getCurrentLogger().warning("Get all teachers - " +
                    "entities not found");
            throw new ServiceException("Failed to retrieve teachers", e);
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public List<TeacherDTO> getTeachersByLastName(String lastName) throws ServiceException {
        try {
            List<Teacher> teachers = teacherDAO.getTeachersByLastName(lastName);
            if (teachers.isEmpty()) {
                throw new ServiceException("No teachers found with last name: " + lastName);
            }
            return teachers.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LoggerUtil.getCurrentLogger().warning("Get teachers - " +
                    "entities not found");
            throw new ServiceException("Failed to retrieve teachers by last name", e);
        } finally {
            closeEntityManager();
        }
    }

    private void beginTransaction() {
        // Start a transaction
        JPAHelper.beginTransaction();
    }

    private void commitTransaction() {
        // Commit the transaction
        JPAHelper.commitTransaction();
    }

    private void rollbackTransaction() {
        // Rollback the transaction
        JPAHelper.rollBackTransaction();
    }

    private void closeEntityManager() {
        // Close the EntityManager to release the database resources
        JPAHelper.closeEntityManager();
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        return teacherDTO;
    }
}
