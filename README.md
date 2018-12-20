# 지역 찾기 서비스 테스트

## 요구사항
1. 로그인
 - 사용자의 아이디와 비번으로 로그인을 할 수 있어야 합니다.
   - 아이디: user, 패스워드: password
 - 단, 사용자 데이터는 애플리케이션 실행 시점에 생성합니다.
   - [참고](https://github.com/boojm/local-search-test/blob/f10bc30de876f82e8f74383b27a837b234152777/src/main/java/boojongmin/localsearch/Application.java#L20)
1. 장소검색
  - [Controller](https://github.com/boojm/local-search-test/blob/f10bc30de876f82e8f74383b27a837b234152777/src/main/java/boojongmin/localsearch/controller/LocalSearchController.java#L26)
  - [Service](https://github.com/boojm/local-search-test/blob/f10bc30de876f82e8f74383b27a837b234152777/src/main/java/boojongmin/localsearch/service/LocalSearchService.java#L41)
1. 검색된 장소의 상세조회
  - 클라이언트측에서 구현. api로부터 받은 documents를 메모리에 저장후 테이블의 row를 클릭하면 상세정보 볼 수 있게 적용
1. 인기검색어 목록
  - 레디스에 SortedSet(ZSETs)을 이용하여 구현
  - [Service](https://github.com/boojm/local-search-test/blob/f10bc30de876f82e8f74383b27a837b234152777/src/main/java/boojongmin/localsearch/service/LocalSearchService.java#L62)
  
비즈니스 로직은 최대한 서버에서 구현합니다.
-> 가능한 서버에서 구현.
또한, 시스템의 확장성(scalability) 및 동시성 이슈를 미리 고려해야 합니다
  - 확장성을 위해 redis 사용
    - 캐시 및 세션 처리
  - 동시성 이슈: 
    - 동시성 문제가 발생하지 않게 쓰레드에서 자원을 공유하는 일이 없도록 코드 작성
    - 다수의 api를 비동기로 호출해야하는 상황이 없어서 따로 비동기 코드를 만들지 않음. 
    - hystrix를 사용해서 IO를 위한 thread 관리는 hystrix를 통해 처리(circuit breaker pattern으로 대기큐에 지속적으로 쌓임 방지) 
## 제약사항 
- JAVA 8 이상
  - open jdk 11.0.1 사용
- Spring Framework 4.x 이상 혹은 Spring Boot 사용
  - springboot 2.1.1.RELEASE 사용(20181220기준 GA) ==> org.springframework:spring-core:5.1.3.RELEASE
- Gradle 또는 Maven 기반의 프로젝트
  - gradle 4.10.3 사용
- DB는 자율적으로 선택 (파일 기반이나 메모리 DB 사용)
  - h2 database 사용
- 클라이언트 구현에 Javascript 사용
  - 사용
- 외부 라이브러리 및 오픈소스 사용 가능 (단, README 파일에 사용한 오픈 소스를 명시해 주세요)
  - 문서 하단에 추가
- JPA 사용
  - spring-data-jpa를 이용하여 entity 및 repository를 작성.
  - [Entity](https://github.com/boojm/local-search-test/blob/master/src/main/java/boojongmin/localsearch/domain/Member.java)
  - [Repository](https://github.com/boojm/local-search-test/blob/master/src/main/java/boojongmin/localsearch/repository/MemberRepository.java)
  
  
## 실행
- java se 11, gradle 설치 (DB와 redis는 어플리케이션에 내장)
- gradle bootRun 
- 브라우저 http://localhost:8080 접속(개발에는 chrome을 사용)
- login form에서 아이디 user, 패스워드 password 입력
- 지역검색 화면에서 키워드 입력후 인기 키워스 상위 및 검색 결과 노출 확인. 




