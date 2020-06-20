package kr.ac.jejunu;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String password;
    String name;
    boolean ownerFlag = false;
}
