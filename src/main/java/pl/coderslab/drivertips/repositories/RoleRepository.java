package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Role;

public interface RoleRepository {
    Role findByName(String name);
}
