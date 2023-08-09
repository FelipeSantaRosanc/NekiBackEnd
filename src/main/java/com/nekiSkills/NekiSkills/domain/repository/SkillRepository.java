package com.nekiSkills.NekiSkills.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekiSkills.NekiSkills.domain.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
