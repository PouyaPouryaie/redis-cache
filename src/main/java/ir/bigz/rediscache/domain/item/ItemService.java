package ir.bigz.rediscache.domain.item;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    ItemView getItemById(Long itemId);
    ItemView saveItem(Item item);
    ItemView updateItem(Item item, Long itemId);
    void deleteItemById(Long itemId);
}
