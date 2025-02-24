package com.example.demo.dto;

import com.example.demo.model.Avatar;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvatarDTO {
    private String id;
    private String blob;

    public AvatarDTO(Avatar avatar){
        this.id = avatar.getId();
        this.blob = avatar.getBlob();
    }
}
