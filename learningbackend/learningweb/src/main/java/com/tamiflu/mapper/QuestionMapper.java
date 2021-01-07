package com.tamiflu.mapper;


import com.tamiflu.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
//分页查询所有提问
    public List<Question> findQuestionsByPage(@Param("lessonId") String lessonId,
                                              @Param("teaId") String teaId,
                                              @Param("schoolId") String schoolId);
//    修改提问
    public void updateQuestion(Question question);
//    删除提问
    public void deleteQuestionById(String qid);
//    发布提问
    public void insertQuestion(Question question);
//    修改is_answered字段
    public void updateIsAnswered(@Param("qid") String qid,@Param("isAnswered") Integer isAnswered);
}
