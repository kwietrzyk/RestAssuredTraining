package newframework.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import newframework.client.ExecutableRequest;

import static io.restassured.RestAssured.given;

public class GetMockStudent implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public GetMockStudent(String name, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.addPathParam("name", name);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().get("/api/studentsDetails/{name}");
    }
}
