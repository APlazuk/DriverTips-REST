package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.User;
import pl.coderslab.drivertips.repositories.UserRepository;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Long> {

}
