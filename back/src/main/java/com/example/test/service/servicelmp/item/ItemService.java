package com.example.test.service.servicelmp.item;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.test.dto.ItemDTO;
import com.example.test.entity.Item;
import com.example.test.exeption.ApiRequestException;
import com.example.test.helper.SpecificationBuilder;
import com.example.test.mapper.ItemMapper;
import com.example.test.repository.ItemRepository;
import com.example.test.type.SortDirection;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private  SpecificationBuilder<Item> specificationBuilder;

    public ItemDTO addItem(ItemDTO itemDTO) {
        if (itemDTO.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        Item item = itemMapper.toItemEntityWithoutCart(itemDTO);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toItemDTO(savedItem);
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }


    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();

            item.setCode(itemDTO.getCode());
            item.setName(itemDTO.getName());
            item.setDescription(itemDTO.getDescription());
            item.setImage(itemDTO.getImage());
            item.setCategory(itemDTO.getCategory());
            item.setPrice(itemDTO.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            item.setInternalReference(itemDTO.getInternalReference());
            item.setShellId(itemDTO.getShellId());
            item.setInventoryStatus(itemDTO.getInventoryStatus());
            item.setRating(itemDTO.getRating());
            item.setUpdatedAt(System.currentTimeMillis());

            Item updatedItem = itemRepository.save(item);

            return itemMapper.toItemDTO(updatedItem);
        } else {
            throw new ApiRequestException("Item not found with ID: " + itemId);
        }
    }


    public ItemDTO getItemById(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            return itemMapper.toItemDTO(itemOptional.get());
        } else {
            throw new ApiRequestException("Item not found with ID: " + itemId);
        }
    }





  public Page<ItemDTO> searchWithCriteria(String productName, int page, int size, String sortField, SortDirection sortDirection) {
        Specification<Item> spec = buildSpecifications(productName);
        Pageable pageable = buildPageable(page, size, sortField, sortDirection);


        Page<Item> pageResult = itemRepository.findAll(spec, pageable);
        return pageResult.map(itemMapper::toItemDTO);
    }

    private Pageable buildPageable(int page, int size, String sortField, SortDirection sortDirection) {
        Sort sort = Sort.by(Objects.equals(sortDirection.label, "desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        return PageRequest.of(page, size, sort);
    }
    private Specification<Item> buildSpecifications(String productName) {
        Specification<Item> spec = Specification.where(null);
        if (productName != null && !productName.isEmpty()) {
            spec = spec.and(specificationBuilder.equalSpec(spec, "productName", productName));   }
            System.out.println("Specification: " + spec);
        return spec;
    }

}
