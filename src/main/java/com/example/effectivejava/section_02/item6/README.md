## 💡 아이템 6: 불필요한 객체 생성을 피하라

> **기존 객체를 재사용해야 한다면, 새로운 객체를 만들지 마라**  
> <-> 방어적 복사 (아이템 50)


### ✔️ 문자열 객체 생성시

```java
/**
 * 절대 사용하면 안되는 극단적인 예시
 * */
String s = new String("bikini");
```

해당 코드는 실행될 때마다 String 인스턴스를 새로 만든다.  
완전히 쓸데없는 행위이다.


```java
/**
 * 개선된 버전
 */
String s = "bikini";
```

해당 코드는 ~~새로운 인스턴스를 매번 만드는 대신~~ **하나의 String 인스턴스를 사용**한다.  
또한 해당 코드는 같은 가상 머신 안에서 **이와 똑같은 문자열 리터럴을 사용하는 모든 코드가 `같은 객체를 재사용함`이 보장**된다.  

* 예시: String pool의 Flyweight Pattern - 같은 내용의 String 객체가 선언된다면 기존의 객체를 참조하게 한다.  
  > **플라이웨이트 패턴 (Flyweight Pattern)**  
  >
  > 구조(Structural) 패턴 중 하나로써 인스턴스가 필요할 때마다 ~~매번 생성~~하는 것이 아니고 **가능한 한 공유해서 사용함으로써 메모리를 절약하는 패턴**  


### ✔️ 정적 팩터리 메서드(static factory method)를 이용해 불필요한 객체 생성 피하기

> 정적 팩터리 메서드를 제공하는 불변 클래스에서는 ~~생성자~~ 대신 `정적 팩터리 메서드`를 사용해 불필요한 객체 생성을 피할 수 있다.  
> 
> **생성자**: 생성할 때마다 새로운 객체 생성  
> **정적 팩터리 메서드**: X

ex) `Boolean(String)` 대신 **`Boolean.valueOf(String)`** 이용하기

```java

public final class Boolean implements java.io.Serializable,
                                      Comparable<Boolean>
{
    /**
     * The {@code Boolean} object corresponding to the primitive
     * value {@code true}.
     */
    public static final Boolean TRUE = new Boolean(true);

    /**
     * The {@code Boolean} object corresponding to the primitive
     * value {@code false}.
     */
    public static final Boolean FALSE = new Boolean(false);

    /**
     * The Class object representing the primitive type boolean.
     *
     * @since   1.1
     */
    @SuppressWarnings("unchecked")
    public static final Class<Boolean> TYPE = (Class<Boolean>) Class.getPrimitiveClass("boolean");

    /**
     * The value of the Boolean.
     *
     * @serial
     */
    private final boolean value;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -3665804199014368530L;

    /**
     * Allocates a {@code Boolean} object representing the
     * {@code value} argument.
     *
     * @param   value   the value of the {@code Boolean}.
     *
     * @deprecated
     * It is rarely appropriate to use this constructor. The static factory
     * {@link #valueOf(boolean)} is generally a better choice, as it is
     * likely to yield significantly better space and time performance.
     * Also consider using the final fields {@link #TRUE} and {@link #FALSE}
     * if possible.
     */
    @Deprecated(since="9")
    public Boolean(boolean value) {
        this.value = value;
    }

    /**
     * Allocates a {@code Boolean} object representing the value
     * {@code true} if the string argument is not {@code null}
     * and is equal, ignoring case, to the string {@code "true"}.
     * Otherwise, allocates a {@code Boolean} object representing the
     * value {@code false}.
     *
     * @param   s   the string to be converted to a {@code Boolean}.
     *
     * @deprecated
     * It is rarely appropriate to use this constructor.
     * Use {@link #parseBoolean(String)} to convert a string to a
     * {@code boolean} primitive, or use {@link #valueOf(String)}
     * to convert a string to a {@code Boolean} object.
     */
    @Deprecated(since="9")
    public Boolean(String s) {
        this(parseBoolean(s));
    }

    /**
     * Returns a {@code Boolean} with a value represented by the
     * specified string.  The {@code Boolean} returned represents a
     * true value if the string argument is not {@code null}
     * and is equal, ignoring case, to the string {@code "true"}.
     * Otherwise, a false value is returned, including for a null
     * argument.
     *
     * @param   s   a string.
     * @return  the {@code Boolean} value represented by the string.
     */
    public static Boolean valueOf(String s) {
        return parseBoolean(s) ? TRUE : FALSE;
    }



```

### ✔️ 무거운 객체

> 만드는 데 메모리나 시간이 오래 걸리는 '비싼 객체' 를 반복해서 필요하다면,  
캐싱하여 재사용할 수 있는지 고려해야한다.

