## Order System
> Shopping Mall Order System based on CLI

### How to run
* Run Commands Below
```shell
docker build -t order-system:latest .
```
```shell
docker run -it -p 50152:50152 order-system:latest
```

### Technologies Used
* Application Framework: Spring Shell
* RDBMS & ORM: H2, Spring Data JPA
* Test Framework: JUnit
  
### Documents
* [Flow Chart](https://github.com/MJbae/order-system-based-on-cli/wiki/Flow-Chart)
* [UML for Entity](https://github.com/MJbae/order-system-based-on-cli/wiki/UML)
* [Converting-CSV-to-SQL](https://github.com/MJbae/order-system-based-on-cli/wiki/Converting-CSV-to-SQL)

### Reference
* Spring Shell: [Baeldung](https://www.baeldung.com/spring-shell-cli)
* Unit Test: [better specs](https://www.betterspecs.org/), [기계인간 블로그](https://johngrib.github.io/wiki/junit5-nested/#describe---context---it-%ED%8C%A8%ED%84%B4)
* Concurrency Test: [삐멜 소프트엔지니어](https://imasoftwareengineer.tistory.com/100)
