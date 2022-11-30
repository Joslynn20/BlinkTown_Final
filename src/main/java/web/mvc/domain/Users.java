package web.mvc.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String usersId;
    
    private String usersPwd;
    private String usersPwdCheck;
    private String usersPhone;
    private String usersEmail;
    private String usersNickName;
    private int  usersMemberShip;
    
    @CreationTimestamp
    private LocalDateTime usersRegDate;
}