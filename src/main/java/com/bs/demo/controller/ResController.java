package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.service.IResService;
import com.bs.demo.entity.Res;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import com.bs.demo.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gf
 * @since 2022-05-25
 */
@CrossOrigin
@RestController
@RequestMapping("/res")
@Slf4j
public class ResController {

    @Autowired
    public IResService iResService;

    // ResponseEntity<byte[]>封装下载对象
    @RequestMapping(value = "/file_download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, @RequestParam String resId) throws Exception {

        Res resInfo = iResService.getOne(new QueryWrapper<Res>().eq("res_id", resId));
        iResService.update(new UpdateWrapper<Res>()
                .eq("res_id",resId)
                .set("res_download",resInfo.getResDownload()+1));
        if (resInfo != null) {
            // 下载文件路径
            String path = resInfo.getResAddress();

            // 创建文件对象
            File file = new File(path);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();

            // 下载显示的文件名, 解决中文乱码的问题
            String downloadFileName = new String((path.split("/")[path.split("/").length - 1]).getBytes("UTF-8"), "ISO-8859-1");

            // 通知浏览器以下载方式(attachment)打开文件
            headers.setContentDispositionFormData("attachment", downloadFileName);

            // 定义以二进制流的方式下载返回文件数据
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // 添加下载次数
            iResService.update(new UpdateWrapper<Res>().eq("res_id", resInfo.getResId())
                    .set("res_download", resInfo.getResDownload() + 1));

            // 使用mvc的ResponseEntity对象封装返回下载数据
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } else {
            return null;
        }
    }


    /**
     * 获取资源列表
     *
     * @param current
     * @param searchText
     * @return
     */
    @GetMapping("/getRes")
    public Result getRes(@RequestParam Integer current, String searchText) {
        QueryWrapper<Res> queryWrapper = new QueryWrapper<>();
        Page<Res> page = new Page<>(current, 8);
        queryWrapper.like("res_title", searchText);

        return Result.success().data(iResService.page(page, queryWrapper));
    }


    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @PostMapping("/upload_file")
    public Result uploadFile(MultipartFile file, @RequestParam String userId) throws IOException, SQLException {

        InputStream inputStream = file.getInputStream();

        OutputStream os = null;
        try {
            String path = "src/main/res";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024 * 10];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }

            String filePath = tempFile.getPath() + File.separator + file.getOriginalFilename();
            os = new FileOutputStream(filePath);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

            Res learnRes = new Res();
            learnRes.setResAddress(filePath);
            learnRes.setUserId(userId);
            learnRes.setResTitle(file.getOriginalFilename());
            learnRes.setResDownload(0);

            iResService.save(learnRes);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error().message("上传失败!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Result.success().message("上传成功!");
    }


    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "列表")
    @PreAuthorize("hasAnyAuthority('res:list')")
    public Result learnResList(@RequestBody SearchOption searchOption) {
        List<Res> list = iResService.list();

        return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(list);
    }



    @OperationLog("删除")
    @PostMapping("/del")
    @ResponseBody
    @ApiOperation("删除")
    @PreAuthorize("hasAnyAuthority('res:del')")
    @Transactional
    public Result deleteLearnRes(@RequestBody JSONObject jsonObject) {
        String resId = jsonObject.getStr("resId");

        iResService.remove(new QueryWrapper<Res>()
                .eq("res_id",resId));
        return Result.success().data("删除成功！");
    }
}
