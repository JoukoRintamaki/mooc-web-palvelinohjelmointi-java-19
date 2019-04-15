package reservations;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/reservations")
    public String getReservatios(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (notPaallekkainen(reservationFrom, reservationTo)) {
            Reservation reservation = new Reservation();
            reservation.setUser(accountRepository.findByUsername(auth.getName()));
            reservation.setReservationFrom(reservationFrom);
            reservation.setReservationTo(reservationTo);
            reservationRepository.save(reservation);
        }
        return "redirect:/reservations";
    }

    private boolean notPaallekkainen(ChronoLocalDate reservationFrom, ChronoLocalDate reservationTo) {
        for (Reservation reservation : reservationRepository.findAll()) {
            if (
                    (
                            reservationFrom.isAfter(reservation.getReservationFrom())
                                    &&
                                    reservationFrom.isBefore(reservation.getReservationTo())

                    )
                            ||
                            (
                                    reservationTo.isAfter(reservation.getReservationFrom())
                                            &&
                                            reservationTo.isBefore(reservation.getReservationTo())

                            )
                            ||
                            (
                                    reservationFrom.isBefore(reservation.getReservationFrom())
                                            &&
                                            reservationTo.isAfter(reservation.getReservationTo())

                            )

            ) {
                return false;
            }

        }
        return true;
    }
}
