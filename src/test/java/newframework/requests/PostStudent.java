package newframework.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Student;
import newframework.client.ExecutableRequest;

import static io.restassured.RestAssured.given;

public class PostStudent implements ExecutableRequest {
    private final RequestSpecBuilder requestSpecBuilder;

    public PostStudent(Student student, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.setBody(student);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().post("/api/studentsDetails");
    }
}