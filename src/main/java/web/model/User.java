package web.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "non_expired")
    private byte isAccountNonExpired;

    @Column(name = "non_locked")
    private byte isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private byte isCredentialsNonExpired;

    @Column(name = "enabled")
    private byte isEnabled;

    @OneToMany(targetEntity=Role.class, cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(String name, String password, String lastName, int age){
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRole(){
        return this.roles;
    }

//    public String getRole(){
//        return roles.stream().findFirst().get().getRole();
//    }

    public void setRole(Set roles){
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }


    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        if(this.isAccountNonExpired == 1){
            return true;
        }
        return false;
    }

    public void setIsAccountNonExpired() {
        this.isAccountNonExpired = (byte) 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(this.isAccountNonLocked == 1){
            return true;
        }
        return false;
    }

    public void setIsAccountNonLocked() {
        this.isAccountNonLocked = (byte) 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(this.isCredentialsNonExpired == 1){
            return true;
        }
        return false;
    }

    public void setIsCredentialsNonExpired() {
        this.isCredentialsNonExpired = (byte) 1;
    }

    @Override
    public boolean isEnabled() {
        if(this.isEnabled == 1){
            return true;
        }
        return false;
    }

    public void setIsEnabled() {
        this.isEnabled = (byte) 1;
    }
}
