package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//다양한 타입의 컨트롤러를 동작시키기위함
public interface MyHandlerAdapter {
    //인자로 넘어온 핸들러를 어댑터가 지원하는지 여부 확인
    boolean supports(Object handler);

    //supports에서 true를 반환하면 해당 메서드가 동작하여 실제 핸들러 호출
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
