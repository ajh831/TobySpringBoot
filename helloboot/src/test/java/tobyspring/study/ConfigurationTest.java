package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    @DisplayName("Configuration의 default 구성 특징 테스트")
    public void configuration() {
//        Assertions.assertThat(new Common()).isSameAs(new Common()); // 실패. 두개가 다른 오브젝트이므로

/*
        Common common = new Common();
        Assertions.assertThat(common).isSameAs(common); // 성공.
*/

/*
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common); // 실패. 주고값이 다르기 때문에
*/

        // MyConfig를 Spring의 구성정보로 사용하면 동작방식이 달라짐
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common); // 성공.
    }

    @Test
    @DisplayName("스프링 빈 동작방식 테스트")
    public void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();// 확장해서 대체하는 방식으로 동작

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common); // 성공
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) this.common = super.common();

            return this.common;
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }

    // Bean1 <-- Common
    // Bean2 <-- Common
}
