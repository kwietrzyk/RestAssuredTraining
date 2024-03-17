package newframework.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import newframework.client.ExecutableRequest;

import static io.restassured.RestAssured.given;

public class GetStudent implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public GetStudent(String studentId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.addPathParam("studentId", studentId);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().get("/api/studentsDetails/{studentId}");
    }
}
