package com.proyecto.forohub.dominio.topicos;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ITopicosRepository extends JpaRepository<Topicos, Long> {
    Page<Topicos>findByStatusTrue(Pageable paginacion);

    @Query("""
SELECT t FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje
""")
    Optional<Topicos> findbyTituloAndMensaje(@Param("titulo") String titulo, @Param("mensaje") String mensaje);
}
