//package tobyspring.helloboot;
//
//import org.springframework.boot.web.server.WebServer;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//public class MySpringApplication {
//
//    public static void run(Class<?> applicationClass, String... args) {
//        // 스프링 컨테이너 생성
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
//            @Override
//            protected void onRefresh() {
//                super.onRefresh(); // 생략 X
//
//                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
//                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//
//                WebServer webServer = serverFactory.getWebServer(servletContext -> {
//                    servletContext.addServlet("dispatcherServlet",dispatcherServlet)
//                            .addMapping("/*");
//                }); // 웹서버 생성
//                webServer.start(); // Tomcat Servelet Container 동작
//            }
//        };
//        applicationContext.register(applicationClass);
//        applicationContext.refresh(); // 컨테이너 초기화(빈 오브젝트 생성)
//    }
//
//}
