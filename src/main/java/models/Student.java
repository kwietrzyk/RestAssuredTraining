package models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {
    String first_name;
    String middle_name;
    String last_name;
    String date_of_birth;
}
