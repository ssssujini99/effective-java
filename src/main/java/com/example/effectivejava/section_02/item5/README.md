## 💡 아이템 5: 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

> 사용하는 자원에 따라 동작이 달라지는 클래스에는 (클래스가 하나 이상의 자원에 의존하는 경우)  
> ~~정적 유틸리티 클래스~~나 ~~싱글턴 방식~~이 적합하지 않다  
> 
> 해당 조건에서는 **인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨 주는** `의존 객체 주입 방식`을 이용해야 한다.


### ✔️ 정적 유틸리티를 잘못 사용한 예시

```java
/**
 * 코드 5-1: 정적 유틸리티를 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
 */

// SpellChecker: 맞춤법 검사기
    
public class SpellChecker {

    private static final Lexicon dictionary = ... ; // 사전에 의존함 -> 그런데 단 하나만 사용..?

    private SpellChecker() {} // 객체 생성 방지

    /**
     *
     * public static 메서드로 제공
     */
    public static boolean isValid(String word) {
        // dictionary 를 이용 ..
    }

    public static List<String> suggestions(String typo) {
        // dictionary 를 이용 ..
    }
}
```


### ✔️ 싱글턴을 잘못 사용한 예시

```java
/**
 * 코드 5-2: 싱글턴을 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
 */
public class SpellChecker {

    private final Lexicon dictionary = ...; // 사전에 의존함 -> 그런데 단 하나만 사용..?

    private SpellChecker() {}

    /**
     *
     * 싱글턴으로 구현
     */
    public static SpellChecker INSTANCE = new SpellChecker(...);

    public boolean isValid(String word) {
        // ...
    }

    public List<String> suggestions(String typo) {
        // ...
    }
}
```

해당 예시에서는 SpellChecker(맞춤법 검사기)가 Dictionary(사전)을 사용하고,  
즉, `SpellChecker -> Dictionary` 로의 의존성이 존재한다.  

두 방식 모두 사전을 단 하나만 사용한다고 가정한다는 점에서 올바르지 않다. 즉, 유연하지 않다.  
또한 직접 명시되어 고정되어 있는 변수는 테스트하기가 어렵다.  
**사용하는 자원에 따라 동작이 달라지는 클래스에는 (클래스가 하나 이상의 자원에 의존하는 경우) ~~정적 유틸리티 클래스~~나 ~~싱글턴 방식~~이 적합하지 않다**

> **즉, 의존성을 바깥으로 분리하여 외부로부터 주입받도록 해야 한다. (의존 객체 주입 패턴)**  


### ✔️ 의존 객체 주입 패턴 예 

```java
/**
 * 코드 5-3: 의존 객체 주입은 유연성과 테스트 용이성을 높여준다.
 */
public class SpellChecker {

    private final Lexicon dictionary;


    /**
     * 인스턴스 SpellChecker를 생성할 때
     * 생성자에 필요한 자원(dictionary)를 넘겨주는 방식
     *
     * @param dictionary
     */
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        // ...
    }

    public List<String> suggestions(String typo) {
        // ...
    }
}
```

*  의존하는 자원이 몇 개든 의존 관계가 어떻든 잘 작동한다
* 불변을 보장하여 여러 클라이언트가 의존 객체들을 안심하고 공유할 수 있다
* 의존 객체 주입은 생성자, 정적 팩터리, 빌더 모두에 똑같이 응용할 수 있다


### ✔️ 의존 객체 주입 패턴의 변형 - 생성자에 자원 팩터리를 넘기기

>  💡 **팩터리란?**
> 
> 호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 객체
> 즉, 팩터리 메서드 패턴을 구현한 것

코드 예시  
`Mosaic create(Supplier<? extends Tile> tileFactory) {...}`  



### ✔️ 핵심 정리

> 클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면  
>   
> ~~정적 유틸리티 클래스~~와 ~~싱글턴~~은 사용하지 않는 것이 좋다.
> 이 ~~자원들을 클래스가 직접 만들게~~ 해서도 안 된다.
> 
> 대신 **필요한 자원을** (혹은 그 자원을 만들어주는 팩터리를) **생성자에** (혹은 정적 팩터리나 빌더에) **넘겨주자.**  
> **의존 객체 주입**이라 하는 이 기법은 **클래스의 유연성, 재사용성, 테스트 용이성**을 개선해준다.  