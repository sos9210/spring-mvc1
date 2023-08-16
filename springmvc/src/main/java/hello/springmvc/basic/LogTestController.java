package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j  //롬복에서 지원하는 애노테이션
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(this.getClass());
    //System.out... 은 성능상 이슈발생 가능성이 큼
    //log라이브러리는 날짜, 로그레벨, 프로세스ID, 쓰레드명, 클래스패키지 정보등 추가로 제공되니 안 쓸 이유가없다.
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);
        return "ok";
    }
}
