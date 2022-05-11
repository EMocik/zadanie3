package sk.stuba.fei.uim.oop.assignment3.list.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IListRepository extends JpaRepository<ListOfLendedBooks, Long> {

    @Override
    Optional<ListOfLendedBooks> findById(Long aLong);

    @Override
    List<ListOfLendedBooks> findAll();

    @Override
    void delete(ListOfLendedBooks lendingList);

    ListOfLendedBooks findLendingListById(long id);
}
