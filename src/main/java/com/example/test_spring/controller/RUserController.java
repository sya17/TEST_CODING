package com.example.test_spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.example.test_spring.DTO.RUserDTO;
import com.example.test_spring.constant.ResponRequestConstant;
import com.example.test_spring.entity.RDepartementEntity;
import com.example.test_spring.entity.RUserEntity;
import com.example.test_spring.service.RDepartementService;
import com.example.test_spring.service.RUserService;
import com.example.test_spring.util.CommonUtil;
import com.example.test_spring.util.response.ResponseUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class RUserController extends CommonUtil {

    @Autowired
    RUserService service;

    @Autowired
    RDepartementService departementService;

    String path = "/user";

    @PostMapping
    ResponseEntity<ResponseUtils> create(@RequestBody RUserEntity user) {
        String message = null;
        Object data = null;

        if (!isNullOrEmpty(user.getUsername())) {
            data = service.save(user);
        } else {
            message = "username tidak boleh null";
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                data,
                                path,
                                ResponRequestConstant.MethodConstant.POST,
                                message));
    }

    @PutMapping()
    ResponseEntity<ResponseUtils> update(@RequestBody RUserEntity user) {
        String message = null;
        Object data = null;
        if (!service.findById(user.getRUserId()).isEmpty()) {
            data = service.save(user);
        } else {
            message = "Id " + user.getRUserId() + " Not found";
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
                                service.findAll(),
                                path,
                                ResponRequestConstant.MethodConstant.GET,
                                null));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseUtils> getById(@PathVariable("id") Integer id) {
        System.out.println(">>> ID " + id);
        String message = null;
        Optional<RUserEntity> data = service.findById(id);
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

    @GetMapping("/user-by-departement/{id}")
    ResponseEntity<ResponseUtils> getUserbyDepartment(@PathVariable("id") Integer id) {
        String message = null;
        Object data = null;
        if (!departementService.findById(id).isEmpty()) {
            List<RUserEntity> list = service.findUserAllByDepartementId(id);
            List<RUserDTO> listDTO = new ArrayList<>();
            for (RUserEntity uEntity : list) {
                ModelMapper mapper = new ModelMapper();
                RUserDTO userDTO = mapper.map(uEntity, RUserDTO.class);
                Optional<RDepartementEntity> departement = departementService
                        .findById(uEntity.getDepartementId());
                userDTO.setDepartementname(departement.get().getNameDepartement());
                listDTO.add(userDTO);
            }
            data = listDTO;
        } else {
            message = "Departement Id " + id + " Not found";
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        setGeneralResponse(
                                data,
                                "/user-by-departement",
                                ResponRequestConstant.MethodConstant.GET,
                                message));
    }
}
