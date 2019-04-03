package examsandquestions;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRapository extends JpaRepository<Exam, Long> {

    @EntityGraph(attributePaths = {"questions"})
    List<Exam> findAll();
}
