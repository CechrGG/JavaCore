package acmr.springframework.xml.controller;

import acmr.springframework.util.SpringXmlUtil;
import acmr.springframework.util.StringUtil;
import acmr.springframework.xml.entity.Cat;
import acmr.springframework.xml.service.EzCatService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CatController implements Controller {
    private final EzCatService catSrv = SpringXmlUtil.getBean("ezCatSrv", EzCatService.class);
    private final int pageSize = 10;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pagenum = request.getParameter("pagenum");
        String pagesize = request.getParameter("pagesize");
        int pnum = StringUtil.isEmpty(pagenum) ? 1 : Integer.parseInt(pagenum);
        int psize = StringUtil.isEmpty(pagesize) ? pageSize : Integer.parseInt(pagesize);
        List<Cat> catList = catSrv.getCatList(pnum, psize);
        return new ModelAndView("cat/index").addObject("cats", JSON.toJSONString(catList));
    }
}
