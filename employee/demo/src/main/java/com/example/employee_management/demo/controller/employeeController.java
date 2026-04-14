package com.example.employee_management.demo.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.example.employee_management.demo.model.employee;
import com.example.employee_management.demo.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class employeeController {
    @Autowired
    private employeeService service;

    @PostMapping
    public employee addEmployee(@RequestBody employee emp) {
        return service.addEmployee(emp);
    }

    @GetMapping
    public List<employee> getAll() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<employee> getById(@PathVariable Long id) {

        employee emp = service.getById(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(emp);


    }

    @PutMapping("/{id}")
    public ResponseEntity<employee> update(@PathVariable Long id, @RequestBody employee employee) {
        employee updated = service.updateEmployee(id, employee);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deleted=service.deleteEmployee(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<employee> patchEmployee(@PathVariable Long id,@RequestBody Map<String,Object> updates){
        employee updated=service.patchEmployee(id,updates);
        if(updated!=null){
            return ResponseEntity.ok(updated);
        }
        else return ResponseEntity.notFound().build();
    }


}