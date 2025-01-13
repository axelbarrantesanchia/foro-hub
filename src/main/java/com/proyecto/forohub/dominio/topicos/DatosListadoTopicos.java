package com.proyecto.forohub.dominio.topicos;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        String titulo,
        String mensaje,
        String autor,
        String nombreCurso,
        LocalDateTime fechaCreacion

) {

    public DatosListadoTopicos(Topicos e) {
        this(e.getCurso(), e.getTitulo(), e.getMensaje(), e.getAutor(), e.getFechaCreacion());
    }

}
