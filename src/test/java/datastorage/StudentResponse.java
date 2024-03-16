package datastorage;

import lombok.*;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentResponse {
    private String status;
    private Data data ;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    static class Data {
        private int id;
        private String first_name;
        private String middle_name;
        private String last_name;
        private String date_of_birth;
    }
}
