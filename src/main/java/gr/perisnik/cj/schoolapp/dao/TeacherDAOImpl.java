package gr.perisnik.cj.schoolapp.dao;

import gr.perisnik.cj.schoolapp.model.Teacher;
import gr.perisnik.cj.schoolapp.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import javax.inject.Named;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Named("teacherDAOImpl")
public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) {
        EntityManager em = getEntityManager();
        em.persist(teacher);
        return teacher;
    }

    @Override
    public Teacher update(Teacher teacher) {
        EntityManager em = getEntityManager();
        return em.merge(teacher);
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Teacher teacher = em.find(Teacher.class, id);
        if (teacher != null) {
            em.remove(teacher);
        }
    }

    @Override
    public Teacher getTeacherById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Teacher.class, id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        EntityManager em = getEntityManager();
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        return query.getResultList();
    }

    @Override
    public List<Teacher> getTeachersByLastName(String lastName) {
        EntityManager em = getEntityManager();
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t WHERE t.lastName LIKE :lastName", Teacher.class);
        query.setParameter("lastName", "%" + lastName + "%");
        return query.getResultList();
    }

    private EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}