import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eric on 3/20/17.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/item")
public class Controller {

    @RequestMapping(value = "/delete")
    public String deleteItems(Integer[] ids) {
        // 大多数情况下一个参数名对应一个参数值，但有时一个参数名可以对应多个参数值，多个值以String数组的形式返回。
        return "";
    }

    @RequestMapping(value = "/delete")
    public String deleteItems(QueryVo vo) {
        // Spring MVC会将表单内容绑定到QueryVo的Integer[] ids中
        return "";
    }

    @RequestMapping(value = "/update")
    public String updateItems(QueryVo vo) {
        // Spring MVC根据表单itemList的下标组装Item对象，并将对象存放至QueryVo的List<Item> itemList属性中
        return "";
    }

    @RequestMapping(value = "/queryItem")
    public String queryItem() {
        return "";
    }

}
