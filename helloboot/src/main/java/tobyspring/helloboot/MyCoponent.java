package tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 애너테이션이 어디까지 살아있을 것인지 언제까지 유지될 것인지
@Target(ElementType.TYPE) // 애너테이션 적용 대상 지정
@Component
public @interface MyCoponent {
}
