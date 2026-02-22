package com.dobrynya.studynotes.repository;

import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByType(NoteType type);
    List<Note> findByTitleContainingIgnoreCase(String title);
}
