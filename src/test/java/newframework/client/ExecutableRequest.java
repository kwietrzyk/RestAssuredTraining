package newframework.client;

import io.restassured.response.Response;

@FunctionalInterface
public interface ExecutableRequest {
    Response execute();
}
