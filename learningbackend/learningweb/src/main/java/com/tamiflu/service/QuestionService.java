package com.tamiflu.service;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Question;

public interface QuestionService {
    //分页查询所有提问
    public PageInfo<Question> findQuestionsByPage(Integer pageNow,
                                                  Integer pageSize,
                                                  String lessonId,
                                                  String teaId,
                                                  String schoolId);
    //    修改提问
    public void updateQuestion(Question question);
    //    删除提问
    public void deleteQuestionById(String qid);
    //    发布提问
    public void insertQuestion(Question question);

}
