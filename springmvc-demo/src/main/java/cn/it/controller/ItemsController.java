package cn.it.controller;

import cn.it.pojo.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 3/15/17.
 */
@Controller
public class ItemsController {

    @RequestMapping("/list")
    public ModelAndView itemsList() throws Exception {

        List<Items> itemList = new ArrayList<>();
        //商品列表
        Items items_1 = new Items();
        items_1.setName("联想笔记本_3");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemList.add(items_1);
        itemList.add(items_2);

        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加模型model
        modelAndView.addObject("itemList", itemList);
        //添加视图view，如果没有配置视图解析器需要指明视图的绝对路径：modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
        modelAndView.setViewName("itemList");
        return modelAndView;
    }


}
