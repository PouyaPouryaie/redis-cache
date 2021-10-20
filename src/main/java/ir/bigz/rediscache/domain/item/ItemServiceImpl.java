package ir.bigz.rediscache.domain.item;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    @Cacheable(value = "itemCache", key = "#itemId")
    @Transactional(readOnly = true)
    public ItemView getItemById(Long itemId) {
        Item item = itemRepository.getById(itemId);
        return ItemView.builder()
                .id(item.getId())
                .createDate(item.getCreateDate())
                .name(item.getName())
                .quantity(item.getQuantity())
                .build();
    }

    @Override
    public ItemView saveItem(Item item) {
        itemRepository.save(item);
        return ItemView.builder()
                .id(item.getId())
                .createDate(item.getCreateDate())
                .name(item.getName())
                .quantity(item.getQuantity())
                .build();
    }

    @Override
    @CachePut(value="itemCache", key="#itemId")
    public ItemView updateItem(Item item, Long itemId) {
        Optional<Item> itemById = itemRepository.findById(itemId);
        if(itemById.isPresent()){
            Item updateItem = itemById.get();
            updateItem.setName(item.getName());
            updateItem.setQuantity(item.getQuantity());
            return ItemView.builder()
                    .id(updateItem.getId())
                    .createDate(updateItem.getCreateDate())
                    .name(updateItem.getName())
                    .quantity(updateItem.getQuantity())
                    .build();
        }
        else {
            throw new RuntimeException("item not found");
        }
    }

    @Override
    @CacheEvict(value="itemCache", key="#itemId")
    public void deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
