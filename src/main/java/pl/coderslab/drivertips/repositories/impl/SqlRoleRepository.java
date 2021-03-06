package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Role;
import pl.coderslab.drivertips.repositories.RoleRepository;


@Repository
interface SqlRoleRepository extends RoleRepository, JpaRepository<Role, Integer> {

}
