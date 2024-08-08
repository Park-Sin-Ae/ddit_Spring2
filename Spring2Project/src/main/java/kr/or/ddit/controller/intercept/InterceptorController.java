package kr.or.ddit.controller.intercept;

public class InterceptorController {
	/*
	 * 17장 인터셉터
	 * - 인터셉터는 웹 애플리케이션 내에서 특정한 URI 호출을 가로채는 역할을 한다.
	 * 
	 * 1. 인터셉터 설명
	 * 
	 * 		필터와 인터셉터
	 * 		- 서블릿 기술의 필터와 스프링 MVC의 인터셉터는 특정 URI에 접근 할 때 제어하는 용도로 사용된다는 공통점이 있다.
	 * 		하지만 실행 시점에 속하는 영역 (Context)에 차이점이 있습니다.
	 * 		인터셉터의 경우 스프링에서 관리하기 때문에 스프링 내의 모든 객체에 접근이 가능하지만 필터는 웹 애플리케이션 영역
	 * 		내의 자원은 활용할 수 있지만 스프링 내의 객체에는 접근이 불가능하다.
	 * 
	 * 		스프링 AOP와 인터셉텨
	 * 		- 특정 객체 동작의 사전 혹은 사후 처리는 AOP 기능을 활용할 수 있지만 컨트롤러의 처리는 인터셉터를 활용하는 경우가 
	 * 		많습니다. AOP의 어드바이스와 인터셉터의 가장 큰 차이는 파라미터의 차이라고 할 수 있습니다.
	 * 		어드바이스의 경우 JoinPoint나 ProceedingJoinPoint 등을 활용해서 호출 대상이 되는 메소드의 파라미터 등을
	 * 		처리하는 방식입니다. 인터셉터는 필터와 유사하게 HttpServletRequest, HttpServletResponse를 파라미터로
	 * 		받는 구조입니다.
	 * 
	 * 		HandlerInterceptorAdapter 클래스
	 * 		- HandlerInterceptorAdapter는 HandlerInterceptor를 쉽게 사용하기 위해서 인터페이스의 메서드를
	 * 		미리 구현한 클래스입니다.
	 * 
	 *  	HandlerInterceptor의 메소드는 아래와 같다.
	 *  
	 *  		- preHandle
	 *  			> 지정된 컨트롤러의 동작 이전에 가로채는 역할을 사용한다.
	 *  		- postHandle
	 *  			> 지정된 컨트롤러의 동작 이후에 처리, DispatcherServlet이 화면을처리하기 전에 동작한다.
	 *  		- afterCompletion
	 *  			> DispatcherServlet의 화면 처리가 완료된 상태에서 처리한다.
	 *  
	 *  2. 인터셉터 구현
	 *  
	 *  	예) 어떤 특수한 기능을 하나 만들어야한다고 가정합시다!
	 *  		요청 경로마다 접근 제어를 다르게 하거나, 특정 URL에 대한 접근 내역을 기록하고 싶을 때
	 *  
	 *  		- 필터, AOP, 인터셉터, 등등
	 *  
	 *  		클라이언트의 요청을 처리하다 보면 요청 경로마다 접근 제어를 다르게 하거나, 특정 URL에 대한 접근 내역을
	 *  		기록하고 싶을 때가 있습니다. 이런 기능은 특정 컨트롤러에 종속되기 보다 여러 컨트롤러에서 공통적으로 적용되는
	 *  		기능들이라 하겠습니다. 이런 기능을 각 컨트롤러에서 개발적으로 구현하면 중복 코드가 발생하므로, 코드 중복 없이
	 *  		기존의 컨트롤러에 수정을 가하지 않고 적용할 수 있는 방법이 필요합니다.
	 *  		지금까지 배운 내용들을 떠올려보면 이를 해결할 수 있는 방식 2가지가 존재한다는걸 떠올릴겁니다!
	 *  		
	 *  		그 첫번째로 Filter라는 서블릿 스펙에 따른 객체를 사용하는 방법이 있습니다. 필터를 통해 DispatcherServlet
	 *  		이나 컨트롤러에게 요청이 위임되기 전/후에 공통적인 어떤 기능을 수행하도록 하면, 기존 컨트롤러나
	 *  		DispatcherServlet에 어떤 수정 사항을 가하거나 코드 중복 없이 이슈를 해결 할 수 있으 것입니다.
	 *  		그러나 이 방법은 한 가지 단점이 있습니다. 바로 Filter가 DispatcherServlet보다 먼저 객체가 생성되고
	 *  		스프링 컨테이너 밖에 존재하기 때문에 컨테이너를 통한 DI를 받을 수 없다는 점입니다.
	 *  		물론 아예 불가능하지는 않습니다. 설정이나 이런 부분이 복잡하고 귀찮을 뿐이죠. DelegatingFilterProxy
	 *  		해당 DelegatingFilterProxy라는 필터는 원래 필터 기반의 보안 처리를 지원하는 Spring Security
	 *  		프레임워크에서 제공했던 필터인데 하도 널리 쓰이기 시작하면서 아예 스프링 코어 웹 모듈로 편입된 타입입니다.
	 *  		그런데 이 필터를 이용해서 스프링 빈에게 필터링을 위임하는 방법도 몇가지 불편한 점들이 있습니다.
	 *  		반드시 위임을 받을 빈은 Filter를 구현하고 있어야하고, 루트 컨텍스트에서 관리되는 빈이어야만 한다는 점입니다.
	 *  
	 *  		두번째로 AOP방법론에 따라 공통 기능을 정의한 Advice를 구현하고, pointcut을 통해 적절한 target 컨트롤러
	 *  		를 골라낸 다음 두 설정으로 Aspect를 생성하며 런타임을 컨트롤러와 위임하도록 하는 방법을 생각해 볼 수 있습니다.
	 *  		실제 보안 프레임워크들에서도 AOP 방법론에 따라 위비을 위한 어노테이션을 활용하고 있기는 합니다.
	 *  
	 *  		그렇지만 첫번째/두번째 모두 우리가 처리하고 싶은 기능을 구현하는데 제약이 따릅니다.
	 *  		우리가 지금 처리하고 싶은 기능은 특정 URL에 대한 접근 내역을 기록하거나 특정 경로에 대한 접근 제어를 하는 등
	 *  		웹 이라는 공간과 환경에 제한된 공통 기능을 처리하고 싶은 것입니다.
	 *  		AOP는 너무 범용적인 방법이라 할 수 있고,Filter 방식은 제약이 많습니다. (사용할 자원의 환경이 다름)
	 *  		이러한 경우에 사용하기 위한 전략으로 스프링은 HandlerInterceptor라는 추상화를 제공하고 있으며,
	 *  		이를 사용하면 Spring MVC에 맞게 공통 기능을 다수의 URL에 적용할 수 있게 됩니다.
	 *  	
	 *  		HandlerInterceptor 인터페이스를 사용하면 아래와 같은 시점에 대해 공통 기능을 넣을 수 있습니다.
	 *  		> 컨트롤러 실행 전(preHandle)
	 *  		> 컨트롤러 실행 후, 아직 뷰를 실행하기 전 단계입니다. (postHandle)
	 *  		> 뷰를 실행한 이후 : 화면이 나가지는 않음(afterCompletion)
	 *  
	 *  		preHandle() 메소드 컨트롤러 객체를 실행하기 전에 필요한 기능을 구현할 때 사용되며, handler 파라미터는
	 *  		웹 요청을 처리할 컨트롤러 객체입니다. 이 메소드를 사용하면 컨틀롤러 실행하기 전에 컨트롤러에서 필요로 하는
	 *  		정보를 생성하거나 접근 권한이 없는 경우 리턴값으로 false를 반환하여 컨트롤러가 실행되지 않도록 하는 작업이 가능
	 *  		합니다.
	 *  
	 *  		postHandle() 메소드는 컨트롤러가 정상적으로 실행된 이후에 추가기능을 구현할 때 사용되는데, 만약
	 *  		컨트롤러에서 예외가 발생했다면 postHandle() 메소드는 실행되지 않습니다.
	 *  
	 *  		afterComplection() 메소드는 클라이언트에 뷰를 전송한 뒤에 실행되며, 만약 컨트롤러에서 실행하는 관점에서
	 *  		예외가 발생했다면 이 메소드의 네번째 파라미터로 전달된다. 예외가 발생하지 않는 경우는 null 값이 들어올 것입니다.
	 *  		따라서 컨트롤러 실행 이후 예기치 않은 예외에 대해 로깅을 한다거나 실행 시간을 기록하는 등의 후처리를 하기에
	 *  		적합한 메소드로 정리하자면 HandlerInterceptor는 세가지 메소드를 통해 AOP 방법론에서 시점에 따른 여러
	 *  		Advice 들의 역할을 하나의 handlerInterceptor객체가 전담할 수 있는 구조를 갖고 있습니다.
	 *  
	 *  3. 인터셉터 설정
	 *  - 인터셉터 클래스를 정의하고 스프링 웹 설정 파일에 인터셉터를 지정한다.
	 *  
	 *  	인터셉터 지정
	 *  	- servlet-context.xml 에서 설정
	 *  	
	 *  		> loginInterceptor 아이디로 빈 등록
	 *  		> interceptor 태그 설정
	 *  
	 *  	** LoginInterceptor는 controller/login 패키지 안에 LoginController와 함께 동작
	 *  		> 인터셉터의 흐름을 파악하기 위한 간단한 시나리오를 만들고 테스트
	 *  
	 *  		1. /login1을 요청하면 로그인 페이지가 나타납니다.
 *  			2. 로그인 성공 후, '/' 리다이렉트에 의해 메인으로 이동한다.
 *  			3. 메인에서 넘어갈 페이지가 home.jsp인데 그 안에 넘겨받은 user정보 출력이 존재한다.
	 *  
	 *  
	 */
}
