package se.jimmy.iths.todolist.service;

import org.springframework.stereotype.Service;

@Service
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;
    private final GroceryListValidator groceryListValidator;

    public GroceryListService(GroceryListRepository groceryListRepository, GroceryListValidator groceryListValidator) {
        this.groceryListRepository = groceryListRepository;
        this.groceryListValidator = groceryListValidator;
    }


    public List<GroceryList> findAll() {
        return groceryListRepository.findAll();
    }

    public GroceryList findById(Long id) {
        return groceryListRepository.findById(id)
                .orElseThrow(() -> new GroceryListNotFoundException(id));
    }

    public GroceryList createGroceryList(GroceryList groceryList) {
        validator.validate(groceryList);
        return repository.save(groceryList);
    }

    public GroceryList updateGroceryList(Long id, GroceryList updated) {
        GroceryList existing = findById(id);

        validator.validate(updated);

        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setCategory(updated.getCategory());
        existing.setPurchased(updated.isPurchased());

        return groceryListRepository.save(existing);
    }

    public void deleteItem(Long id) {
        //groceryListRepository.deleteById(id);
        GroceryList existing = findById(id);
        groceryListRepository.delete(existing);
    }

}
