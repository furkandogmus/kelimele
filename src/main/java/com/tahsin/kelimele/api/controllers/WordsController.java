package com.tahsin.kelimele.api.controllers;

import com.tahsin.kelimele.business.abstracts.WordService;
import com.tahsin.kelimele.core.utilities.results.DataResult;
import com.tahsin.kelimele.core.utilities.results.Result;
import com.tahsin.kelimele.core.utilities.results.SuccessDataResult;
import com.tahsin.kelimele.entities.concretes.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/words")
public class WordsController {


    private WordService wordService;
    @Autowired
    public WordsController(WordService wordService) {
        super();
        this.wordService = wordService;
    }
    @GetMapping("/getall")
    public DataResult<List<Word>> getAll(){
        return this.wordService.getAll();
    }
    @PostMapping("/add")
    public Result add(@RequestBody  Word word){
        return this.wordService.add(word);
    }

    @DeleteMapping("/delete")
    public Result delete(Word word){
        return this.wordService.delete(word);
    }

    @GetMapping("/getByName")
    public DataResult<Word> getWordByName(@RequestParam String word){
        return  new SuccessDataResult<Word>(this.wordService.getWordByName(word).getData());
    }

    @GetMapping("/getById")
    public DataResult<Word> getWordById(@RequestParam int word_id){
        return new SuccessDataResult<Word>(this.wordService.getWordById(word_id).getData());
    }

    @GetMapping("/get")
    public DataResult<Word> getWord(){
        return new SuccessDataResult<Word>(this.wordService.getWord().getData());
    }

    @GetMapping("/getByNameStartingWith")
    public DataResult<List<Word>> getWordByNameStartsWith(String prefix){
        return  new SuccessDataResult<List<Word>>(this.wordService.getWordByNameStartsWith(prefix).getData());
    }
    @GetMapping("/getByNameEndingWith")
    public DataResult<List<Word>> getWordByNameEndsWith(String suffix){
        return  new SuccessDataResult<List<Word>>(this.wordService.getWordByNameEndsWith(suffix).getData());
    }
}
