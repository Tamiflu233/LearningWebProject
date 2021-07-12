package com.tamiflu.controller;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.FileInfo;
import com.tamiflu.service.FileService;
import com.tamiflu.vo.DelFileParams;
import com.tamiflu.vo.FileParamsVO;
import com.tamiflu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public Result uploadFile(MultipartFile[] multipartFiles, HttpServletRequest request) {
        List<FileInfo> infos = new ArrayList<>();
        FileInfo info = new FileInfo();
        String rootPath = null;
        try {
            rootPath = new ClassPathResource("").getFile().getAbsolutePath() + "\\file";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(rootPath);
        File fileDir = new File(rootPath);
        Result result = new Result();
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            System.out.println(multipartFiles.length);
            if (multipartFiles != null && multipartFiles.length > 0) {
                for (int i = 0; i < multipartFiles.length; i++) {
                    try {
                        //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
                        String storagePath = rootPath + "\\" + multipartFiles[i].getOriginalFilename();
                        File curr = new File(storagePath);
                        if (curr.exists()) {
                            result.setCode(500);
                            result.setMsg("文件已存在！请勿重复提交!");
                            return result;
                        }
                        System.out.println("上传的文件：" + multipartFiles[i].getName() + "," + multipartFiles[i].getContentType() + "," + multipartFiles[i].getOriginalFilename()
                                + "，保存的路径为：" + storagePath);
                        System.out.println(multipartFiles[i].getSize());
                        info.setUrl(storagePath);

                        Long sizeB = multipartFiles[i].getSize();
                        String size = sizeB + "B";
                        if (sizeB >= 1024 * 1024 * 1024) {
                            size = String.format("%.2f", sizeB / 1024.0 / 1024.0 / 1024.0) + "GB";
                        } else if (sizeB >= 1024 * 1024) {
                            size = String.format("%.2f", sizeB / 1024.0 / 1024.0) + "MB";
                        } else if (sizeB >= 1024) {
                            size = String.format("%.2f", sizeB / 1024.0) + "KB";
                        }
                        info.setSize(size);
                        info.setName(multipartFiles[i].getOriginalFilename());
                        infos.add(info);

                        // 3种方法： 第1种
//                        Streams.copy(multipartFiles[i].getInputStream(), new FileOutputStream(storagePath), true);
                        // 第2种
//                        Path path = Paths.get(storagePath);
//                        Files.write(path,multipartFiles[i].getBytes());
                        // 第3种
                        multipartFiles[i].transferTo(new File(storagePath));
                        result.setMsg("文件上传成功!");
                        result.setData(infos);
                    } catch (IOException e) {
                        result.setCode(500);
                        result.setMsg("文件上传失败，请稍后重试!");
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("文件上传失败，请稍后重试!");
            e.printStackTrace();
        }
        //前端可以通过状态码，判断文件是否上传成功
        return result;
    }

    @PostMapping("/saveFileInfo")
    public Result saveFileInfo(@RequestBody FileInfo file) {
        System.out.println(file);
        Result result = new Result();
        try {
            fileService.insertFile(file);
            result.setMsg("文件信息保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("文件信息保存失败!");
        }
        return result;
    }

    @GetMapping("/getFiles")
    public Result getFiles(Integer pageNow, Integer pageSize, String lessonId, String teaId) {
        Result result = new Result();
        try {
            PageInfo pageInfo = fileService.selectAllFiles(pageNow,
                    pageSize,
                    lessonId,
                    teaId);
            result.setMsg("查询教室课程文件成功！");
            result.setData(pageInfo);
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("查询文件失败!");
            e.printStackTrace();
        }

        return result;
    }

    @PostMapping("/deleteFile")
    public Result deleteFile(@RequestBody DelFileParams delFileParams) {
        Result result = new Result();
        try {
            fileService.deleteFile(delFileParams.getId());
            File file = new File(delFileParams.getPath());
            file.delete();
            result.setMsg("删除文件成功!");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("删除文件失败!");
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/download")
    public static void download(@RequestBody FileParamsVO fileParamsVO, HttpServletResponse res) throws IOException {

        //getParentPath是获取文件保存的目录（可参考第一个单文件上传模块）
        File file = new File(fileParamsVO.getFilename());
        if (!file.exists()) {
            return;
        }
        BufferedInputStream bis = null;
        try {
            res.setContentType("application/octet-stream");
            //response.setContentType("application/force-download");
            res.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));// 设置文件名
            byte[] buffer = new byte[1024];
            bis = new BufferedInputStream(new FileInputStream(file));
            OutputStream os = res.getOutputStream();
            int i = -1;
            while ((i = bis.read(buffer)) != -1) {
                os.write(buffer, 0, i);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (Exception e) {
            }
        }

    }


}

