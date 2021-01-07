package com.tamiflu.service;

import com.tamiflu.entity.Answer;

public interface AnswerService {
    //    回答问题
    public void addAnswer(Answer answer);
    //    修改回答
    public void updateAnswer(Answer answer);
    //    删除回答
    public void deleteAnswer(String aid,String qid);
//    按qid找回答
    public Answer findAnswerByQid(String qid);
}
