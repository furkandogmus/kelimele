package com.tahsin.kelimele.business.concretes;

import com.tahsin.kelimele.business.abstracts.WordService;
import com.tahsin.kelimele.core.utilities.results.DataResult;
import com.tahsin.kelimele.core.utilities.results.Result;
import com.tahsin.kelimele.core.utilities.results.SuccessDataResult;
import com.tahsin.kelimele.core.utilities.results.SuccessResult;
import com.tahsin.kelimele.dataAccess.abstracts.WordDao;
import com.tahsin.kelimele.entities.concretes.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WordManager implements WordService {
    private WordDao wordDao;

    @Autowired
    public WordManager(WordDao wordDao) {
        super();
        this.wordDao = wordDao;
    }


    @Override
    public DataResult<List<Word>> getAll() {
        return new SuccessDataResult<List<Word>>
                (this.wordDao.findAll(),"Data listelendi!");
    }

    @Override
    public DataResult<Word> getWordByName(String name) {
        return new SuccessDataResult<Word>(this.wordDao.getWordByName(name),"Başarılı");
    }

    @Override
    public DataResult<Word> getWordById(int id) {
        return new SuccessDataResult<Word>(this.wordDao.getWordById(id),"Başarılı");
    }

    @Override
    public DataResult<List<Word>> getWordByNameStartsWith(String prefix) {
        return new SuccessDataResult<List<Word>>(this.wordDao.getWordByNameStartsWith(prefix),"Başarılı");
    }

    @Override
    public DataResult<List<Word>> getWordByNameEndsWith(String suffix) {
        return new SuccessDataResult<List<Word>>(this.wordDao.getWordByNameEndsWith(suffix),"Başarılı");
    }


    @Override
    public Result add(Word word) {
        this.wordDao.save(word);
        return new SuccessResult("Ürün Eklendi.");
    }

    @Override
    public Result delete(Word word) {
        this.wordDao.delete(word);
        return new SuccessResult("Ürün Silindi.");
    }


}
