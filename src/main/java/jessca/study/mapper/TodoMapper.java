package jessca.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jessca.study.entity.ToDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TodoMapper extends BaseMapper<ToDo> {
}
