package com.sparta.thymeleaf.controller;

import com.sparta.thymeleaf.dto.Star;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class HelloRequestController {
    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }
//    http://localhost:8080/hello/request/form/html


    // [Request sample]
    // GET http://localhost:8080/hello/request/star/BTS/age/28
    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age)
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }
//    <response>    - 쌩 문자열이다. String.format을 사용해 escapeString을 사용하는 점을 집중
//    Hello, @PathVariable.
//    name = BTS, age = 28


    // [Request sample]
    // GET http://localhost:8080/hello/request/form/param?name=BTS&age=28
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }
//    <response>    - 쌩 문자열이다. 위와 같지만 다른 점이라면 url의 형태이다. Get방식이라 url에 위나 아래나 보이는 건 같다
//    Hello, @PathVariable.
//    name = BTS, age = 28

    
    // [Request sample]
    // POST http://localhost:8080/hello/request/form/param
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=BTS&age=28
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }
//    usecase = http://localhost:8080/hello/request/form/param?name=ktr&age=3
//    <response>    - Post방식이라 url에 드러나지 않는다
//    Hello, @RequestParam.
//    name = ktr, age = 3


    // [Request sample]
    // POST http://localhost:8080/hello/request/form/model
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=BTS&age=28
    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.getName(), star.getAge());
    }
//    h2>POST /hello/request/form/model</h2>
//<form method="POST" action="/hello/request/form/model">
//  <div>
//    이름: <input name="name" type="text">
//  </div>
//  <div>
//    나이: <input name="age" type="text">
//  </div>
//  <button>전송</button>
//</form>
//<br>
//    동기화 방식으로 보내지고 있다 input 태그에 있는 name을 변수로 @ModelAttribute된 객체에 value가 담겨져서 보내진다.
//    Post 방식이라 url에 드러나지 않고, 위와 다르게 @RequestParam이 아닌 @ModelAttribute로 받고 있기 때문에 url로는 보낼 수 없다


    // [Request sample]
    // POST http://localhost:8080/hello/request/form/json
    // Header
    //  Content type: application/json
    // Body
    //  {"name":"BTS","age":"28"}
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.getName(), star.getAge());
    }
    // 자바스크립트 async await을 사용해 비동기 방식으로 보내지고 있다
    // Post 방식이라 url에 드러나지 않고, @RequestBody로 받지만 js가 비동기 형식으로 JSON 형태로 가공하여 보냈기에 JSON 형태로 받아온다.
    // 프론트에서 response 받을 때는 다시금 body: JSON.stringify(data) 하여 문자열로 변형하고 있다
    // 이 역시 데이터를 JSON으로 가공하여 보내기에 url로는 보낼 수 없다

    //<h2>POST /hello/request/form/json</h2>
    //<form id="helloJsonForm">
    //  <div>
    //    이름: <input name="name" type="text">
    //  </div>
    //  <div>
    //    나이: <input name="age" type="text">
    //  </div>
    //</form>
    //<div>
    //  <button id="helloJsonSend">전송</button>
    //</div>
    //<div>
    //  <div id="helloJsonResult"></div>
    //</div>
    //      const helloJson = document.querySelector("#helloJsonSend")
    //    helloJson.onclick = async (e) => {
    //    const form = document.querySelector("#helloJsonForm");
    //
    //    const data = {
    //                name: form.querySelector('input[name="name"]').value,
    //                age: form.querySelector('input[name="age"]').value
    //    }
    //
    //    const response = await fetch('/hello/request/form/json', {
    //                method: 'POST',
    //                headers: {
    //            'Content-Type': 'application/json',
    //        },
    //        body: JSON.stringify(data),
    //    })
    //
    //    const text = await response.text(); // read response body as text
    //        document.querySelector("#helloJsonResult").innerHTML = text;
    //    };



}