## 아이템 3
### private 생성자나 열거 타입으로 싱글턴임을 보증하라

> **싱글턴 (singleton)**
> 
> : 인스턴스를 오직 하나만 생성할 수 있는 클래스  
> ex) 무상태(stateless) 객체, 설계상 유일해야 하는 시스템 컴포넌트

* 싱글턴 사용 이유?  
한번의 객체 생성으로 재사용이 가능하기 때문에 메모리 낭비를 방지할 수 있다.   
또한, 싱글톤으로 생성된 객체는 무조건 한번 생성으로 전역성을 띄기에 다른 객체와 공유가 용이하다.  


> **싱글턴을 만드는 방식**
> 1. public static final 필드 방식
> 2. 정적 팩터리 메서드 방식
> 3. 열거 타입 방식


### 1. public static final 필드 방식

```java
/**
* 코드 3-1: public static final 필드 방식의 싱글턴
*/
public class Elvis {

  public static final Elvis INSTANCE = new Elvis();

  private Elvis() {
  }
}
```

* 장점
  * 해당 클래스가 싱글턴임이 API에 명백히 드러난다.
  * public static 필드가 final이니 절대 다른 객체를 참조할 수 없다.
  * 간결하다.  


* 예외
  * 리플렉션 API인 `AccessibleObject.setAccessible` 을 사용해 private 생성자를 호출할 수 있다.
  * `리플렉션 API`: java.lang.reflect, class 객체가 주어지면, 해당 클래스의 인스턴스를 생성하거나 메소드를 호출하거나, 필드에 접근할 수 있다.  

* 해결 방법
  * 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외를 던지게 하면 된다.


### 2. 정적 팩터리 메서드 방식

```java
/**
 * 코드 3-2: 정적 팩터리 메서드 방식의 싱글턴
 */
public class Elvis {

    private static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public static Elvis getInstance() {
        return INSTANCE;
    }

}
```

* 장점
    * API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.
      * (getInstance() 호출부 수정 없이 내부에서 private static이 아닌 새 인스턴스를 생성해주면 된다.)
    유일한 인스턴스를 반환하던 팩터리 메서드가 호출하는 스레드 별로 다른 인스턴스를 넘겨주게 할 수 있다.
    * 원한다면 정적 팩터리 메서드를 제네릭 싱글턴 팩터리로 만들 수 있다.
    * 정적 팩터리의 메서드 참조를 공급자(Supplier)로 사용할 수 있다.
      * `Supplier`: get 메서드 만을 가지고 아무 type 이나 return 할 수 있는 인터페이스


(리플렉션을 통한 예외는 똑같이 적용된다.)


### 두 방식의 문제점

각 클래스를 직렬화한 후 역직렬화할 때 새로운 인스턴스를 만들어서 반환한다.  
역직렬화는 기본 생성자를 호출하지 않고 값을 복사해서 새로운 인스턴스를 반환한다. 그때 통하는게 readResolve() 메서드이다.  
이를 방지하기 위해 readResolve 에서 싱글턴 인스턴스를 반환하고, 모든 필드에 transient(직렬화 제외) 키워드를 넣는다.  

싱글턴 클래스를 직렬화하려면 단순히 Serializable을 구현하고 선언하는 것만으로 부족하다.  
모든 인스턴스 필드를 일시적(transient)라고 선언하고 readResolve 메서드를 제공해야 한다.  

이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 만들어진다.  

가짜 Elvis탄생을 예방하고 싶다면 Elvis 클래스에 다음의 readResolve 메서드를 추가해야한다.  

* 역직렬화시 반드시 호출되는 **readResolve** 메소드를 싱글턴을 리턴하도록 수정

```java
private Object readResolve() {
    return INSTANCE;
}
```

진짜 Elvis를 반환하고, 가짜 Elvis는 가비지 컬렉터에 맡긴다.


### 3. 열거 타입 방식의 싱글턴

```java
/**
 * 코드 3-3: 열거 타입 방식의 싱글턴
 */
public enum Elvis {
    INSTANCE;
    
}

```

* Elvis 타입의 인스턴스는 INSTANCE 하나 뿐, 더 이상 만들 수 없다.  
* 복잡한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.  
* 최선의 방법이다.    
  

* 단, 만들려는 싱글턴이 Enum 이외의 다른 상위 클래스를 상속해야 한다면 이 방법은 사용할 수 없다.