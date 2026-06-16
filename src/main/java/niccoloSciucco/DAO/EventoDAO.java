package niccoloSciucco.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import niccoloSciucco.entities.Evento;
import niccoloSciucco.exceptions.IdNotFound;

public class EventoDAO {
    private final EntityManager entityManager;

    public EventoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Evento newEvento) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(newEvento);
        transaction.commit();

        System.out.println("Nuovo Evento Aggiunto con Successo");
    }

    public Evento getById(long id) {
        Evento evento = this.entityManager.find(Evento.class, id);

        if (evento == null) {
            throw new IdNotFound("L'evento cercato non esiste");
        } else {
            System.out.println(evento);
            return evento;
        }
    }

    public void delete(long id) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        Evento evento = getById(id);
        this.entityManager.remove(evento);
        transaction.commit();

        System.out.println("Evento eliminato con successo");
    }

}
