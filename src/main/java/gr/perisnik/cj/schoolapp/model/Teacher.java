package gr.perisnik.cj.schoolapp.model;

import jakarta.persistence.*;

/**
 * Represents a Teacher entity with attributes id, firstName, and lastName.
 * Uses JPA annotations for ORM mapping.
 *
 * @author Peris Nik
 */
@Entity
@Table(name = "TEACHERS", indexes = {
        @Index(name = "LASTNAME_IDX", columnList = "LASTNAME")
})
public class Teacher {

    /**
     * The unique identifier for the teacher.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The first name of the teacher.
     */
    @Column(name = "FIRSTNAME", length = 50, nullable = true, unique = false)
    private String firstName;

    /**
     * The last name of the teacher.
     */
    @Column(name = "LASTNAME", length = 50, nullable = true, unique = false)
    private String lastName;

    /**
     * Gets the ID of the teacher.
     *
     * @return the teacher ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the teacher.
     *
     * @param id the teacher ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the teacher.
     *
     * @return the teacher's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the teacher.
     *
     * @param firstName the teacher's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the teacher.
     *
     * @return the teacher's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the teacher.
     *
     * @param lastName the teacher's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns a string representation of the teacher.
     *
     * @return a string representation of the teacher
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}