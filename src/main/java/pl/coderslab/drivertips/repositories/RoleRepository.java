package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.domain.Role;

public interface RoleRepository {
    Role findByName(String name);
}
