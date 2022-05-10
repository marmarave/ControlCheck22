package acme.features.inventor.item;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import acme.entities.Item;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;

import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorComponentUpdateService implements AbstractUpdateService<Inventor,Item> {
	

	

	

		// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorItemRepository repository;

		// AbstractUpdateService<Authenticated, Worker> interface -----------------


		@Override
		public boolean authorise(final Request<Item> request) {
			assert request != null;
			boolean result;
			
			Item item;
			int id;
			
			id = request.getModel().getInteger("id");
			item = this.repository.findOneItemById(id);
			result = item.isPublished();
			return !result;
		}

		@Override
		public void validate(final Request<Item> request, final Item entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void bind(final Request<Item> request, final Item entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "name", "type", "code","technology","description","retailPrice","moreInfo","published");
		}

		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "name", "type", "code","technology","description","retailPrice","moreInfo","published");
		}

		@Override
		public Item findOne(final Request<Item> request) {
			assert request != null;

			Item result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneItemById(id);

			return result;
			
			
		}

		@Override
		public void update(final Request<Item> request, final Item entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

		

	}


