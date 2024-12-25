package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.dto.ItemDTO;
import com.example.test.service.servicelmp.item.ItemService;
import com.example.test.type.SortDirection;

@RestController
@RequestMapping("/v1/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }
    @PostMapping("/add")
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) {
        if (!"admin@admin.com".equalsIgnoreCase(getCurrentUserEmail())) {
            return ResponseEntity.status(403).body(null);
        }
        ItemDTO createdItem = itemService.addItem(itemDTO);
        return ResponseEntity.ok(createdItem);
    }

     @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
         if (!"admin@admin.com".equalsIgnoreCase(getCurrentUserEmail())) {
             return ResponseEntity.status(403).body(null);
         }
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long itemId, @RequestBody ItemDTO itemDTO) {
        if (!"admin@admin.com".equalsIgnoreCase(getCurrentUserEmail())) {
            return ResponseEntity.status(403).body(null);
        }
        ItemDTO updatedItem = itemService.updateItem(itemId, itemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long itemId) {

        ItemDTO item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }
//getAll
      @GetMapping("/listProjet")
      public Page<ItemDTO> searchRejetMessages(
             @RequestParam(required = false) String productName,
              @RequestParam(defaultValue = "0") int page,
              @RequestParam(defaultValue = "25") int size,
              @RequestParam(required = false) String sortField,
              @RequestParam(defaultValue = "ASC") SortDirection sortDirection) {
          return itemService.searchWithCriteria(productName, page, size, sortField, sortDirection);
      }
}
