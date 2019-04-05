package reservations;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationTestUtilities {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AccountRepository accountRepository;

    public long reservationCount() {
        return reservationRepository.count();
    }

    public void clearReservations() {
        reservationRepository.deleteAll();
    }

    public void addReservation(String username, LocalDate from, LocalDate to) {

        Account a = accountRepository.findByUsername(username);
        if (a == null) {
            a = new Account();
            a.setUsername(username);
            a.setPassword("pw");

            a = accountRepository.save(a);
        }

        Reservation r = new Reservation();
        r.setUser(a);
        r.setReservationFrom(from);
        r.setReservationTo(to);

        reservationRepository.save(r);
    }
}
