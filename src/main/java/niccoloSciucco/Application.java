package niccoloSciucco;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import niccoloSciucco.DAO.EventoDAO;
import niccoloSciucco.entities.Evento;
import niccoloSciucco.enums.TipoEvento;
import niccoloSciucco.exceptions.IdNotFound;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestioneeventipu");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EventoDAO eventoDAO = new EventoDAO(entityManager);

        Evento primoEvento = new Evento("Concerto", LocalDate.of(2026, 6, 30), "Concertone inizio estate", TipoEvento.PUBBLICO, 5000);
        Evento secondoEvento = new Evento("Conferenza Tech", LocalDate.of(2026, 7, 15), "Incontro sulle novità di Java e AI", TipoEvento.PUBBLICO, 150);
        Evento terzoEvento = new Evento("Festa Aziendale", LocalDate.of(2026, 9, 10), "Cena di gala e team building", TipoEvento.PRIVATO, 80);
        Evento quartoEvento = new Evento("Mostra d'Arte", LocalDate.of(2026, 10, 5), "Esposizione di pittura contemporanea", TipoEvento.PUBBLICO, 200);
        Evento quintoEvento = new Evento("Torneo di Tennis", LocalDate.of(2026, 11, 22), "Torneo amatoriale della domenica", TipoEvento.PRIVATO, 32);

        eventoDAO.save(primoEvento);
        eventoDAO.save(secondoEvento);
        eventoDAO.save(terzoEvento);
        eventoDAO.save(quartoEvento);
        eventoDAO.save(quintoEvento);

        try {
            Evento eventoById = eventoDAO.getById(7);
        } catch (IdNotFound ex) {
            System.out.println(ex.getMessage());
        }

        try {
            eventoDAO.delete(3);
        } catch (IdNotFound ex) {
            System.out.println(ex.getMessage());
        }
    }
}
