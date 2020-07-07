package models;

import javax.persistence.*;

@Entity
@Table(name = "users_passwords")
public class UserPassword {

    @Id
    @TableGenerator(name = "myGen", table = "my_seq", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myGen")
    private int id;

    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserUnits userUnits;

    public UserPassword() {}

    public UserPassword(String username, String password, UserUnits userUnits) {
        this.username = username;
        this.password = password;
        this.userUnits = userUnits;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
