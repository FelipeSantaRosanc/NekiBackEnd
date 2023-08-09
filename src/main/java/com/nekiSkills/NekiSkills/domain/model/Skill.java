package com.nekiSkills.NekiSkills.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(
  name = "generator_skill",
  sequenceName = "skill_seq",
  initialValue = 1000,
  allocationSize = 1,
  schema = "projeto_neki"
)
@Entity
@Table(name = "skill", schema = "projeto_neki")
public class Skill {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "generator_skill"
    )
    private Integer id;

    @Column
    @Size(max = 100)
    private String name;

    @Column
    @Size(max = 10)
    private String version;

    @Column
    private String description;

    @Column
    private String imageUrl;
    
    @ManyToMany(mappedBy = "skill")
    @JsonBackReference
    private List<Usuario> user = new ArrayList<Usuario>();

    
}
