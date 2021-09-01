package jessca.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jessca.study.entity.Study;
import jessca.study.entity.Word;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ScoreService extends IService<Study> {
}
