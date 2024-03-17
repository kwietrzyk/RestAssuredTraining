package newframework.test;

import models.Student;
import newframework.base.NewFrameworkTestBase;
import newframework.client.ApiClient;
import newframework.dto.StudentDto;
import newframework.factories.StudentFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NewFrameworkStudentApiTest extends NewFrameworkTestBase {

    private final String baseUri= "https://thetestingworldapi.com";
    private ApiClient I_want;

    @BeforeEach
    public void setupClient() {
        I_want = createApiClient(baseUri);  // variable name "I_want" to pretty read test line
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/studentIds.csv")
    public void shouldGetStudent(String id) {
        assertThat(I_want.getRealStudent(id)
                .execute()
                .getStatusCode())
                .isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void shouldPostNewStudent() {
        Student requestedStudent = StudentFactory.createRegularStudent();
        StudentDto actualStudent = I_want.postRealStudent(requestedStudent).saveAsDto();
//        assertThat(actualStudent.getId()).isNotZero();
//        assertThat(actualStudent.getMiddle_name()).isNotEmpty();
        assertThat(actualStudent).usingRecursiveComparison()
                .ignoringFieldsOfTypes(Integer.class)
                .isEqualTo(requestedStudent);
    }
}
