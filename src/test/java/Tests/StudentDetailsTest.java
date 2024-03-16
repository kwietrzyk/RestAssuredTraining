package Tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import models.Student;

import static io.restassured.RestAssured.given;

public class StudentDetailsTest {

    String url = "https://thetestingworldapi.com";
    String studentsDetails = "/api/studentsDetails";
    String studentBody = """
            {
                "id": 1,
                "first_name": "Angelina",
                "middle_name": "Jolie",
                "last_name": "Camila",
                "date_of_birth": "01/03/1977"
              }
            """;

    Student student;

    @Test
    public void shouldPostNewStudent() {
        given()
                .baseUri(url)
                .basePath(studentsDetails)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(studentBody)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all();
    }

    @Test
    public void shouldGetNewStudent() {
        given()
                .baseUri(url)
                .basePath(studentsDetails)
                .pathParam("studentId", "10093353")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void shouldDeleteNewStudent() {
        String studentId = "10093393";
        given()
                .baseUri(url)
                .basePath(studentsDetails)
                .pathParam("studentId", studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .delete("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void shouldPostOtherNewStudent() {
        student = new Student("Salma", "Hayek", "Dorota", "01/05/1980");
        given()
                .baseUri(url)
                .basePath(studentsDetails)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(student)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all();
    }
}