package ir.bigz.rediscache.item;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    @Cacheable(value = "itemCache", key = "#itemId")
    public Item getItemById(long itemId) {
        Item item = itemRepository.getById(itemId);
        return item;
    }

    @Override
    public Item saveItem(Item item) {
        item.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        return itemRepository.save(item);
    }

    @Override
    @CachePut(value="itemCache", key="#itemId")
    public Item updateItem(Item item, long itemId) {
        Optional<Item> itemById = itemRepository.findById(itemId);
        if(itemById.isPresent()){
            Item itemFind = itemById.get();
            itemFind.setName(item.getName());
            itemFind.setQuantity(item.getQuantity());
            itemFind.setVersion(item.getVersion());
            return itemFind;
        }
        else {
            throw new RuntimeException("item not found");
        }
    }

    @Override
    @CacheEvict(value="itemCache", key="#itemId")
    public void deleteItemById(long itemId) {
        itemRepository.deleteById(itemId);
    }
}
