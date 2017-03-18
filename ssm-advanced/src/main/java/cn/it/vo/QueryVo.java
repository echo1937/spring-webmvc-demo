package cn.it.vo;

import cn.it.pojo.Items;

import java.util.List;

/**
 * Created by Eric on 3/16/17.
 */
public class QueryVo {

    private Items items;                    //商品对象
    private Integer[] ids;                  //批量删除使用
    private List<Items> itemsList;          //批量修改使用
    //订单对象
    //用户对象

    public Items getItems() {
        return items;
    }

    public void setItems(Items item) {
        this.items = item;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }
}
