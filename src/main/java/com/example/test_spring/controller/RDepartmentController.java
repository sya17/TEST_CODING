package com.example.test_spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test_spring.constant.ResponRequestConstant;
import com.example.test_spring.entity.RDepartementEntity;
import com.example.test_spring.service.RDepartementService;
import com.example.test_spring.service.RDepartementService;
import com.example.test_spring.util.CommonUtil;
import com.example.test_spring.util.response.ResponseUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/departement")
public class RDepartmentController extends CommonUtil {

    @Autowired
    RDepartementService service;

    String path = "/departement";

    @PostMapping
    ResponseEntity<ResponseUtils> create(@RequestBody RDepartementEntity user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                service.save(user),
                                path,
                                ResponRequestConstant.MethodConstant.POST,
                                null));
    }

    @PutMapping()
    ResponseEntity<ResponseUtils> update(@RequestBody RDepartementEntity user) {
        String message = null;
        Object data = null;
        if (!service.findById(user.getRDepartementId()).isEmpty()) {
            data = service.save(user);
        } else {
            message = "Id " + user.getRDepartementId() + " Not found";
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                data,
                                path,
                                ResponRequestConstant.MethodConstant.PUT,
                                message));
    }

    @GetMapping()
    ResponseEntity<ResponseUtils> getAll(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                service.getAll(),
                                path,
                                ResponRequestConstant.MethodConstant.GET,
                                null));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseUtils> getById(@PathVariable("id") Integer id) {
        System.out.println(">>> ID " + id);
        String message = null;
        Optional<RDepartementEntity> data = service.findById(id);
        if (data.isEmpty()) {
            message = "Id " + id + " Not found";
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                data,
                                path,
                                ResponRequestConstant.MethodConstant.GET,
                                message));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseUtils> deleteById(@PathVariable("id") Integer id) {
        String message = null;
        if (!service.findById(id).isEmpty()) {
            service.deleteById(id);
            message = "Success Deleted";
        } else {
            message = "Id " + id + " Not found";
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                null,
                                path,
                                ResponRequestConstant.MethodConstant.DELETE,
                                message));
    }
}
