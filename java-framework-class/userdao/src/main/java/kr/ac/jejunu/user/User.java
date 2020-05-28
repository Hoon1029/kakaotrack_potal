package kr.ac.jejunu.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  User {
    private Integer id;
    private String name;
    private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
