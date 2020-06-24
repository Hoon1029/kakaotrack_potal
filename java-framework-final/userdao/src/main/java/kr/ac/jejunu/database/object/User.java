package kr.ac.jejunu.database.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String id;
    private String password;
    private String name;
    private boolean ownerFlag;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
