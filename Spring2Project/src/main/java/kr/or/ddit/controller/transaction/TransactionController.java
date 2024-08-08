package kr.or.ddit.controller.transaction;

public class TransactionController {
	/*
	 * 15장 트랜젝션
	 * 
	 * 1. 트랜잭션 설정
	 * - 한 번에 이루어지는 작업의 단위를 의미한다.
	 * 
	 * 		# 트랜잭션 성격(ACID 원칙)
	 * 		- 원자성 (Automicity)
	 * 			> 하나의 트랜잭션은 모두 하나의 단위로 처리되어야 한다.
	 * 			> 하나의 트랜잭션은 완벽하게 성공 처리되거나, 실패 처리되는 것이다.(부분 실행이 되지 않는다.)
	 * 
	 * 		- 일관성 (Consistency)
	 * 			> 트랜잭션이 성공했다면 모든 데이터는 일관성을 유지해야 한다.
	 * 
	 * 		- 격리성 (Isolation)
	 * 			> 트랜잭션으로 처리되는 동안에는 외부에서의 간섭이 없어야 한다.
	 * 			> 하나의 트랜잭션 수행 시, 타 트랜잭션의 작업 단위가 간섭하지 못한다.
	 * 
	 * 		- 영속성 (Durability)
	 * 			> 트랜잭션이 성공적으로 처리되면 그 결과는 영속적으로 보관되어야 한다.
	 * 			> 하나의 트랜잭션이 성공적으로 끝났다면 끝까지 보존되어야 하며, 영구적으로 반영되어야 한다.
	 * 
	 *  2. 트랜잭션 설정
	 *  
	 *  	# 스프링 설정
	 *  	- root-context.xml 설정
	 *  		> 네임스페이스, 스키마 추가
	 *  	- transactionManager 빈 등록
	 *  	- 어노테이션 기반의 트랜잭션 제어 활성화
	 *  
	 *  3. 트린잭션 적용
	 *  
	 *  	[테스트 AOP가 적용된 CrudController와 연계된 ServiceImple에서 한다.]
	 *  
	 *  	# 트랜젝션 상황 부여
	 *  
	 *  		예) 회원은 반드시 하나의 권한을 가진다는 비즈니스 규칙이 있다.
	 *  			회원과 회원권한 테이블이 각각 개별로 존재하지만 회원 정보를 저장할 때 반드시 회원권한 정보를 동시에 저장해야 한다.
	 *  			클래스나 메서드에 대해 @Transactional 어노테이션을 부여하여 트랜잭션을 적용할 수 있다.
	 *  
	 *  	# 서비스 구현 객체에 트랜잭션 적용
	 *  	
	 *  		회원 정보를 저장하다가 실패하거나 회원 권한 정보를 저장하다가 실패하면 회원 테이블과 회원 권한 테이믈 모두 저장되지 않는다.
	 *  		회원 정보와 회원 권한 정보 모두 저장에 성공해야 비로소 회원 테이블과 회원 권한 테이블에 저장이 이루어진다.
	 *  		삭제, 수정도 마찬가지로 이루어진다.
	 *  
	 *  	# @Trasactional의 예외 처리 견해
	 *  
	 *  		@Transational을 적용하고 중간에 에러를 발생 시킨 후, 롤백된 상태를 확인하려고 했으나 롤백이 되지 않고
	 *  		데이터베이스에 데이터가 등록된다. (분명 롤백이 된다고 많은 기술 블로거들이 명시하고 있지만 아니었다...)
	 *  		그 이유는 스프링 프레임워크에서 @Transactional은 기본적오르 Checked Exception에 대해서는 롤백처리를
	 *  		하지 않도록 설계되어 있다.
	 *  		기본적으로 스프링에서 트랜잭션 처리는 RuntimeException계역(Unchecked Exception)이라면 Rollback
	 *  		처리를 한다.
	 *  
	 *  		여기서, 트랜잭션으로 국한된 롤백 정책이 아니라 스프링 프레임워크에서의 기본 정책에 대한 내용일 뿐이다.
	 *  		실제로 '트랜잭션 롤백 처리', 'checked Exception vs unchecked Exception' 등 검색 해보면
	 *  		잘못된 정보들로 복사/붙여넣기 되어 있는 내용이 수두룩하여 많은 에러 정보를 공유하고 있다.
	 *  		(모든 블로그가 그렇진 않지만, 대표적인 표의 형태가 무수히 많이 공유되고 있음을 알 수 있다...)
	 *  
	 *			***
	 *			'예외처리 시 트랜잭션 처리'에 대한 정보가 잘못 표기된 경우가 많은데,
	 *			이럴 때 되고 저럴 때 되고가 아니라, 기본적인 스프링 트랜잭션의 정책이 있고 그 안에서 정책 이외에 옵션으로 특정
	 *			에러가 발생했을 때 롤백을 진행할 수 있도록 제공한다.	
	 *			(많은 블로그들을 100% 믿지 말길... 혹여나 블로그를 작성한다면 검증된 내용으로만 적어주길...!!!)
	 *
	 *  	# Exception(예외)와 Error(에러)
	 *  
	 *  		- Exception : 개발 알고리즘에서 발생하는 오류로 개발자가 작성한 코드에서 발생하므로 예외를 상황에 맞춰
	 *  					처리할 수 있다.
	 *  		- Error : 시스템에서 발생하는 심각한 수준의 에러로 개발자가 미리 예측하여 대응할 수 없기 때문에 예외처리에
	 *  				대한 부분을 신경쓰지 않아도 된다.
	 *  
	 *  		CheckedException과 UnCheckedException
	 *  		- RuntimeException의 상속 여부에 따라서 CheckedException과 UnCheckedExceptio으로 나눈다.
	 *  			
	 *  					|		Checked Exception		|			UnChecked Exception
	 *  		───────────────────────────────────────────────────────────────────────────────
	 *  		예외 처리 여부	| 반드시 예외 처리 코드가 있어야 한다.		| 강제로 예외 처리는 아니다.
	 * 			예외 확인 시점	| 컴파일 단계에서부터 컴파일이 되지 않는다.	| 런타임 중 예외가 확인된다.
	 * 			클래스		| IOException, SQLException		| NullPointException, IndexOutOfBoundeException
	 *  		───────────────────────────────────────────────────────────────────────────────
	 *  
	 *  		트랜잭션도 AOP의 개념이 반영된 관점 지향 프로그래밍이라 할 수 있습니다.
	 *  
	 *  	# RuntimeException 계열의 종류들
	 *  		- ArithmeticException
	 *  		- ArrayStoreException
	 *  		- ArrayIndexOutOfBoundsException
	 *  		- ClassCatException
	 *  		- NullPointerException
	 *  		- NegativeArraySizeException
	 *  		- NoClassDefFoundException
	 *  		- OutOfMemoryException
	 *  		- IndexOutOfBoundsException
	 *  		- IlleagalArgumentException
	 *  		- IlleagalMonitorStateException
	 *  		- 등등이 존재합니다.
	 *  
	 *  	# 선언적 트랜잭션 @Transactional
	 *  
	 *  		- 컨트롤러 메소드 각 단위로 세밀한 트랜잭션 속성 제어가 가능
	 *  		- 해당 어노테이션이 클래스 수준에서 선언되면 선언 클래스 및 해당 하위 클래스의 모든 메소드에 기본값으로 적용된다.
	 *  		- RuntimeException 계열, Error 예외에 대해서는 Rollback이 가능하다. (공식 문서 내용)
	 *  
	 *  		1) isolation (격리 수준)
	 *  			- 각 트랜잭션이 존재할 때, 트랜잭션들 끼리 서로 고립된 수준을 나타내며 서로간에 가용된 데이터를
	 *  				 컨트롤 할지에 대한 부분들을 설정할 수 있다.
	 *  			- isolation 기본 값은 DEFAULT이다.
	 *  			- 새롭게 시작된 트랜잭션에만 적용되므로 Propagation.REQUIRED 또는 Propagation.REQUIRES_NEW와 함께 사용되도록 독점 설계되었다.
	 *  
	 *  			1-1) 옵션
	 *  		
	 *  				*** 용어
	 *  				# Dirty read
	 *  				- Commit이 이루어지지 않은 다른 트랜잭션의 데이터를 읽는 것을 의미
	 *  				# Non-repeatable Read(데이터의 수정/삭제)
	 *  				- 처리중인 트랜잭션에서 다른 트랜잭션이 Commit한 데이터를 읽을 수 있는 것을 의미
	 *  				(처음 조회에 대한 트랜잭션과 두번째 조회에 대한 트랜잭션 결과가 다를 수 있다.)
	 *  				# Phantom Read
	 *  				- 자신이 처리중인 트랜잭션에서 처리했던 내용안에서 다른 트랜잭션이 데이터를 수정 후 Commit
	 *  					하더라도 자신의 트랜잭션에서 처리한 내용만 사용하는 것을 의미
	 *  
	 *  			- DEFAULT : 기본 데이터 저장소의 기본 격리 수준을 사용합니다.
	 *  				> 우리가 사용하는 Oracle은 READ_COMMITED를 사용하며, MySQL은 REPEATABLE_READ를
	 *  					기본 격리 수준으로 사용한다.
	 *  			- READ_COMMITED : Dirty read가 방지됨을 나타내는 상수입니다.
	 *  							Non-repetable Read 및 Phantom Read가 발생할 수 있습니다.
	 *  				> 하나의 트랜잭션처리가 이루어진 변경 내용이 Commit 된 후, 다른 트랜잭션에서 조회가 가능하다.
	 *  				> A 트랜잭션이 데이터를 변경하고 커밋을 하지 않은 상태에서 B 트랜잭션이 조회를 진행할 떄,
	 *  				B 트랜잭션은 Shared lock이 걸린다.
	 *  				> 트랜잭션 A에서 memId의 값을 'a001' 에서 'a002'로 변경 후 커밋을 진행하지 않고
	 *  					트랜잭션 B에서 memId를 조회하면 'a001'이 조회된다.
	 *  					하지만, 트랜잭션 A에서 커밋을 진행 후 트랜잭션 B에서 조회를 해보면 'a002'가 조회된다.
	 *  			- READ_UNCOMMITED : Dirty read, Non-repeatable Read 및 Phantom read가 발생할 수 
	 *  								있음을 나타내는 상수입니다.
	 *  				> 다른 트랜잭션의 내용이 Commit 또는 ROllback되거나 되지 않아도 다른 트랜잭션에서 조회가 가능
	 *  				> 트랜잭션 A에서 memId의 값을 'a001'에서 'a002'로 변경 후 커밋을 하지 않더라도 트랜잭션 B에서
	 *  					memId를 조회해보면 'a002'로 노출되는걸 확인할 수있다.
	 *  				> 데이터를 가용하기 위한 격리 수준은 가장 낮지만, 트랜잭션 처리 성능에 있어서는 극대화	
	 *  			- REPEATABLE_READ : Dirty read, Non-repeatable Read가 방지됨을 나타내는 상수입니다.
	 *  								Phantom Read가 발생할 수 있습니다.
	 *  				> 트랜잭션 Commit이 일어난 데이터에 대해서 조회가 가능(트랜잭션 완료 시까지 조회에 대한 Shared lock이 걸리진 않음)
	 *  				> 트랜잭션 A에서 memId를 조회해본 결과 'a001'이고, 트랜잭션 B에서 memId를 'a002'로 변경 후
	 *  					커밋을 진행합니다. 이 떄, 트랜잭션 A에서는 여전히 조회결과가 'a001로 나타납니다.
	 *  					그리고 트랜잭션 A에서 커밋 후 재조회를 해보면 변경된 'a002'가 조회된다.
	 *  					트랜잭션 A는 조회된 겨로가가 하나의 스냅샷에서 저장되어 있고, 저장되어 있는 스냅샷 정보를 꺼내다보니
	 *  					'a001' 로 표시된다. 커밋 후 기존 스냅샷에서 커밋 된 결과가 스냅샷으로 저장되기 때문에 변경된
	 *  					'a002'를 데이터로 확인할 수 있다.
	 *  			- SERIALIZABLE : Dirty read, Non-repeatable Read 및 Phantom read가 방지됨을 
	 *  							나타내는 상수입니다.
	 *  				> Phantom Read가 발생하지 않는다.
	 *  				> 현재의 트랜잭션 처리가 커밋되지 않으면 다른 트랜잭션이 실행되지 않는다.
	 *  				> 트랜잭션 A에서 memId를 'a002'로 변경 후 커밋합니다. 이때, 다른 트랜잭션은 트랜잭션 A가
	 *  					커밋하지 않는 이상 트랜잭션을 이어 진행할 수 없다.
	 *  					트랜잭션 A에서 커밋으로 처리를 마무리 해주어야 트랜잭션 B에서 memId를 'a003'과 같은
	 *  					값으로 변경 처리 할 트랜잭션이 실행될 수 있다.
	 *  				> 거의 사용하지 않는 옵션(너무 강력하기 때문에 거의 사용하지 않는다.)
	 *  
	 *  		2) propagation (전파옵션)
	 *   			- 기존 진행중인 트랜잭션 외에 추가적으로 진행중인 트랜잭션이 존재할 때 추가적으로 트랜잭션에 대해서 어떻게
	 *   				처리할지에 대한 설정
	 * 				- 추가적인 트랜잭션을 기존 트랜잭션에 포함 시켜 함께 처리할 수도 있고, 추가적으로 트랜잭션처럼 별도의
	 * 					트랜잭션으로 추가 할 수도 있고 다른 트랜잭션처럼 진행되다 에러를 발생 시킬 수도 있다.
	 * 		
	 * 				2-1) 옵션
	 * 				- REQUIRED : 현재 트랜잭션을 지원하고 존재하지 않는 경우 새 트랜잭션을 만듭니다.
	 * 					> propagation 기본 default 옵션
	 * 					> 트랜잭션이 존재하면 참여하고, 존재하지 않으면 새롭게 트랜잭션을 생성하여 시작한다.
	 * 					> 부모/자식간에 상관관계에서 자식 부분의 트랜잭션이 rollback 처리 시, 부모까지 영향이 가서
	 * 						rollback 처리 된다.
	 * 
	 *  			- REQUIRES_NEW : 새로운 트랜잭션을 생성한다.
	 *  				> 무조건 새로운 트랜잭션을 시작한다.
	 *  				> 기존 작업중인 트랜잭션이 존재하다면, 기존 트랜잭션을 잠시 보류하고 새롭게 만들어진 트랜잭션을
	 *  					이행 후 기존 트랜잭션을 실행한다.
	 *  				> rollback은 각각 이루어진다.
	 *  
	 *  			- SUPPORTS : 트랜잭션이 있으면 현재 트랜잭션을 지원하고 트랜잭션이 없으면 트랜잭션이 아닌 
	 *  						방식으로 실행
	 *  			- MANDATORY : 현재 트랜잭션을 지원하고, 없으면 예외를 발생시킨다.
	 *  				> 독립적인 트랜잭션으로 진행하면 안되는 경우 사용
	 *  			- NESTED : 현재 트랜잭션이 있는 경우 중첩된 (부모-자식) 트랜잭션 내에서 실행되고 그렇지 않으면
	 *  					REQUIRED와 같이 동작한다. (완전한 REQUIRED는 아님)
	 *  				> 부모 트랜잭션에서 진행중인 커밋 또는 롤백은 자식 트랜잭션까지 영향력을 주지만, 자식 트랜잭션에서
	 *  					발생한 커밋, 롤백은 부모 트랜잭션에 영향력을 주지 않는다.
	 *  			- NEVER : 트랜잭션이 아닌 방식으로 실행하고 트랜잭션이 있으면 예외를 발생시킨다.
	 *					> 실행 자체가 트랜잭션을 필요로  하지 않고, 트랜잭션이 존재한다면 예외를 발생시킨다.
	 *					: Existing transaction found for transaction marked with propatation
	 *						'never' 에러
	 *				- NOT SUPPORTS : 트랜잭션 사용 자체를 금한다.	
	 *					> 하나의 트랜잭션이 존재한다면 보류한다.
	 *					> 클래스 라인에 선언적 트랜잭션 어노테이션이 걸려있을 때, 특정 메서드만 트랜잭션을 처리하지 않겠다고
	 *						했을 때 사용할 수 있는 속성
	 *
	 *  		3) readOnly (읽기전용 설정)
	 *  			- 읽기 전용의 경우 설정할 수 있는 Bool Flag, 런타임 시 최적화를 허용한다.
	 *  			- 기본값은 false
	 *  			- readOnly 속성을 설정했다고 해서, 읽기 전용으로 무조건 설정된다는 보장이 없음.
	 *  				(쓰기와 같은 트랜잭션이 실행될 수도 있음)
	 *					> 읽기 전용에 대한 힌트를 분석할 수 없는 트랜잭션인 경우 throw를 던지지 않고 조용히 힌트를 무시함.
	 *
	 *  		4) timeout (트랜잭션 제한시간)
	 *  			- 기본값은 -1로 무제한이다.
	 *  			- timeout은 클라이언트와 서버와의 통신 중, 서버측 문제로 다음 처리를 이어나가지 못하는 'Dead lock'
	 *  				을 방지할 수 있는 속성이다.
	 *  			- 클라이언트와 서버간의 Restful API를 개발 시 고려해볼 속성
	 *  
	 *  		5) rollbackFor
	 *  			- 트랜잭션 롤백을 유발해야 하는 예외 유형을 나타내는 0개 이상의 예외 유형을 정의한다.
	 *  			- 기본적으로 RuntimeException계열과 Error는 롤백된다.(공식 문서 내용)
	 *  			- 기본적인 정책 이외에 에러를 처리할 경우, 해당 에러를 선언하여 롤백 정책을 추가한다.			
	 *  
	 *  				
	 *  	
	 */
}
