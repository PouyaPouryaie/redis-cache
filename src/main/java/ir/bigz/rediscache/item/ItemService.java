package ir.bigz.rediscache.item;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    Item getItemById(long itemId);
    Item saveItem(Item item);
    Item updateItem(Item item, long itemId);
    void deleteItemById(long itemId);
}
