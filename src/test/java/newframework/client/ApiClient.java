package newframework.client;

import io.restassured.builder.RequestSpecBuilder;
import models.Student;
import newframework.requests.GetMockStudent;
import newframework.requests.GetStudent;
import newframework.requests.PostMockStudent;
import newframework.requests.PostStudent;

import java.util.function.Supplier;

public class ApiClient {

    // add here all request methods similar to getRealStudent and PostRealStudent

    private final Supplier<RequestSpecBuilder> reqSpecBuilder;

    public ApiClient(Supplier<RequestSpecBuilder> reqSpecBuilder) {
        this.reqSpecBuilder = reqSpecBuilder;
    }

    public GetStudent getRealStudent(String studentId) {
        return new GetStudent(studentId, reqSpecBuilder.get());
    }

    public PostStudent postRealStudent(Student student) {
        return new PostStudent(student, reqSpecBuilder.get());
    }

    public GetMockStudent getMockStudent(String name) {
        return new GetMockStudent(name, reqSpecBuilder.get());
    }

    public PostMockStudent postMockStudent(String name) {
        return new PostMockStudent(name, reqSpecBuilder.get());
    }
}
