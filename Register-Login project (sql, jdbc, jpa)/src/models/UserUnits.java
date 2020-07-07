package models;

import javax.persistence.*;

@Entity
@Table(name = "user_units")
public class UserUnits {

    @Id
    @TableGenerator(name = "myGen", table = "my_seq", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myGen")
    private int id;

    @Column(name = "usr_description")
    private String description;

    @Column(name = "user_fullname")
    private String fullName;

    public UserUnits() {}

    public UserUnits(String description, String fullName) {
        this.description = description;
        this.fullName = fullName;
    }


}
