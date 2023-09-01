// package com.shop.web.repository;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import com.shop.web.dto.GroupDTO;
// import com.shop.web.models.Group;
// import com.shop.web.models.Users;

// public interface GroupRepository extends JpaRepository<Group, Long>{
//     //custom queries
//     Optional<Group> findById(long id);
//     Group save(GroupDTO userDto);
//     @Query("SELECT g from Group g WHERE g.name LIKE CONCAT('%', :query, '%')")
//     List<Group> searchGroups(String query);
//     List<Group> findByAdminOrderById(Users user);
//     List<Group> findByUsersIdOrderById(Long id);
// }