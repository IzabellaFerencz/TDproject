package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.EventParticipant;

public class EventParticipantDAO extends BasicDAO<EventParticipant> {

	private EntityManagerFactory factory;

	public EventParticipantDAO(EntityManagerFactory factory) {
		super(EventParticipant.class);
		this.factory = factory;
	}

	@Override
	public EntityManager getEntityManager() {
		try {
			return factory.createEntityManager();
		} catch (Exception ex) {
			System.out.println("The entity manager cannot be created!");
			return null;

		}

	}
}
