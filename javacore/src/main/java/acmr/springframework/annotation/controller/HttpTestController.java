package acmr.springframework.annotation.controller;

import acmr.springframework.annotation.entity.JsonReturnData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/httptest")
public class HttpTestController {

    @RequestMapping(value = "/test", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new JsonReturnData(200, "成功", "啊我就试试"));
    }
}
