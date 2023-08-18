package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}",memberName,memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age) {
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    //필수값설정에 빈문자열 값이 들어가면 정상요청으로 인식함
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    //빈문자열 값이 들어와도 defaultValue가 적용된다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "-1") int age) {
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(@RequestParam Map<String,Object> paramMap) {
        log.info("username = {}, age = {}",paramMap.get("username"),paramMap.get("age"));

        return "ok";
    }

    //@ModelAttribute를 사용해 객체 프로퍼티에 값을 넣어주기위해 setter 또는 생성자로 데이터 바인딩이 가능하다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}",helloData.getUsername(),helloData.getAge());
        log.info(helloData.toString());
        return "ok";
    }


    //애노테이션 생략시 String, int, Integer와 같은 타입은 @RequestParam을 적용
    //나머지 ArgumenyResolver로 지정해둔 타입 외에 객체는 @ModelAttribute를 적용한다
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username = {}, age = {}",helloData.getUsername(),helloData.getAge());
        log.info(helloData.toString());
        return "ok";
    }


}
