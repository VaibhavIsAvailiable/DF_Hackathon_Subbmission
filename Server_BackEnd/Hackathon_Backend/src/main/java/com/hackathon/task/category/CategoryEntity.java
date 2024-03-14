package com.hackathon.task.category;

import java.util.ArrayList;
import java.util.List;

import com.hackathon.task.product.ProductEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="category")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    
    private String name;
    
    private String description;
    
    private String status;
   
    @OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<ProductEntity>posts= new ArrayList<>();	
}