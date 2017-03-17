package cn.it.service;

import cn.it.dao.ItemsMapper;
import cn.it.pojo.Items;
import cn.it.pojo.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eric on 3/15/17.
 */

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> list() throws Exception {

        ItemsExample itemsExample = new ItemsExample();
        List<Items> list = itemsMapper.selectByExample(itemsExample);
        return list;
    }

    @Override
    public Items findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    @Override
    public void updateItems(Items items) throws Exception {
        itemsMapper.updateByPrimaryKeyWithBLOBs(items);
    }
}
