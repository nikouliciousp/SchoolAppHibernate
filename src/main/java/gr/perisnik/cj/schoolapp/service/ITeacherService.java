package gr.perisnik.cj.schoolapp.service;

import gr.perisnik.cj.schoolapp.dto.TeacherDTO;
import gr.perisnik.cj.schoolapp.service.exceptions.ServiceException;

import java.util.List;

/**
 * Service Interface for Teacher entities.
 * Provides methods to handle business logic related to Teacher operations.
 */
public interface ITeacherService {

    /**
     * Inserts a new teacher into the system.
     *
     * @param teacherDTO The Teacher DTO containing the teacher's information.
     * @return The inserted Teacher DTO.
     * @throws ServiceException if an error occurs during insertion.
     */
    TeacherDTO insertTeacher(TeacherDTO teacherDTO) throws ServiceException;

    /**
     * Updates an existing teacher in the system.
     *
     * @param teacherDTO The Teacher DTO containing the updated information.
     * @return The updated Teacher DTO.
     * @throws ServiceException if an error occurs during update.
     */
    TeacherDTO updateTeacher(TeacherDTO teacherDTO) throws ServiceException;

    /**
     * Deletes a teacher from the system by ID.
     *
     * @param id The ID of the teacher to be deleted.
     * @throws ServiceException if an error occurs during deletion.
     */
    void deleteTeacher(Long id) throws ServiceException;

    /**
     * Retrieves a teacher by their ID.
     *
     * @param id The ID of the teacher.
     * @return The Teacher DTO corresponding to the provided ID.
     * @throws ServiceException if the teacher is not found or an error occurs.
     */
    TeacherDTO getTeacherById(Long id) throws ServiceException;

    /**
     * Retrieves all teachers from the system.
     *
     * @return A list of Teacher DTOs.
     * @throws ServiceException if no teachers are found or an error occurs.
     */
    List<TeacherDTO> getAllTeachers() throws ServiceException;

    /**
     * Retrieves teachers by their last name.
     *
     * @param lastName The last name of the teachers to search for.
     * @return A list of Teacher DTOs matching the provided last name.
     * @throws ServiceException if no teachers are found or an error occurs.
     */
    List<TeacherDTO> getTeachersByLastName(String lastName) throws ServiceException;
}