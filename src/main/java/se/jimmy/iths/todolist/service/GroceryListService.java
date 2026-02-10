package se.jimmy.iths.todolist.service;

import org.springframework.stereotype.Service;

@Service
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;

    public GroceryListService(GroceryListRepository groceryListRepository) {
        this.groceryListRepository = groceryListRepository;
    }

    public GroceryList save(GroceryList item) {
        return groceryListRepository.save(item);
    }

    public List<GroceryList> getAllGrocery() {
        return groceryListRepository.getAllGrocery();
    }

    public GroceryList findById(Long id) {
        return groceryListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public void deleteItem(Long id) {
        groceryListRepository.deleteItem(id);
    }

}
