package com.codeoftheweb.salvo.Repositories;

import com.codeoftheweb.salvo.Modelo.Player;
import com.codeoftheweb.salvo.Modelo.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScoreRepository extends JpaRepository<Score, Long> {


}
