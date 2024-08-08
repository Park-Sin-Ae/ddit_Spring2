package kr.or.ddit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder{

   private static final Logger log = LoggerFactory.getLogger(CustomNoOpPasswordEncoder.class);
   
   @Override
   public String encode(CharSequence rawPassword) { 
      log.info("encode() 실행");
      log.info("before encode(password): "+ rawPassword);
      return rawPassword.toString();
   }

   @Override
   public boolean matches(CharSequence rawPassword, String encodedPassword) {  // 로그인을 인증하기 위해서 일치하는지 보는 메소드
      log.info("matchs() 실행..");
      log.info("match : " + rawPassword + " : "+encodedPassword);
      return rawPassword.toString().equals(encodedPassword);
   }

}
