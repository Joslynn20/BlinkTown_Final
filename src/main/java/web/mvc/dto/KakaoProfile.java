package web.mvc.dto;

import lombok.Data;

@Data
public class KakaoProfile {

	private Long id;
	private String connecteAt;
	private Properties properties;
	private KakaoAccount kakaoAccount;
	
	@Data
	public class Properties {

		private String nickname;
		public String profilImage;
		public String thumbnaiImage;

}
	
	@Data
	public class KakaoAccount {

		private Boolean profileNicknameNeedAgreement;
		private Profile profile;
		private Boolean hasEmail;
		private Boolean emailNeedAgreement;
		public Boolean isEmaiValid;
		public Boolean isEmailVerified;
		public String email;
		
		@Data
		public class Profile {

			private String nickname;
			public String thumbnailImageUrl;
			public String profileImageUrl;
			
		}
	}
}