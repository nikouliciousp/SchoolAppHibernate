package gr.perisnik.cj.schoolapp.rest;

import gr.perisnik.cj.schoolapp.dto.TeacherDTO;
import gr.perisnik.cj.schoolapp.service.TeacherServiceImpl;
import gr.perisnik.cj.schoolapp.service.exceptions.ServiceException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/teachers")
public class TeacherRestController {

    @Inject
    private TeacherServiceImpl teacherService;

    // POST method to insert a new teacher
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTeacher(TeacherDTO teacherDTO) {
        try {
            TeacherDTO createdTeacher = teacherService.insertTeacher(teacherDTO);
            return Response.status(Response.Status.CREATED).entity(createdTeacher).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create teacher: " + e.getMessage())
                    .build();
        }
    }

    // PUT method to update an existing teacher
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("id") Long id, TeacherDTO teacherDTO) {
        try {
            teacherDTO.setId(id); // Ensure the ID is set from the URL
            TeacherDTO updatedTeacher = teacherService.updateTeacher(teacherDTO);
            return Response.ok(updatedTeacher).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update teacher: " + e.getMessage())
                    .build();
        }
    }

    // DELETE method to delete a teacher by ID
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return Response.ok("Teacher deleted successfully").build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete teacher: " + e.getMessage())
                    .build();
        }
    }

    // GET method to fetch a teacher by ID
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacherById(@PathParam("id") Long id) {
        try {
            TeacherDTO teacherDTO = teacherService.getTeacherById(id);
            return Response.ok(teacherDTO).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Teacher not found: " + e.getMessage())
                    .build();
        }
    }

    // GET method to fetch teachers by last name, or all teachers if no last name is provided.
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachersByLastName(@QueryParam("lastName") String lastName) {
        try {
            List<TeacherDTO> teacherDTOList;

            if (lastName != null && !lastName.trim().isEmpty()) {
                // Get teachers by last name
                teacherDTOList = teacherService.getTeachersByLastName(lastName);
            } else {
                // Get all teachers
                teacherDTOList = teacherService.getAllTeachers();
            }

            // If no teachers found, return a 404
            if (teacherDTOList.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No teachers found").build();
            }

            // Return the list of teachers with a 200 OK status
            return Response.ok(teacherDTOList).build();

        } catch (ServiceException e) {
            // If there's a ServiceException, return a 500 Internal Server Error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while retrieving teachers: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            // If a generic exception occurs, return a 500 Internal Server Error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error: " + e.getMessage())
                    .build();
        }
    }
}
