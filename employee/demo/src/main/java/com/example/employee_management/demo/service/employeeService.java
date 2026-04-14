package com.example.employee_management.demo.service;

import com.example.employee_management.demo.model.employee;
import com.example.employee_management.demo.repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class employeeService {
    @Autowired
    private employeeRepository repo;
    public employee addEmployee(employee emp){
        return repo.save(emp);
    }
    public List<employee> getAllEmployees(){
        return repo.findAll();
    }
    public employee getById(Long Id){
        return repo.findById(Id).orElse(null);
    }
    public employee updateEmployee(Long id,employee updatedEmployee){
        employee existing = repo.findById(id).orElse(null);
        if(existing != null){
            existing.setName(updatedEmployee.getName());
            existing.setDepartment(updatedEmployee.getDepartment());
            return repo.save(existing);
        }
        return null;
    }
    public boolean deleteEmployee(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public employee patchEmployee(Long id, Map<String, Object> updates){
        employee existing=repo.findById(id).orElse(null);
        if(existing==null) return null;
        updates.forEach((key,value)->{
            switch (key){
                case "name": existing.setName((String) value);
                break;
                case "department": existing.setDepartment((String) value);
                break;
            }
        });
    return repo.save(existing);
    }


}
