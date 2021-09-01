package jessca.study.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jessca.study.entity.Study;
import jessca.study.entity.Word;
import jessca.study.mapper.ScoreMapper;
import jessca.study.mapper.WordMapper;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImplement extends ServiceImpl<ScoreMapper, Study> implements ScoreService {
}
