package com.tahsin.kelimele.business.concretes;

import com.tahsin.kelimele.business.abstracts.WordService;
import com.tahsin.kelimele.core.utilities.results.*;
import com.tahsin.kelimele.dataAccess.abstracts.WordDao;
import com.tahsin.kelimele.entities.concretes.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WordManager implements WordService {
    private WordDao wordDao;
    private LocalDateTime lastAccessTime;
    private Word lastWord;
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

    public DataResult<Word> getWord() {
        LocalDateTime currentTime = LocalDateTime.now();

        if (lastAccessTime != null && lastAccessTime.getDayOfMonth() == currentTime.getDayOfMonth()) {
           return new SuccessDataResult<Word>(lastWord);
        }
        lastWord = this.wordDao.getWordById(RandomNumberGenerator.getNumber(10000));
        lastAccessTime = currentTime;
        return new SuccessDataResult<Word>(lastWord);
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
