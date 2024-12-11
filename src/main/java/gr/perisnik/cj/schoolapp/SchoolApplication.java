package gr.perisnik.cj.schoolapp;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class SchoolApplication extends Application {
    // No direct EntityManager initialization here
}
