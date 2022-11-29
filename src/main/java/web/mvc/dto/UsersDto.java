package web.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
	
	 private String usersId;
	    
	    private String usersPwd;
	    private String usersPwdCheck;
	    private String usersPhone;
	    private String usersEmail;
	    private String usersNickName;
	    private int  usersMemberShip;
	    private String usersRegDate;
		
		}

