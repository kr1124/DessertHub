package com.desserthub.test;

import org.springframework.stereotype.Service;

// 이 어노테이션은 해당 클래스가 서비스 계층에 속함을 나타냅니다.
// Spring은 이 클래스를 **빈(Bean)**으로 등록하여 다른 클래스에서 의존성 주입을 통해 사용할 수 있게 만듭니다.
@Service
public class TestService {
    //비즈니스 로직을 처리하는 메서드입니다. 이 예시에서는 단순히 인사말을 반환합니다.
    public String getGreeting() {
        return "Hello, welcome to Dessert Hub!";
    }
}
