package com.proyecto.forohub.dominio.topicos;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String nombreCurso,
        LocalDateTime fechaCreacion

) {
}
