DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS ( 
	userId          varchar(12)		NOT NULL, 
	password		varchar(12)		NOT NULL,
	name			varchar(20)		NOT NULL,
	email			varchar(50),	
  	
	PRIMARY KEY               (userId)
);

INSERT INTO USERS VALUES('admin', 'password', '자바지기', 'admin@slipp.net');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
	questionId 			bigint				auto_increment,
	writer				varchar(30)			NOT NULL,
	title				varchar(50)			NOT NULL,
	contents			varchar(5000)		NOT NULL,
	createdDate			timestamp			NOT NULL,
	countOfAnswer int,
	PRIMARY KEY               (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS (
	answerId 			bigint				auto_increment,
	writer				varchar(30)			NOT NULL,
	contents			varchar(5000)		NOT NULL,
	createdDate			timestamp			NOT NULL,
	questionId			bigint				NOT NULL,				
	PRIMARY KEY         (answerId)
);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(1, '자바지기',
'국내에서 Ruby on Rails와 Play가 활성화되기 힘든 이유는 뭘까?', 
'Ruby on Rails(이하 RoR)는 2006년 즈음에 정말 뜨겁게 달아올랐다가 금방 가라 앉았다. Play 프레임워크는 정말 한 순간 잠시 눈에 뜨이다가 사라져 버렸다. RoR과 Play 기반으로 개발을 해보면 정말 생산성이 높으며, 웹 프로그래밍이 재미있기까지 하다. Spring MVC + JPA(Hibernate) 기반으로 진행하면 설정할 부분도 많고, 기본으로 지원하지 않는 기능도 많아 RoR과 Play에서 기본적으로 지원하는 기능을 서비스하려면 추가적인 개발이 필요하다.',
CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(2, '김문수',
'runtime 에 reflect 발동 주체 객체가 뭔지 알 방법이 있을까요?', 
'설계를 희한하게 하는 바람에 꼬인 문제같긴 합니다만. 여쭙습니다.
상황은 mybatis select 실행될 시에 return object 의 getter 가 호출되면서인데요. getter 안에 다른 property 에 의존중인 코드가 삽입되어 있어서, 만약 다른 mybatis select 구문에 해당 property 가 없다면 exception 이 발생하게 됩니다.',
CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(3, '자바지기',
'scala에서 named parameter를 활용한 test fixture 생성 방법', 
'자바로 구현할 때 귀찮은 작업 중의 하나는 객체의 복잡도가 증가하는 경우 test fixture를 생성하는 것이 여간 귀찮은 작업이 아니다.
스칼라는 named parameter를 활용해 test fixture를 생성할 수 있다.',
CURRENT_TIMESTAMP(), 0);


INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(4, '자바지기',
'DB 설계할 때 table id는 어떤 방식을 사용하나요?', 
'어느 순간부터 DB id를 설계할 때 특별히 신경을 쓰지 않은 것 같네요. 최근에는 JPA 사용하면서 무의식적으로 auto increment를 사용하고 있어요. 물론 auto increment가 적합한 경우도 있겠지만 그렇지 않은 경우도 많다고 생각해요. 보통 DB 설계할 때 각 테이블의 id는 어떤 방식을 사용하나요?
UUID를 사용하는 것도 하나의 방식이 될 수 있을거 같은데요. UUID 사용에 따른 장단점은 JPA Implementation Patterns: Using UUIDs As Primary Keys 문서에서 다루고 있고, 단점을 극복하는 방법을 댓글에서 볼 수 있네요.
테이블 id를 설계할 때 어떤 방식으로 접근하나요? 저는 요즘 너무 아무 생각없이 id를 추가하고 있다는 마음이 들어 질문 남겨 봅니다.',
CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(5, 'johnburr',
'이클립스 JRE설정에 대해서 질문을 드립니다.', 
'이곳에서 보면 이클립스의 jre에 대해서 3개의 설정이 나옵니다.
jre 버전 설정
실행환경 설정(execution environment)
컴파일러 설정',
CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(6, '자바지기',
'프로그래머가 알아야할 기본 지식은 어디까지일까?', 
'오늘 무엇인가 정리하다가 도대체 프로그래머가 알아야하는 기본 지식은 어디까지일까라는 의문이 들었다. 물론 컴퓨터 기본 구조, 네트워크, 자료 구조, 알고리즘, C, C++, 자바, 다양한 프레임워크 등등 모든 영역을 잘하면 좋겠지만 모든 영역을 학습하기에는 지금의 지식이 너무 방대하기 때문이다.',
CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(7, 'javajigi',
'javascript 학습하기 좋은 라이브러리를 추천한다면...', 
'이번 slipp에서 진행하는 5번째 스터디 주제가 trello의 아키텍처를 분석하고, trello에서 사용하는 기술을 학습하는 과정이다. 이 아이디어로 스터디를 진행하게 된 계기는 http://www.mimul.com/pebble/default/2014/03/17/1395028081476.html 글을 보고 스터디 주제로 진행해 보면 좋겠다는 생각을 했다.
이번 스터디를 진행하면서 가장 힘든 점이 javascript라 생각한다. 지금까지 javascript를 사용해 왔지만 깊이 있게 사용한 경험은 없기 때문에 이번 기회에 틈틈히 학습해 보려고 생각하고 있다. 단, 학습 방법을 지금까지와는 다르게 오픈 소스 라이브러리 중에서 괜찮은 놈을 하나 골라 소스 코드를 읽으면서 학습하는 방식으로 진행해 보려고 한다. 아무래도 책 한권을 처음부터 읽으면서 단순 무식하게 공부하기 보다는 이 방식이 좋지 않을까라는 기대 때문이다. javascript의 개발 스타일도 이해할 수 있기 때문에 좋은 접근 방법이라 생각한다.
혹시 javascript를 학습하기 좋은 라이브러리가 있을까? 소스 코드 라인 수가 많지 않으면서 소스 코드 스타일에서도 배울 점이 많은 그런 라이브러리면 딱 좋겠다.',
CURRENT_TIMESTAMP(), 2);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('eungju',
'http://underscorejs.org/docs/underscore.html Underscore.js 강추합니다!
쓸일도 많고, 코드도 길지 않고, 자바스크립트의 언어나 기본 API를 보완하는 기능들이라 자바스크립트 이해에 도움이 됩니다. 무엇보다 라이브러리 자체가 아주 유용합니다.', 
CURRENT_TIMESTAMP(), 7);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('Hanghee Yi',
'언더스코어 강력 추천드려요.
다만 최신 버전을 공부하는 것보다는 0.10.0 버전부터 보는게 더 좋더군요.
코드의 변천사도 알 수 있고, 최적화되지 않은 코드들이 기능은 그대로 두고 최적화되어 가는 걸 보면 재미가 있습니다 :)', 
CURRENT_TIMESTAMP(), 7);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
(8, '자바지기',
'anonymous inner class는 final 변수에만 접근해야 하는 이유는?', 
'오늘 자바 8에 추가된 람다와 관련한 내용을 읽다가 다음과 같이 내용이 있어 궁금증이 생겼다.
람다 표현식에서 변수를 변경하는 작업은 스레드에 안전하지 않다. - 가장 빨리 만나는 자바8 28페이지...
람다 표현식을 이전 버전의 anonymous inner class와 같은 용도로 판단했을 때 기존의 anonymous inner class에서도 final 변수에만 접근할 수 있었다.
지금까지 anonymous inner class에서 final 변수로 정의하는 이유가 현재 method의 Context가 anonymous inner class 인스턴스까지 확대되기 때문에 anonymous inner class 내에서 값을 변경할 경우 그에 따른 side effect가 생길 가능성이 많아 final로 정의하는 것으로 생각했다.
그런데 위 내용은 스레드에 안전하지 않기 때문에 람다 표현식에서 변수 값을 변경하는 것을 막는다고 이야기하고 있다. 왜 스레드에 안전하지 않은 것일까?',
CURRENT_TIMESTAMP(), 3);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('jhindhal.jhang',
'Thread safe 랑 final은 관계가 있는거지만 다르게 봐야 하는게 아닌가?
굳이 lambda로 변수를 쓸 때 final 지정을 하지 않은 변수 더라도 final효과처럼 사용 한다면 (읽기만 한다 던지...) 사용 가능 하니
Final 과 꼭 lambda를 연관 하지 말고 thread safe하게 프로그래밍 하기 위해 final(또는 final처럼)을 해야 한다는 의미가 아닐까? 생각 하는데...', 
CURRENT_TIMESTAMP(), 8);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('강우',
'저도 잘은 모르겠지만, 그냥 몇글자 적어볼께요.
일단 변수의 생명 주기랑, 값이 아닌 레퍼런스에 의한 부수효과는 무시하고,
쓰레드 관점에서만 볼때에,
간단히 생각하면, 서블릿에서 인스턴스 변수를 사용하는 것은 쓰레드에 안전할까요? 안전하지 않을까요?
저는 같은 맥락인거 같은데 ^^;;
아 그러고 보니 "안정"이라고 되어있네요. 저건 다른 의미인가.. ^^;;', 
CURRENT_TIMESTAMP(), 8);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('Toby Lee',
'람다식에서 사용되는 변수라면 람다식 내부에서 정의된 로컬 변수이거나 람다식이 선언된 외부의 변수를 참조하는 것일텐데, 전자라면 아무리 변경해도 문제될 이유가 없고, 후자는 변경 자체가 허용이 안될텐데. 이 설명이 무슨 뜻인지 이해가 안 됨.', 
CURRENT_TIMESTAMP(), 8);