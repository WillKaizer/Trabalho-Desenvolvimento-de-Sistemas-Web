package br.edu.sensai.sc.locadoravideo.controller;

import br.edu.sensai.sc.locadoravideo.entity.Video;
import br.edu.sensai.sc.locadoravideo.service.GerenciamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gerenciamento")
@Slf4j



public class GerenciamentoController {

private final GerenciamentoService gerenciamentoService;

    public GerenciamentoController(GerenciamentoService gerenciamentoService) {
        this.gerenciamentoService = gerenciamentoService;
    }


    @GetMapping("/hello")
    public ResponseEntity <Video> helloworld(){
        Video video = new Video();
        video.setNome("A Lagoa Azul");
        video.setAno(1990);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
@PostMapping()
    public ResponseEntity<String> cadastrarVideo(@RequestBody Video video){
        try {
            gerenciamentoService.salvarVideo(video);
        }catch (Exception exception){
            return new ResponseEntity<>("Erro ao Cadastrar Video", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Video Cadastrado com Sucesso", HttpStatus.CREATED);
    }
@PutMapping("/alterar/{codigo}")
    public ResponseEntity<String> alterarVideo(@RequestBody Video video, @PathVariable("codigo") int codigo) {
    try {
        gerenciamentoService.salvarVideo(video);
    } catch (Exception exception) {
        return new ResponseEntity<>("Erro ao Cadastrar Video", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Video Alterado com Sucesso", HttpStatus.OK);
}
@PatchMapping("/alterarVideo/{codigo}")
 public ResponseEntity<String> alterarVideo(@RequestParam String video, @PathVariable("codigo")Long codigo){
    try {
        gerenciamentoService.alterarVideo(video, codigo);
    } catch (Exception exception) {
        return new ResponseEntity<>("Erro ao Alterar o nome do Video", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Video Alterado com Sucesso", HttpStatus.OK);
    }


    @DeleteMapping("/excluir/{codigo}")
    public ResponseEntity<String> excluirCarro(@PathVariable ("codigo") Long codigo){
        try {
            gerenciamentoService.excluirVideo(codigo);
        } catch (Exception exception) {
            return new ResponseEntity<>("Erro ao Excluir Video", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Video Excluido com Sucesso", HttpStatus.OK);
    }

    @GetMapping("/consultar/{codigo}")
    public ResponseEntity<Video> consultaVideo(@PathVariable("codigo") Long codigo){
        try {
            Optional<Video> video = gerenciamentoService.buscarVideoPorCodigo(codigo);
            if (Optional.ofNullable(video).isPresent())
                return new ResponseEntity<>(video.get(), HttpStatus.OK);
            return new ResponseEntity<>(new Video(), HttpStatus.NO_CONTENT);
        }catch (Exception exception){
        return new ResponseEntity<>(new Video(), HttpStatus.BAD_REQUEST);
    }

    }

    @GetMapping("/videos")
    public ResponseEntity<List<Video>> buscarVideos() {
        try {
            List<Video> videos = gerenciamentoService.buscarVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/videos/modelo")
    public ResponseEntity<List<Video>> buscarVideos(@RequestParam("modelo") String modelo) {
        try {
            List<Video> videos = gerenciamentoService.buscarVideoPorNome(modelo);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

}
