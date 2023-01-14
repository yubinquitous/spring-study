package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")    // "/hello"로 들어오면 이 메서드를 호출해줌
    public String hello(Model model) {
        model.addAttribute("data", "hello!");   // key:value 쌍으로 전달
        return "hello"; // hello.html로 넘어감
    }

    /* MVC와 템플릿 엔진 */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
