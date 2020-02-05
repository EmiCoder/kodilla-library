package com.crud.kodillalibrary;

import org.junit.jupiter.api.Test;

import com.crud.kodillalibrary.domain.Item;
import com.crud.kodillalibrary.domain.ItemDAO;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KodillaLibraryApplicationTests {

	private ItemDAO itemDAO;

	@Test
	public void testItemDAO_save() {
		Item item = new Item("IN USE");
		itemDAO.save(item);
		String status=item.getStatus();
		List<Item> byStatus=itemDAO.findByStatus(status);
		Assert.assertEquals(1, byStatus.size());
		itemDAO.deleteById(item.getId());
	}

}
