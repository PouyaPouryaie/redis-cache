package ir.bigz.rediscache.domain.item;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(path = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItemById(@PathVariable Long itemId) {
        try {
            ItemView item = itemService.getItemById(itemId);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveItem(@RequestBody Item item) {
        try {
            ItemView itemResult = itemService.saveItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(itemResult);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message error: " + e.getMessage());
        }
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        try {
            ItemView itemResult = itemService.updateItem(item, itemId);
            return ResponseEntity.ok(itemResult);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message error: " + e.getMessage());
        }
    }

    @PostMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        try {
            itemService.deleteItemById(itemId);
            return ResponseEntity.ok("{\"message\":\"item is deleted\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message error: " + e.getMessage());
        }
    }
}
