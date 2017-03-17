package cn.it.service;

import cn.it.pojo.Items;

import java.util.List;

/**
 * Created by Eric on 3/15/17.
 */
public interface ItemsService {

    List<Items> list() throws Exception;

    Items findItemsById(Integer id) throws Exception;

    void updateItems(Items items) throws Exception;
}
