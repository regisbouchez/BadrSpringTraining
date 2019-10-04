package fr.training.samples.spring.shop.exposition.item.rest;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.training.samples.spring.shop.application.item.ItemManagement;
import fr.training.samples.spring.shop.domain.item.ItemEntity;

/**
 * @author Badr NASS
 *
 */
@RestController
@RequestMapping("/api")
public class ItemResource {

	/**
	 * itemManagement of type ItemManagement
	 */
	private transient ItemManagement itemManagement;

	/**
	 * itemMapper of type ItemMapper
	 */
	private transient final ItemMapper itemMapper;

	/**
	 * logger of type Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ItemResource.class);

	/**
	 * @param itemManagement itemManagement
	 * @param itemMapper     itemMapper
	 */
	public ItemResource(ItemManagement itemManagement, ItemMapper itemMapper) {
		super();
		this.itemManagement = itemManagement;
		this.itemMapper = itemMapper;
	}


	@PostMapping("/items")
	public ResponseEntity<URI> addItem(@Valid @RequestBody final ItemLightDTO itemDTO) {
		final ItemEntity itemEntity = itemMapper.mapToEntity(itemDTO);
		itemManagement.addItem(itemEntity);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(itemEntity.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
