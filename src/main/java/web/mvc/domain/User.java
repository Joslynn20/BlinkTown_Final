package web.mvc.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usersId;
    
    private String usersPwd;
    private String usersPhone;
    private String usersEmail;
    private String usersNickName;
    private int  usersMemberShip;
    
    @CreationTimestamp
    private String usersRegDate;
}