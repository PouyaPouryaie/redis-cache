package ir.bigz.rediscache.item;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    Item getItemById(Long itemId);
    Item saveItem(Item item);
    Item updateItem(Item item, Long itemId);
    void deleteItemById(Long itemId);
}
