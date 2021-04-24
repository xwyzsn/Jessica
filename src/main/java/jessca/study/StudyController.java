package jessca.study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.nio.file.*;
//http://120.77.174.209/
@RestController
@RequestMapping(path = "api/study" ,method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
@CrossOrigin(origins = "http://120.77.174.209:8080")
public class StudyController {

    private final StudyService studyService;
    @Autowired
    public StudyController(StudyService studyService) throws SQLException {
        this.studyService = studyService;
    }


    @GetMapping()
    public List<Study> getStudy() throws Exception {

        return studyService.getStudy();
    }
    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public  void postToDb( Study study) throws Exception {
        studyService.postToDb(study);
    }

    @GetMapping(path = "todolist")
    public List<ToDo> getTodo() throws Exception {
        return studyService.getTodo();
    }
    @DeleteMapping(path = "todo/{itemId}")
    public void deleteTo(@PathVariable("itemId") int itemId) throws Exception {
        studyService.deleteTo(itemId);
    }

    @PostMapping(path = "todo" )
    public void addNewTodo(@RequestBody ToDo toDo) throws Exception {
        studyService.addNewTodo(toDo);
    }
    @GetMapping(path = "chart")
    public List<Chart> getChart() throws Exception {

        return studyService.getChar();
    }
    @GetMapping(path = "chart2")
    public List<Chart> getCharGreatZero() throws Exception {
        return studyService.getCharGreatZero();
    }
    @GetMapping(path = "word")
    public List<Word> getWord() throws Exception {
        return studyService.getWord();
    }
    @PostMapping(path = "wordpost")
    public void addWord(@RequestBody Word word) throws Exception {
        studyService.addWord(word);
    }
    @GetMapping(path="wordtotal")
    public List<TotalWord> getTotalWord() throws Exception {
        return studyService.getTotalWord();
    }

    @PostMapping("/upload")
    public void handleFileUpload(@RequestPart(value = "file") final MultipartFile[] uploadfile,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "uploadtime") String uploadtime
    ) throws Exception {

        studyService.saveUploadedFiles(uploadfile ,title,description,uploadtime);
    }
    @GetMapping("/picture")
    public List<Picture> getPictureInfo() throws Exception {
        return studyService.getPictureInfo();
    }
    @GetMapping("/giftlist")
    public List<Study> getGiftList() throws Exception {
       return studyService.getGiftList();
    }
    @PutMapping("/finishgift")
    public void updateGiftStatus(@RequestParam(value="id") Integer id,
                                 @RequestParam(value="finish") String finish) throws Exception {
        studyService.updateGiftStatus(id,finish);
    }

}
