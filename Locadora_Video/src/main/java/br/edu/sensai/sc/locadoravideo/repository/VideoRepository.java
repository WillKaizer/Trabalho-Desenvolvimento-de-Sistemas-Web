package br.edu.sensai.sc.locadoravideo.repository;

import br.edu.sensai.sc.locadoravideo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findVideoBy(int ano);

    List<Video> findVideoByNomeContaining(String nome);

    @Query(value = "select c from Video c where c.nome like ?1")
    List<Video> buscarVideoPorNome(String nome);

}
