package com.nekiSkills.NekiSkills.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nekiSkills.NekiSkills.domain.model.UsuarioSkill;

@Repository
public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Integer> {

    @Query(value = "SELECT user_id, Skill_id FROM projeto_neki.user_skill WHERE user_id = :id", nativeQuery = true)
    public UsuarioSkill buscarSkillUsuario(@Param("id") Integer id);

}
