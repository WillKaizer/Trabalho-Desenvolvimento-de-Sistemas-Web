package br.edu.sensai.sc.locadoravideo.service;

import br.edu.sensai.sc.locadoravideo.entity.Video;
import br.edu.sensai.sc.locadoravideo.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenciamentoService {
    private final VideoRepository videoRepository;

    public GerenciamentoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video salvarVideo(Video video){
        videoRepository.save(video);

        return video;
    }

    public void excluirVideo(Long codigo){
        videoRepository.deleteById(codigo);

    }

    public Optional<Video> buscarVideoPorCodigo(Long codigo){

        return videoRepository.findById(codigo);
    }

    public List<Video> buscarVideos(){

        return videoRepository.findAll();
    }

    public List<Video> buscarVideoPorNome(String nome){

        return videoRepository.findVideoByNomeContaining(nome);
    }

    public void alterarVideo(String nome, Long codigo){
        Optional<Video> video = videoRepository.findById(codigo);
        if (Optional.ofNullable(video).isPresent()){
            video.get().setCodigo(codigo);
            videoRepository.save(video.get());
        }
    }

}

