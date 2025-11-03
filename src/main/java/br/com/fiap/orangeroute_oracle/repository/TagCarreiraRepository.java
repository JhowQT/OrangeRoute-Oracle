package br.com.fiap.orangeroute_oracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.orangeroute_oracle.entity.*;

@Repository
public interface TagCarreiraRepository extends JpaRepository<TagCarreira, Long>{
    
}
