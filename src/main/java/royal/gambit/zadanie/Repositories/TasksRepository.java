package royal.gambit.zadanie.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import royal.gambit.zadanie.Entities.TaskEntity;

public interface TasksRepository extends JpaRepository<TaskEntity, Long> {

}
