package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {

        // given
        Item item = new Item("ItemA", 1000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(savedItem.getId());

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {

        // given
        Item itemA = new Item("ItemA", 1000, 10);
        Item itemB = new Item("ItemB", 2000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> findAll = itemRepository.findAll();

        // then
        assertThat(findAll.size()).isEqualTo(2);
        assertThat(findAll).contains(itemA, itemB);
    }

    @Test
    void updateItem() {

        // given
        Item itemA = new Item("itemA", 1000, 10);
        itemRepository.save(itemA);
        Long itemAId = itemA.getId();

        // when
        Item itemB = new Item("itemB", 1000, 10);
        itemRepository.update(itemAId, itemB);

        // then
        assertThat(itemA.getItemName()).isEqualTo(itemB.getItemName());
        assertThat(itemA.getPrice()).isEqualTo(itemB.getPrice());
        assertThat(itemA.getQuantity()).isEqualTo(itemB.getQuantity());
    }



}
