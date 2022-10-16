package com.maksdave.angularspringbootdemo.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageFileBytes;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long postId;
}
