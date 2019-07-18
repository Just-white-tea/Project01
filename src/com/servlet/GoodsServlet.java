package com.servlet;

import com.Dao.GoodsDao;
import com.good.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class GoodsServlet extends HttpServlet {
    //上传文件
    public Goods uploadFile(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        Goods goods=new Goods();
        //判断当前表单是否为上传表单
        boolean isMultipart=ServletFileUpload.isMultipartContent(req);
        if (isMultipart){
            //创建ServletFileUpload对象
            FileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);

            // 获得上传表单里的所有控件对象
            List<FileItem> fileItemList = upload.parseRequest(req);
            //控件对象不为空时
            if (fileItemList!=null&&fileItemList.size()>0){
                for (FileItem fileItem:fileItemList){
                    // 判断控件对象是普通表单控件还是上传表单控件
                    if (fileItem.isFormField()){
                        if ("goodName".equals(fileItem.getFieldName())){
                            goods.setGoodName(fileItem.getString());
                        }else if ("goodPrice".equals(fileItem.getFieldName())){
                            goods.setGoodPrice(Integer.parseInt(fileItem.getString()));
                        }else if ("goodStock".equals(fileItem.getFieldName())){
                            goods.setGoodStock(Integer.parseInt(fileItem.getString()));
                        }else if ("goodDescription".equals(fileItem.getFieldName())){
                            goods.setGoodDescription(fileItem.getString());
                        }else if ("goodPic".equals(fileItem.getFieldName())){
                            goods.setGoodPic(fileItem.getString("utf-8"));
                        }else if ("goodId".equals(fileItem.getFieldName())){
                            goods.setGoodId(Integer.parseInt(fileItem.getString()));
                        }
                    }else {
                        //获得文件名
                        String fileName = fileItem.getName();
                        if (fileName==null|| "".equals(fileName)) {
                        }else {
                            // 获得web工程在tomcat下的绝对路径
                            String parentPath = req.getServletContext().getRealPath("/upload");
                            // 判断目录是否存在，如果不存在就需要创建
                            File parentFile = new File(parentPath);
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();//创建目录
                            }
                            // 获得上传文件的文件对象
                            File newFile = new File(parentFile, fileName);
                            // 输入流用于读文件
                            InputStream is = fileItem.getInputStream();
                            // 输出流用于写文件
                            OutputStream os = new FileOutputStream(newFile);
                            // 写文件操作
                            IOUtils.copy(is, os);
                            // 关闭流：由内往外关
                            os.close();
                            is.close();

                        }
                        // 设置上传文件的文件名保存在对象属性中
                        goods.setGoodPic(fileName);
                    }
                }
            }
        }else {
            //普通表单
        }
        return goods;
    }

    //下载文件
    public void downLoadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置编码（请求和响应）
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获得下载的文件名
        String fileName=req.getParameter("fileName");
        //获得文件父路径
        String parentPath=req.getServletContext().getRealPath("/upload");
        //获得下载文件的对象
        File downFile=new File(parentPath,fileName);
        if ((downFile.exists())){
            //文件名带中文时，更改编码
            String newFileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");
            resp.setHeader("content-disposition","attachment;filename="+newFileName);

            // 获得输入流和输出流
            // 读文件
            InputStream is=new FileInputStream(downFile);
            // 获得响应输出：写文件
            OutputStream os = resp.getOutputStream();
            // 写文件
            IOUtils.copy(is, os);
            // 关闭资源
            os.close();
            is.close();
        }else {
            //文件不存在则错误提示
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>alert('文件不存在');history.back();</script>");
        }
    }

}
