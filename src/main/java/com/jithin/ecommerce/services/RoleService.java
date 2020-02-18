package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Role;
import com.jithin.ecommerce.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<RoleRepository, Role> {
    public RoleService(RoleRepository repository) {
        super(repository);
    }

    public Role getUserRoleByName(String role) {
        return getRepository().findByName(role);
    }
}
