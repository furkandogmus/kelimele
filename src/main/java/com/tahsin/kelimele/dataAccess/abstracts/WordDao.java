package com.tahsin.kelimele.dataAccess.abstracts;

import com.tahsin.kelimele.entities.concretes.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordDao extends JpaRepository<Word,Integer> {
    Word getWordByName(String name);
    Word getWordById(int id);
    List<Word> getWordByNameStartsWith(String prefix);
    List<Word> getWordByNameEndsWith(String suffix);

}
