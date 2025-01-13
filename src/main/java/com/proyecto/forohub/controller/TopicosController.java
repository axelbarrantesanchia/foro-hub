package com.proyecto.forohub.controller;

import com.proyecto.forohub.dominio.topicos.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private ITopicosRepository topicosRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> agregarUnTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Topicos> topicos = topicosRepository.findbyTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());
        if (topicos.isPresent()) {
            throw new ValidationException("Ya existe un topico con ese titulo y mensaje");
        }
        Topicos topico = topicosRepository.save(new Topicos(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getAutor(), topico.getCurso(), topico.getFechaCreacion());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico( @PathVariable Long id,@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topicos topicos = topicosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un tópico con ese ID"));
        topicos.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok().body(new DatosRespuestaTopico(topicos.getId(),topicos.getTitulo(),topicos.getMensaje(),
                topicos.getAutor(),topicos.getCurso(),topicos.getFechaCreacion()));
    }



    @GetMapping
    public Page<DatosListadoTopicos> obtenerTopicos(Pageable paginacion) {
        return topicosRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@RequestBody @PathVariable Long id) {
        Topicos topico = topicosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe un tópico con ese ID"));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(), topico.getAutor(), topico.getMensaje(), topico.getCurso(), topico.getFechaCreacion());
        return ResponseEntity.ok().body(datosRespuestaTopico);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        Optional<Topicos> topicos = topicosRepository.findById(id);
        if (topicos.isEmpty()) {
            throw new ValidationException("No existe un topico con ese id");
        }
        topicosRepository.deleteById(id);
    }
}