```java
public class RomanNumerals {

    /**
     * isRomanNumeralSlow 함수의 문제점
     *
     * String.matches 메서드를 사용
     * 해당 메서드가 내부에서 만드는 정규 표현식용 Pattern 인스턴스는 한 번 쓰고 버려져서 곧바로 가비지 컬렉션의 대상이 된다.
     * Pattern 은 생성비용이 높은 클래스 중 하나
     */
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    /**
     *
     * 늘 같은 Pattern 이 필요함이 보장되고 재사용 빈도가 높다면
     * 다음과 같이 상수(static final)로 초기에 캐싱해놓고 재사용하기
     *
     * 성능을 개선하려면 필요한 정규표현식을 표현하는 (불변) Pattern 인스턴스를
     * 클래스 생성시 초기화 (정적 초기화) 과정에서 직접 생성해 캐싱해두고,
     * 나중에 isRomanNumeralFast 함수가 호출될 때마다 이를 재사용한다.
     */
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

}

```

> 생성비용이 비싼 객체라면 "캐싱" 방식을 고려해야 한다.  
> 자주 쓰는 값이라면 `static final`로 초기에 캐싱해놓고 이를 재사용하자.


### ✔️ 같은 인스턴스를 대변하는 여러 개의 인스턴스를 생성하지 말자

```java
Map<String, Object> map = new HashMap<>();
map.put("Hello", "World");

Set<String> set1 = map.keySet();
Set<String> set2 = map.keySet();

assertThat(set1).isSameAs(set2); // TRUE

set1.remove("Hello");
System.out.println(set1.size()); // 1
System.out.println(set1.size()); // 1
```

Map 인터페이스의 keySet 메서드는 Map 객체 안의 키 전부를 담은 Set 인터페이스의 뷰를 반환한다.  
하지만, 동일한 Map에서 호출하는 keySet 메서드는 같은 Map을 대변하기 때문에 반환한 객체 중 하나를 수정하면 다른 모든 객체가 따라서 바뀐다.  
따라서 keySet이 뷰 객체 여러 개를 만들 필요도 없고 이득도 없다.  


### ✔️ 불필요한 객체를 만들어내는 또 다른 예시: 오토박싱(auto boxing)

> **오토박싱(auto boxing)**
> 
> 프로그래머가 `기본 타입`과 `박싱된 기본 타입`을 섞어 쓸 때 자동으로 상호 변환해주는 기술  

```java
/**
 * 코드 6-3: 박싱된 기본 타입보다는 "기본 타입"을 사용하고, 의도치 않은 오토박싱이 숨어있지 않도록 주의하자.
 */
public class Sum {

  public static long sumSlow() {
    Long sum = 0L;
    for (long i=0; i<Integer.MAX_VALUE; i++) {
      sum += i; // 문제인 부분 -> 여기서 매번 불필요한 Long 인스턴스가 생성된다
    }
    return sum;
  }

  public static long sumFast() {
    long sum = 0L;
    for (long i=0; i<Integer.MAX_VALUE; i++) {
      sum += i;
    }
    return sum;
  }
}

```

> **박싱된 기본 타입보다는 "기본 타입"을 사용하고, 의도치 않은 오토박싱이 숨어있지 않도록 주의하자.**  


### ✔️ 기타

"객체 생성은 비싸니 피해야 한다"로 오해하면 안 된다.  

특히나 요즘의 JVM에서는 별다른 일을 하지 않는 작은 객체를 생성하고 회수하는 일이 크게 부담되지 않는다.  
프로그램의 명확성, 간결성, 기능을 위해 객체를 추가로 생성하는 것이라면 일반적으로 좋은 일이다.  

그렇다고 단순히 객체 생성을 피하기 위해 자신만의 객체 풀(pool)을 만들지는 말자.  
DB 커넥션 같은 경우 생성 비용이 워낙 비싸니 재사용 하는 편이 낫다.  
하지만 일반적으로 자체 객체 풀은 코드를 헷갈리게 하고, 메모리 사용량을 늘리고, 성능을 떨어뜨린다.  

요즘 JVM의 GC는 상당히 잘 최적화 되어서, 가벼운 객체를 다룰 때는 직접 만든 객체 풀보다 훨씬 빠르다.  


### ✔️ 재사용 <-> 방어적 복사(아이템 50)

* 아이템6: 기존 객체를 재사용해야 한다면, 새로운 객체를 만들지 마라
* 아이템50: 새로운 객체를 만들어야 한다면, 기존 객체를 재사용하지 마라
  * 방어적 복사가 필요한 상황에서 객체를 재사용했을 때의 피해 >> 필요 없는 객체를 반복 생성했을 때의 피해