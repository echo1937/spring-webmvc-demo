package cn.it.controller;

import cn.it.pojo.Items;
import cn.it.service.ItemsService;
import cn.it.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eric on 3/15/17.
 */
@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping(value = {"/list.action"})
    public ModelAndView itemsList(boolean u) throws Exception {
        List<Items> itemsList = itemsService.list();

        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加model(模型)，模型名为"itemList"，值为List<Items>对象itemsList
        modelAndView.addObject("itemList", itemsList);
        //添加view(视图)，逻辑视图名为"itemList"
        modelAndView.setViewName("itemList");

        if (u == true) {
            modelAndView.setViewName("update");
        }
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
        //model(模型): 模型中放入了返回给页面的数据,底层实际上用的是request域,但是对request域做了扩展.
        model.addAttribute("item", items);
        return "editItem";
    }

    @RequestMapping("/itemEdit.action")
    public String itemEdit(Model model, @RequestParam(value = "id", required = true) Integer item_id) throws Exception {
        Items items = itemsService.findItemsById(item_id);
        //model(模型): 模型中放入了返回给页面的数据,底层实际上用的是request作用域,但是对request作用域做了扩展.
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
    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public String update(Items items, MultipartFile pictureFile) throws Exception {
        // items.setCreatetime(new Date());

        // 1. 获取文件的原始名称
        String originalFilename = pictureFile.getOriginalFilename();
        // 2. 使用随机生成的字符串 + 原文件扩展名 为图片命名, 防止重名现象发生
        String s = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 3. 将文件保存到硬盘
        pictureFile.transferTo(new File("/Users/Eric/Pictures/tomcat_tmp/" + s));
        // 4. 将新文件名保存到数据库
        items.setPic(s);

        itemsService.updateItems(items);
        return "redirect:/list.action";
    }

    // 如果Controller中接收的入参是QueryVo,那么页面上input框的name属性值要等于"属性.属性..."的形式
    @RequestMapping(value = "/item/delAll")
    public String queryItem(QueryVo vo) throws Exception {
        //如果是批量删除,多行复选框可以用数组提交,我们把Integer[] ids属性放到QueryVo中.
        Integer[] ids = vo.getIds();
        for (Integer id : ids) {
            itemsService.deleteItems(id);
        }
        return "redirect:/list.action";
    }

    @RequestMapping(value = "/item/itemAdd")
    public String itemAdd(Items items) throws Exception {
        itemsService.insertItems(items);
        return "redirect:/list.action";
    }

    @RequestMapping(value = "/item/updateAll")
    public String updateAll(QueryVo vo) throws Exception {
        List<Items> itemsList = vo.getItemsList();
        Integer[] ids = vo.getIds();

        if (ids == null) {
            return "redirect:/list.action?u=true";
        }
        for (Integer id : ids) {
            for (Items items : itemsList) {
                if (id == items.getId()) {
                    itemsService.updateItems(items);
                    continue;
                }
            }
        }
        return "redirect:/list.action?u=true";
    }

    @RequestMapping(value = "/item/sendJson.action")
    @ResponseBody
    public Items json(@RequestBody Items items) throws Exception {
        // Spring MVC将Json格式字符串自动转化为Java对象, 要求json的key与Items的属性名相同
        System.out.println(items);
        return items;
    }


}
