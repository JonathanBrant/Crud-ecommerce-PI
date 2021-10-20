package com.raphael.crud.dto;

import com.raphael.crud.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class ClienteDTO {

    private Long id;

    @NotEmpty(message = "digita o nome animal !")
    @Length(max = 50, min = 10, message = "digita o nome animal !")
    private String name;
    private String phone;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        name = obj.getName();
        phone = obj.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
