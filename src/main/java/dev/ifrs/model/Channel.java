package dev.ifrs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
public class Channel extends PanacheEntity{

    private String hash;
    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<User> users;

    public Channel() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user){
        this.users.add(user);
    }

}