package fr.training.samples.spring.shop.domain.item;

import java.util.Set;

/**
 * @author Badr NASS
 *
 */
public interface ItemRepository {

    /**
     * @param itemEntity
     * @return
     */
    ItemEntity addItem(ItemEntity itemEntity);

    /**
     * @param itemId
     * @return
     */
    ItemEntity findOne(String itemId);

    /**
     * @param itemsId
     * @return
     */
	Set<ItemEntity> getAllItems(Set<String> itemsId);
}
