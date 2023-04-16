package com.tahsin.kelimele.business.abstracts;

import com.tahsin.kelimele.core.utilities.results.DataResult;
import com.tahsin.kelimele.core.utilities.results.Result;
import com.tahsin.kelimele.entities.concretes.Word;

import javax.xml.crypto.Data;
import java.util.List;

public interface WordService {
    DataResult<List<Word>> getAll();
    DataResult<Word> getWordByName(String name);
    DataResult<Word> getWordById(int id);
    DataResult<Word> getWord();
    DataResult<Integer> getLength();
    DataResult<List<Word>> getWordByNameStartsWith(String prefix);
    DataResult<List<Word>> getWordByNameEndsWith(String suffix);

    DataResult<String> checkUserWord(String name);
    Result existsWordByName(String Name);

    Result add(Word word);
    Result delete(Word word);
}
