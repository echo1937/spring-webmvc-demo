package cn.it.controller;

import cn.it.pojo.Items;
import cn.it.service.ItemsService;
import cn.it.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Eric on 3/15/17.
 */
@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/list")
    public ModelAndView itemsList() throws Exception {
        List<Items> itemsList = itemsService.list();

        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加model(模型)，模型名为"itemList"，值为List<Items>对象itemsList
        modelAndView.addObject("itemList", itemsList);
        //添加view(视图)，逻辑视图名为"itemList"
        modelAndView.setViewName("itemList");
        //如果没有配置视图解析器, 则需要指明视图的实际位：modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");

        return modelAndView;
    }

    /**
     * 默认支持的参数类型包括: HttpServletRequest/HttpServletResponse/HttpSession/Model/ModelAndMap...
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemEdit")
    public String itemEdit(Model model, HttpServletRequest request) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        Items items = itemsService.findItemsById(id);
        model.addAttribute("item", items);

        return "editItem";
    }

    /**
     * 也支持直接接收基本类型：public String update(Integer id, String name, Float price, String detail) throws Exception {}
     *
     * @param items
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateItem")
    public String update(Items items) throws Exception {
        // items.setCreatetime(new Date());


        itemsService.updateItems(items);
        return "redirect:/list.action";
    }

    // 如果Controller中接收的入参是QueryVo,那么页面上input框的name属性值要等于"属性.属性..."的形式
    @RequestMapping("/item/queryItem")
    public String queryItem(QueryVo vo) {
        System.out.println(vo.getItems().getName() + "  " + vo.getItems().getPrice());
        return "redirect:/list.action";
    }


}
