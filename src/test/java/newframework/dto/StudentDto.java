package newframework.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class StudentDto {
    public int id;
    String first_name;
    String middle_name;
    String last_name;
    String date_of_birth;
}
