package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("hello basic");
        return "ok";
    }

    //GetMapping사용용
   @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    //PathVariable사용
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}",data);
        return "ok";
    }
    //다중 PathVariable사용
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String multiMappingPath(@PathVariable String userId,@PathVariable String orderId) {
        log.info("multiMappingPath userId={}",userId);
        log.info("multiMappingPath orderId={}",orderId);
        return "ok";
    }
    //특정 파라미터 매핑
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //특정 헤더로 매핑
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //Content-Type 헤더 기반 매핑
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }

    //Accept 헤더 기반 매핑
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
}
