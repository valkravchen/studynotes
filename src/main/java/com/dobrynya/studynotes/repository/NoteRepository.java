package com.dobrynya.studynotes.repository;

import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByType(NoteType type);

    List<Note> findByTitleContainingIgnoreCase(String title);

    boolean existsByTitle(String title);

    List<Note> findAllByOrderByTitleAsc();

    @Query(value = """
            SELECT * FROM notes
            WHERE to_tsvector('simple', title) || to_tsvector('simple', COALESCE(content, '') )
                        @@ plainto_tsquery('simple', :query)
            """, nativeQuery = true)
    List<Note> fullTextSearch(@Param("query") String query);
}
