package com.proyecto.forohub.dominio.topicos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(


        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String autor,
        @NotNull
        String nombreCurso
) {
}
