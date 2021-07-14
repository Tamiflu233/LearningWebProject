/*
 Navicat Premium Data Transfer

 Source Server         : JavaWeb
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : learn

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 14/07/2021 10:56:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `uid` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_type` enum('student','teacher','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `username`(`username`, `nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', 'admin', 'Tamiflu', 'admin');
INSERT INTO `account` VALUES (2, 'root', 'root', 'Tommy', 'admin');
INSERT INTO `account` VALUES (4, 'Tami', 'weng6666', '卢本伟', 'teacher');
INSERT INTO `account` VALUES (5, 'tomato', 'tomato233', '老番茄', 'teacher');
INSERT INTO `account` VALUES (6, 'fafa', 'weng7788', '某某某', 'teacher');
INSERT INTO `account` VALUES (8, 'haha_hehe', 'wewe2333555', '我是张艳', 'teacher');
INSERT INTO `account` VALUES (9, 'white55', '123123', '卢本伟本伟', 'student');
INSERT INTO `account` VALUES (10, 'vans233', '123123', '不想上课', 'student');
INSERT INTO `account` VALUES (11, 'jiangchen123', '123123123', '江老师', 'teacher');
INSERT INTO `account` VALUES (12, 'wangss123', '12345678', '王老师', 'teacher');
INSERT INTO `account` VALUES (13, 'tian123', '123123123', '小天', 'student');
INSERT INTO `account` VALUES (14, 'Tamiflu233', 'weng6622', 'Tamiflu233', 'student');

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `aid` int(8) NOT NULL AUTO_INCREMENT,
  `qid` int(8) NULL DEFAULT NULL,
  `lesson_id` int(8) NULL DEFAULT NULL,
  `answer_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tea_id` int(10) NULL DEFAULT NULL,
  `answer_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_read` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `qid`(`qid`) USING BTREE,
  INDEX `tea_id`(`tea_id`) USING BTREE,
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `question` (`qid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES (1, 1, 1, '2021-01-05 18:55:40', 776688, '大叔大婶大所多', 1);
INSERT INTO `answer` VALUES (2, 2, 1, '2021-01-03 18:17:02', 45678888, '暗示', 1);
INSERT INTO `answer` VALUES (3, 4, 1, '2021-01-03 17:23:11', 776688, '123123', NULL);
INSERT INTO `answer` VALUES (12, 3, 1, '2021-01-03 18:42:25', 45678888, '阿达 ', NULL);
INSERT INTO `answer` VALUES (19, 15, 2, '2021-07-12 23:36:58', 776688, 'haha', NULL);

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson`  (
  `lesson_id` int(8) NOT NULL,
  `lesson_name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `school_id` int(8) NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`lesson_id`) USING BTREE,
  INDEX `school_id`(`school_id`) USING BTREE,
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, 'JavaWeb', 1024, 'web开发教学');
INSERT INTO `lesson` VALUES (2, '高等数学', 1653, '必修课之一');
INSERT INTO `lesson` VALUES (3, '英语一', 2555, '英语课');
INSERT INTO `lesson` VALUES (4, '图像处理', 1024, '图像处理，计算机视觉');
INSERT INTO `lesson` VALUES (6, '体育', 2333, '体育课');
INSERT INTO `lesson` VALUES (7, '数据库原理A', 1024, '数据库');

-- ----------------------------
-- Table structure for lessonfiles
-- ----------------------------
DROP TABLE IF EXISTS `lessonfiles`;
CREATE TABLE `lessonfiles`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lesson_id` int(8) NULL DEFAULT NULL,
  `tea_id` int(10) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `teaName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tea_id`(`tea_id`) USING BTREE,
  INDEX `lesson_id`(`lesson_id`) USING BTREE,
  CONSTRAINT `lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tea_id` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lessonfiles
-- ----------------------------
INSERT INTO `lessonfiles` VALUES (19, 2, 776688, '123.txt', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\123.txt', '唐浩天', '2021-07-12 18:27:39', '3B');
INSERT INTO `lessonfiles` VALUES (20, 2, 776688, 'abc.txt', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\abc.txt', '唐浩天', '2021-07-12 18:27:44', '21B');
INSERT INTO `lessonfiles` VALUES (21, 2, 776688, '上海理工大学 翁祯敏 夏令营承诺书.pdf', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\上海理工大学 翁祯敏 夏令营承诺书.pdf', '唐浩天', '2021-07-12 18:27:49', '140.01KB');
INSERT INTO `lessonfiles` VALUES (22, 2, 776688, '夏令营承诺书(1).pdf', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\夏令营承诺书(1).pdf', '唐浩天', '2021-07-12 18:27:53', '63.36KB');
INSERT INTO `lessonfiles` VALUES (23, 2, 776688, '信息学院夏令营面试考生须知.pdf', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\信息学院夏令营面试考生须知.pdf', '唐浩天', '2021-07-12 18:28:00', '157.56KB');
INSERT INTO `lessonfiles` VALUES (24, 2, 776688, '英文自我介绍.docx', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\英文自我介绍.docx', '唐浩天', '2021-07-12 18:28:10', '12.60KB');
INSERT INTO `lessonfiles` VALUES (25, 2, 776688, '自我介绍.pptx', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\自我介绍.pptx', '唐浩天', '2021-07-12 18:28:20', '1.86MB');
INSERT INTO `lessonfiles` VALUES (26, 3, 776688, '.gitignore', 'F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\.gitignore', '唐浩天', '2021-07-12 19:46:16', '116B');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `qid` int(8) NOT NULL AUTO_INCREMENT,
  `lesson_id` int(8) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tea_id` int(10) NULL DEFAULT NULL,
  `is_answered` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`qid`) USING BTREE,
  INDEX `username`(`username`, `nickname`) USING BTREE,
  INDEX `tea_id`(`tea_id`) USING BTREE,
  INDEX `lesson_id`(`lesson_id`) USING BTREE,
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 1, '这题谁会啊', '1+1=?', '2021-01-03 11:37:17', 'white55', '卢本伟本伟', 776688, 1);
INSERT INTO `question` VALUES (2, 1, '老师这怎么做?', '2*3=?', '2021-01-03 18:18:50', 'vans233', '不想上课', 45678888, 1);
INSERT INTO `question` VALUES (3, 1, '这里哪里错了', 'cout << endl;', '2021-01-03 18:42:25', 'vans233', '不想上课', 45678888, 1);
INSERT INTO `question` VALUES (4, 1, '好难', 'abc', '2021-01-03 17:23:11', 'white55', '卢本伟本伟', 776688, 1);
INSERT INTO `question` VALUES (7, 2, 'sss', 'aaa', '2021-01-03 10:22:21', 'white55', '卢本伟本伟', 45678888, NULL);
INSERT INTO `question` VALUES (12, 1, '撒大声地', '啊实打实大撒', '2021-01-04 15:33:26', 'white55', '卢本伟本伟', 45678888, NULL);
INSERT INTO `question` VALUES (14, 1, 'dsadas', 'dasdasd', '2021-01-05 18:54:55', 'white55', '卢本伟本伟', 45678888, NULL);
INSERT INTO `question` VALUES (15, 2, 'sdasd', '123123', '2021-07-12 23:36:58', 'white55', '卢本伟本伟', 776688, 1);
INSERT INTO `question` VALUES (16, 2, '??!!!', 'aaaa', '2021-07-12 22:36:14', 'Tamiflu233', 'Tamiflu233', 776688, NULL);

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `school_id` int(8) NOT NULL COMMENT '学院号',
  `school_name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院名称',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院简介',
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (123, '皇家帝国学院', 'dasdassdaddd');
INSERT INTO `school` VALUES (1024, '光电信息与计算机工程学院', '俗称光电学院');
INSERT INTO `school` VALUES (1038, '机械工程学院', '俗称机械学院呵呵');
INSERT INTO `school` VALUES (1088, '沪江学院', '沪江学院');
INSERT INTO `school` VALUES (1653, '理学院', '上海理工理学院');
INSERT INTO `school` VALUES (2333, '基础学院', '基院');
INSERT INTO `school` VALUES (2555, '外语学院', '外语');
INSERT INTO `school` VALUES (5877, '中美学院', '哈哈');
INSERT INTO `school` VALUES (13333, '沪江学院', '呵呵');
INSERT INTO `school` VALUES (123123, '灌灌灌灌', '撒大声地');
INSERT INTO `school` VALUES (1231231, 'asdasd', 'asdasd');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` int(10) NOT NULL,
  `sex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `school_id` int(8) NULL DEFAULT NULL,
  `uid` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `account` (`uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1812450423, '男', '卢本伟', 1024, 9);
INSERT INTO `student` VALUES (1812480426, '男', '翁某', 1024, 14);
INSERT INTO `student` VALUES (1813450426, '女', '扬天', 1024, 13);
INSERT INTO `student` VALUES (1815670623, '男', '郭德纲', 1038, 10);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tea_id` int(10) NOT NULL,
  `sex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `profession` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uid` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`tea_id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `account` (`uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (776688, '男', '唐浩天', '教授', '计算机学院教授', 4);
INSERT INTO `teacher` VALUES (8868507, '女', '王芳', '讲师', 'aaa', 6);
INSERT INTO `teacher` VALUES (12458866, '男', '马云', '教授', '大佬', 5);
INSERT INTO `teacher` VALUES (18124507, '男', '王德刚', '讲师', 'JavaWeb讲师', 12);
INSERT INTO `teacher` VALUES (45678888, '女', '江城', '副教授', '外语学院副教授', 11);
INSERT INTO `teacher` VALUES (66668888, '女', '张艳', '讲师', '数学讲师', 8);

-- ----------------------------
-- Table structure for teaches
-- ----------------------------
DROP TABLE IF EXISTS `teaches`;
CREATE TABLE `teaches`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_id` int(10) NULL DEFAULT NULL,
  `lesson_id` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_teaches_info`(`tea_id`, `lesson_id`) USING BTREE,
  INDEX `lesson_id`(`lesson_id`) USING BTREE,
  CONSTRAINT `teaches_ibfk_1` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teaches_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teaches
-- ----------------------------
INSERT INTO `teaches` VALUES (29, 776688, 2);
INSERT INTO `teaches` VALUES (4, 776688, 3);
INSERT INTO `teaches` VALUES (28, 776688, 7);
INSERT INTO `teaches` VALUES (23, 8868507, 6);
INSERT INTO `teaches` VALUES (20, 8868507, 7);
INSERT INTO `teaches` VALUES (24, 12458866, 7);
INSERT INTO `teaches` VALUES (34, 45678888, 2);

SET FOREIGN_KEY_CHECKS = 1;
