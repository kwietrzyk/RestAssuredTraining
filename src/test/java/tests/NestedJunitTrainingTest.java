package tests;
import org.junit.jupiter.api.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Student service is up and running")
public class NestedJunitTrainingTest {

    @Test
    @DisplayName("When student object is created")
    public void createNewStudent() {
        System.out.println("Student object is created");
    }

    @Nested
    @Order(1)
    @DisplayName("I can perform")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class GetStudentTest {

        @Order(1)
        @Test
        @DisplayName("Get student details")
        public void getStudentId() {
            System.out.println("Student ID verified");
        }

        @Order(2)
        @Test
        @DisplayName("Update student details")
        public void updateStudentId() {
            System.out.println("Student is updated");
        }
    }

    @Nested
    @Order(2)
    @DisplayName("I can persist data")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class PersistDataTest {

        @Order(1)
        @Test
        @DisplayName("In Postgres")
        public void saveStudentToPostgres() {
            System.out.println("Student persisted in Postgres");
        }

        @Order(2)
        @Test
        @DisplayName("In Kafka")
        public void saveStudentToKafka() {
            System.out.println("Student persisted in Kafka");
        }
    }

    @Nested
    @Order(3)
    @DisplayName("I can run University service")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class UniversityServiceTest {

        @Order(1)
        @Test
        @DisplayName("And present data in university dashboard")
        public void presentDataInDashboard() {
            System.out.println("Student is presented in Dashboard");
        }

        @Order(2)
        @Test
        @DisplayName("And send object to DownStream")
        public void sendObjectToDownstream() {
            System.out.println("Student is sent to downstream");
        }

        @Order(3)
        @Test
        @DisplayName("And delete student object")
        public void deleteStudent() {
            System.out.println("Student is deleted");
        }
    }
}
