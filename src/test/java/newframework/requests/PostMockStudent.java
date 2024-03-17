package newframework.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.MockStudent;
import models.Student;
import newframework.client.ExecutableRequest;
import newframework.dto.StudentDto;

import static io.restassured.RestAssured.given;

public class PostMockStudent implements ExecutableRequest {
    private final RequestSpecBuilder requestSpecBuilder;

    public PostMockStudent(String name, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.setBody(name);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().post("/api/studentsDetails");
    }
}