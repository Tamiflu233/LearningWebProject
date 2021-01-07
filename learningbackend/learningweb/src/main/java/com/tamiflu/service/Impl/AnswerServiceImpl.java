package com.tamiflu.service.Impl;

import com.tamiflu.entity.Answer;
import com.tamiflu.mapper.AnswerMapper;
import com.tamiflu.mapper.QuestionMapper;
import com.tamiflu.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public void addAnswer(Answer answer) {
        answerMapper.addAnswer(answer);
        questionMapper.updateIsAnswered(answer.getQid(),1);
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerMapper.updateAnswer(answer);
    }

    @Override
    public void deleteAnswer(String aid,String qid) {
        answerMapper.deleteAnswer(aid);
        questionMapper.updateIsAnswered(qid,null);
    }

    @Override
    public Answer findAnswerByQid(String qid) {
        return answerMapper.findAnswerByQid(qid);
    }
}
