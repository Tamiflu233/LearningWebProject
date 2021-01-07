package com.tamiflu.mapper;

import com.tamiflu.entity.Answer;

public interface AnswerMapper {
//    回答问题
    public void addAnswer(Answer answer);
//    修改回答
    public void updateAnswer(Answer answer);
//    删除回答
    public void deleteAnswer(String aid);
//按qid查找回答
    public Answer findAnswerByQid(String qid);
}
