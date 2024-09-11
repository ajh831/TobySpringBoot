package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    public void helloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate(); // RestTemplate : api 요청을 호출해서 응답을 가져와서 사용할 수 있음

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // 응답 검증
        // status code 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    public void failshelloApi() {
        TestRestTemplate rest = new TestRestTemplate(); // RestTemplate : api 요청을 호출해서 응답을 가져와서 사용할 수 있음

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:9090/app/hello?name=", String.class, "Spring");

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
