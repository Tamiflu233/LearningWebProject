package com.tamiflu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Question;
import com.tamiflu.mapper.QuestionMapper;
import com.tamiflu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PageInfo<Question> findQuestionsByPage(Integer pageNow, Integer pageSize, String lessonId, String teaId, String schoolId) {
        PageHelper.startPage(pageNow,pageSize);
        List<Question> questions = questionMapper.findQuestionsByPage(lessonId,teaId,schoolId);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    public void deleteQuestionById(String qid) {
        questionMapper.deleteQuestionById(qid);
    }

    @Override
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }
}
