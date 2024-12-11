package gr.perisnik.cj.schoolapp.dao;

import gr.perisnik.cj.schoolapp.model.Teacher;
import java.util.List;

/**
 * Data Access Object Interface for Teacher entities.
 * Defines the standard operations to be performed on Teacher entities.
 *
 * @autor Peris Nik
 */
public interface ITeacherDAO {

    /**
     * Inserts a new Teacher.
     *
     * @param teacher The Teacher entity to be inserted.
     * @return The inserted Teacher entity.
     */
    Teacher insert(Teacher teacher);

    /**
     * Updates an existing Teacher.
     *
     * @param teacher The Teacher entity with updated information.
     * @return The updated Teacher entity.
     */
    Teacher update(Teacher teacher);

    /**
     * Deletes a Teacher by its ID.
     *
     * @param id The ID of the Teacher to be deleted.
     */
    void delete(Long id);

    /**
     * Retrieves a Teacher by its ID.
     *
     * @param id The ID of the Teacher to be retrieved.
     * @return The retrieved Teacher entity.
     */
    Teacher getTeacherById(Long id);

    /**
     * Retrieves all Teachers.
     *
     * @return A list of all Teacher entities.
     */
    List<Teacher> getAllTeachers();

    /**
     * Retrieves Teachers by their last name.
     *
     * @param lastName The last name of the Teachers to be retrieved.
     * @return A list of Teacher entities with the specified last name.
     */
    List<Teacher> getTeachersByLastName(String lastName);
}