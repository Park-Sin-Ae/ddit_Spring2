package kr.or.ddit.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.mapper.ILoginMapper;
import kr.or.ddit.mapper.IMemberMapper;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
   
   @Inject
   private ILoginMapper loginMapper;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      log.info("# loadUserByUsername() 실행...");
      log.info("# loadUserByUsername() : " +username);
      
      NoticeMemberVO memberVO;
      try {
         memberVO = loginMapper.readByUserId(username);
         log.info("quert by member mapper: " + memberVO);
         return memberVO == null ? null : new CustomUser(memberVO);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

}
