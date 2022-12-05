package web.mvc.domain;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    private String usersId;
    
    @Column(nullable = false )
    private String usersPwd;
    @Column(nullable = false, unique = true)
    private String usersPhone;
    @Column(nullable = false, length = 30)
    private String usersEmail;
    @Column(nullable = false, length = 30)
    private String usersNickName;
    @Column(nullable = false, length = 1)
    private int  usersMemberShip;
    
    @CreationTimestamp
    private LocalDateTime usersRegDate;
    
    /*
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Reply> replyList;
    
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Orders> ordersList;
    */
    
    
}