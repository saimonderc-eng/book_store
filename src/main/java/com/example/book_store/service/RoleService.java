package com.example.book_store.service;

import com.example.book_store.entity.Role;
import com.example.book_store.exception.NotFoundException;
import com.example.book_store.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public Role getRoleById(Long id){
        return roleRepository.findRoleById(id)
                .orElseThrow(() -> new NotFoundException("role not found!"));
    }
    public Role getUserRole(){
        return roleRepository.findUserRole();
    }
}