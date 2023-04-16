package com.tahsin.kelimele.business.concretes;

import com.tahsin.kelimele.business.abstracts.WordService;
import com.tahsin.kelimele.core.utilities.results.*;
import com.tahsin.kelimele.dataAccess.abstracts.WordDao;
import com.tahsin.kelimele.entities.concretes.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
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

        if (name.length()<4 || name.length()>7 || !this.wordDao.existsWordByName(name)) {
            return new ErrorDataResult<Word>(null,"Kelimeler 4 ile 7 harf arasındadır. ");
        }
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
    public DataResult<Integer> getLength(){
        return new SuccessDataResult<Integer>(getWord().getData().getName().length(),"Başarılı");
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
    public DataResult<String> checkUserWord(String name) {
        Word userAnswer = getWordByName(name).getData();
        if(userAnswer == null){
            return new ErrorDataResult<Word>(null,"Başarısız");
        }
        Word answer = getWord().getData();
        HashMap<Character,Integer> hm = new HashMap<>();
        HashMap<Character,Integer> hm2 = new HashMap<>();
        StringBuilder result = new StringBuilder(answer.getName().length());
        int size = answer.getName().length();
        for(int i=0;i<size;i++){
            hm.put(answer.getName().charAt(i),hm.getOrDefault(answer.getName().charAt(i),0)+1);
            hm2.put(userAnswer.getName().charAt(i),hm2.getOrDefault(userAnswer.getName().charAt(i),0)+1);
            result.append("B");
        }
        for(int i=0;i<size;i++){
            if(userAnswer.getName().charAt(i) == answer.getName().charAt(i)) {
                hm.put(answer.getName().charAt(i), hm.get(answer.getName().charAt(i)) - 1);
                hm2.put(userAnswer.getName().charAt(i), hm2.get(userAnswer.getName().charAt(i)) - 1);
                result.setCharAt(i,'G');
            }
            }
        for(int i=0;i<size;i++){
            if(result.charAt(i)!='G'){
                if(hm.getOrDefault(userAnswer.getName().charAt(i),0)>0){
                    hm.put(userAnswer.getName().charAt(i),hm.get(userAnswer.getName().charAt(i))-1);
                    result.setCharAt(i,'Y');
                    continue;
                }
                result.setCharAt(i,'R');
            }
        }

        return new SuccessDataResult<String>(String.valueOf(result),"Başarılı");


    }

    @Override
    public Result existsWordByName(String name) {
        boolean result = this.wordDao.existsWordByName(name);
        if(result)
            return new SuccessResult("Başarılı");
        else
            return new ErrorResult("Başarısız");
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
