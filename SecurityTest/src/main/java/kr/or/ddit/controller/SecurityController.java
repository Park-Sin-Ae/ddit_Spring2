package kr.or.ddit.controller;

public class SecurityController {
	/*
	 * 18장 스프링 시큐리티
	 * 
	 * 1. 스프링 시큐리티 소개
	 * - 애플리케이션에서 보안 기능을 구현하는데 사용되는 프레임워크이다.
	 * - 스프링 시큐리티는 필터 기반으로 동작하기 때문에 스프링 MVC와 분리되어 동작한다.
	 * 
	 * 		# 기본 보안 기능
	 * 		- 인증(Authentication)
	 * 			> 애플리케이션 사용자의 정당성을 확인한다.
	 * 		- 인가(Authorization)
	 * 			> 애플리케이션의 리소스나 처리에 대한 접근을 제어한다.\
	 * 
	 * 		# 시큐리티 제공 기능
	 * 		- 세션 관리
	 * 		- 로그인 처리
	 * 		- CSRF 토큰 처리
	 * 		- 암호화 처리
	 * 		- 자동 로그인
	 * 
	 * 		** CSRF 용어 설명
	 * 		- 크로스 사이트 요청 위조는 웹 사이트 취약점 공격의 하나로, 사용자가 자신의 의지와는 무관하게 공격자가
	 * 			의도한 행위(수정,삭제,등록 등)를 특정 웹 사이트에 요청하게 하는 공격을 말한다.
	 * 
	 * 			> CSRF 공격을 대비하기 위해서는 스프링 시큐리티 CSRF Token을 이용하여 인증을 진행한다.
	 * 
	 * 		# 시큐리티 인증 구조
	 * 
	 * 		클라이언트에서 타겟으로 들어가기 위해 요청을 진행합니다.
	 * 		이때, 타겟에 설정되어 있는 요청 접근 권한이 '사용자' 등급일 때로 가정합시다.
	 * 		타겟으로 접근하기 위한 요청을 날렸고 요청 안에서 사용자 등급에 해당하는 인가 정보가 포함되어 있지 않으면 스프링 시큐리티는
	 * 		인증을 진행할 수 있도록 인증 페이지(로그인 페이지)를 제공하여 사용자에게 인증을 요청합니다.
	 * 		사용자는 아이디, 비밀번호를 입력 후 인증을 요청하겠죠?
	 * 		클라이언트에서 서버로 요청한 HttpServletRequest의 요청 객체를 AuthenticationFilter가 요청을
	 * 		Intercept합니다. 그리고 UsernamePasswordAuthenticationToken을 통해 인증을 진행할 토근을 만들어서
	 * 		AuthenticationManager에게 위임합니다.
	 * 		넘겨받은 id,pw를 이용해 인증을 진행하는데 성공 시, Authentication 객체 생성과 성공을 전달하고 그렇지 않으면
	 * 		Exception 에러를 발생시킵니다.
	 * 		인증을 진행하기 위해 Authentication객체를 AhthenticationProvider에게 전달하고 인증을 진행하는데,
	 * 		이 때 입력받은 아이디 비밀번호와 일치하는 회원정보를 데이터베이스에 저장되어 있는 여러 정보들과 비교해서 일치하는 정보를
	 * 		조회합니다. 조회된 회원정보를 UserDetailService에서 넘겨받은 Authentication 객체 저보안에 있는 회원정보를
	 * 		UserDetail 객체로 만들고, 아이디, 비밀번호, 권한들에 대한 정보들로 User 객체를 만듭니다.
	 * 		만들어진 객체 정보는 이 후 SecurityContextHolder인 메모리 공간 내 존재하는 최종 객체 정보에서 꺼낼 값이 됩니다.
	 * 		조회된 정보들로 내가 입력한 비밀번호와 데이터베이스에서 조회된 정보와 일치됬을 때 인증이 성공합니다.
	 * 		성공된 상태 flag는 Authentication 객체 내 상태로 저장되고 인증이 완료된 객체정보를 Provider가 Manager한테
	 * 		전달해주면, AuthenticationFilter를 통해서 시큐리티 내 InMemory 공간에 해당하는 영역안에 저장을 합니다.
	 * 		SecurityContextHolder안에 SecurityContext안에 Authentication 객체 안에 Principal 객체정보로
	 * 		User객체 안에 저장된 정보를 담아둡니다.
	 * 		그리고 최종 타겟으로 넘어가기 전 JSESSIONID인 세선에 대한 쿠키 정보값이 유효한지를 채킹 후, 유효하다면 타겟정보로
	 * 		넘어가 인증된 회원정보로 타겟 화면을 확인할 수 있습니다.
	 * 
	 * 		
	 * 2. 스프링 시큐리티 설정
	 * 	
	 * 		# 환경 설정
	 * 			- 의존 라이브러리 설정(pom.xml 설정)
	 * 				> spring-security-web
	 * 				> spring-security-config
	 * 				> spring-security-core
	 * 				> spring-security-taglibs
	 * 
	 * 		# 웹 컨테이너 설정(web.xml 설정)
	 * 			- 스프링 시큐리티가 제공하는 서블릿 필터 클래스를 서블릿 컨테이너에 등록한다. (web.xml)
	 * 			- 스프링 시큐리티가 필터 기반으로 시큐리티 필터체인을 등록한다.
	 * 				> context-param 태그의 param-value 추가
	 * 				(추가 파라미터 :/WEB-INF/spring/security-context.xml)
	 * 				> SpringSecurityFilterChain 추가
	 * 
	 * 		# 스프링 시큐리티 설정
	 * 			- 스프링 시큐리티 컴포넌트를 빈으로 정의한다.
	 * 			- Spring/security-context.xml 설정
	 * 
	 * 		# 웹 화면 접근 정책
	 * 			- 웹 화면 접근 정책을 정한다.(테스트를 위한 각 화면당 접근 정책을 설정한다.)
	 * 
	 * 				대상		|	화면		|	접근정책
	 * 			────────────────────────────────────────────────
	 * 			일반 게시판		| 목록화면		| 모두가 접근 가능하다.
	 * 						| 등록화면		| 로그인 한 회원만 접근 가능하다
	 * 			────────────────────────────────────────────────
	 * 			공지사항 게시판	| 목록화면		| 모두가 접근 가능하다.
	 * 						| 등록화면		| 로그인 한 관리자만 접근 가능하다.
	 * 			────────────────────────────────────────────────
	 * 
	 * 		# 화면 설정
	 * 			- 컨트롤러
	 * 				> security.SecurityBoardController
	 * 				> security.SecurityNoticeController
	 * 			- 화면 페이지
	 * 				> security/board/list
	 * 				> security/board/register
	 * 				> security/notice/list
	 * 				> secirity/notice/register
	 * 
	 * 3. 접근 제한 설정
	 * - 시큐리티 설정을 통해서 특정 URI에 대한 접근을 제한할 수 있다.
	 * 
	 * 		# 환경 설정
	 * 		- 스프링 시큐리티 설정
	 * 			> URI 패턴으로 접근 제한을 설정한다.
	 * 			> security-context.xml 설정
	 * 				<security:intercept-url patter="/board/list" access="permitAll"/>
	 * 				<security:intercept-url patter="/board/register" access="hasRole('ROLE_MEMBER')"/>
	 * 
	 * 		# 화면 설정
	 * 		- 일반 게시판 목록 화면(모두 접근 가능하도록 되어 있다: permitAll)
	 * 		- 일반 게시판 등록 화면(회원권한을 가진 사용자만 접근 가능 : hasRole('ROLE_MEMBER'))
	 * 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지로 이동합니다.
	 * 		- 공지사항 게시판 목록 화면(모두 접근 가능하도록 되어 있다 : permitAll)
	 * 		- 공지사항 게시판 등록 화면(관리자 권한을 가진 사용자만 접근 가능 : hasRole('ROLE_ADMIN'))
	 * 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지로 이동합니다.
	 * 
	 * 4. 로그인 처리
	 * - 메모리 상에 아이디와 패스워드를 저장하고 로그인을 처리한다.
	 * - 스프링 시큐리티5버전부터는 패스워드 암호화 처리기를 반드시 이용하도록 변경이 되었다.
	 * - 암호화 처리기를 사용하지 않도록 하기위해서는 "{noop}" 문자열을 비밀번호 앞에 사용한다.
	 * 
	 * 		# 환경 설정
	 * 		- 스프링 시큐리티 설정
	 * 			> security-context.xml 설정
	 * 		- <security:authentication-manager>
	 * 		-	<security:authentication-provider>
	 * 		-		<security:user name="member" password="{noop}1234" authrities="ROLE_MEMBER"/>
	 * 		-	</security:authentication-provider>
	 * 		- </security:authentication-manager>
	 * 
	 * 		# 화면 설정
	 * 		- 일반 게시판 등록 화면
	 * 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지가 연결되고,
	 * 				일반 회원 등급이 ROLE_MEMBER 권한을 가진 member 계정으로 로그인 후 해당 페이지로 접근 가능
	 * 			> 관리자 등급인 admin 계정은 ROLE_MEMBER 권한도 가지고 있는 계정이므로 해당 페이지로 접근 가능
	 * 		- 공지사항 게시판 등록 화면
	 * 			> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지가 연결되고,
	 * 				관리자 등급인 ROLE_ADMIN 권한을 가진 admin 계정으로 로그인 후 해당 페이지로 접근 가능
	 * 
	 * 5. 접근 거부 처리
	 * - 접근 거부가 발생한 상황을 처리하는 접근 거부 처리자의 URI를 지정할 수 있다.
	 * 
	 * 		# 환경 설정
	 * 		- 스프링 웹 설정 (servlet-context.xml 설정)
	 * 			> <context:component-scan base-package="kr.or.ddit.security"/>
	 * 
	 *  	- 스프링 시큐리티 설정(security-context.xml 설정)
	 *  		> 접근 거부 처리자의 URI를 지정
	 *  		> <secirity:access-denied-handler error-page="/accessError"/>
	 *  
	 *  	# 접근 거부 처리
	 *  	- 접근 거부 처리 컨트롤러 작성
	 *  		> security/CommonCotroller
	 *  
	 *  	# 화면 설명
	 *  	- 일반 게시판 등록 페이지
	 *  		> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로 접근 시 접근 가능
	 *  	- 공지사항 게시판 등록 페이지
	 *  		> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로 접근 시에
	 *  			공지사항 게시판 등록 페이지는 관리자 권한으로만 접근 가능하므로 접근이 거부된다.
	 *  			이때, access-denied-handler로 설정되어 있는 URI로 이동하고 해당 페이지에서 접근이 거부되었을 때
	 *  			보여질 페이지의 정보가 나타난다.
	 *  
	 *  6. 사용자 정의 접근 거부 처리자
	 *  - 접근 거부가 발생한 상황에 단순 메세지 처리 이상의 다양한 처리를 하고 싶다면 AccessDeniedHandler를 직접 구현해야 한다.
	 *  
	 *  	# 환경 설정
	 *  	- 스프링 시큐리티 설정(security-context.xml 설정)
	 *  		> id가 customAccessDenied를 가진 빈을 등록한다.
	 *  		> <security:access-denied-handler ref="customAccessDenied"/>
	 *  
	 *  	# 접근 거부 처리자 클래스 정의
	 *  	- 사용자가 정의한 접근 거부 처리자
	 *  		> CustomAccessDeniedHandler 클래스 정의
	 *  		- AccessDeniedHandler 인터페이스를 참조받아서 handle 메소드를 재정의하여 사용합니다.
	 *  		: 우리는 접근이 거부되었을 때 빈으로 등록해둔 CustomAccessDeniedHandler 클래스가 발동해	
	 *  			해당 메소드가 실행되고 response 내장객체를 활용하여 다시'/accessError' URL로 이동하여
	 *  			접근 거부 시 보여질 페이지로 이동하지만 이곳에서 더 많은 로직을 처리하길 원하면 request, response
	 *  			내장 객체를 이용하여 다양한 처리가 가능합니다.
	 *  
	 *  	# 화면 설명
	 *  		- 일반 게시판 등록 페이지
	 *  			> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로 접근 시 접근 가능
	 *  		- 공지사항 게시판 등록 페이지
	 *  			> 접근 제한에 거렬 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고, 회원 권한을 가진 계정으로 접근 시에
	 *  				공지사항 게시판 등록 페이지는 관리자 권한으로 접근 가능하므로 접근이 거부된다.
	 *  				이 때, access-denied-handler로 설정되어 있는 ref 속성에 부여된 클래스 메소드로 이동하고 해당 페이지에서
	 *  				접근이 거부되었을 때 페이지의 정보가 나타난다. (/accessError 요청 URI로)
	 *  
	 *  7. 사용자 정의 로그인 페이지
	 *  - 기본 로그인 페이지가 아닌 사요자가 직접 정의한 로그인 페이지를 사용한다.
	 *  
	 *  	# 환경 설정
	 *  	- 스프링 시큐리티 설정(security-context.xml 설정)
	 *  		> <security:form-login> 수정
	 *  		> <security:form-login login-page="/login"> 설정
	 *  			: 사용자가 직접 만든 로그인 페이지로 이동할 '/login' URI를 가지고 있는 컨트롤러 메소드를 정의합니다.
	 *  
	 *  	# 로그인 페이지 정의
	 *  	- 사용자가 정의한 로그인 컨트롤러
	 *  		> controller 패키지 안에 LoginController 메소드 생성
	 *  
	 *  	- 사용자가 정의한  로그인 뷰
	 *  		> /views/loginForm.jsp
	 *  
	 *  	** 시큐리티에서 제공하는 기본 로그인 페이지로 이동하지 않고 , 사용자가 정의한 로그인 페이지 URI를 요청하여
	 *  	해당 페이지에서 권한을 체크하도록 합니다.
	 *  	인증이 완료되면 최초의 요청된 target URI로 이동합니다. 
	 *  	그렇지 않은 경우 사용자가 만들어놓은 접근 거부 페이지로 이동합니다.
	 *  
	 *  8. 로그인 성공 처리
	 *  - 로그인을 성공한 후에 로그인 이력 로그를 기록하는 등의 동작을 하고 싶은 경우가 있다.
	 *   이런 경우에 AuthenticationSuccessHandler라는 인터페이스를 구현해서 로그인 성공 처리자로 지정할 수 있다.
	 *   
	 *   	# 환경 설정
	 *   	- 스프링 시큐리티 설정(security-context.xml 설정)
	 *   		> customLoginSuccess bean 등록
	 *   		> <security:form-login login-page="/login" authentication-success-handler-ref="customLoginSuccess"/>
	 *   
	 *   	# 로그인 성공 처리자 클래스 정의
	 *   		- 로그인 성공 처리자
	 *   		> SaveRequestAwareAuthenticationSuccessHandler는 AuthenticationSuccessHandler의
	 *   			구현 클래스이다. 인증 전에 접근을 시도한 URL로 리다이렉트하는 기능을 가지고 있으며 스프링 시큐리티에서
	 *   			기본적으로 사용되는 구현 클래스입니다.
	 *   		- 로그인 성공 처리자 2
	 *   		> AuthenticationSuccessHandler 인터페이스를 직접 구현하여 인증 전에 접근을 시도한 URL로
	 *   			리다이렉트하는 기능을 구현한다.
	 *   
	 *   	# 화면 설명
	 *   		- 일반 게시판 등록 화면
	 *   			> 사용자가 정의한 로그인 페이지에서 회원권한에 해당하는 계정으로 로그인 시, 성공했다면 성공 처리자인
	 *   			CustomLoginSuccess 클래스로 넘어가 넘겨받은 파라미터들 중 authentication안에 principal로
	 *   			USER 정보를 받아서 username과 password를 출력한다. (출력된 정보는 인증된 회원 정보)
	 *   
	 *   9. 로그아웃 처리
	 *   - 로그아웃을 위한 URI를 지정하고, 로그아웃 처리 후에 별도의 작업을 하기 위해서 사용자가 직접 구현한 처리자를 등록할 수 있다.
	 *   
	 *   	# 환경 설정
	 *   	- 스프링 시큐리티 설정(security-context.xml 설정)
	 *   		> <security:logout logout-url="/logout" invalidate-session="true"/>
	 *   
	 *   		** logout 경로는 스프링에서 제공하는 /logout경로로 설정한다.
	 *   
	 *   		** 스프링 시큐리티 로그아웃에서 제공되는 logout-url속성은 로그아웃을 요청할 경로,
	 *   			기본값은 '/logout' 경로로 설정되어 있다.
	 *   			원하는 경로를 지정할 수 있으며, 기본적으로 method 방식은 POST로 동작한다.
	 *   			로그아웃이 완료된 이후에 /login?logout 이라는 경로로 자동 넘어간다. (별도의 커스텀 가능)
	 *    10. JDBC를 이용한 인증/인가 처리
    - 지정한 형식으로 테이블을 생성하면 JDBC를 이용해서 인증/인가를 처리할 수 있다.
    - 생성할 테이블은 사용자를 관리하는 테이블(users)과 권한을 관리하는 테이블입니다.
    
                 
           # 데이터베이스 테이블 준비
              - users, authorities 테이블 준비
              
           # 환경 설정
              - 의존 라이브러리 설정
              > 데이터베이스 관련 라이브러리를 추가한다.
              > 기존 데이터베이스 연결을 위한 라이브러리가 추가되어 있다.
              
           # 스프링 설정(root-context.xml 설정)
               - 데이터 소스 설정
               
           # 스프링 시큐리티 설정(security-context.xml 설정)
               - customPasswordEncoder 빈 등록 진행
                  > <security:authentication-manager> 태그 설정
                  
           # 비밀번호 암호화 처리기 클래스 정의
               - 비밀번호 암호화 처리기
                  > 스프링 시큐리티 5부터는 기본적으로 PasswordEncoder를 지정해야 하는데, 제대로 하려면 
                     생성된 사용자 테이블(users)에 비밀번호를 암호화하여 지정해야한다. 테스트를 위해서 생성한 데이터는 암호화를
                     처리하지 않고 로그인하면 당연히 로그인 에러가 발생할 것입니다. (암호화된 비밀번호가 아니라서)
                     그래서 암호화를 하지 않는 PasswordEncoder를 직접 구현하여 지정하면 로그인 시 암호화를 고려하지
                     않으므로 로그인이 정상적으로 이루어지는걸 확인할 수 있습니다.
               - security/CustomPasswordEncoder 클래스 구현      
	 * 
	 */ 
}
