package com.proyecto.forohub.dominio.topicos;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "Topicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "titulo" , "mensaje"}) //Compara objetos UNICAMENTE mediante EL ID DEL OBJETO
public class Topicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private boolean status = Boolean.TRUE;
    private String autor;
    private String curso;
    private String respuestas;

    public Topicos(DatosRegistroTopico datosRegistroTopico) {
        this.mensaje = datosRegistroTopico.mensaje();
        this.titulo = datosRegistroTopico.titulo();
        this.curso = datosRegistroTopico.nombreCurso();
        this.autor = datosRegistroTopico.autor();
    }

    public void actualizarTopico(@Valid DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.nombreCurso() != null) {
            this.curso = datosActualizarTopico.nombreCurso();
        }

    }

    public void eliminarTopico() {
        this.status = false;
    }
}
