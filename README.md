## 주문 시스템
> CLI 기반 쇼핑몰의 주문 시스템

### 실행 방법
* 프로젝트의 루트 디렉토리에서 이하의 명령을 실행하세요
```shell
docker build -t order-system:latest .
```
```shell
docker run -it -p 50152:50152 order-system:latest
```

### TDD 기반 구현 절차
1. 코드 작업 전에 요구사항을 분석하여 전체적인 로직을 직관적으로 파악하기 위해 ‘Flow Chart’ 작성
2. 가장 간단한 방식(Happy Path)의 주문 기능 구현
3. ‘Flow Chart’에 작성한 주요 분기점에 대해 실패하는 단위 테스트 작성 
4. 실패하는 테스트를 성공시키기 위해 코드 수정
5. 2번과 3번 과정을 충분히 반복한 후, 테스트 기반으로 전체적인 코드 리팩토링

### 사용 기술
* Application Framework: Spring Shell
* RDBMS & ORM: H2, Spring Data JPA
* Test Framework: JUnit

### 참고 자료
* Spring Shell: [Baeldung](https://www.baeldung.com/spring-shell-cli)
* Unit Test: [better specs](https://www.betterspecs.org/), [기계인간 블로그](https://johngrib.github.io/wiki/junit5-nested/#describe---context---it-%ED%8C%A8%ED%84%B4)
* Concurrency Test: [삐멜 소프트엔지니어](https://imasoftwareengineer.tistory.com/100)
