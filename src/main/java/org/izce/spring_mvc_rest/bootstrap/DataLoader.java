package org.izce.spring_mvc_rest.bootstrap;

import org.izce.spring_mvc_rest.domain.Category;
import org.izce.spring_mvc_rest.domain.Customer;
import org.izce.spring_mvc_rest.repo.CategoryRepo;
import org.izce.spring_mvc_rest.repo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

	private final CategoryRepo categoryRepo;
	private final CustomerRepo customerRepo;

	public DataLoader(CategoryRepo categoryRepo, CustomerRepo customerRepo) {
		log.debug("Initializing DataLoader...");
		this.categoryRepo = categoryRepo;
		this.customerRepo = customerRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
	}

	private void loadCategories() {
		Category fruits = new Category();
		fruits.setName("Fruits");

		Category dried = new Category();
		dried.setName("Dried");

		Category fresh = new Category();
		fresh.setName("Fresh");

		Category exotic = new Category();
		exotic.setName("Exotic");

		Category nuts = new Category();
		nuts.setName("Nuts");

		categoryRepo.save(fruits);
		categoryRepo.save(dried);
		categoryRepo.save(fresh);
		categoryRepo.save(exotic);
		categoryRepo.save(nuts);

		log.info("Categories Loaded: " + categoryRepo.count());
	}

	private void loadCustomers() {
		// given
		Customer customer1 = new Customer();
		customer1.setId(1l);
		customer1.setFirstname("Michale");
		customer1.setLastname("Weston");
		customerRepo.save(customer1);

		Customer customer2 = new Customer();
		customer2.setId(2l);
		customer2.setFirstname("Sam");
		customer2.setLastname("Axe");

		customerRepo.save(customer2);

		log.info("Customers Loaded: " + customerRepo.count());
	}

}
